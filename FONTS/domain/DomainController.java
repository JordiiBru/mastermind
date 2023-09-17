package domain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Map;

import domain.exceptions.*;
import persistence.PersistenceController;
import utils.*;

/**
 * @author jordi
 * Domain controller of the game. It will be in charge of the communication between Presentation and Persistance.
 * Domain controller will communicate with the GameController to make sure the game works.
 */
public class DomainController {
    private Records records;
    private Ranking ranking;
    private final GameController game;
    private final PersistenceController persistence;

    public DomainController(){
        game = new GameController();
        persistence = new PersistenceController();
        records = new Records();
        ranking =new Ranking();
    }

    public void createRecords() {
        records = new Records();
        records.initRecords();
    }

    public void createRanking() {
        ranking = new Ranking();
    }

    // notifiers

    /**
     * Presentation gives the necessary information to create a new player.
     * @param playerName player name given by presentation
     * @param role string of the desired role
     * @throws DifficultyMaxTurnsMismatchException exceptions from the game controller
     * @throws DifficultyNumberPositionsMismatchException exceptions from the game controller
     * @throws InvalidDimensionBoardException exceptions from the game controller
     * @throws PositionOutOfBoundsException exceptions form the game controller
     */
    public void notifyNewPlayer(String playerName, String role) throws Exception {
        Mode playerMode;
        if(role == "CB")  playerMode = Mode.CodeBreaker;
        else playerMode = Mode.CodeMaker;
        game.startGame(playerName,playerMode);
    }

    /**
     * Presentation asks domain to set a normal game
     */
    public void notifyNormal() {
        game.setDifficultyToNormal();
    }

    /**
     * Presentation asks domain to set a hard game
     */
    public void notifyHard() {
        game.setDifficultyToHard();
    }

    /**
     * Presentation asks domain to set a custom game with desired parameters
     * @param algorithm true: 5-guess, false: Genetic
     * @param repeated_colors allowing repeated colors (boolean)
     * @param number_positions number of balls in a code
     * @param empty_colors allowing empty colors
     * @param max_turns number of maximum turns in the game
     * @throws DifficultyMaxTurnsMismatchException exceptions from the game controller
     * @throws DifficultyNumberPositionsMismatchException exceptions from the game controller
     */
    public void notifyCustom(boolean algorithm,boolean repeated_colors, int number_positions, boolean empty_colors, int max_turns) throws Exception {
        game.setDifficultyToCustom(algorithm,repeated_colors,number_positions,empty_colors,max_turns);
    }

    /**
     * Notification to update records with the finished game stats
     */
    public void notifyUpdateRecords() {
        records.updateRecords(game.getPlayerName(), game.getScore(), game.getElapsedTime(), game.getTurn());
    }

    /**
     * Notification to update the Code maker ranking of a won game stats
     * @param win boolean describing if the game is won or not
     * @throws InvalidPlayerNameException exceptions from the game controller
     */
    public void notifyUpdateRankingCM(boolean win) throws Exception {
        //if(win){
            ranking.updateRankingCM(game.getPlayerName(), game.getScore());
        //}
    }

    /**
     * Notification to update the Code breaker ranking of a won game stats
     * @param win boolean describing if the game is won or not
     * @throws InvalidPlayerNameException exceptions from the game controller
     */
    public void notifyUpdateRankingCB(boolean win) throws Exception {
        if (win) {
            ranking.updateRankingCB(game.getPlayerName(), game.getScore());
        }
    }

    public int getScore() {
        return game.getScore();
    }

    /**
     * Presentation chooses where to place a new color in the code
     * @param position desired position to place the new ball
     * @param color desired color of the new ball
     * @throws PositionOutOfBoundsException when position does not belong in board bounding
     */
    public void notifyNewColor(int position, String color) throws Exception { // returns a pair of color and position
        color = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
        game.newColor(position,ParserPersistence.parseColor(color));
    }


