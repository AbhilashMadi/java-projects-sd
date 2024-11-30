
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

public class SnakeTest {

    @Test
    public void testInitialState() {
        Snake snake = new Snake(3, new Position(5, 5), Direction.RIGHT);
        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(5, 5));
        expectedBody.add(new Position(4, 5));
        expectedBody.add(new Position(3, 5));

        assertEquals(expectedBody, snake.getBody());
        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    public void testModifyingDirection() {
        Snake snake = new Snake(3, new Position(5, 5), Direction.RIGHT);
        snake.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.getDirection());
    }

    @Test
    public void testMove() {
        Snake snake = new Snake(3, new Position(5, 5), Direction.RIGHT);
        snake.move();
        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(6, 5));
        expectedBody.add(new Position(5, 5));
        expectedBody.add(new Position(4, 5));

        assertEquals(expectedBody, snake.getBody());
    }

    @Test
    public void testGrow() {
        Snake snake = new Snake(3, new Position(5, 5), Direction.RIGHT);
        snake.grow();
        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(6, 5));
        expectedBody.add(new Position(5, 5));
        expectedBody.add(new Position(4, 5));
        expectedBody.add(new Position(3, 5));

        assertEquals(expectedBody, snake.getBody());
    }

    @Test
    public void testPreventTurningBackOnItself() {
        Snake snake = new Snake(3, new Position(5, 5), Direction.DOWN);
        snake.setDirection(Direction.UP);
        assertEquals(Direction.DOWN, snake.getDirection());
    }

    @Test
    public void testMovingInDifferentDirections() {
        Snake snake = new Snake(4, new Position(2, 2), Direction.UP);
        snake.move();
        snake.setDirection(Direction.LEFT);
        snake.move();
        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(1, 3));
        expectedBody.add(new Position(2, 3));
        expectedBody.add(new Position(2, 2));
        expectedBody.add(new Position(2, 1));

        assertEquals(expectedBody, snake.getBody());
        assertEquals(Direction.LEFT, snake.getDirection());
    }

    @Test
    public void testGrowAndChangeDirection() {
        Snake snake = new Snake(4, new Position(2, 2), Direction.UP);
        snake.setDirection(Direction.LEFT);
        snake.move();
        snake.grow();

        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(0, 2));
        expectedBody.add(new Position(1, 2));
        expectedBody.add(new Position(2, 2));
        expectedBody.add(new Position(2, 1));
        expectedBody.add(new Position(2, 0));


        assertEquals(expectedBody, snake.getBody());
        assertEquals(Direction.LEFT, snake.getDirection());
    }

    @Test
    public void testGrowMultipleTimes() {
        Snake snake = new Snake(4, new Position(2, 2), Direction.UP);
        snake.grow();
        snake.grow();
        snake.grow();

        LinkedList<Position> expectedBody = new LinkedList<>();

        expectedBody.add(new Position(2, 5));
        expectedBody.add(new Position(2, 4));
        expectedBody.add(new Position(2, 3));
        expectedBody.add(new Position(2, 2));
        expectedBody.add(new Position(2, 1));
        expectedBody.add(new Position(2, 0));
        expectedBody.add(new Position(2, -1));

        assertEquals(expectedBody, snake.getBody());
    }

    @Test
    public void testSnakeLengthReduction() {
        Snake snake = new Snake(5, new Position(3, 3), Direction.RIGHT);

        LinkedList<Position> expectedBody = new LinkedList<>();
        expectedBody.add(new Position(3, 3));
        expectedBody.add(new Position(2, 3));
        expectedBody.add(new Position(1, 3));
        expectedBody.add(new Position(0, 3));
        expectedBody.add(new Position(-1, 3));

        assertEquals(expectedBody, snake.getBody());
        assertEquals(Direction.RIGHT, snake.getDirection());
    }
}

