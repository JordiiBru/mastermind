package test;

import domain.GameStats;
import domain.exceptions.GameNotFinishedException;
import domain.exceptions.GameNotStartedException;

import domain.exceptions.InvalidTurnIncrementException;
import domain.exceptions.VariableNotDefinedException;
import utils.Mode;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author jordi
 * Testing of the class GameStats
 */
public class GameStatsTest {
    private GameStats gameStats;

    @Before
    public void setUp() {
        gameStats = new GameStats();
    }

    /**
     * Test to check if startGameStats works fine
     * @throws GameNotStartedException when trying to start the timer in a not started game
     */
    @Test
    public void testStartGameStats() throws GameNotStartedException {
        gameStats.startGameStats();
        assertNotNull(gameStats.getStartTime());
        assertEquals(1, gameStats.getNumTurn());
    }

    /**
     * Test to see what if we try to finish a game that hasn't started
     * @throws GameNotStartedException to finish a game, it has to be started previously
     * @throws VariableNotDefinedException calculate score needs variables to be set
     * @throws GameNotFinishedException when trying to calculate the score or the duration without finishing the game (won't happen)
     */
    @Test(expected = GameNotStartedException.class)
    public void testFinishWhenNotStarted() throws GameNotStartedException, VariableNotDefinedException, GameNotFinishedException {
        gameStats.finishGameStats(Mode.CodeBreaker,8,1.5f);
    }

    /**
     * Base case test for incrementing turn in an ongoing game
     * @throws GameNotStartedException you can't add a turn if the games not started
     */
    @Test
    public void testAddTurnBaseCase() throws GameNotStartedException, InvalidTurnIncrementException {
        gameStats.startGameStats();
        gameStats.addTurn(8);
        assertEquals(2,gameStats.getNumTurn());
    }

    /**
     * Exception case when trying to add turn when the game is not started
     * @throws GameNotStartedException when game is not started yet
     */
    @Test(expected = GameNotStartedException.class)
    public void testAddTurnNotAllowed() throws GameNotStartedException, InvalidTurnIncrementException {
        gameStats.addTurn(8);
    }

    /**
     * Test the pause and restore methods
     * @throws GameNotStartedException not a started game
     * @throws VariableNotDefinedException variables not correctly set
     * @throws GameNotFinishedException game still not finished
     */
    @Test
    public void testPauseRestoreGameStats() throws GameNotStartedException, VariableNotDefinedException, GameNotFinishedException {
        gameStats.startGameStats();
        gameStats.pauseGameStats();
        gameStats.restoreGameStats();
        gameStats.finishGameStats(Mode.CodeBreaker,8,1.5f);
        assertEquals(0,gameStats.getElapsedTime());
        assertEquals(10501,gameStats.getScore());
    }

    /**
     * More complex test for the pause/restore method
     * @throws VariableNotDefinedException variables not correctly set
     * @throws InterruptedException thread sleep exception
     * @throws GameNotStartedException game not started is a condition to calculations
     * @throws GameNotFinishedException game not finished is a condition to calculation
     */
    @Test
    public void testPauseRestoreComplexGameStatsCB() throws VariableNotDefinedException, InterruptedException, GameNotStartedException, GameNotFinishedException, InvalidTurnIncrementException {
        gameStats.startGameStats();
        gameStats.addTurn(8);
        gameStats.addTurn(8);
        Thread.sleep(2000);
        gameStats.pauseGameStats();
        Thread.sleep(2000);
        gameStats.restoreGameStats();
        gameStats.finishGameStats(Mode.CodeBreaker,8,1.5f);
        assertEquals(2,gameStats.getElapsedTime());
        assertEquals(2500,gameStats.getScore());
    }

    @Test
    public void testPauseRestoreComplexGameStatsCM() throws GameNotStartedException, InvalidTurnIncrementException, GameNotFinishedException, InterruptedException, VariableNotDefinedException {
        gameStats.startGameStats();
        gameStats.addTurn(8);
        gameStats.addTurn(8);
        Thread.sleep(2000);
        gameStats.pauseGameStats();
        Thread.sleep(2000);
        gameStats.restoreGameStats();
        gameStats.finishGameStats(Mode.CodeMaker,8,1.5f);
        assertEquals(2,gameStats.getElapsedTime());
        assertEquals(3,gameStats.getScore());
    }

    /**
     * You can't pause a game that is not started yet.
     * @throws GameNotStartedException not started
     */
    @Test(expected = GameNotStartedException.class)
    public void testPauseGameStatsWithoutStartingGame() throws GameNotStartedException, GameNotFinishedException {
        gameStats.pauseGameStats();
    }

    @Test(expected = GameNotStartedException.class)
    public void testCalculatedElapsedTimeWithoutStartingGame() throws GameNotStartedException, GameNotFinishedException {
        gameStats.calculatedElapsedTime();
    }

    @Test(expected = GameNotFinishedException.class)
    public void testCalculatedElapsedTimeWithoutFinishingGame() throws GameNotStartedException, GameNotFinishedException {
        gameStats.startGameStats();
        gameStats.calculatedElapsedTime();
    }

    @Test(expected = GameNotStartedException.class)
    public void testCalculateScoreWithoutStartingGame() throws GameNotStartedException, GameNotFinishedException, VariableNotDefinedException {
        gameStats.calculateScoreCB(8,1.5f);
    }

    @Test(expected = GameNotFinishedException.class)
    public void testCalculateScoreWithoutFinishingGame() throws GameNotStartedException, GameNotFinishedException, VariableNotDefinedException {
        gameStats.startGameStats();
        gameStats.calculateScoreCB(8,1.5f);
    }

    /**
     * Test when the time is 0
     * @throws GameNotStartedException if game is not started
     * @throws GameNotFinishedException if game is not finished
     */
    @Test
    public void testStartedAndStoppedBeforeOneSecond() throws GameNotStartedException, GameNotFinishedException {
        gameStats.startGameStats();
        gameStats.pauseGameStats();
        assertEquals(0, gameStats.getElapsedTime());
        assertEquals(0, gameStats.getScore());
    }

    /**
     * Test that if you win, but you take long time, you will end up with 0 points anyway
     * @throws GameNotStartedException game is not started
     * @throws GameNotFinishedException game is not finished
     */
    @Test
    public void testStartedAndStoppedAfterMaximumSeconds() throws GameNotStartedException, GameNotFinishedException {
        gameStats.startGameStats();
        gameStats.setEndTime(gameStats.getStartTime().plusSeconds(Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, gameStats.calculatedElapsedTime());
        assertEquals(0, gameStats.getScore());
    }

    /**
     * Test to see the minimum score possible is always 0. Not less. That's the intended.
     * @throws GameNotStartedException game is not started
     * @throws GameNotFinishedException game is not finished
     * @throws VariableNotDefinedException variables not set up correctly
     */
    @Test
    public void testMinimumPossibleScore() throws GameNotStartedException, GameNotFinishedException, VariableNotDefinedException {
        gameStats.startGameStats();
        gameStats.setEndTime(gameStats.getStartTime().plusSeconds(1));
        gameStats.setNumTurn(8);
        assertEquals(0, gameStats.calculateScoreCB(8,1.5f));
        assertEquals(1, gameStats.getElapsedTime());
    }

}