    /**
     * Notification from Presentation to confirm the code of the actual turn
     * @return TRUE if the confirmed code is the secret code; FALSE if the confirmed code is NOT the secret code
     * @throws NotRepeatedColorsException exceptions from the game controller
     * @throws NotEmptyColorsException exceptions from the game controller
     * @throws InvalidCodeSizeException exceptions from the game controller
     * @throws FeedbackSecretCodeLengthMismatchException exceptions from the game controller
     * @throws PositionOutOfBoundsFeedbackException exceptions from the game controller
     * @throws GameNotStartedException exceptions from the game controller
     * @throws InvalidTurnIncrementException exceptions from the game controller
     * @throws VariableNotDefinedException exceptions from the game controller
     * @throws GameNotFinishedException exceptions from the game controller
     * @throws Exception exceptions from the game controller
     */
    public boolean notifyConfirmCode() throws Exception {
        return game.confirmCode();
    }

    /**
     * Notification from Presentation to clean the code of the actual turn
     * @throws PositionOutOfBoundsException exceptions from the game controller
     */
    public void notifyCleanCode() throws Exception {
        game.cleanCode();
    }

    /**
     * Notification from Presentation to ask for a hint.
     * Will be stored in player current code
     * @throws FeedbackHintIndexOutOfBoundsException exceptions from the game controller
     * @throws PositionOutOfBoundsException exceptions from the game controller
     */
    public void notifyAskForHint() throws Exception {
        game.desiredHint();
    }

    /**
     * Notification from Presentation to surrender and immediately lose
     * @throws GameNotStartedException exceptions from the game controller
     * @throws VariableNotDefinedException exceptions from the game controller
     * @throws GameNotFinishedException exceptions from the game controller
     */
    public void notifySurrender() throws Exception {
        game.surrender();
        persistence.removeGame();
    }

    /**
     * Notification from Presentation to set a secret code when the player is code breaker.
     * Computer will set this secret code randomly.
     * @throws Exception exceptions from the game controller
     */
    public void notifySetComputerSecretCode() throws Exception {
        game.newSecretCodeComputer();
    }

    // getters

    /**
     * Method to be called in presentation to get the actual records
     * @return a map with all th records
     */
    public Map<String, Pair<String, Integer>> getDomainRecords() {
        return this.records.getRecordsMap();
    }

    /**
     * Method to be called in presentation to get the actual code maker ranking
     * @return an arraylist with the ranking
     */
    public ArrayList<Pair<String, Integer>> getDomainRankingCM() {
        return ranking.getRankingCM();
    }

    /**
     * Method to be called in presentation to get the actual code breaker ranking
     * @return an arraylist with the ranking
     */
    public ArrayList<Pair<String,Integer>> getDomainRankingCB() {
        return ranking.getRankingCB();
    }

    /**
     * Check the maximum turns allowed
     * @return maximum turns
     */
    public int getGameMaxTurns() {
        return game.getMaxTurns();
    }

    /**
     * Ask the current state of the board, so it can print it
     * @return board of the game
     */
    public Ball_color[][] getGameBoard() {
        return game.getBoard();
    }

    /**
     * Asks the game controller for the full feedback state
     * @return Returns an array of Pair<int,int> with the feedback of the board
     */
    public Pair<Integer,Integer>[] getGameFeedbackBoard() {
        return game.getBoardFeedback();
    }

    /**
     * Asks the game controller the last feedback set in the board
     * @return Returns a Pair<int,int> with the last feedback set in Board
     */
    public Pair<Integer,Integer> getPlayerCurrentFeedback() {
        return game.getCurrentFeedback();
    }

    /**
     * Presentation will ask for the current setting code, so it can print it
     * @return current code
     */
    public Ball_color[] getCurrentCode() {
        return game.getPlayerCurrentCode();
    }

    // ------------ TO PERSISTENCE -----------

    /**
     * Ask persistence if there is a saved game.
     * @return true if a saved game exists, false otherwise.
     */
    public boolean savedGameExists() {
        return persistence.existsGame();
    }

    /**
     * Notifier to persistence in order to delete the saved file of the finished game.
     */
    public void notifyEndedGame() {
        persistence.removeGame();
    }

    public int getMaxBalls() {
        return game.getDiffNumPositions();
    }

    public String getPlayerRole() {
        Mode role = game.getPlayerRole();
        if(role.equals(Mode.CodeBreaker)) return "CB";
        else return "CM";
    }

