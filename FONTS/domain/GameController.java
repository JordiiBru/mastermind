package domain;

import domain.exceptions.*;
import utils.*;
import java.util.List;

/**
 * @author javier
 * Controller class of the game.
 * This class will be the one in charge to make sure that all classes are able to communicate to one another.
 * This class will also comunicate with the DomainController to ensure a good comunication with the other layers.
 */
public class GameController {

    //Data
    private Player player;
    private Computer computer;
    private Difficulty difficulty;
    private Board board;
    private Feedback feedback;
    private GameStats gamestats;

    //Constructor
    /**
     * Creates an empty GameController class
     */
    public GameController() {}

    //Setter
    /**
     *  Instantiates the difficulty as a DifficultyNormal and sets the initial values to Normal default
     */
    public void setDifficultyToNormal() {
        difficulty = new DifficultyNormal();
    }

    /**
     * Instantiates the difficulty as a DifficultyHard and sets the initial values to Hard default
     */
    public void setDifficultyToHard() {
        difficulty = new DifficultyHard();
    }

    /**
     * Instantiates the difficulty as a DifficultyCustom and sets the values of the newly instantiated class to the sent parameters
     * @param algorithm true: 5-guess, false: Genetic
     * @param repeated_colors allowing repeated colors (boolean)
     * @param number_positions number of balls in a code
     * @param empty_colors allowing empty colors
     * @param max_turns number of maximum turns in the game
     * @throws DifficultyNumberPositionsMismatchException Exception from Difficulty
     * @throws DifficultyMaxTurnsMismatchException Exception from Difficulty
     */
    public void setDifficultyToCustom(boolean algorithm,boolean repeated_colors, int number_positions, boolean empty_colors, int max_turns) throws DifficultyNumberPositionsMismatchException, DifficultyMaxTurnsMismatchException {
        difficulty = new DifficultyCustom();
        DifficultyCustom custom = new DifficultyCustom();
        custom.setAlgCus(algorithm);
        custom.setRepColCus(repeated_colors);
        custom.setNumPosCus(number_positions);
        custom.setEmptyColCus(empty_colors);
        custom.setMaxTurCus(max_turns);
        custom.setMulCus();
        difficulty = custom;
    }

