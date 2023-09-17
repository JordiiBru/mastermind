package test;

import domain.Board;

import domain.exceptions.*;

import utils.Ball_color;
import utils.Pair;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {
    private Board board;

    /**
     * Set Up the Board test
     */
    @Before
    public void setUp() {
        board = new Board();
    }

    /**
     * Checks that the Board class constructor with no arguments creates an empty board without feedback pairs
     */
    @Test
    public void testEmptyBoardConstructor() {
        String[][] emptyBoard = new String[0][0];
        Pair<Integer,Integer>[] emptyFeedback = new Pair[0];
        assertArrayEquals(emptyBoard, board.getBoard());
        assertArrayEquals(emptyFeedback, board.getFeedback());
    }

    /**
     * Checks that the Board class constructor with two arguments (number of balls and maximum number of turns) creates a board matrix with dimensions numTurnsMax x numBalls and an empty feedback array of size numTurnsMax
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     */
    @Test
    public void testBoardConstructor() throws InvalidDimensionBoardException {
        int numBalls = 4;
        int numTurnsMax = 12;
        board = new Board(numBalls, numTurnsMax);
        String[][] expectedBoard = new String[numTurnsMax][numBalls];
        Pair<Integer,Integer>[] expectedFeedback = new Pair[numTurnsMax];
        assertArrayEquals(expectedBoard, board.getBoard());
        assertArrayEquals(expectedFeedback, board.getFeedback());
    }

    /**
     * checks that the Board class constructor throws an InvalidDimensionBoardException when any of the two parameters are negative.
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     */
    @Test(expected = InvalidDimensionBoardException.class)
    public void testInvalidBoardConstructor() throws InvalidDimensionBoardException {
        int numBalls = 4;
        int numTurnsMax = -12;
        board = new Board(numBalls, numTurnsMax);
    }

    /**
     * Checks whether setBoard sets the board correctly.
     */
    @Test
    public void testSetBoard() {
        Board board = new Board();
        Ball_color[][] newBoard = {{Ball_color.Red, Ball_color.Yellow}, {Ball_color.Blue, Ball_color.Green}};
        board.setBoard(newBoard);
        assertArrayEquals(newBoard, board.getBoard());
    }

    /**
     * Checks whether setFeedback sets the feedback correctly.
     */
    @Test
    public void testSetFeedback() {
        Board board = new Board();
        Pair<Integer,Integer>[] newFeedback = new Pair[]{new Pair<>(1,1), new Pair<>(0,2)};
        board.setFeedback(newFeedback);
        assertArrayEquals(newFeedback, board.getFeedback());
    }

    /**
     * Checks that the updateRowBoard() method correctly updates the board matrix with a given code player
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     * @throws InvalidCodeSizeException if the code size is invalid
     */
    @Test
    public void testUpdateRowBoard() throws InvalidDimensionBoardException, InvalidCodeSizeException {
        int numBalls = 4;
        int numTurnsMax = 12;
        int numTurn = 1;
        board = new Board(numBalls, numTurnsMax);
        Ball_color[] codePlayer = {Ball_color.Blue, Ball_color.Red, Ball_color.Green, Ball_color.Green};
        Ball_color[][] expectedBoard = new Ball_color[numTurnsMax][numBalls];
        expectedBoard[numTurn-1] = codePlayer;
        board.updateRowBoard(codePlayer,numTurn);
        assertArrayEquals(expectedBoard, board.getBoard());
    }

    /**
     * Checks that the updateRowBoard() method throws an InvalidCodeSizeException when the length of the code player array does not match the number of balls in the board
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     * @throws InvalidCodeSizeException if the code size is invalid
     */
    @Test(expected = InvalidCodeSizeException.class)
    public void testInvalidUpdateRowBoard() throws InvalidDimensionBoardException, InvalidCodeSizeException {
        int numBalls = 6;
        int numTurnsMax = 8;
        board = new Board(numBalls, numTurnsMax);
        Ball_color[] codePlayer = {Ball_color.Blue, Ball_color.Red, Ball_color.Green, Ball_color.Green, Ball_color.Empty};
        board.updateRowBoard(codePlayer, 2);
    }

    /**
     * Checks that the updateFeedback() method correctly updates the feedback array at a given turn with a given feedback pair
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     * @throws PositionOutOfBoundsFeedbackException if turn number is invalid to update feedback
     */
    @Test
    public void testUpdateFeedback() throws InvalidDimensionBoardException, PositionOutOfBoundsFeedbackException {
        int numBalls = 4;
        int numTurnsMax = 12;
        board = new Board(numBalls, numTurnsMax);
        Pair<Integer, Integer> fd = new Pair<>(0, 0);
        Pair<Integer, Integer>[] expectedFeedback = new Pair[numTurnsMax];
        expectedFeedback[0] = fd;
        board.updateFeedback(1, fd);
        assertArrayEquals(expectedFeedback, board.getFeedback());
    }

    /**
     * Checks that the updateFeedback() method throws a PositionOutOfBoundsFeedbackException when the turn index is negative
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     * @throws PositionOutOfBoundsFeedbackException if turn number is invalid to update feedback
     */
    @Test(expected = PositionOutOfBoundsFeedbackException.class)
    public void testNegativePositionUpdateFeedback() throws InvalidDimensionBoardException, PositionOutOfBoundsFeedbackException {
        int numBalls = 4;
        int numTurnsMax = 8;
        board = new Board(numBalls, numTurnsMax);
        Pair<Integer, Integer> fd = new Pair<>(0, 0);
        board.updateFeedback(-1, fd);
    }

    /**
     * Checks that the updateFeedback() method throws a PositionOutOfBoundsFeedbackException when the turn index is greater than size feedback
     * @throws InvalidDimensionBoardException if there are invalid dimensions for board
     * @throws PositionOutOfBoundsFeedbackException if turn number is invalid to update feedback
     */
    @Test(expected = PositionOutOfBoundsFeedbackException.class)
    public void testGreaterPositionUpdateFeedback() throws InvalidDimensionBoardException, PositionOutOfBoundsFeedbackException {
        int numBalls = 4;
        int numTurnsMax = 8;
        board = new Board(numBalls, numTurnsMax);
        Pair<Integer, Integer> fd = new Pair<>(0, 0);
        board.updateFeedback(9, fd); //numTurn start to 0
    }
}
