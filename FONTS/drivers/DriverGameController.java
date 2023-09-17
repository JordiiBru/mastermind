package drivers;

import utils.Ball_color;
import utils.Mode;
import utils.Pair;
import utils.ParserColorInt;
import drivers.stubs.*;
import domain.exceptions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DriverGameController {

    public static void main(String[] args) throws Exception {
        welcomeMsg();
        while (true) {
            int option = inputOption(25);
            int option2;
            switch (option) {
                case 0:
                    showOptions(0);
                    break;
                case 1: //GameController
                    showOptions(1);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(GameControllerTest());
                    break;
                case 2: //setDifficultyToNormal
                    showOptions(2);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(setDifficultyToNormalTest());
                    break;
                case 3: //setDifficultyToHard
                    showOptions(3);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(setDifficultyToHardTest());
                    break;
                case 4://setDifficultyToCustom
                    showOptions(4);
                    option2 = inputOption(2);
                    switch (option2) {
                        case 0:
                            System.out.println(setDifficultyToCustomTest(false));
                            break;
                        case 1:
                            System.out.println(setDifficultyToCustomTest(true));
                            break;
                    }
                    break;
                case 5: //startGame
                    showOptions(5);
                    option2 = inputOption(2);
                    switch (option2) {
                        case 0:
                            System.out.println(startGameTest(false));
                            break;
                        case 1:
                            System.out.println(startGameTest(true));
                            break;
                    }
                    break;
                case 6: //getPlayerName
                    showOptions(6);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getPlayerNameTest());
                    break;
                case 7: //getGameStats
                    showOptions(7);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getGameStatsTest());
                    break;
                case 8: //getScore
                    showOptions(8);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getScoreTest());
                    break;
                case 9: //getTurn
                    showOptions(9);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getTurnTest());
                    break;
                case 10://getMaxTurns
                    showOptions(10);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getMaxTurnsTest());
                    break;
                case 11: //getBoard
                    showOptions(11);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getBoardTest());
                    break;
                case 12: //getBoardFeedback
                    showOptions(12);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getBoardFeedbackTest());
                    break;
                case 13: //getFeedback
                    showOptions(13);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getFeedbackTest());
                    break;
                case 14: //getPlayerCurrentCode
                    showOptions(14);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getPlayerCurrentCodeTest());
                    break;
                case 15: //getSecretCode
                    showOptions(15);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getSecretCodeTest());
                    break;
                case 16: //getElapsedTime
                    showOptions(16);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(getElapsedTimeTest());
                    break;
                case 17: //newColor
                    showOptions(17);
                    option2 = inputOption(2);
                    switch (option2) {
                        case 0:
                            System.out.println(newColorTest(false));
                            break;
                        case 1:
                            System.out.println(newColorTest(true));
                            break;
                    }
                    break;
                case 18: //cleanCode
                    showOptions(18);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(cleanCodeTest());
                    break;
                case 19: //confirmCode
                    showOptions(19);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(confirmCodeTest());
                    break;
                case 20: //checkIfGameWon
                    showOptions(20);
                    option2 = inputOption(2);
                    switch (option2) {
                        case 0:
                            System.out.println(checkIfGameWonTest(4, false));
                            break;
                        case 1:
                            System.out.println(checkIfGameWonTest(0, true));
                            break;
                    }
                    break;
                case 21: //setBoardToComputerSolution
                    showOptions(21);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(setBoardToComputerSolutionTest());
                    break;
                case 22: //newSecretCodeComputer
                    showOptions(22);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(newSecretCodeComputerTest());
                    break;
                case 23: //desiredHint
                    showOptions(23);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(desiredHintTest());
                    break;
                case 24: //surrender
                    showOptions(24);
                    option2 = inputOption(1);
                    if (option2 == 0) System.out.println(surrenderTest());
                    break;
                case 25: //Quit
                    showOptions(25);
                    System.exit(0);
                    break;
            }
        }
    }

    //TestManagers
    private static String GameControllerTest() {
        return "New GameController instance created successfully." + " \n";
    }

    private static String setDifficultyToNormalTest() {
        DifficultyNormalStub normal = new DifficultyNormalStub();
        return normal.DifficultyNormal() + " \n";
    }

    private static String setDifficultyToHardTest() {
        DifficultyHardStub hard = new DifficultyHardStub();
        return hard.DifficultyHard() + " \n";
    }

    private static String setDifficultyToCustomTest(boolean input) {
        DifficultyCustomStub custom = new DifficultyCustomStub();
        if (!input) return (custom.DifficultyCustom() + " \n" +
                            custom.setAlgCus(true) + " \n" +
                            custom.setRepColCus(true) + " \n" +
                            custom.setNumPosCus(7) + " \n" +
                            custom.setEmptyColCus(true) + " \n" +
                            custom.setMaxTurCus(1) + " \n" +
                            custom.setMulCus() + " \n");
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Introduce a value for algorithm:");
            System.out.println("boolean: true / false");
            boolean alg = scan.nextBoolean();
            System.out.println("Introduce a value for repeated_colors:");
            System.out.println("boolean: true / false");
            boolean rep_col = scan.nextBoolean();
            System.out.println("Introduce a value for number_positions:");
            System.out.println("int: [1,7]");
            int num_pos = scan.nextInt();
            System.out.println("Introduce a value for empty_colors:");
            System.out.println("boolean: true / false");
            boolean emp_col = scan.nextBoolean();
            System.out.println("Introduce a value for max_turns:");
            System.out.println("int: [1,10]");
            int max_tur = scan.nextInt();
            return (custom.DifficultyCustom() + " \n" +
                    custom.setAlgCus(alg) + " \n" +
                    custom.setRepColCus(rep_col) + " \n" +
                    custom.setNumPosCus(num_pos) + " \n" +
                    custom.setEmptyColCus(emp_col) + " \n" +
                    custom.setMaxTurCus(max_tur) + " \n" +
                    custom.setMulCus() + " \n");
        }
    }

    private static String startGameTest(boolean input) {
        PlayerStub player = new PlayerStub();
        CodeMakerComputerStub cmcomputer = new CodeMakerComputerStub();
        FiveGuessComputerStub fgcomputer = new FiveGuessComputerStub();
        BoardStub board = new BoardStub();
        FeedbackStub feedback = new FeedbackStub();
        GameStatsStub gamestats = new GameStatsStub();
        String ret;

        if (!input) {
            ret = player.Player("javier_was_here") + " \n";
            ret += player.setRole(Mode.CodeBreaker) + " \n";
            ret += player.initCode(4) + " \n";
            ret += cmcomputer.CodeMakerComputer(4, false, false) + " \n";
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Introduce a value for player_name:");
            System.out.println("String: ");
            String pla_nam = scan.next();
            System.out.println("Introduce a value for player_role:");
            System.out.println("Mode: 0 = CodeMaker / 1 = CodeBreaker");
            int pla_rol = scan.nextInt();
            ret = player.Player(pla_nam) + " \n";
            if (pla_rol == 0) ret += player.setRole(Mode.CodeMaker) + " \n";
            else ret += player.setRole(Mode.CodeBreaker) + " \n";
            ret += player.initCode(4) + " \n";
            if (pla_rol == 0) ret += fgcomputer.FiveGuessComputer(2, 4, false, false) + " \n";
            else ret += cmcomputer.CodeMakerComputer(4, false, false) + " \n";
        }
        ret += board.Board(4, 2) + " \n";
        ret += feedback.Feedback() + " \n";
        ret += gamestats.GameStats() + " \n";
        return ret;
    }

    private static String getPlayerNameTest() {
        PlayerStub player = new PlayerStub();
        return player.getName() + " \n";
    }

    private static String getGameStatsTest() {
        return "this->gamestats returned successfully. \n";
    }

    private static String getScoreTest() {
        GameStatsStub gameStats = new GameStatsStub();
        return gameStats.getScore() + " \n";
    }

    private static String getTurnTest() {
        GameStatsStub gameStats = new GameStatsStub();
        return gameStats.getNumTurn() + " \n";
    }

    private static String getMaxTurnsTest() {
        DifficultyNormalStub normal = new DifficultyNormalStub();
        return normal.getMaxTurns() + " \n";
    }

    private static String getBoardTest() {
        BoardStub board = new BoardStub();
        return Arrays.deepToString(board.getBoard()) + " \n";
    }

    private static String getBoardFeedbackTest() {
        BoardStub board = new BoardStub();
        Pair<Integer,Integer>[] fb = board.getFeedback();
        String ret = fb[0].first() + " " + fb[0].second() + " \n";
        ret += fb[1].first() + " " + fb[1].second() + " \n";
        ret += fb[2].first() + " " + fb[2].second() + " \n";
        ret += fb[3].first() + " " + fb[3].second() + " \n";
        return ret;
    }

    private static String getFeedbackTest() {
        BoardStub board = new BoardStub();
        Pair<Integer,Integer> fb = board.getCurrentFeedback(0);
        return (fb.first() + " " + fb.second() + " \n");
    }

    private static String getPlayerCurrentCodeTest() {
        PlayerStub player = new PlayerStub();
        return Arrays.toString(player.getCurrentCode()) + " \n";
    }

    private static String getSecretCodeTest() {
        FeedbackStub feedback = new FeedbackStub();
        return Arrays.toString(feedback.getSecretCode()) + " \n";
    }

    private static String getElapsedTimeTest() {
        GameStatsStub gameStats = new GameStatsStub();
        return String.valueOf(gameStats.getElapsedTime()) + " \n";
    }

    private static String newColorTest(boolean input) {
        PlayerStub player = new PlayerStub();
        if (!input) {
            return player.addColor(0, Ball_color.Red) + " \n";
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Introduce a value for position:");
            System.out.println("int: [0,6]");
            int pos = scan.nextInt();
            System.out.println("Introduce a value for color:");
            System.out.println("Ball_color: Red / Green / Blue / Yellow / Purple / Orange / Pink / White / Empty");
            String color = scan.next();
            Ball_color ball = null;
            switch (color) {
                case "Red":
                    ball = Ball_color.Red;
                    break;
                case "Green":
                    ball = Ball_color.Green;
                    break;
                case "Blue":
                    ball = Ball_color.Blue;
                    break;
                case "Yellow":
                    ball = Ball_color.Yellow;
                    break;
                case "Purple":
                    ball = Ball_color.Purple;
                    break;
                case "Orange":
                    ball = Ball_color.Orange;
                    break;
                case "Pink":
                    ball = Ball_color.Pink;
                    break;
                case "White":
                    ball = Ball_color.White;
                    break;
                case "Empty":
                    ball = Ball_color.Empty;
                    break;
            }
            return player.addColor(pos, ball) + " \n";
        }
    }

    private static String cleanCodeTest() {
        PlayerStub player = new PlayerStub();
        return (player.cleanCode() + " \n");
    }

    private static String confirmCodeTest() {
        String ret;
        PlayerStub player = new PlayerStub();
        BoardStub board = new BoardStub();
        GameStatsStub gamestats = new GameStatsStub();
        FeedbackStub feedback = new FeedbackStub();
        DifficultyHardStub difficulty = new DifficultyHardStub();

        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce a value for player_role:");
        System.out.println("Mode: 0 = CodeMaker / 1 = CodeBreaker");
        Mode p_getrole = Mode.CodeBreaker;
        if (scan.nextInt() == 0) p_getrole = Mode.CodeMaker;

        Ball_color[] code = player.confirmedCode(difficulty.getRepeatedColors(), difficulty.getEmptyColors());
        ret = "Code confirmed in player: " + Arrays.toString(code) + " \n";
        if (p_getrole == Mode.CodeBreaker) {
            ret += board.updateRowBoard(code, gamestats.getNumTurn()) + " \n";
            Pair<Integer,Integer> diff = feedback.compareSecretCode(code);
            ret += board.updateFeedback(gamestats.getNumTurn(), diff) + " \n";
            ret += player.cleanCode() + " \n";
            return ret + "checkIfGameWon return: \n" + checkIfGameWonTest(diff.first(), false);
        }
        else {
            ret += feedback.setSecretCode(code) + " \n";
            ret += gamestats.startGameStats() + " \n";
            ret += "Call to function setBoardToComputerSolution() \n";
            String end = setBoardToComputerSolutionTest();
            ret += gamestats.finishGameStats(player.getRole(),difficulty.getMaxTurns(), difficulty.getMultiplier()) + " \n";
            ret += player.cleanCode() + " \n";
            return ret + "setBoardToComputerSolution return:" + " \n" + end;
        }
    }

    private static String checkIfGameWonTest(int first, boolean input) {
        String ret;
        DifficultyHardStub difficulty = new DifficultyHardStub();
        GameStatsStub gamestats = new GameStatsStub();
        PlayerStub player = new PlayerStub();

        if (!input) {
            if (first == difficulty.getNumberPositions()) {
                ret = gamestats.finishGameStats(player.getRole(), difficulty.getMaxTurns(), difficulty.getMultiplier()) + " \n";
                return ret + "return: " + true + " \n";
            } else {
                ret = gamestats.addTurn(difficulty.getMaxTurns()) + " \n";
                return ret + "return: " + false + " \n";
            }
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Introduce a value for first: ");
            System.out.println("int: [0,7]");
            first = scan.nextInt();
            if (first == difficulty.getNumberPositions()) {
                ret = gamestats.finishGameStats(player.getRole(), difficulty.getMaxTurns(), difficulty.getMultiplier()) + " \n";
                return ret + "return: " + true + " \n";
            } else {
                ret = gamestats.addTurn(difficulty.getMaxTurns()) + " \n";
                return ret + "return: " + false + " \n";
            }
        }
    }

    private static String setBoardToComputerSolutionTest() {
        String ret;
        FeedbackStub feedback = new FeedbackStub();
        FiveGuessComputerStub computer = new FiveGuessComputerStub();
        BoardStub board = new BoardStub();
        GameStatsStub gamestats = new GameStatsStub();
        DifficultyNormalStub difficulty = new DifficultyNormalStub();

        Ball_color[] secret_code = feedback.getSecretCode();
        ret = "secret_code given by Feedback: " + Arrays.toString(secret_code)  + " \n";
        List<Integer> secretParser = ParserColorIntStub.ballColorArrayToIntList(secret_code);
        List<List<Integer>> computer_solution = computer.solve(secretParser);
        ret += "solution given by Computer: " + computer_solution + " \n";
        Ball_color[][] solution = ParserColorInt.intListListToBallColorMatrix(computer_solution);
        for (int i = 0; i < solution.length; ++i) {
            ret += board.updateRowBoard(solution[i], gamestats.getNumTurn()) + " \n";
            Pair<Integer,Integer> diff = feedback.compareSecretCode(solution[i]);
            ret += board.updateFeedback(gamestats.getNumTurn(), diff) + " \n";
            if (i < solution.length - 1) ret += gamestats.addTurn(difficulty.getMaxTurns()) + " \n";
            else if (diff.first() == difficulty.getNumberPositions()) return ret + "return: " + true + " \n";
        }
        return ret + "return: " + false + " \n";
    }

    private static String newSecretCodeComputerTest() {
        String ret;
        CodeMakerComputerStub computer = new CodeMakerComputerStub();
        FeedbackStub feedback = new FeedbackStub();
        GameStatsStub gamestats = new GameStatsStub();

        List<List<Integer>> secretCode = computer.solve(null);
        ret = "Secret computer code given: " + secretCode.toString() + ". \n";
        Ball_color[][] ballColorMatrix = ParserColorIntStub.intListListToBallColorMatrix(secretCode);
        ret += "Secret parser code given: " + Arrays.toString(ballColorMatrix[0]) + ". \n";
        ret += feedback.setSecretCode(ballColorMatrix[0]) + " \n";
        ret += gamestats.startGameStats() + " \n";

        return ret;
    }

    private static String desiredHintTest() {
        String ret;
        FeedbackStub feedback = new FeedbackStub();
        GameStatsStub gamestats = new GameStatsStub();
        PlayerStub player = new PlayerStub();

        Pair<Integer,Ball_color> hint = feedback.askHint();
        ret = "Hint given: " + hint.first() + " " + hint.second() + " \n";
        ret += gamestats.addHintUsed() + " \n";
        ret += player.addColor(hint.first(), hint.second()) + " \n";

        return  ret;
    }

    private static String surrenderTest() {
        GameStatsStub gamestats = new GameStatsStub();
        PlayerStub player = new PlayerStub();
        DifficultyNormalStub difficulty = new DifficultyNormalStub();

        return gamestats.finishGameStats(player.getRole(), difficulty.getMaxTurns(), difficulty.getMultiplier()) + " \n";
    }

    //Input
    private static int inputOption(int end_value) throws InvalidStartingOptionException {
        System.out.println("Choose an option:");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option < 0 || option > end_value) {
            throw new InvalidStartingOptionException();
        }
        return option;
    }

    //Prints
    private static void welcomeMsg() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to the testing driver of the GameController class.");
        System.out.println("Choose an option between 0 to 25 to navigate through the methods.");
        System.out.println("Here you have the possible options:");
        showOptions(0);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void showOptions(int options) {
        switch (options) {
            case 0: //Options
                System.out.println("  -> 00. Show possible options");
                System.out.println("  -> 01. GameController");
                System.out.println("  -> 02. setDifficultyToNormal");
                System.out.println("  -> 03. setDifficultyToHard");
                System.out.println("  -> 04. setDifficultyToCustom");
                System.out.println("  -> 05. startGame");
                System.out.println("  -> 06. getPlayerName");
                System.out.println("  -> 07. getGameStats");
                System.out.println("  -> 08. getScore");
                System.out.println("  -> 09. getTurn");
                System.out.println("  -> 10. getMaxTurns");
                System.out.println("  -> 11. getBoard");
                System.out.println("  -> 12. getBoardFeedback");
                System.out.println("  -> 13. getFeedback");
                System.out.println("  -> 14. getPlayerCurrentCode");
                System.out.println("  -> 15. getSecretCode");
                System.out.println("  -> 16. getElapsedTime");
                System.out.println("  -> 17. newColor");
                System.out.println("  -> 18. cleanCode");
                System.out.println("  -> 19. confirmCode");
                System.out.println("  -> 20. checkIfGameWon");
                System.out.println("  -> 21. setBoardToComputerSolution");
                System.out.println("  -> 22. newSecretCodeComputer");
                System.out.println("  -> 23. desiredHint");
                System.out.println("  -> 24. surrender");
                System.out.println("  -> 25. Quit driver");
                break;
            case 1: //GameController
                System.out.println("GameController: ");
                System.out.println("parameters: ");
                System.out.println("return: GameController");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 2: //setDifficultyToNormal
                System.out.println("setDifficultyToNormal: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 3: //setDifficultyToHard
                System.out.println("setDifficultyToHard: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 4: //setDifficultyToCustom
                System.out.println("setDifficultyToCustom: ");
                System.out.println("parameters: boolean algorithm, boolean repeated_colors,");
                System.out.println("            int number_positions, boolean empty_colors,");
                System.out.println("            int max_turns");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Add manual input");
                System.out.println("  -> 02. Go Back");
                break;
            case 5: //startGame
                System.out.println("startGame: ");
                System.out.println("parameters: String player_name, Mode player_role");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Add manual input");
                System.out.println("  -> 02. Go Back");
                break;
            case 6: //getPlayerName
                System.out.println("getPlayerName: ");
                System.out.println("parameters: ");
                System.out.println("return: String");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 7: //getGameStats
                System.out.println("getGameStats: ");
                System.out.println("parameters: ");
                System.out.println("return: GameStats");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 8: //getScore
                System.out.println("getScore: ");
                System.out.println("parameters: ");
                System.out.println("return: int");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 9: //getTurn
                System.out.println("getTurn: ");
                System.out.println("parameters: ");
                System.out.println("return: int");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 10://getMaxTurns
                System.out.println("getMaxTurns: ");
                System.out.println("parameters: ");
                System.out.println("return: int");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 11: //getBoard
                System.out.println("getBoard: ");
                System.out.println("parameters: ");
                System.out.println("return: Ball_color[][]");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 12: //getBoardFeedback
                System.out.println("getBoardFeedback: ");
                System.out.println("parameters: ");
                System.out.println("return: Pair<Integer,Integer>[]");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 13: //getFeedback
                System.out.println("getFeedback: ");
                System.out.println("parameters: ");
                System.out.println("return: Pair<Integer,Integer>[]");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 14: //getPlayerCurrentCode
                System.out.println("getPlayerCurrentCode: ");
                System.out.println("parameters: ");
                System.out.println("return: Ball_color[]");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 15: //getSecretCode
                System.out.println("getSecretCode: ");
                System.out.println("parameters: ");
                System.out.println("return: Ball_color[]");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 16: //getElapsedTime
                System.out.println("getElapsedTime: ");
                System.out.println("parameters: ");
                System.out.println("return: int");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 17: //newColor
                System.out.println("newColor: ");
                System.out.println("parameters: int position, Ball_color color");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Add manual input");
                System.out.println("  -> 02. Go Back");
                break;
            case 18: //cleanCode
                System.out.println("cleanCode: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 19: //confirmCode
                System.out.println("confirmCode: ");
                System.out.println("parameters: ");
                System.out.println("return: boolean");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 20: //checkIfGameWon
                System.out.println("checkIfGameWon: ");
                System.out.println("parameters: int first");
                System.out.println("return: boolean");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Add manual input");
                System.out.println("  -> 02. Go Back");
                break;
            case 21: //setBoardToComputerSolution
                System.out.println("setBoardToComputerSolution: ");
                System.out.println("parameters: ");
                System.out.println("return: boolean");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 22: //newSecretCodeComputer
                System.out.println("newSecretCodeComputer: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 23: //desiredHint
                System.out.println("desiredHint: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 24: //surrender
                System.out.println("surrender: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 00. Add automatic input");
                System.out.println("  -> 01. Go Back");
                break;
            case 25: //Quit
                System.out.println("Goodbye :)");
            break;
        }
    }

}