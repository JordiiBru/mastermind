package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ViewNewDifficulty extends javax.swing.JPanel {

    //Data
    private final NewGameOptions newGameOptions;
    private final JLabel background = new JLabel();
    private String difficulty = "normal";
    private String[] values = new String[]{"5guess","false","4","false","8"};
    private final JLabel backgroundImageSelection = new JLabel();
    private final JButton normal = new JButton();
    private final JButton hard = new JButton();
    private final JButton custom = new JButton();
    private final JLabel difficultyInfo = new JLabel();
    private final JLabel difficultyText = new JLabel();
    private final JButton exit = new JButton();
    private final JButton confirm = new JButton();

    public ViewNewDifficulty(NewGameOptions newGameOptions) {
        this.newGameOptions = newGameOptions;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1024, 720));
        this.setBackground(new java.awt.Color(0,0,0,0));
        initialize();
    }

    private void updateText() {
        difficultyText.setText("<html><body>Algoritmo: " + values[0] + "<br>" +
                                            "Colores Repetidos: " + values[1] + "<br>" +
                                            "Cantidad bolas: " + values[2] + "<br>" +
                                            "Colores Vacíos: " + values[3] + "<br>" +
                                            "Turnos Máximos: " + values[4] + "</body></html>");
        difficultyText.revalidate();
    }

    private void initialize() {
        int buttonSizeX = 300;
        int buttonSizeY = 60;
        //Selection Label
        backgroundImageSelection.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundImageSelection.setBorder(null);
        ImageIcon iconBackgroundImageSelection = new ImageIcon("../ASSETS/NewGameOptions/text_selection.png");
        Image resizedImageBackgroundImageSelection = iconBackgroundImageSelection.getImage().getScaledInstance((int) (iconBackgroundImageSelection.getIconWidth()*0.65), (int) (iconBackgroundImageSelection.getIconHeight()*0.65), Image.SCALE_SMOOTH);
        backgroundImageSelection.setIcon(new ImageIcon(resizedImageBackgroundImageSelection));
        backgroundImageSelection.setLocation(208,20);
        backgroundImageSelection.setSize((int) (iconBackgroundImageSelection.getIconWidth()*0.65), (int) (iconBackgroundImageSelection.getIconHeight()*0.65));
        this.add(backgroundImageSelection);
        //Difficulty Text Label
        difficultyText.setBackground(new java.awt.Color(64, 64, 64,255));
        difficultyText.setBorder(null);
        difficultyText.setLocation(188,275);
        difficultyText.setSize((int) (782*0.80), (int) (369*0.80));
        difficultyText.setForeground(Color.WHITE);
        difficultyText.setFont(new java.awt.Font("Serif", Font.BOLD, 45));
        updateText();
        this.add(difficultyText);
        //Button Images
        ImageIcon iconNormal = new ImageIcon("../ASSETS/NewGameOptions/button_N.png");
        Image resizedImageNormal = iconNormal.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        ImageIcon iconHard = new ImageIcon("../ASSETS/NewGameOptions/button_H.png");
        Image resizedImageHard = iconHard.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        ImageIcon iconCustom = new ImageIcon("../ASSETS/NewGameOptions/button_C.png");
        Image resizedImageCustom = iconCustom.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        ImageIcon iconNormal_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_N_pressed.png");
        Image resizedImageNormal_pressed = iconNormal_pressed.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        //Button Normal
        normal.setBackground(new java.awt.Color(0, 0, 0,0));
        normal.setBorder(null);
        normal.setIcon(new ImageIcon(resizedImageNormal_pressed));
        normal.setContentAreaFilled(false);
        normal.setPressedIcon(new ImageIcon(resizedImageNormal_pressed));
        normal.setLocation(20,150);
        normal.setSize(buttonSizeX, buttonSizeY);
        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normal.setIcon(new ImageIcon(resizedImageNormal_pressed));
                hard.setIcon(new ImageIcon(resizedImageHard));
                custom.setIcon(new ImageIcon(resizedImageCustom));
                difficulty = "normal";
                values = new String[]{"5guess", "false", "4", "false", "8"};
                updateText();
            }
        });
        this.add(normal);
        //Button Hard
        hard.setBackground(new java.awt.Color(0, 0, 0,0));
        hard.setBorder(null);
        hard.setIcon(new ImageIcon(resizedImageHard));
        hard.setContentAreaFilled(false);
        ImageIcon iconHard_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_H_pressed.png");
        Image resizedImageHard_pressed = iconHard_pressed.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        hard.setPressedIcon(new ImageIcon(resizedImageHard_pressed));
        hard.setLocation(362,150);
        hard.setSize(buttonSizeX, buttonSizeY);
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hard.setIcon(new ImageIcon(resizedImageHard_pressed));
                normal.setIcon(new ImageIcon(resizedImageNormal));
                custom.setIcon(new ImageIcon(resizedImageCustom));
                difficulty = "hard";
                values = new String[]{"genetic", "false", "7", "false", "8"};
                updateText();
            }
        });
        this.add(hard);
        //Button Custom
        custom.setBackground(new java.awt.Color(0, 0, 0,0));
        custom.setBorder(null);
        custom.setIcon(new ImageIcon(resizedImageCustom));
        custom.setContentAreaFilled(false);
        ImageIcon iconCustom_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_C_pressed.png");
        Image resizedImageCustom_pressed = iconCustom_pressed.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        custom.setPressedIcon(new ImageIcon(resizedImageCustom_pressed));
        custom.setLocation(704,150);
        custom.setSize(buttonSizeX, buttonSizeY);
        custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                custom.setIcon(new ImageIcon(resizedImageCustom_pressed));
                hard.setIcon(new ImageIcon(resizedImageHard));
                normal.setIcon(new ImageIcon(resizedImageNormal));
                difficulty = "custom";
                values = new String[]{"5guess", "false", "4", "false", "8"};
                updateText();
            }
        });
        this.add(custom);
        //Difficulty Info Label
        difficultyInfo.setBackground(new java.awt.Color(0, 0, 0,0));
        difficultyInfo.setBorder(null);
        ImageIcon iconBackgroundDifficultyInfo = new ImageIcon("../ASSETS/NewGameOptions/text_diff_preview.png");
        Image resizedImageBackgroundDifficultyInfo = iconBackgroundDifficultyInfo.getImage().getScaledInstance((int) (iconBackgroundDifficultyInfo.getIconWidth()*0.85), (int) (iconBackgroundDifficultyInfo.getIconHeight()*0.85), Image.SCALE_SMOOTH);
        difficultyInfo.setIcon(new ImageIcon(resizedImageBackgroundDifficultyInfo));
        difficultyInfo.setLocation(180,270);
        difficultyInfo.setSize((int) (iconBackgroundDifficultyInfo.getIconWidth()*0.85), (int) (iconBackgroundDifficultyInfo.getIconHeight()*0.85));
        this.add(difficultyInfo);
        //Button Exit
        exit.setBackground(new java.awt.Color(0, 0, 0,0));
        exit.setBorder(null);
        ImageIcon iconExit = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir.png");
        Image resizedImageExit = iconExit.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        exit.setIcon(new ImageIcon(resizedImageExit));
        exit.setContentAreaFilled(false);
        ImageIcon iconExit_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir_pressed.png");
        Image resizedImageExit_pressed = iconExit_pressed.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        exit.setPressedIcon(new ImageIcon(resizedImageExit_pressed));
        exit.setLocation(10,640);
        exit.setSize(buttonSizeX, buttonSizeY);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameOptions.synchronizationDifficultyToPlayer();
            }
        });
        this.add(exit);
        //Button Confirm
        confirm.setBackground(new java.awt.Color(0, 0, 0,0));
        confirm.setBorder(null);
        ImageIcon iconConfirm = new ImageIcon("../ASSETS/NewGameOptions/button_confirm.png");
        Image resizedImageConfirm = iconConfirm.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        confirm.setIcon(new ImageIcon(resizedImageConfirm));
        confirm.setContentAreaFilled(false);
        ImageIcon iconConfirm_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_confirm_pressed.png");
        Image resizedImageConfirm_pressed = iconConfirm_pressed.getImage().getScaledInstance(buttonSizeX, buttonSizeY, Image.SCALE_SMOOTH);
        confirm.setPressedIcon(new ImageIcon(resizedImageConfirm_pressed));
        confirm.setLocation(714,640);
        confirm.setSize(buttonSizeX, buttonSizeY);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(difficulty, "custom")) newGameOptions.synchronizationDifficultyToCustom();
                else newGameOptions.startGame(difficulty, values);
            }
        });
        this.add(confirm);
        //Background
        background.setBackground(new java.awt.Color(0, 0, 0,0));
        background.setBorder(null);
        ImageIcon iconBackground = new ImageIcon("../ASSETS/PrincipalMenu/fondo.png");
        Image resizedImageBackground = iconBackground.getImage().getScaledInstance(1024, 720, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(resizedImageBackground));
        background.setLocation(0,0);
        background.setSize(1024, 720);
        this.add(background);
    }
}
