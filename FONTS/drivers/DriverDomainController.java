package drivers;

import domain.exceptions.*;
import utils.*;
import drivers.stubs.GameControllerStub;
import drivers.stubs.RankingStub;
import drivers.stubs.RecordsStub;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverDomainController {

    public static void main(String[] args) throws Exception {
        welcomeMsg();
        // life loop of the app
        while (true) {
            int option = inputOption(0, 15);
            int optionMethod;
            switch (option) {
                case 0:
                    showOptions(0);
                    break;
                case 1:
                    showOptions(1);
                    testDomainController();
                    break;
                case 2:
                    showOptions(2);
                    testCreateRanking();
                    break;
                case 3:
                    showOptions(3);
                    testCreateRecords();
                    break;
                case 4:
                    showOptions(4);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyNewPlayer(false);
                            break;
                        case 1:
                            testNotifyNewPlayer(true);
                            break;
                    }
                    break;
                case 5:
                    showOptions(5);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testAskDifficulty(false);
                            break;
                        case 1:
                            testAskDifficulty(true);
                            break;
                    }
                    break;
                case 6:
                    showOptions(6);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyUpdateRecords(false);
                            break;
                        case 1:
                            testNotifyUpdateRecords(true);
                            break;
                    }
                    break;
                case 7:
                    showOptions(7);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyUpdateRankingCM(false);
                            break;
                        case 1:
                            testNotifyUpdateRankingCM(true);
                            break;
                    }
                    break;
                case 8:
                    showOptions(8);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyUpdateRankingCB(false);
                            break;
                        case 1:
                            testNotifyUpdateRankingCB(true);
                            break;
                    }
                    break;
                case 9:
                    showOptions(9);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyNewColor(false);
                            break;
                        case 1:
                            testNotifyNewColor(true);
                            break;
                    }
                    break;
                case 10:
                    showOptions(10);
                    optionMethod = inputOption(0,1);
                    switch (optionMethod) {
                        case 0:
                            testNotifyConfirmCode(false);
                            break;
                        case 1:
                            testNotifyConfirmCode(true);
                            break;
                    }
                    break;
                case 11:
                    showOptions(11);
                    testNotifyCleanCode();
                    break;
                case 12:
                    showOptions(12);
                    testNotifyAskForHint();
                    break;
                case 13:
                    showOptions(13);
                    testNotifySurrender();
                    break;
                case 14:
                    showOptions(14);
                    testNotifySetComputerSecretCode();
                    break;
                case 15:
                    showOptions(15);
                    System.exit(0);
                    break;
            }
        }

    }

    //TestManagers

    public static void testDomainController() {
        RankingStub rankings = new RankingStub();
        RecordsStub records = new RecordsStub();
        System.out.println("New DomainController instance created successfully.");
        System.out.println(rankings.Ranking());
        System.out.println(records.Records());
    }
    public static void testCreateRanking() {
        RankingStub rankings = new RankingStub();
        System.out.println(rankings.Ranking());
    }

    public static void testCreateRecords() {
        RecordsStub records = new RecordsStub();
        System.out.println(records.Records());
    }

    public static void testNotifyNewPlayer(boolean input) throws Exception {
        GameControllerStub gameController = new GameControllerStub();
        if (!input) System.out.println(gameController.startGame("raul", Mode.CodeBreaker));
        else {
            String playerName = askPlayerName();
            int mode = askPlayerMode();
            if (mode == 0) System.out.println(gameController.startGame(playerName, Mode.CodeMaker));
            else System.out.println(gameController.startGame(playerName, Mode.CodeBreaker));
        }
    }

    public static void testAskDifficulty(boolean input) throws InvalidStartingOptionException {
        GameControllerStub gameController = new GameControllerStub();
        System.out.println("Choose the game difficulty:");
        System.out.println("  -> 1. Normal");
        System.out.println("  -> 2. Hard");
        System.out.println("  -> 3. Custom");
        int difficulty = inputOption(1, 3);
        if (difficulty == 1) {
            System.out.println(gameController.setDifficultyToNormal());
        }
        if (difficulty == 2) {
            System.out.println(gameController.setDifficultyToHard());
        }
        if (difficulty == 3) {
            if (!input) System.out.println(gameController.setDifficultyToCustom(false, true, 4, false, 9));
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
                System.out.println(gameController.setDifficultyToCustom(alg,rep_col, num_pos, emp_col, max_tur));
            }
        }
    }

    public static void testNotifyUpdateRecords(boolean input) {
        RecordsStub records = new RecordsStub();
        if (!input) System.out.println(records.updateRecords("RaulTestingUpdateRecords", 5000, 300, 7));
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Introduce a player name with which the records will be updated [String]: ");
            String playerName = scan.nextLine();
            System.out.println("Enter a score [int]: ");
            int playerScore = scan.nextInt();
            System.out.println("Enter the number of seconds the game has lasted [int]: ");
            int num_seconds = scan.nextInt();
            System.out.println("Enter the number of the turn in which the game has ended [int]:");
            int numTurn = scan.nextInt();
            System.out.println(records.updateRecords(playerName, playerScore, num_seconds, numTurn));
        }
    }

    public static void testNotifyUpdateRankingCM(boolean input) {
        RankingStub rankingCM = new RankingStub();
        String name = "RaulTestingUpdateRankingCM";
        int score = 2000;
        if (!input) System.out.println(rankingCM.updateRankingCM(name, score));
        else {
            Scanner scanName = new Scanner(System.in);
            System.out.println("Player name from the CodeMaker ranking entry: ");
            String playerName = scanName.nextLine();
            Scanner scanScore = new Scanner(System.in);
            System.out.println("Score from the CodeMaker ranking entry: ");
            int playerScore = scanScore.nextInt();
            name = playerName;
            score = playerScore;
            System.out.println(rankingCM.updateRankingCM(playerName, playerScore));
        }
        ArrayList<Pair<String, Integer>> ranking = new ArrayList<>();
        ranking.add(new Pair<>(name, score));
        System.out.println("CodeMaker ranking:");
        for (int i = 0; i < ranking.size(); i++) { //Loop that iterates over each element in the rankingCM ArrayList
            Pair<String, Integer> rankingEntry = ranking.get(i); //Gets the Pair<String,Integer> object at the current iteration index (i) from the rankingCM ArrayList
            System.out.println((i + 1) + ". " + rankingEntry.first() + ": " + rankingEntry.second());
        }
    }

    public static void testNotifyUpdateRankingCB(boolean input) {
        RankingStub rankingCB = new RankingStub();
        String name = "RaulTestingUpdateRankingCB";
        int score = 2000;
        if (!input) System.out.println(rankingCB.updateRankingCB(name, score));
        else {
            Scanner scanName = new Scanner(System.in);
            System.out.println("Player name from the CodeBreaker ranking entry: ");
            String playerName = scanName.nextLine();
            Scanner scanScore = new Scanner(System.in);
            System.out.println("Score from the CodeBreaker ranking entry: ");
            int playerScore = scanScore.nextInt();
            name = playerName;
            score = playerScore;
            System.out.println(rankingCB.updateRankingCB(playerName, playerScore));
        }
        ArrayList<Pair<String, Integer>> ranking = new ArrayList<>();
        ranking.add(new Pair<>(name, score));
        System.out.println("CodeBreaker ranking:");
        for (int i = 0; i < ranking.size(); i++) { //Loop that iterates over each element in the rankingCB ArrayList
            Pair<String, Integer> rankingEntry = ranking.get(i); //Gets the Pair<String,Integer> object at the current iteration index (i) from the rankingCB ArrayList
            System.out.println((i + 1) + ". " + rankingEntry.first() + ": " + rankingEntry.second());
        }
    }

    public static void testNotifyNewColor(boolean input) throws InvalidStartingOptionException {
        GameControllerStub gameController = new GameControllerStub();
        int pos = 1;
        Ball_color color = Ball_color.Blue;
        if (!input) System.out.print(gameController.newColor(1, Ball_color.Blue));
        else {
            int posBall = askPosition();
            Ball_color colorBall = askColor();
            pos = posBall;
            color = colorBall;
            System.out.print(gameController.newColor(posBall, colorBall));
        }
        Ball_color[] currentCode = new Ball_color[]{Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
        currentCode[pos] = color;
        printCurrentCode(currentCode);
    }

    private static void testNotifyConfirmCode(boolean input) throws InvalidStartingOptionException {
        GameControllerStub gameController = new GameControllerStub();
        Ball_color[] currentCode = new Ball_color[]{Ball_color.Blue, Ball_color.Red, Ball_color.Orange, Ball_color.Pink};
        if (!input) System.out.print(gameController.confirmCode());
        else {
            System.out.println("Enter a 4-position code [0-3], which will be confirmed: ");
            System.out.println("Position 0: ");
            Ball_color colorBall0 = askColor();
            currentCode[0] = colorBall0;
            System.out.println("Position 1: ");
            Ball_color colorBall1 = askColor();
            currentCode[1] = colorBall1;
            System.out.println("Position 2: ");
            Ball_color colorBall2 = askColor();
            currentCode[2] = colorBall2;
            System.out.println("Position 3: ");
            Ball_color colorBall3 = askColor();
            currentCode[3] = colorBall3;
            System.out.print(gameController.confirmCode());
        }
        int numBalls = currentCode.length;
        System.out.print("               ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("|   " + (numCol) + "   ");
        }
        System.out.print("| \n");
        System.out.print(" Confirmed code: ");
        for (Ball_color ball_color : currentCode) {
            System.out.print("| " + ball_color + " ");
        }
        System.out.println("|\n");
    }

    public static void testNotifyCleanCode() {
        GameControllerStub gameController = new GameControllerStub();
        System.out.print("GameController has been notified that Presentation want to Clean the current code: \n");
        Ball_color[] currentCode = gameController.cleanCode();
        printCurrentCode(currentCode);
        System.out.print("                  Code cleared successfully!\n");
    }

    public static void testNotifyAskForHint() {
        GameControllerStub gameController = new GameControllerStub();
        System.out.print("GameController has been notified that Presentation want to ask for a hint: \n");
        Pair<Integer, Ball_color> hint = gameController.desiredHint();
        System.out.print("It is given as a hint that a " + hint.second() + " ball must be placed in the " + hint.first() + " position \n");
    }

    public static void testNotifySurrender() {
        GameControllerStub gameController = new GameControllerStub();
        System.out.print(gameController.surrender());
    }

    public static void testNotifySetComputerSecretCode() {
        GameControllerStub gameController = new GameControllerStub();
        System.out.print("GameController has been notified that Presentation wants Computer to set a secret code when the player is CodeBreaker: \n");
        Ball_color[] currentCode = gameController.newSecretCodeComputer();

        int numBalls = currentCode.length;
        System.out.print("                               ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("|   " + (numCol) + "   ");
        }
        System.out.print("| \n");
        System.out.print(" Secret code set by Computer: ");
        for (Ball_color ball_color : currentCode) {
            System.out.print("| " + ball_color + " ");
        }
        System.out.println("|\n");
    }

    //Support methods

    public static int inputOption(int first, int second) throws InvalidStartingOptionException {
        System.out.println("Choose an option:");
        Scanner scanInput = new Scanner(System.in);
        int option = scanInput.nextInt();
        if (option < first || option > second) {
            throw new InvalidStartingOptionException();
        }
        return option;
    }

   public static String askPlayerName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your player name:");
        return scan.nextLine();
    }

   public static int askPlayerMode() throws InvalidStartingOptionException {
        System.out.println("Choose game mode:");
        System.out.println("  -> 0. Code maker");
        System.out.println("  -> 1. Code breaker");
        return inputOption(0, 1);
    }

    public static Ball_color askColor() throws InvalidStartingOptionException {
        System.out.println("Enter one of the possible colors: ");
        System.out.println("  Red -> 0 ");
        System.out.println("  Green -> 1 ");
        System.out.println("  Blue -> 2 ");
        System.out.println("  Yellow -> 3 ");
        System.out.println("  Purple -> 4 ");
        System.out.println("  Orange -> 5 ");
        System.out.println("  Pink -> 6 ");
        System.out.println("  White -> 7 ");
        System.out.println("  Empty -> 8 ");
        int option = inputOption(0, 8);
        // return the ball_color parse of the integer input
        return Ball_color.values()[option];
    }

    public static int askPosition() throws InvalidStartingOptionException {
        System.out.println("Enter the position to place the color (0-3): ");
        return inputOption(0, 3);
    }

    public static void printCurrentCode(Ball_color[] currentCode) {
        int numBalls = currentCode.length;

        System.out.print("               ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("|   " + (numCol) + "   ");
        }
        System.out.print("| \n");
        System.out.print(" Current code: ");
        for (Ball_color ball_color : currentCode) {
            System.out.print("| " + ball_color + " ");
        }
        System.out.println("|\n");
    }

    //Prints

    public static void welcomeMsg() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to the testing driver of the DomainController class.");
        System.out.println("Choose an option between 0 to 15 to navigate through the methods.");
        System.out.println("Here you have the possible options:");
        showOptions(0);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static void showOptions(int options) {
        switch (options) {
            case 0: //Driver options
                System.out.println("  -> 0. Show possible options");
                System.out.println("  -> 1. DomainController");
                System.out.println("  -> 2. CreateRanking");
                System.out.println("  -> 3. CreateRecords");
                System.out.println("  -> 4. notifyNewPlayer");
                System.out.println("  -> 5. notifyDifficulty");
                System.out.println("  -> 6. notifyUpdateRecords");
                System.out.println("  -> 7. notifyUpdateRankingCM");
                System.out.println("  -> 8. notifyUpdateRankingCB");
                System.out.println("  -> 9. notifyNewColor");
                System.out.println("  -> 10. notifyConfirmCode");
                System.out.println("  -> 11. notifyCleanCode");
                System.out.println("  -> 12. notifyAskForHint");
                System.out.println("  -> 13. notifySurrender");
                System.out.println("  -> 14. notifySetComputerSecretCode");
                System.out.println("  -> 15. Exit");
                break;
            case 1: //DomainController
                System.out.println("DomainController: ");
                System.out.println("parameters: ");
                System.out.println("return: DomainController");
                break;
            case 2: //createRanking
                System.out.println("createRanking: ");
                System.out.println("parameters: ");
                System.out.println("return: Ranking");
                break;
            case 3: //createRecords
                System.out.println("createRecords: ");
                System.out.println("parameters: ");
                System.out.println("return: Records");
                break;
            case 4: //notifyNewPlayer
                System.out.println("notifyNewPlayer: ");
                System.out.println("parameters: String playerName, Mode playerMode");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 5: //notifyDifficulty
                System.out.println("notifyDifficulty: ");
                System.out.println("parameters: Normal-> void; Hard-> void; Custom-> boolean algorithm, boolean repeated_colors, \n" +
                                   "                                                 int number_positions, boolean empty_colors, int max_turns");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 6: //notifyUpdateRecords
                System.out.println("notifyUpdateRecords: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 7: //notifyUpdateRankingCM
                System.out.println("notifyUpdateRankingCM: ");
                System.out.println("parameters: boolean win");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 8: //notifyUpdateRankingCB
                System.out.println("notifyUpdateRankingCB: ");
                System.out.println("parameters: boolean win");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 9: //notifyNewColor
                System.out.println("notifyNewColor: ");
                System.out.println("parameters: int position, Ball_color color");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 10: //notifyConfirmCode
                System.out.println("notifyConfirmCode: ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                System.out.println("  -> 0. Add automatic input");
                System.out.println("  -> 1. Add manual input");
                break;
            case 11: //notifyCleanCode
                System.out.println("notifyCleanCode ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                break;
            case 12: //notifyAskForHint
                System.out.println("notifyAskForHint ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                break;
            case 13: //notifySurrender
                System.out.println("notifySurrender ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                break;
            case 14: //notifySetComputerSecretCode
                System.out.println("notifySetComputerSecretCode ");
                System.out.println("parameters: ");
                System.out.println("return: void");
                break;
            case 15: //Exit
                System.out.println("See you next time :(");
                break;
        }
    }
}

