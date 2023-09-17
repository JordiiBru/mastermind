package presentation;

import javax.swing.*;

public class NewGameOptions extends javax.swing.JPanel {

    //Data
    private final PresentationController presentationController;
    private final JPanel viewNewPlayer = new ViewNewPlayer(this);
    private final JPanel viewNewDifficulty = new ViewNewDifficulty(this);
    private final JPanel viewNewCustom = new ViewNewCustom(this);
    private String role;
    private String name;
    private String difficulty;
    private String[] values = new String[]{};

    public NewGameOptions(PresentationController presentationController) {
        this.presentationController = presentationController;
        initialize();
    }

    private void initialize() {
        this.setBackground(new java.awt.Color(0,0,0,0));
        this.add(viewNewPlayer);
        viewNewPlayer.setVisible(true);
    }

    public void goBack() {
        presentationController.synchronizationNewGameOptionsToPrincipalMenu();
    }

    public void synchronizationPlayerToDifficulty(String role, String name) {
        this.role = role;
        this.name = name;
        viewNewPlayer.setVisible(false);
        this.remove(viewNewPlayer);
        this.add(viewNewDifficulty);
        viewNewDifficulty.setVisible(true);
    }

    public void synchronizationDifficultyToPlayer() {
        viewNewDifficulty.setVisible(false);
        this.remove(viewNewDifficulty);
        this.add(viewNewPlayer);
        viewNewPlayer.setVisible(true);
    }

    public void synchronizationDifficultyToCustom() {
        viewNewDifficulty.setVisible(false);
        this.remove(viewNewDifficulty);
        this.add(viewNewCustom);
        viewNewCustom.setVisible(true);
    }

    public void synchronizationCustomToDifficulty() {
        viewNewCustom.setVisible(false);
        this.remove(viewNewCustom);
        this.add(viewNewDifficulty);
        viewNewDifficulty.setVisible(true);
    }

    public void startGame(String difficulty, String[] values) {
        this.difficulty = difficulty;
        this.values = values;
        presentationController.synchronizationNewGameOptionsToInGame(this.role, this.name, this.difficulty, this.values);
        setVisible(false);
    }
}
