import java.util.LinkedList;

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}

class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }
}

public class Snake {

    private Direction direction;
    private final LinkedList<Position> body;

    public Snake(int size, Position position, Direction direction) {
        body = new LinkedList<>();
        this.direction = direction;

        for (int i = 0; i < size; i++) {
            int x = position.x;
            int y = position.y;

            switch (direction) {
                case UP:
                    y -= i;
                    break;
                case DOWN:
                    y += i;
                    break;
                case LEFT:
                    x += i;
                    break;
                case RIGHT:
                    x -= i;
                    break;
                default:
                    break;
            }

            body.add(new Position(x, y));
        }
    }

    public void setDirection(Direction newDirection) {
        if (newDirection == Direction.UP && direction == Direction.DOWN)
            return;
        if (newDirection == Direction.DOWN && direction == Direction.UP)
            return;
        if (newDirection == Direction.LEFT && direction == Direction.RIGHT)
            return;
        if (newDirection == Direction.RIGHT && direction == Direction.LEFT)
            return;

        this.direction = newDirection;
    }

    public void move() {
        getHeadMove();
        body.removeLast();
    }

    private void getHeadMove() {
        Position head = body.getFirst();

        switch (direction) {
            case UP:
                head = new Position(head.x, head.y + 1);
                break;
            case DOWN:
                head = new Position(head.x, head.y - 1);
                break;
            case LEFT:
                head = new Position(head.x - 1, head.y);
                break;
            case RIGHT:
                head = new Position(head.x + 1, head.y);
                break;
            default:
                break;
        }

        body.addFirst(head);
    }

    public void grow() {
        getHeadMove();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public LinkedList<Position> getBody() {
        return this.body;
    }
}