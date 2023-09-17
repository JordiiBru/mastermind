package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class InGame extends javax.swing.JLayeredPane {
    private final PresentationController presentationController;
    private ViewCM viewCM;
    private ViewCB viewCB;
    private final String gameType;
    private ViewPause viewPause;
    private ViewResult viewResult;
    private ViewBoard viewBoard;

    public InGame(PresentationController presentationController, String role) {
        this.gameType = role;
        this.presentationController = presentationController;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1024, 720));
        this.setBackground(new java.awt.Color(0, 0, 0, 0));
        viewPause = new ViewPause(this);
        viewPause.setEnabled(false);
        viewPause.setVisible(false);
        initializeComponents(role, presentationController.getMaxBalls(), presentationController.getMaxTurns());
    }

    public void initializeComponents(String role, int maxBalls, int maxTurns) {
        viewBoard = new ViewBoard(maxBalls, maxTurns, role);
        if (role == "CB") {
            viewCB = new ViewCB(this, maxBalls, maxTurns, viewBoard.getCoordsColors(), viewBoard.getCoordsFeedback(), viewBoard.getCoordsNumber());
            viewCB.printBoard(presentationController.getBoard(), presentationController.getFeedbackBoard());
            this.add(viewCB);
            viewCB.setVisible(true);
            viewCB.setSize(1024, 720);
        } else {
            viewCM = new ViewCM(this, maxBalls, maxTurns, viewBoard.getCoordsColors(), viewBoard.getCoordsFeedback(), viewBoard.getCoordsNumber());
            this.add(viewCM);
            viewCM.setVisible(true);
            viewCM.setSize(1024, 720);
        }
        this.add(viewBoard);
        viewBoard.setVisible(true);
        viewBoard.setSize(1024, 720);
    }

    public void synchronizeGameToPause() {
        if (gameType.equals("CB")) viewCB.setVisible(false);
        else viewCM.setVisible(false);
        viewBoard.setVisible(false);
        viewPause.setVisible(true);
        viewPause.initializeComponents();
    }

    public void synchronizePauseToGame() {
        viewPause.setVisible(false);
        viewBoard.setVisible(true);
        if (gameType.equals("CB")) viewCB.setVisible(true);
        else viewCM.setVisible(true);
    }

    private static void show_message(String msg) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, msg, "Gracias por jugar!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void synchronizeGameToResult(boolean win) throws InterruptedException {
        show_message("Pulse aceptar cuando esté preparado para ir a la pantalla de resultados.");
        viewBoard.setVisible(false);
        if (gameType.equals("CB")) {
            notifyUpdateRecords();
            notifyUpdateRankingCB(win);
            viewCB.setVisible(false);
            this.remove(viewCB);
        } else {
            notifyUpdateRankingCM(!win);
            viewCM.setVisible(false);
            this.remove(viewCM);
        }
        this.remove(viewBoard);
        viewResult.setVisible(true);
        this.add(viewResult);
        viewResult.initializeComponents();
    }


    // ---- viewGame needs ----

    public void notifyNewColor(int position, String color) {
        this.presentationController.newColor(position, color);
    }

    public boolean notifyConfirmCode() {
        return this.presentationController.confirmCode();
    }

    public void notifyCleanCode() {
        this.presentationController.cleanCode();
    }

    public void notifySurrender() {
        this.presentationController.surrender();
        notifyLose();
    }

    public void notifyAskForHint() {
        this.presentationController.askForHint();
    }


    public String[] getPlayerCurrentCode() {
        return this.presentationController.getCurrentCode();
    }

    /**
     * Ask for the feedback of the last confirmed code
     *
     * @return feedback of the last turn
     */
    public Map.Entry<Integer, Integer> getNewFeedback() {
        return this.presentationController.getCurrentFeedback();
    }

    /**
     * This method will assist in-game views in order to detect if a code was valid or not when confirming.
     * If you take the row in the turn position and is null, that means the code confirmed was not applied to domain because it was invalid.
     *
     * @return number of the actual turn
     */
    public int getTurn() {
        return this.presentationController.getTurn();
    }

    public void notifyWin() {
        viewResult = new ViewResult(this, true);
        try {
            synchronizeGameToResult(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifyLose() {
        viewResult = new ViewResult(this, false);
        try {
            synchronizeGameToResult(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void notifyPause() {
        synchronizeGameToPause();
        this.presentationController.notifyPause();
    }

    public String[][] getBoardComputer() {
        return presentationController.getBoard();
    }

    public List<Map.Entry<Integer,Integer>> getFeedBackComputer() {
        return presentationController.getFeedbackBoard();
    }

    public String[] getSecretCode() {
        return presentationController.getSecretCode();
    }


    /**
     * Method to be called after confirmCode, so the view will know if that code was valid.
     *
     * @return true if the position in the board is not null
     */
    public boolean validCode() {
        return presentationController.validCode();
    }

    // ---- viewPause needs ----

    public void notifyResume() {
        synchronizePauseToGame();
        this.presentationController.notifyResume();
    }

    public void notifySaveAndExit() {
        this.presentationController.saveGame();
        this.presentationController.synchronizationInGameToPrincipalMenu(); // to menu or exit
    }

    public void notifyExit() {
        this.presentationController.synchronizationInGameToPrincipalMenu();
    }

    // ---- viewResult needs ----

    public int notifyGetScore() {
        return this.presentationController.getScore();
    }

    public void notifyResultExit() {
        this.presentationController.notifyEndedGame();
        this.presentationController.synchronizationInGameToPrincipalMenu();
    }

    public void notifyUpdateRecords() {
        this.presentationController.notifyUpdateRecords();
    }

    public void notifyUpdateRankingCM(boolean win) {
        this.presentationController.notifyUpdateRankingCM(win);
    }

    public void notifyUpdateRankingCB(boolean win) {
        this.presentationController.notifyUpdateRankingCB(win);
    }

}
