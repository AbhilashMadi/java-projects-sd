package com.example.demo.services;

import com.example.demo.entities.Board;
import com.example.demo.entities.BoardMember;
import com.example.demo.entities.BoardVisibility;
import com.example.demo.entities.Card;
import com.example.demo.entities.Column;
import com.example.demo.entities.User;
import com.example.demo.repositories.BoardMemberRepository;
import com.example.demo.repositories.BoardRepository;
import com.example.demo.repositories.ColumnRepository;
import com.example.demo.repositories.UserRepository;

public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardMemberRepository boardMemberRepository;
    private final ColumnRepository columnRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository,
            BoardMemberRepository boardMemberRepository, ColumnRepository columnRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardMemberRepository = boardMemberRepository;
        this.columnRepository = columnRepository;
    }

    /**
     * Creates a new board with the specified name, owner (user), and visibility.
     *
     * @param userId The ID of the user who will be the owner of the board.
     * @param boardName The name of the board.
     * @param boardVisibility The visibility of the board.
     * @return The newly created Board object.
     * @throws RuntimeException if the user with the given ID does not exist.
     */
    public Board createBoard(Long userId, String boardName, BoardVisibility boardVisibility) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with an id " + userId + " does not exist"));

        Board newBoard = new Board(boardName, user, boardVisibility);
        Board board = boardRepository.save(newBoard);

        return board;
    }
    

    /**
     * Adds a user as a member to a board.
     *
     * @param boardId The ID of the board.
     * @param userId The ID of the user to be added as a member.
     * @return The newly created BoardMember object.
     * @throws RuntimeException if the board or user with the given ID does not exist, or if the
     *         user is already a member.
     */
    public BoardMember addMemberToBoard(Long boardId, Long userId) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board with an id " + boardId + " does not exist"));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with an id " + userId + " does not exist"));

        if (boardMemberRepository.exists(boardId, userId)) {
            throw new RuntimeException("User with an id " + user.getId()
                    + " is already a member of Board with an id " + board.getId());
        }

        BoardMember newBoardMember = new BoardMember(board, user);
        BoardMember boardMember = boardMemberRepository.save(newBoardMember);

        return boardMember;
    }

    /**
     * Removes a user from a board.
     *
     * @param boardId The ID of the board.
     * @param userId  The ID of the user to be removed.
     * @return True if the removal was successful, or false if the user was not a member of the board.
     * @throws RuntimeException if the board or user with the given ID does not exist.
     */
    public Boolean removeMemberFromBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board with an id " + boardId + " does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        if (!boardMemberRepository.exists(boardId, userId)) {
            throw new RuntimeException("User with an id "+ user.getId() + " is not a member of Board with an id " + board.getId());
        }

        boardMemberRepository.deleteById(boardId, userId);

        if (!boardMemberRepository.exists(boardId, userId)) {
            return true;
        }

        return false; 
    }

    /**
     * Adds a new column to the board with the specified name.
     *
     * @param boardId    The ID of the board where the column will be added.
     * @param columnName The name of the new column.
     * @return The newly created Column object.
     * @throws RuntimeException if the board with the given ID does not exist.
     */
    public Column addColumn(Long boardId, String columnName) {
        
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board with an id " + boardId + " does not exist"));

        Column newColumn = new Column(columnName);

        if (columnRepository.findById(newColumn.getId()).isPresent()) {
            throw new RuntimeException("Column with an id " + newColumn.getId() + " already exist");
        } else {
            newColumn = columnRepository.save(newColumn);
        }

        newColumn.setBoard(board);

        return newColumn;
    }


    /**
     * Display the contents of a board, including its columns and cards.
     *
     * @param boardId The ID of the board to be displayed.
     * @throws RuntimeException if the board with the given ID does not exist.
     */
    public void showBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new RuntimeException("Board with an id " + boardId + " does not exist"));

        System.out.println("Board Name: " + board.getName());
        System.out.println("Visibility: " + board.getVisibility());

        board.getColumns().forEach((Column col) -> {
            System.out.println("Column: " + col.getName());

            col.getCards().forEach((Card card) -> {
                System.out.println("  Card: " + card.getTitle());
            });
        });
    }
}
