/*package drivers;

import domain.DomainController;
import domain.exceptions.*;
import utils.Ball_color;
import utils.Mode;
import utils.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws Exception, InvalidTurnIncrementException {
        DomainController domain = new DomainController();
        welcomeMsg();
        // life loop of the app
        while (true) {
            int option = inputOption(0, 5);
            switch (option) {
                case 0:
                    showOptions(0);
                    break;
                case 1: //see use cases: first we set player name and role. Then we set the difficulty. Ask @javier on the game controller implementation
                    String playerName = askPlayerName();
                    int mode = askPlayerMode();
                    askDifficulty(domain,mode); //see use cases: code maker chooses difficulty? see use cases: code breaker chooses algorithm? NEEDS A CHANGE, probably.
                    // method to play the game. loop to give the domain (e.g. color and position) -> basically the game flow
                    // al these method will have to call the domain. it will be in charge to send everything to the game controller
                    startGameInstance(domain, playerName, mode);
                    break;
                case 2:
                    showRecords(domain);
                    break;
                case 3:
                    showRankingCM(domain);
                    break;
                case 4:
                    showRankingCB(domain);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }

    }

    public static void welcomeMsg() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to our Mastermind game!");
        System.out.println("Choose an option between 0 to 5 to navigate through the game.");
        System.out.println("Here you have the possible options:");
        showOptions(0);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static void showOptions(int options) {
        switch (options) {
            case 0:
                System.out.println("  -> 0. Show possible options");
                System.out.println("  -> 1. Play Mastermind");
                System.out.println("  -> 2. See records");
                System.out.println("  -> 3. See Code Maker ranking");
                System.out.println("  -> 4. See Code Breaker ranking");
                System.out.println("  -> 5. Quit app");
                break;
            case 1:
                System.out.println("Game actions: ");
                System.out.println("  -> 0. Show possible options");
                System.out.println("  -> 1. Add color");
                System.out.println("  -> 2. Clean code");
                System.out.println("  -> 3. Confirm code");
                System.out.println("  -> 4. Quit");
                break;
            case 2:
                System.out.println("Game actions: ");
                System.out.println("  -> 0. Show possible options");
                System.out.println("  -> 1. Add color");
                System.out.println("  -> 2. Clean code");
                System.out.println("  -> 3. Confirm code");
                System.out.println("  -> 4. Ask for a hint");
                System.out.println("  -> 5. Surrender");
                System.out.println("  -> 6. Quit");
                break;
        }
    }

    public static String askPlayerName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your player name:");
        return scan.nextLine();
    }

    public static int askPlayerMode() throws InvalidStartingOptionException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose game mode:");
        System.out.println("  -> 1. Code maker");
        System.out.println("  -> 2. Code breaker");
        int mode = inputOption(1, 2);
        return mode;
    }

    public static void askDifficulty(DomainController domain, int mode) throws InvalidStartingOptionException, DifficultyMaxTurnsMismatchException, DifficultyNumberPositionsMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose the game difficulty:");
        System.out.println("  -> 1. Normal");
        System.out.println("  -> 2. Hard");
        System.out.println("  -> 3. Custom");
        int option = inputOption(1, 3);
        if (option == 1) domain.notifyNormal();
        if (option == 2) domain.notifyHard();
        if (option == 3) askCustomSettings(domain,mode);
    }

    public static void askCustomSettings(DomainController domain, int mode) throws InvalidStartingOptionException, DifficultyMaxTurnsMismatchException, DifficultyNumberPositionsMismatchException {
        Scanner scan = new Scanner(System.in);

        //setting default parameters
        boolean algorithm = false;
        boolean repCol = false;
        int numPos = 4;
        boolean emptyCol = false;
        int maxTurns = 8;

        System.out.println("You have selected the custom difficulty!");
        System.out.println("Choose your preferences:");

        int tmp_option;
        if (mode == 1) {
            System.out.println("  * Algorithm: 0 (Genetic) or 1 (Five-guess)");
            tmp_option = scan.nextInt();
            if (tmp_option == 1) algorithm = true;
            else if (tmp_option != 0) throw new InvalidStartingOptionException();
        }

        System.out.println("  * Allowing repeated colors: 0 (No) or 1 (Yes)");
        tmp_option = scan.nextInt();
        if (tmp_option == 1) repCol = true;
        else if (tmp_option != 0) throw new InvalidStartingOptionException();

        System.out.println("  * Length of the code: 2 - 7");
        numPos = scan.nextInt();
        if (numPos < 2 || numPos > 7) throw new InvalidStartingOptionException();

        System.out.println("  * Allowing empty colors: 0 (No) or 1 (Yes)");
        tmp_option = scan.nextInt();
        if (tmp_option == 1) emptyCol = true;
        else if (tmp_option != 0) throw new InvalidStartingOptionException();

        System.out.println("  * Number of maximum turns: 1 - 10");
        maxTurns = scan.nextInt();
        if (maxTurns < 1 || maxTurns > 10) throw new InvalidStartingOptionException();

        domain.notifyCustom(algorithm, repCol, numPos, emptyCol, maxTurns);
    }

    public static int inputOption(int first, int second) throws InvalidStartingOptionException {
        System.out.println("Choose an option:");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option < first || option > second) {
            throw new InvalidStartingOptionException();
        }
        return option;
    }

    public static void showRecords(DomainController domain) {
        System.out.println("Records:");
        for (String key : domain.getDomainRecords().keySet()) {
            Pair<String, Integer> record = domain.getDomainRecords().get(key);
            if (record.first() == null) System.out.println("> " + key + ": " + "- " + "-");
            else System.out.println("- " + key + ": " + record.first() + " [" + record.second() + "]");
        }
    }

    public static void showRankingCM(DomainController domain) {
        System.out.println("Code Maker ranking:");
        ArrayList<Pair<String, Integer>> rankingCM = domain.getDomainRankingCM();
        if (rankingCM.size() == 0) {  //Checks if the rankingCM ArrayList is empty
            System.out.println("> The ranking is empty.");
        }
        for (int i = 0; i < rankingCM.size(); i++) { //Loop that iterates over each element in the rankingCM ArrayList
            Pair<String, Integer> ranking = rankingCM.get(i); //Gets the Pair<String,Integer> object at the current iteration index (i) from the rankingCM ArrayList
            System.out.println((i + 1) + ". " + ranking.first() + ": " + ranking.second());
        }
    }

    public static void showRankingCB(DomainController domain) {
        System.out.println("Code Breaker ranking:");
        ArrayList<Pair<String, Integer>> rankingCB = domain.getDomainRankingCB();
        if (rankingCB.size() == 0) { //Checks if the rankingCB ArrayList is empty
            System.out.println("> The ranking is empty.");
        }
        for (int i = 0; i < rankingCB.size(); i++) {  //Loop that iterates over each element in the rankingCB ArrayList
            Pair<String, Integer> ranking = rankingCB.get(i); //Gets the Pair<String,Integer> object at the current iteration index (i) from the rankingCB ArrayList
            System.out.println((i + 1) + ". " + ranking.first() + ": " + ranking.second());
        }
    }


    public static Ball_color askColor() throws InvalidStartingOptionException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter one of the possible colors: ");
        System.out.println("  Red -> 1 ");
        System.out.println("  Green -> 2 ");
        System.out.println("  Blue -> 3 ");
        System.out.println("  Yellow -> 4 ");
        System.out.println("  Purple -> 5 ");
        System.out.println("  Orange -> 6 ");
        System.out.println("  Pink -> 7 ");
        System.out.println("  White -> 8");
        System.out.println("  Empty -> 9 ");
        int option = inputOption(1, 9);
        // return the ball_color parse of the integer input
        return Ball_color.values()[option - 1];
    }

    public static int askPosition() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the position to place the color: ");

        int position = scan.nextInt();
        return position;
    }

    public static void printCurrentCode(DomainController domain) {
        Ball_color[] currentCode = domain.getCurrentCode();
        int numBalls = currentCode.length;

        System.out.print("               ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("|   " + (numCol) + "   ");
        }
        System.out.print("| \n");
        System.out.print(" Current code: ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("| " + currentCode[numCol] + " ");
        }
        System.out.println("|\n");
    }

    public static void printGame(DomainController domain) {
        Ball_color[][] gameBoard = domain.getGameBoard();
        Pair<Integer, Integer>[] feedback = domain.getGameFeedbackBoard();
        int numTurnsMax = gameBoard.length;
        int numBalls = gameBoard[0].length;

        // Print header row
        System.out.print("    ");
        for (int numCol = 0; numCol < numBalls; numCol++) {
            System.out.print("|   " + (numCol + 1) + "   ");
        }
        System.out.println("|      Feedback");

        // Print separator
        System.out.print("   ");
        for (int i = 0; i < numBalls; i++) {
            System.out.print("+---");
        }
        System.out.println("+-----------");

        // Print game board
        for (int turn = 0; turn < numTurnsMax; turn++) {
            System.out.print(" " + (turn + 1) + " ");
            for (int ball = 0; ball < numBalls; ball++) {
                System.out.print(" | " + gameBoard[turn][ball] + " ");
            }
            System.out.print("|   ");
            if (feedback[turn] != null) {
                int blackPins = feedback[turn].first();
                int whitePins = feedback[turn].second();
                for (int npin = 0; npin < blackPins; npin++) {
                    System.out.print("B");
                }
                for (int npin = 0; npin < whitePins; npin++) {
                    System.out.print("W");
                }
            }
            System.out.println("");
        }

        // Print separator
        System.out.print("   ");
        for (int i = 0; i < numBalls; i++) {
            System.out.print("+---");
        }
        System.out.println("+-----------\n");
    }

    public static void askForHint(DomainController domain) throws PositionOutOfBoundsException, FeedbackHintIndexOutOfBoundsException {
        domain.notifyAskForHint();
    }

    public static void startGameInstance(DomainController domain, String playerName, int mode) throws Exception, InvalidTurnIncrementException {
        String playerRole;
        if(mode == 1) playerRole = "CM";
        else playerRole = "CB";
        domain.notifyNewPlayer(playerName, playerRole);
        //start the game depending on the role
        if (mode == 1) {
            //mode = 1 -> Mode.CodeMaker
            //flow code maker loop
            //machine plays as code breaker
            int option;
            boolean secretCodeSet = false, won = false;
            while (!secretCodeSet) {
                showOptions(1);
                option = inputOption(0, 4);
                switch (option) {
                    case 0:
                        showOptions(1);
                        break;
                    case 1:
                        //ask for a color and the position of the code where to place it
                        Ball_color color = askColor();
                        int position = askPosition();
                        domain.notifyNewColor(position, color.toString());
                        printCurrentCode(domain); //the current code turn you are creating
                        //should I handle the possible exceptions??
                        break;
                    case 2:
                        //cleans the solution code, now the code is empty
                        domain.notifyCleanCode();
                        break;
                    case 3:
                        //confirms code, now this code is the current game solution code
                        won = domain.notifyConfirmCode();
                        printGame(domain);
                        //should I handle the possible exceptions??
                        //break loop, so the algorithm can solve the game
                        secretCodeSet = true;
                        break;
                    case 4:
                        //how to go back to menu?
                        System.exit(0);
                        break;
                }
            }
            //Notify the code maker ranking if the game concluded in victory or not
            domain.notifyUpdateRankingCM(won);
            if (won) showRankingCM(domain);
            //once the solution is set the algorithm must solve the secret code
            //we will get the whole game output
            //now the end of the current game must be finished giving the user the corresponding options
            //Go back to the starting menu or start a new game for example
        }
        if (mode == 2) {
            //call a domain method with player name and mode that will send it to the game controller to set the player
            //mode = 1 -> Mode.CodeBreaker
            //flow code breaker loop
            //machine plays as code maker
            int option, turn = 0;
            boolean gameFinished = false, won = false;
            //We must generate the secret code
            domain.notifySetComputerSecretCode();
            while (!gameFinished) {
                showOptions(2);
                option = inputOption(0, 6);
                switch (option) {
                    case 0:
                        showOptions(2);
                        break;
                    case 1:
                        //ask for a color and the position of the code where to place it
                        Ball_color color = askColor();
                        int position = askPosition();
                        domain.notifyNewColor(position, color.toString());
                        printCurrentCode(domain); //the current code turn you are creating
                        break;
                    case 2:
                        //cleans the solution code, now the code is empty
                        domain.notifyCleanCode();
                        break;
                    case 3:
                        //confirms code, now the turn is set, and we get the feedback response
                        gameFinished = won = domain.notifyConfirmCode();
                        gameFinished = gameFinished || (turn == domain.getGameMaxTurns());
                        //in case the code was the correct one, the loop breaks
                        printGame(domain);
                        //should I handle the possible exceptions??
                        //break loop, so the algorithm can solve the game
                        break;
                    case 4:
                        //Ask for a hint
                        askForHint(domain);
                        printCurrentCode(domain);
                        //so now the player's current code (the actual turn), has a new color confirmed by the feedback
                        break;
                    case 5:
                        //Surrender option
                        domain.notifySurrender();
                        gameFinished = true;
                        break;
                    case 6:
                        //how to go back to menu?
                        System.exit(0);
                        break;
                }
            }
            domain.notifyUpdateRecords();
            domain.notifyUpdateRankingCB(won);
            showRankingCB(domain);
        }
            System.out.println("Thank you for playing!");
    }
}
*/