    /**
     * Creates an empty instance of the remaining classes needed to play a game and sets their initial values
     * @param player_name player name given by presentation
     * @param player_role Enum Mode; the remaining mode will be automatically assigned to the computer
     * @throws InvalidDimensionBoardException Exception from Board
     * @throws PositionOutOfBoundsException Exception from Player
     */
    public void startGame(String player_name, Mode player_role) throws Exception {
        player = new Player(player_name);
        player.setRole(player_role);
        player.initCode(difficulty.getNumberPositions());
        if (player_role == Mode.CodeMaker) {
            if (difficulty.getAlgorithm()) computer = new FiveGuessComputer(difficulty.getMaxTurns(), difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
            else computer = new GeneticComputer(difficulty.getMaxTurns(), difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
            board = new Board(difficulty.getNumberPositions(), difficulty.getMaxTurns());
            feedback = new Feedback();
            gamestats = new GameStats();
            gamestats.startGameStats();
        }
        else{
            computer = new CodeMakerComputer(difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
            board = new Board(difficulty.getNumberPositions(), difficulty.getMaxTurns());
            feedback = new Feedback();
            gamestats = new GameStats();
            newSecretCodeComputer();
        }

    }

    // Continue Game Procedure  vvvv @author jordi

    /**
     * Method that restores the game initializing the computer with the saved stats and start the timer to continue.
     * All the difficulty stats and other necessary information is set before calling this method in the domain controller.
     */
    public void restoreGame() {
        if(player.getRole() == Mode.CodeMaker) {
            if (difficulty.getAlgorithm()) computer = new FiveGuessComputer(difficulty.getMaxTurns(), difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
            else computer = new GeneticComputer(difficulty.getMaxTurns(), difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
        }
        else computer = new CodeMakerComputer(difficulty.getNumberPositions(), difficulty.getEmptyColors(), difficulty.getRepeatedColors());
        gamestats.restoreGameStats(); //start again the timer
    }

    /**
     * Method called in the domain controller which initializes the player with the saved information.
     * Necessary method before calling restoreGame().
     * @param player_name saved player name
     * @throws PositionOutOfBoundsException number of positions given is not allowed
     */
    public void setSavedPlayer(String player_name) throws PositionOutOfBoundsException {
        player = new Player(player_name);
        player.setRole(Mode.CodeBreaker);
        player.initCode(difficulty.getNumberPositions());
    }

    /**
     * Method called in the domain controller which initializes the difficulty with the saved information.
     * Necessary method before calling restoreGame().
     * @param diff name of the desired difficulty
     * @param algorithm saved algorithm given
     * @param repeated_colors saved parameter given
     * @param number_positions saved parameter given
     * @param empty_colors saved parameter given
     * @param max_turns saved parameter given
     * @throws DifficultyMaxTurnsMismatchException max turns not matching
     * @throws DifficultyNumberPositionsMismatchException number of positions not matching
     */
    public void setSavedDifficulty(String diff, boolean algorithm,boolean repeated_colors, int number_positions, boolean empty_colors, int max_turns) throws DifficultyMaxTurnsMismatchException, DifficultyNumberPositionsMismatchException {
        switch (diff) {
            case "normal":
                setDifficultyToNormal();
                break;
            case "hard":
                setDifficultyToHard();
                break;
            case "custom":
                setDifficultyToCustom(algorithm, repeated_colors, number_positions, empty_colors, max_turns);
                break;
        }
    }

    /**
     * Method called in the domain controller which initializes the feedback with the saved information.
     * Necessary method before calling restoreGame().
     * @param sc saved secret code given
     */
    public void setSavedSecretCode(Ball_color[] sc) {
        feedback = new Feedback();
        feedback.setSecretCode(sc);
    }

    /**
     * Method called in the domain controller which initializes the GameStats with the saved information.
     * Necessary method before calling restoreGame().
     * @param elapsedTime elapsed time of the saved game
     * @param numTurn actual number of turns of the saved game
     * @param hintsUsed number of hints used of the saved game
     */
    public void setSavedGameStats(int elapsedTime, int numTurn, int hintsUsed) {
        gamestats = new GameStats(numTurn,hintsUsed,elapsedTime);
    }

    /**
     * Method called in the domain controller which initializes the board with the saved information.
     * Necessary method before calling restoreGame().
     * @param saved_board saved board
     * @param saved_feedback saved feedback board
     */
    public void setSavedBoardState(Ball_color[][] saved_board, Pair<Integer,Integer>[] saved_feedback) {
        board = new Board();
        board.setBoard(saved_board);
        board.setFeedback(saved_feedback);
    }

    //--- new getters when saving game ---

    /**
     * Check what difficulty we are playing if domain controller asks for it
     * @return a string with the name of the difficulty
     */
    public String checkDiffName() {
        if(difficulty instanceof DifficultyNormal) return "normal";
        else if(difficulty instanceof DifficultyHard) return "hard";
        else return "custom";
    }

    /**
     * Getter of the player role
     * @return actual player role
     */
    public Mode getPlayerRole(){
        return player.getRole();
    }

    /**
     * Getter of the number of positions in difficulty
     * @return number of positions of the code
     */
    public int getDiffNumPositions() {
        return difficulty.getNumberPositions();
    }

    /**
     * Getter of the algorithm used
     * @return true if 5guess, false is genetic.
     */
    public boolean getDiffAlgorithm() {
        return difficulty.getAlgorithm();
    }

    /**
     * Getter to know if repeated colors are allowed
     * @return true if it is allowed, false otherwise
     */
    public boolean getDiffRepeated() {
        return difficulty.getRepeatedColors();
    }

    /**
     * Getter to know if empty colors are allowed
     * @return true if empty colors is allowed, false otherwise
     */
    public boolean getDiffEmpty() {
        return difficulty.getEmptyColors();
    }

    /**
     * Getter of the number of hints used
     * @return the number of hints used in the actual game
     */
    public int getHintsUsed() {
        return gamestats.getNumHintsUsed();
    }


    // Continue Game Procedure ^^^^

    //Getter

    /**
     * Getter of the player name
     * @return Returns a String containing the player name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Getter of the game stats
     * @return Returns the instance of GameStats saved in GameController
     */
    public GameStats getGameStats() {
        return gamestats;
    }

    /**
     * Getter of the score of the game
     * @return Returns an integer with the punctuation of the game
     */
    public int getScore() {
        return gamestats.getScore();
    }

    /**
     * Getter of the number of the actual turn
     * @return Returns an integer with the actual turn
     */
    public int getTurn() {
        return gamestats.getNumTurn();
    }

    /**
     * Getter of the maximum turns allowed in this game
     * @return Returns an integer with the maximum turns
     */
    public int getMaxTurns() {
        return difficulty.getMaxTurns();
    }

    /**
     * Getter of the full board state
     * @return Returns a matrix Ball_color[][] with the current state of the board
     */
    public Ball_color[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Getter of the full feedback state
     * @return Returns an array of Pair<int,int> with the feedback of the board
     */
    public Pair<Integer, Integer>[] getBoardFeedback() {
        return board.getFeedback();
    }

    /**
     * Getter of the last feedback set in the board
     * @return Returns a Pair<int,int> with the last feedback set in Board
     */
    public Pair<Integer,Integer> getCurrentFeedback() {

        return board.getCurrentFeedback(gamestats.getNumTurn());
    }

    /**
     * Getter of the current code of the player
     * @return Returns an array Ball_color[] with the current code of the player
     */
    public Ball_color[] getPlayerCurrentCode() {
        return player.getCurrentCode();
    }

    /**
     * Getter of the secret_code set in Feedback
     * @return Returns a Ball_color[] with the combination of colors of the secret code
     */
    public Ball_color[] getSecretCode() {
        return feedback.getSecretCode();
    }

    /**
     * Getter of the elapsed time calculated in GameStats
     * @return Returns an integer with the elapsed time
     */
    public int getElapsedTime() {
        return gamestats.getElapsedTime();
    }

    //Other
    /**
     * Sends the desired color in the desired position to the Player
     * @param position desired position to place the new ball
     * @param color desired color of the new ball
     * @throws PositionOutOfBoundsException Exception from Player
     */
    public void newColor(int position, Ball_color color) throws PositionOutOfBoundsException {
        player.addColor(position, color);
    }

    /**
     * Tells the player that the actual code is wished to be removed completely
     * @throws PositionOutOfBoundsException Exception from Player
     */
    public void cleanCode() throws PositionOutOfBoundsException {
        player.cleanCode();
    }

    /**
     * Tells the player that the code has been completed and confirmed.
     * 1)
     * If the player's role is CodeMaker, this will be the confirmation for what will be the secret code.
     * The code returned by Player confirmation will be set in Feedback as the secret code and will be sent to Computer to resolve the game.
     * The result given by Computer will be sent to Board to save the information.
     * 2)
     * If the player's role is CodeBreaker, it will simply be sent to Board to update the corresponding row and will be compared with the secret code saved in Feedback.
     * RETURN:
     * This function also tells if the game has ended within the max_turns returning true, or if it's still ongoing returning false.
     * @return TRUE - The game has been won; FALSE - The game is still ongoing
     * @throws Exception This Exception includes the following exceptions:
     * @throws NotRepeatedColorsException Exception from Player
     * @throws NotEmptyColorsException Exception from Player
     * @throws InvalidCodeSizeException Exception from Board
     * @throws PositionOutOfBoundsFeedbackException Exception from Board
     * @throws FeedbackSecretCodeLengthMismatchException Exception from Feedback
     * @throws GameNotStartedException Exception from GameStats
     * @throws VariableNotDefinedException Exception from GameStats
     * @throws GameNotFinishedException Exception from GameStats
     * @throws InvalidTurnIncrementException Exception from GameStats
     */
     public boolean confirmCode() throws Exception {
        Ball_color[] code = player.confirmedCode(difficulty.getRepeatedColors(), difficulty.getEmptyColors());
        if (player.getRole() == Mode.CodeBreaker) {
            board.updateRowBoard(code, gamestats.getNumTurn());
            Pair<Integer,Integer> diff = feedback.compareSecretCode(code);
            board.updateFeedback(gamestats.getNumTurn(), diff);
            player.cleanCode();
            return checkIfGameWon(diff.first());
        }
        else {
            feedback.setSecretCode(code);
            boolean end = setBoardToComputerSolution();
            gamestats.finishGameStats(player.getRole(),difficulty.getMaxTurns(), difficulty.getMultiplier());
            player.cleanCode();
            return end;
        }
    }

    /**
     * Auxiliary function to help confirmCode() calculate the state of the game
     * @param first The first integer of the Pair<int,int> returned by the comparison done in Feedback
     * @return TRUE - The game has been won; FALSE - The game is still ongoing
     * @throws GameNotStartedException Exception from GameStats
     * @throws VariableNotDefinedException Exception from GameStats
     * @throws GameNotFinishedException Exception from GameStats
     * @throws InvalidTurnIncrementException Exception from GameStats
     */
    private boolean checkIfGameWon(int first) throws GameNotStartedException, VariableNotDefinedException, GameNotFinishedException, InvalidTurnIncrementException {
        if (first == difficulty.getNumberPositions()) {
            gamestats.finishGameStats(player.getRole(), difficulty.getMaxTurns(), difficulty.getMultiplier());
            return true;
        }
        else {
            if(gamestats.getNumTurn() != difficulty.getMaxTurns()) gamestats.addTurn(difficulty.getMaxTurns());
            return false;
        }
    }

    /**
     * Auxiliary function to help confirmCode() to set in Board the complete solution given by Computer
     * and the feedback to each row.
     * This function will only be called if the player's role is CodeMaker. This means that this function will complete the game
     * with the solution given by Computer and return TRUE if the computer was able to break the code or FALSE if the computer
     * was not able no break the code within the max turns.
     * @return TRUE - The game has been won; FALSE - The game has been lost
     * @throws Exception Exception from Computer. This Exception also includes the following exceptions:
     * @throws InvalidCodeSizeException Exception from Board
     * @throws PositionOutOfBoundsFeedbackException Exception from Board
     * @throws FeedbackSecretCodeLengthMismatchException Exception from Feedback
     * @throws GameNotStartedException Exception from GameStats
     * @throws InvalidTurnIncrementException Exception from GameStats
     */
    private boolean setBoardToComputerSolution() throws Exception {
        Ball_color[] secret_code = feedback.getSecretCode();
        List<Integer> secretParser = ParserColorInt.ballColorArrayToIntList(secret_code);
        List<List<Integer>> computer_solution = computer.solve(secretParser);
        Ball_color[][] solution = ParserColorInt.intListListToBallColorMatrix(computer_solution);
        for (int i = 0; i < solution.length; ++i) {
            board.updateRowBoard(solution[i], gamestats.getNumTurn());
            Pair<Integer,Integer> diff = feedback.compareSecretCode(solution[i]);
            board.updateFeedback(gamestats.getNumTurn(), diff);
            if (i < solution.length - 1) gamestats.addTurn(difficulty.getMaxTurns());
            else if (diff.first() == difficulty.getNumberPositions()) return true;
        }
        return false;
    }

    /**
     * Tells Computer to create a new secret code and sends the result to feedback
     * @throws Exception Exception from Computer
     */
    public void newSecretCodeComputer() throws Exception {
        List<List<Integer>> secretCode = computer.solve(null);
        Ball_color[][] ballColorMatrix = ParserColorInt.intListListToBallColorMatrix(secretCode);
        feedback.setSecretCode(ballColorMatrix[0]);
        gamestats.startGameStats();
    }

    /**
     * Asks feedback for a hint of the code to solve. Puts the result in the actual code of Player
     * @throws FeedbackHintIndexOutOfBoundsException Exception from Feedback
     * @throws PositionOutOfBoundsException Exception from Player
     */
    public void desiredHint() throws FeedbackHintIndexOutOfBoundsException, PositionOutOfBoundsException {
        Pair<Integer,Ball_color> hint = feedback.askHint();
        gamestats.addHintUsed();
        player.addColor(hint.first(), hint.second());
    }

    /**
     * Tells GameStats that the game has ended
     * @throws GameNotStartedException Exception from GameStats
     * @throws VariableNotDefinedException Exception from GameStats
     * @throws GameNotFinishedException Exception from GameStats
     */
    public void surrender() throws GameNotStartedException, VariableNotDefinedException, GameNotFinishedException {
        gamestats.finishGameStats(player.getRole(), difficulty.getMaxTurns(), difficulty.getMultiplier());
        gamestats.setScore(0);
    }

    public void notifyPause() throws GameNotStartedException, GameNotFinishedException {
        gamestats.pauseGameStats();
    }

    public void notifyResume() {
        gamestats.restoreGameStats();
    }

}
