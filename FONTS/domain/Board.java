package domain;

import domain.exceptions.*;
import utils.Ball_color;
import utils.Pair;

public class Board {
    private Ball_color[][] board; // The matrix representing the player codes.
    private Pair<Integer, Integer>[] feedback; // An array representing the feedback for each turn.

    // Constructors
    /**
     * Constructor to create a new board instance with empty values.
     */
    public Board() {
        this.board = new Ball_color[0][0];
        this.feedback = new Pair[0];
    }

    /**
     * Constructor for creating a board instance with given dimensions.
     *
     * @param numBalls the number of balls in each code
     * @param numTurnsMax the maximum number of turns allowed in the game
     * @throws InvalidDimensionBoardException when the parameters are less than 0
     */
    public Board(int numBalls, int numTurnsMax) throws InvalidDimensionBoardException {
        if (numBalls < 0 || numTurnsMax < 0) {
            throw new InvalidDimensionBoardException();
        }
        this.board = new Ball_color[numTurnsMax][numBalls];
        this.feedback = new Pair[numTurnsMax];
    }

    /**
     * Updates a row of the board with the given player code.
     *
     * @param codePlayer the code played by the player
     * @throws InvalidCodeSizeException when the size of codePlayer is not equal to size of Board
     */
    public void updateRowBoard(Ball_color[] codePlayer, int numTurn) throws InvalidCodeSizeException {
        if (codePlayer.length != this.board[0].length) {
            throw new InvalidCodeSizeException();
        }
        this.board = setRow(codePlayer, numTurn-1); //parameter GameStats.getNumTurn();
    }

    /**
     * Updates the feedback for a specific turn of the game.
     *
     * @param numTurn the turn number to update the feedback for
     * @param fd a Pair representing the number of black and white pins in the feedback
     * @throws PositionOutOfBoundsFeedbackException when numTurn is less than 0 or grater than feedback size
     */
    public void updateFeedback(int numTurn, Pair<Integer,Integer> fd) throws PositionOutOfBoundsFeedbackException {
        if (numTurn > this.feedback.length || numTurn < 1) { //parameter GameStats.getNumTurn();
            throw new PositionOutOfBoundsFeedbackException();
        }
        this.feedback[numTurn-1] = fd;
    }

    /**
     * Getter method to return the game board.
     *
     * @return a matrix representing the player codes in each turn
     */
    public Ball_color[][] getBoard() {
        return this.board;
    }

    /**
     * Setter method to set the game board to a new value.
     *
     * @param board a matrix representing the player codes in each turn
     */
    public void setBoard(Ball_color[][] board) {
        this.board = board;
    }

    /**
     * Getter method to return the feedback for each turn of the game.
     *
     * @return an array representing the feedback for each turn
     */
    public Pair<Integer,Integer>[] getFeedback() {
        return this.feedback;
    }

    /**
     * Getter method to return the feedback for specific turn of the game.
     *
     * @return an array representing the feedback for each turn
     */
    public Pair<Integer, Integer> getCurrentFeedback(int numTurn) {
        if(board[numTurn-1][0] == null)  return this.feedback[numTurn-2];
        else return this.feedback[numTurn-1];
    }

    /**
     * Setter method to set the feedback for each turn of the game.
     *
     * @param feedback an array representing the feedback for each turn
     */
    public void setFeedback(Pair<Integer,Integer>[] feedback) {
        this.feedback = feedback;
    }

    /**
     * Private method to set a row of the board to the given player code.
     *
     * @param codePlayer the code played by the player
     * @return a matrix representing the updated game board
     */
    private Ball_color[][] setRow(Ball_color[] codePlayer, int numTurn) { //parameter GameStats.getNumTurn();
        int numBalls = codePlayer.length;
        for (int col = 0; col < numBalls; col++) {
            this.board[numTurn][col] = codePlayer[col];
        }
        return board;
    }
}