    /**
     * Method that saves all the necessary information of the desired game to persistence.
     */
    public void saveGame2Persistence() throws IOException {
        List<String> list = new ArrayList<>();

        // player info
        list.add(game.getPlayerName());

        // difficulty info
        list.add(String.valueOf(game.checkDiffName()));
        list.add(String.valueOf(game.getDiffAlgorithm()));
        list.add(String.valueOf(game.getDiffRepeated()));
        list.add(String.valueOf(game.getDiffNumPositions()));
        list.add(String.valueOf(game.getDiffEmpty()));
        list.add(String.valueOf(game.getMaxTurns()));

        // game stats info
        list.add(String.valueOf(game.getElapsedTime()));
        list.add(String.valueOf(game.getTurn()));
        list.add(String.valueOf(game.getHintsUsed()));

        // solution info
        Ball_color[] secret_code = game.getSecretCode();
        int i = 0;
        StringBuilder solution = new StringBuilder("{");
        while (i < secret_code.length) {
            solution.append(secret_code[i].toString()).append(":");
            i++;
        }
        list.add(solution.toString());

        // board info
        int numTurn = game.getTurn();

        for (int it = 0; it < game.getBoard().length; it++) {
            Ball_color[] row = game.getBoard()[it];
            StringBuilder boardTurn = new StringBuilder("{");
            for (int j = 0; j < row.length; j++) {
                Ball_color color = row[j];
                String colorString;
                if (it < numTurn-1) {
                    colorString = color.toString();

                } else {
                    colorString = "null";
                }
                if(j+1 == row.length) boardTurn.append(colorString).append("}");
                else boardTurn.append(colorString).append(":");
            }
            list.add(boardTurn.toString());
        }

        // feedback board info
        Pair<Integer, Integer>[] feedback = game.getBoardFeedback();
        for (int it = 0; it < feedback.length; it++) {
            Pair<Integer, Integer> pair = feedback[it];
            String feedbackString;
            if (it < numTurn-1) {
                feedbackString = "{" + pair.first() + ":" + pair.second() + "}";
            } else {
                feedbackString = "{null:null}";
            }
            list.add(feedbackString);
        }

        persistence.saveGame(list);
    }

    /**
     * Method that loads the saved data and creates a game based on that.
     */
    public void loadGameFromPersistence() throws Exception {
        List<String> data = persistence.loadGame();
        int index = 0;

        // player info
        String player_name = data.get(index); ++index;

        // difficulty info
        String diffName = data.get(index); ++index;
        boolean diffAlgorithm = Boolean.parseBoolean(data.get(index)); ++index;
        boolean diffRepeated = Boolean.parseBoolean(data.get(index)); ++index;
        int diffNumPositions = Integer.parseInt(data.get(index)); ++index;
        boolean diffEmpty = Boolean.parseBoolean(data.get(index)); ++index;
        int diffMaxTurns = Integer.parseInt(data.get(index)); ++index;

        // game stats info
        int elapsedTime = Integer.parseInt(data.get(index)); ++index;
        int turn = Integer.parseInt(data.get(index)); ++index;
        int hintsUsed = Integer.parseInt(data.get(index)); ++index;

        // solution info
        String solution = data.get(index); ++index;
        String cropped_solution = solution.substring(1, solution.length()-1); // we remove the { }
        String[] listSolutions = cropped_solution.split(":");
        Ball_color[] secret_code = new Ball_color[listSolutions.length];
        for (int i = 0; i < listSolutions.length; ++i) {
            secret_code[i] = ParserPersistence.parseColor(listSolutions[i]);
        }

        //board info
        Ball_color[][] board = new Ball_color[diffMaxTurns][diffNumPositions];
        for (int i = 0; i < turn-1; ++i) {
            String rowString = data.get(index);
            String[] rowColors = rowString.substring(1, rowString.length() - 1).split(":");
            for (int j = 0; j < diffNumPositions; ++j) {
                board[i][j] = ParserPersistence.parseColor(rowColors[j]);
            }
            ++index;
        }

        index = index + (diffMaxTurns - turn) +1;

        //feedback board info
        Pair<Integer, Integer>[] feedback = new Pair[diffMaxTurns];
        for (int i = 0; i < turn-1; ++i) {
            String feedbackString = data.get(index);
            String[] feedbackValues = feedbackString.substring(1, feedbackString.length() - 1).split(":");
            int first = Integer.parseInt(feedbackValues[0]);
            int second = Integer.parseInt(feedbackValues[1]);
            feedback[i] = new Pair<>(first, second);
            ++index;
        }

        // setters procedure to create a game with the desired state
        game.setSavedDifficulty(diffName,diffAlgorithm,diffRepeated,diffNumPositions,diffEmpty,diffMaxTurns);
        game.setSavedPlayer(player_name);
        game.setSavedSecretCode(secret_code);
        game.setSavedGameStats(elapsedTime,turn,hintsUsed);
        game.setSavedBoardState(board,feedback);
        game.restoreGame();

    }

