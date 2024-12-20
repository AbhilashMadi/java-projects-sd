/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.entities.Board;
import com.example.demo.entities.BoardMember;
import com.example.demo.entities.BoardVisibility;
import com.example.demo.entities.Card;
import com.example.demo.entities.Column;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;
import com.example.demo.models.CardUpdateDTO;
import com.example.demo.repositories.BoardMemberRepository;
import com.example.demo.repositories.BoardRepository;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.ColumnRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.InmemoryBoardMemberRepository;
import com.example.demo.repositories.InmemoryBoardRepository;
import com.example.demo.repositories.InmemoryCardRepository;
import com.example.demo.repositories.InmemoryColumnRepository;
import com.example.demo.repositories.InmemoryCommentRepository;
import com.example.demo.repositories.InmemoryUserRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.BoardService;
import com.example.demo.services.CardService;
import com.example.demo.services.ColumnService;
import com.example.demo.services.UserService;


public class App {

     // Initialize repositories
    private final UserRepository userRepository = new InmemoryUserRepository();
    private final BoardRepository boardRepository = new InmemoryBoardRepository();
    private final BoardMemberRepository boardMemberRepository = new InmemoryBoardMemberRepository();
    private final ColumnRepository columnRepository = new InmemoryColumnRepository();
    private final CardRepository cardRepository = new InmemoryCardRepository();
    private final CommentRepository commentRepository = new InmemoryCommentRepository();
     
    // Initialize services
    private final UserService userService = new UserService(userRepository);
    private final BoardService boardService = new BoardService(boardRepository,userRepository,boardMemberRepository,columnRepository);
    private final ColumnService columnService = new ColumnService(columnRepository,cardRepository);
    private final CardService cardService = new CardService(cardRepository, userRepository, commentRepository);

    public static void main(String[] args) {

        // Test your code by ading commands in sample_input/sample_input_one.txt
        // Run run.sh script using "bash run.sh" in your terminal.
        if (args.length == 1){
            List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            String inputFile = commandLineArgs.get(0).split("=")[1];
            try {
                List<String> file_commands = Files.readAllLines(Paths.get(inputFile));
                // Execute the commands
                new App().run(file_commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }        

        // OR
        // Test your code by ading commands in this list
        List<String> inplace_commands = new LinkedList<>(){
            {
            }
        };

        new App().run(inplace_commands);
 
    }

    public void run(List<String> commands){

        Iterator<String> it = commands.iterator();
        while(it.hasNext()){
            String line = it.next();
                if(line == null){
                    break;
                }
                List<String> tokens = Arrays.asList(line.split(","));

                try {
                    //Execute Services
                    switch(tokens.get(0)){
                        case "CREATE_USER":
                            CREATE_USER(tokens);
                        break;
                        case "CREATE_BOARD":
                            CREATE_BOARD(tokens);
                        break;
                        case "ADD_BOARD_MEMBER":
                            ADD_BOARD_MEMBER(tokens);
                        break;
                        case "REMOVE_BOARD_MEMBER":
                            REMOVE_BOARD_MEMBER(tokens);
                        break;
                        case "CREATE_COLUMN":
                            CREATE_COLUMN(tokens);
                        break;
                        case "SHOW_BOARD":
                            SHOW_BOARD(tokens);
                        break;
                        case "CREATE_CARD":
                            CREATE_CARD(tokens);
                        break;
                        case "MOVE_CARD":
                            MOVE_CARD(tokens);
                        break;
                        case "UPDATE_CARD":
                            UPDATE_CARD(tokens);
                        break;
                        case "ADD_CARD_MEMBER":
                            ADD_CARD_MEMBER(tokens);
                        break;
                        case "REMOVE_CARD_MEMBER":
                            REMOVE_CARD_MEMBER(tokens);
                        break;
                         case "ADD_CARD_COMMENT":
                            ADD_CARD_COMMENT(tokens);
                        break;
                        case "ADD_CARD_LABEL":
                            ADD_CARD_LABEL(tokens);
                        break;
                        case "ADD_CARD_CHECKLIST":
                            ADD_CARD_CHECKLIST(tokens);
                        break;
                        case "ADD_CARD_DUE_DATE":
                            ADD_CARD_DUE_DATE(tokens);
                        break;
                        case "ARCHIVE_CARD":
                            ARCHIVE_CARD(tokens);
                        break;
                        default:
                            throw new RuntimeException("INVALID_COMMAND");
                }
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
    }

	// CREATE_USER
    private void CREATE_USER(List<String> tokens){
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: CREATE_USER,<email>,<password>");
            return;
        }
        String email = tokens.get(1);
        String password = tokens.get(2);
        User user = userService.createUser(email, password);
        System.out.println("USER_CREATED " + user.getId());
    }

        // CREATE_BOARD
    private void CREATE_BOARD(List<String> tokens) {
        if (tokens.size() != 4) {
            System.out.println("Invalid command format. Use: CREATE_BOARD,<user_id>,<name>,<visibility>");
            return;
        }
        Long ownerId =  Long.valueOf(tokens.get(1));
        String boardName = tokens.get(2);
        BoardVisibility boardVisibility = BoardVisibility.valueOf(tokens.get(3));
        Board board = boardService.createBoard(ownerId, boardName, boardVisibility);
        System.out.println("BOARD_CREATED " + board.getId());
    }

    // ADD_BOARD_MEMBER
    private void ADD_BOARD_MEMBER(List<String> tokens){
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: ADD_BOARD_MEMBER,<board_id>,<user_id>");
            return;
        }
        Long boardId = Long.valueOf(tokens.get(1));
        Long userId = Long.valueOf(tokens.get(2));
        BoardMember boardMember = boardService.addMemberToBoard(boardId, userId);
        System.out.println("BOARD_MEMBER_ADDED " + boardMember.getBoardId() +" "+ boardMember.getUserId());
    }

    // REMOVE_BOARD_MEMBER
    private void REMOVE_BOARD_MEMBER(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: REMOVE_BOARD_MEMBER,<board_id>,<user_id>");
            return;
        }
        Long boardId = Long.valueOf(tokens.get(1));
        Long userId = Long.valueOf(tokens.get(2));
        boardService.removeMemberFromBoard(boardId, userId);
        System.out.println("BOARD_MEMBER_REMOVED " + boardId + " " + userId);
    }

    // CREATE_COLUMN
    private void CREATE_COLUMN(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: CREATE_COLUMN,<board_id>,<name>");
            return;
        }
        Long boardId = Long.valueOf(tokens.get(1));
        String name = tokens.get(2);
        Column column = boardService.addColumn(boardId, name);
        System.out.println("COLUMN_CREATED " + column.getId());
	}

