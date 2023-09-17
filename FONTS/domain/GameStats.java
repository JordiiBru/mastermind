package domain;
import domain.exceptions.*;
import utils.Mode;

import java.time.Duration;
import java.time.Instant;

/**
 * @author jordi
 * All the relevant statistics of an ongoing game. It also computes the final score, and it manages all the timings.
 */
public class GameStats {
    private int score;
    private int numTurn;
    private int numHintsUsed;
    private Instant startTime;
    private Instant endTime;
    private int elapsedTime;

    /**
     * Initialization of the base attributes by a default number, but will be changed during the game.
     */
    public GameStats() {
        this.score = 0;
        this.numTurn = 0;
        this.numHintsUsed = 0;
        this.elapsedTime = 0;
    }

    /**
     * Init of a saved game stats with its values
     * @param new_numTurn number of turns stored in persistence
     * @param new_numHintsUsed  number of hints used stored in persistence
     * @param new_elapsedTime  elapsed time in seconds stored in persistence
     */
    public GameStats(int new_numTurn, int new_numHintsUsed, int new_elapsedTime) {
        this.score = 0;
        this.numTurn = new_numTurn;
        this.numHintsUsed = new_numHintsUsed;
        this.elapsedTime = new_elapsedTime;
    }

    /**
     * Method to make all the initials steps regarding setting the necessary information to start the game and start the timer
     */
    public void startGameStats() {
        this.numTurn = 1;
        startTimer();
    }

    /**
     * Method called when the player saves and exits the game. It stops the timer and stores the duration of the game played
     * @throws GameNotStartedException when the game is not started yet
     * @throws GameNotFinishedException when the game is not finished yet
     */
    public void pauseGameStats() throws GameNotStartedException, GameNotFinishedException {
        endTimer();
        setElapsedTime(calculatedElapsedTime());
        setStartTime(null);
        setEndTime(null);
    }

    /**
     * Method to be called when a game was paused. So it can start again the timer.
     */
    public void restoreGameStats() {
        startTimer();
    }

    /**
     * Method to make all the last steps to calculate and set the information of a finished game. It works different depending on the role
     * @param playerRole role of the player currently playing
     * @param maxTurns number of maximum turns set in difficulty
     * @param multiplier multiplier set in difficulty
     * @throws GameNotStartedException when the game is not started yet
     * @throws GameNotFinishedException when the game is not finished yet (because of calculate score)
     * @throws VariableNotDefinedException when it tries to calculate the score, and they are variables like multiplier or maxTurns that are not set correctly
     */
    public void finishGameStats(Mode playerRole, int maxTurns, double multiplier) throws GameNotStartedException, GameNotFinishedException, VariableNotDefinedException {
        if (startTime == null) {
            throw new GameNotStartedException();
        }
        endTimer();
        int total_score;
        if(playerRole == Mode.CodeBreaker) total_score = calculateScoreCB(maxTurns,multiplier);
        else total_score = calculateScoreCM();
        setScore(total_score);
    }

    /**
     * Method to be called when a turn is made. It increments the stat of number of turns of the ongoing game.
     * @param maxTurns number of maximum turns set in difficulty to check if it is a legal incrementation
     * @throws GameNotStartedException when the game is not started and the number of turns is still set to 0.
     * @throws InvalidTurnIncrementException turn can not be more than max turns
     */
    public void addTurn(int maxTurns) throws GameNotStartedException, InvalidTurnIncrementException {
        if (getStartTime() == null) throw new GameNotStartedException();
        if(getNumTurn()+1 > maxTurns) throw new InvalidTurnIncrementException();
        else this.numTurn++;
    }

    /**
     * Method to be called when the codebreaker asks for a hint. It increments the stat of the hints used.
     */
    public void addHintUsed() {
        this.numHintsUsed++;
    }

    private void startTimer() {
        this.startTime = Instant.now();
    }

    private void endTimer() {
        this.endTime = Instant.now();
    }

    /**
     *
     * @return The elapsed time between the initial and final time of the game (in seconds)
     * @throws GameNotStartedException when trying to calculate the elapsed time in a non-started game
     * @throws GameNotFinishedException when trying to calculate the elapsed time in a non-finished game
     */
    public int calculatedElapsedTime() throws GameNotStartedException, GameNotFinishedException {
        if(startTime == null){
            throw new GameNotStartedException();
        }
        if(endTime == null) {
            throw new GameNotFinishedException();
        }
        Duration duration = Duration.between(this.startTime, this.endTime);
        return (int) duration.getSeconds();
    }

    /**
     * Calculates the final score of a finished game and stores it in the score attribute (when the player is code breaker)
     * @param maxTurns number of maximum turns set in difficulty
     * @param multiplier multiplier set in difficulty
     * @throws GameNotFinishedException when trying to calculate the final score in a non-finished game
     */
    public int calculateScoreCB(int maxTurns, double multiplier) throws GameNotStartedException, GameNotFinishedException, VariableNotDefinedException {
        if(startTime == null) {
            throw new GameNotStartedException();
        }
        if(endTime == null) {
            throw new GameNotFinishedException();
        }
        setElapsedTime(calculatedElapsedTime());
        if (maxTurns == 0 || multiplier == 0f) throw new VariableNotDefinedException();
        int actual_score = (int) (((1000*maxTurns - 500*this.getNumTurn() + 1) * multiplier) / (getElapsedTime() + 1 + getNumHintsUsed()));
        if (actual_score < 0) return actual_score= 0;
        else return actual_score;
    }

    /**
     * Calculates the final score when the player is Code maker.
     * @return score which is the number of turns for the computer to solve the code
     */
    private int calculateScoreCM() {
        return getNumTurn();
    }

    // getters and setters of the class

    /**
     * Method to get all the GameStats object for records and ranking purposes.
     * The class who calls this method will have to check every param needed and check if it is a win in the rankings class.
     * @throws GameNotFinishedException when trying to get all the information when the game is not finished yet
     * @throws GameNotStartedException when trying to get all the information when the game is not started yet
     */
    public GameStats getGameStats() throws GameNotFinishedException, GameNotStartedException {
        if(startTime == null) {
            throw new GameNotStartedException();
        }
        if(endTime == null) {
            throw new GameNotFinishedException();
        }
        return this;
    }

    /**
     * Get the score of the ongoing game.
     * @return score of the game
     */
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the actual turn of the ongoing game
     * @return actual turn of the game
     */
    public int getNumTurn() {
        return this.numTurn;
    }

    public void setNumTurn(int numTurn) { // testing purposes
        this.numTurn = numTurn;
    }

    /**
     * Get the number of hints used of the ongoing game
     * @return number of hints used
     */
    public int getNumHintsUsed() {
        return this.numHintsUsed;
    }

    /**
     * @return startTime from the object GameStats
     * @throws GameNotStartedException when trying to get the startTime in a non-started game
     */
    public Instant getStartTime() throws GameNotStartedException {
        if(this.startTime == null) {
            throw new GameNotStartedException();
        }
        return this.startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    /**
     * @return endTime from the object GameStats
     * @throws GameNotFinishedException when trying to get the endTime in a non-finished game
     */
    public Instant getEndTime() throws GameNotFinishedException {
        if(this.endTime == null) {
            throw new GameNotFinishedException();
        }
        return this.endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the value of the elapsedTime stored of the ongoing game
     * @return duration of the game in seconds
     */
    public int getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(int seconds) {
        this.elapsedTime += seconds;
    }

}