    /**
     * Save both rankings. It parses both rankings so persistence can understand it. Both rankings will be merge in only one data structure.
     */
    public void saveRankings() throws IOException {
       persistence.saveRankings(ParserPersistence.combineRankings(getDomainRankingCB(),getDomainRankingCM()));
    }

    /**
     * Load both rankings saved in persistence. It parses both data structures in order to treat it.
     */
    public void loadRankings() throws FileNotFoundException {
        Pair<ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> saved_rankings = ParserPersistence.splitRankings(persistence.loadRankings());
        if(saved_rankings.first().size() != 0) ranking.setRankingCB(saved_rankings.first());
        if(saved_rankings.second().size() != 0) ranking.setRankingCM(saved_rankings.second());
        if(saved_rankings.first().size() == 0 && saved_rankings.second().size() == 0) createRanking();
    }

    /**
     * Save the records. It parses it so persistence can understand it.
     */
    public void saveRecords() throws IOException {
        persistence.saveRecords(ParserPersistence.convertMapToList(getDomainRecords()));
    }

    /**
     * Load the records  saved in persistence. It parses the data structures in order to treat it.
     */
    public void loadRecords() throws FileNotFoundException {
        Map<String, Pair<String, Integer>> loaded = ParserPersistence.convertListToMap(persistence.loadRecords());
        records.setRecords(loaded);
    }

    // ------------ TO PRESENTATION -----------

    /**
     * Method to be called in presentation when wants the code maker ranking.
     * @return a list with the code maker ranking
     */
    public List<Map.Entry<String, Integer>> giveRankingCM() {
        return ParserPresentation.convertToEntryList(getDomainRankingCM());
    }

    /**
     * Method to be called in presentation when wants the code breaker ranking.
     * @return a list with the code breaker ranking
     */
    public List<Map.Entry<String, Integer>> giveRankingCB() {
        return ParserPresentation.convertToEntryList(getDomainRankingCB());
    }

    /**
     * Method to be called in presentation when wants the records.
     * @return a map with the records.
     */
    public Map<String, Map.Entry<String, Integer>> giveRecords() {
        return ParserPresentation.convertToEntryMap(getDomainRecords());
    }

    /**
     * Method to be called in presentation when wants the board.
     * @return an array of strings with the board.
     */
    public String[][] giveBoard() {
        return ParserPresentation.convertBoardToString(getGameBoard(),getGameTurn());
    }

    /**
     * Method to be called in presentation when wants the feedback board
     * @return a list with the feedback board
     */
    public List<Map.Entry<Integer,Integer>> giveFeedbackBoard() {
        return ParserPresentation.convertToEntryList(getGameFeedbackBoard(),getGameTurn());
    }

    /**
     * Method called in presentation to update players code
     * @return an array of string with all the colors of the current balls that the player has chosen in that turn
     */
    public String[] giveCurrentCode() {
        Ball_color[] current_balls = getCurrentCode();
        String[] current_code = new String[current_balls.length];
        for(int i = 0; i < current_balls.length; ++i) current_code[i] = current_balls[i].toString();
        return current_code;
    }

    /**
     * Method called in presentation to update feedback of the turn
     * @return a map entry (java pair) with the values of the feedback in the actual turn
     */
    public Map.Entry<Integer,Integer> giveCurrentFeedback() {
        Pair<Integer,Integer> fb = getPlayerCurrentFeedback();
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(fb.first(), fb.second());
        return entry;
    }

    public String[] getSecretCode() {
        return ParserPresentation.convertToStringArray(game.getSecretCode());
    }

    public int getGameTurn() {
        return game.getTurn();
    }

    public void notifyPause() throws Exception {
        game.notifyPause();
    }

    public void notifyResume() {
        game.notifyResume();
    }
}