    // CREATE_CARD
	private void CREATE_CARD(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: CREATE_CARD,<column_id>,<title>");
            return;
        }
        Long columnId = Long.valueOf(tokens.get(1));
        String title = tokens.get(2);
        Card card = columnService.addCard(columnId, title);
        System.out.println("CARD_CREATED " + card.getId());
	}

    // SHOW_BOARD
	private void SHOW_BOARD(List<String> tokens) {
        if (tokens.size() != 2) {
            System.out.println("Invalid command format. Use: SHOW_BOARD,<board_id>");
            return;
        }
        Long boardId = Long.valueOf(tokens.get(1));
        boardService.showBoard(boardId);
	}

    // MOVE_CARD
	private void MOVE_CARD(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: MOVE_CARD,<card_id>,<target_column_id>");
            return;
        }
        Long cardId = Long.valueOf(tokens.get(1));
        Long targetColumnId = Long.valueOf(tokens.get(2));
        columnService.moveCard(cardId, targetColumnId);
        System.out.println("CARD_MOVED " + cardId +" " +targetColumnId);
	}


    // UPDATE_CARD
	private void UPDATE_CARD(List<String> tokens) {
        if (tokens.size() != 4) {
            System.out.println("Invalid command format. Use: UPDATE_CARD,<card_id>,<new_title>,<new_description>");
            return;
        }
        Long cardId = Long.valueOf(tokens.get(1));
        String newTitle = tokens.get(2);
        String newDescription = tokens.get(3);
        CardUpdateDTO cardUpdateDTO = new CardUpdateDTO();
        cardUpdateDTO.setNewTitle(newTitle);
        cardUpdateDTO.setNewDescription(newDescription);
        Card updatedCard = cardService.updateCard(cardId, cardUpdateDTO);
        System.out.println("CARD_UPDATED Card[id=" + updatedCard.getId() + ", title=" + updatedCard.getTitle() +", description=" + updatedCard.getDescription()+"]");
	}

    // ADD_CARD_MEMBER
	private void ADD_CARD_MEMBER(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: ADD_CARD_MEMBER,<card_id>,<user_id>");
            return;
        }
        Long cardId = Long.valueOf(tokens.get(1));
        Long userId = Long.valueOf(tokens.get(2));
        Card updatedCard = cardService.addMemberToCard(cardId, userId);
        System.out.println("CARD_MEMBER_ADDED " + updatedCard.getMembers());
	}

    // REMOVE_CARD_MEMBER
	private void REMOVE_CARD_MEMBER(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("Invalid command format. Use: REMOVE_CARD_MEMBER,<card_id>,<user_id>");
            return;
        }
        Long cardId = Long.valueOf(tokens.get(1));
        Long userId = Long.valueOf(tokens.get(2));
        cardService.removeMemberFromCard(cardId, userId);
        System.out.println("CARD_MEMBER_REMOVED " + cardId + " " + userId);
	}

    // ADD_CARD_COMMENT
	private void ADD_CARD_COMMENT(List<String> tokens) {
        if (tokens.size() != 4) {
            System.out.println("Invalid command format. Use: ADD_CARD_COMMENT,<user_id>,<card_id>,<text>");
            return;
        }
        Long userId = Long.valueOf(tokens.get(1));
        Long cardId = Long.valueOf(tokens.get(2));
        String text = tokens.get(3);
        Comment comment = cardService.addCommentToCard(userId, cardId,text);
        System.out.println("CARD_COMMENT_ADDED " + comment.getId());
	}

    // Below are bonus functionalities and will not be assessed:- 

    // ADD_CARD_LABEL
	private void ADD_CARD_LABEL(List<String> tokens) {
	}

    // ADD_CARD_CHECKLIST
	private void ADD_CARD_CHECKLIST(List<String> tokens) {
	}

    // ADD_CARD_DUE_DATE
	private void ADD_CARD_DUE_DATE(List<String> tokens) {
	}

    // ARCHIVE_CARD
	private void ARCHIVE_CARD(List<String> tokens) {
	}
}
