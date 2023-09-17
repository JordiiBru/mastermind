package presentation;

import domain.DomainController;
import domain.exceptions.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PresentationController {
    private JFrame main;
    private DomainController domainController;
    private PrincipalMenu principalMenu;
    private NewGameOptions newGameOptions;
    private InGame inGame;
    private boolean confirmedCode;

    public PresentationController() throws IOException, FontFormatException {
        main = new JFrame();
        domainController = new DomainController();
        principalMenu = new PrincipalMenu(this);

        initializePresentation();
        loadStart(); // load to domain all persistence data
        this.confirmedCode = true;
    }

    public void initializePresentation() {
        main.setPreferredSize(new Dimension(1024, 720));
        Image backgroundImage = new ImageIcon("../ASSETS/PrincipalMenu/fondo.png").getImage();
        main.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        main.setTitle("MasterMind");
        main.setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));
        main.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        main.setResizable(false);
        main.pack();
        main.setVisible(true);

        main.add(principalMenu);
        principalMenu.setVisible(true);
        //newGameOptions.setVisible(false);
    }

    public void synchronizationPrincipalMenuToNewGameOptions() {
        newGameOptions = new NewGameOptions(this);
        main.add(newGameOptions);
        principalMenu.setVisible(false);
        newGameOptions.setVisible(true);
    }

    public void synchronizationNewGameOptionsToPrincipalMenu() {
        principalMenu.updateContinueButton();
        newGameOptions.setVisible(false);
        main.remove(newGameOptions);
        principalMenu.setVisible(true);
    }

    public void initInGame() {
        inGame = new InGame(this,domainController.getPlayerRole());
        main.add(inGame);
    }

    public void synchronizationPrincipalMenuToInGame() {
        principalMenu.setVisible(false);
        initInGame();
        inGame.setVisible(true);
    }

    public void synchronizationInGameToPrincipalMenu() {
        principalMenu.updateContinueButton();
        inGame.setVisible(false);
        if(newGameOptions != null) newGameOptions.setVisible(false);
        main.remove(inGame);
        principalMenu.setVisible(true);
    }

    public void synchronizationNewGameOptionsToInGame(String role, String name, String difficulty, String[] values) {
        notifyStartGame(role, name, difficulty, values);
        initInGame();
        newGameOptions.setVisible(false);
        main.remove(newGameOptions);
        inGame.setVisible(true);
    }

    public boolean savedGameExist() {
        return domainController.savedGameExists();
    }

    public int getScore() {
        return domainController.getScore();
    }

    public void saveGame() {
        try {
            domainController.saveGame2Persistence();
            domainController.saveRankings();
            domainController.saveRecords();
            try {
                domainController.loadGameFromPersistence();
            } catch (Exception e) {
                // do nothing
            }
        } catch (IOException e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void notifyStartGame(String role, String name, String difficulty, String[] values) {
        if (difficulty.equals("normal")) sendDifficultyNormal();
        else if (difficulty.equals("hard")) sendDifficultyHard();
        else {
            boolean alg;
            alg = !values[0].equals("5guess");
            boolean repColors;
            repColors = values[1].equals("true");
            int balls = Integer.parseInt(values[2]);
            boolean empty;
            empty = values[3].equals("true");
            int maxTurns = Integer.parseInt(values[4]);
            sendDifficultyCustom(alg, repColors, balls, empty, maxTurns);
        }
        sendNewPlayer(name, role);
    }

    public void loadSavedGame() {
        try {
            domainController.loadGameFromPersistence();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void newColor(int position, String color) {
        try {
            domainController.notifyNewColor(position, color);
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public boolean validCode() {
        return this.confirmedCode;
    }

    public boolean confirmCode() {
        boolean won = false;
        try {
            this.confirmedCode =  true;
            won = domainController.notifyConfirmCode();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
            this.confirmedCode = false;
        }
        return won;
    }

    public void cleanCode() {
        try {
            domainController.notifyCleanCode();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void surrender() {
        try {
            domainController.notifySurrender();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void askForHint() {
        try {
            domainController.notifyAskForHint();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    /**
     * Load anything possible from the start  of the app
     */
    public void loadStart() {
        try {
            domainController.loadRankings();
        } catch (FileNotFoundException e) {
            domainController.createRanking();
        }
        try {
            domainController.loadRecords();
        } catch (FileNotFoundException e) {
            domainController.createRecords();
        }
        try {
            domainController.loadGameFromPersistence();
        } catch (Exception e) {
            //do nothing
        }
    }

    public List<Map.Entry<String, Integer>> getRankingCM() {
        return domainController.giveRankingCM();
    }

    public String[] getSecretCode() {
        return domainController.getSecretCode();
    }

    public List<Map.Entry<String, Integer>> getRankingCB() {
        return domainController.giveRankingCB();
    }

    public Map<String, Map.Entry<String, Integer>> getRecords() {
        return domainController.giveRecords();
    }

    public String[][] getBoard() {
        return domainController.giveBoard();
    }

    public List<Map.Entry<Integer,Integer>> getFeedbackBoard() {
        return domainController.giveFeedbackBoard();
    }

    public String[] getCurrentCode() {
        return domainController.giveCurrentCode();
    }

    public Map.Entry<Integer,Integer> getCurrentFeedback() {
        return domainController.giveCurrentFeedback();
    }

    public void sendDifficultyCustom(boolean algorithm, boolean repeated_colors, int number_positions, boolean empty_colors, int max_turns)  {
        try {
            domainController.notifyCustom(algorithm, repeated_colors, number_positions, empty_colors, max_turns);
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void sendDifficultyNormal() {
        domainController.notifyNormal();
    }

    public void sendDifficultyHard() {
        domainController.notifyHard();
    }

    public int getMaxTurns() {
        return domainController.getGameMaxTurns();
    }

    public int getMaxBalls() {
        return domainController.getMaxBalls();
    }


    public void sendNewPlayer(String playerName, String player_role) {
        try {
            domainController.notifyNewPlayer(playerName,player_role);
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public int getTurn() {
        return domainController.getGameTurn();
    }

    public void notifyPause() {
        try {
            domainController.notifyPause();
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void notifyResume() {
        domainController.notifyResume();
    }

    public void notifyUpdateRecords() {
        domainController.notifyUpdateRecords();
    }

    public void notifyUpdateRankingCM(boolean win) {
        try {
            domainController.notifyUpdateRankingCM(win);
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void notifyUpdateRankingCB(boolean win) {
        try {
            domainController.notifyUpdateRankingCB(win);
        } catch (Exception e) {
            ExceptionsHandler.handleException(e);
        }
    }

    public void notifyEndedGame() {
        domainController.notifyEndedGame();
    }

    public void exitGame() {
        try{
            domainController.saveRankings();
            domainController.saveRecords();
        } catch (IOException e) {
            ExceptionsHandler.handleException(e);
        }

    }

}


