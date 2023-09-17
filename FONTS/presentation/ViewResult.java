package presentation;

import javax.swing.*;
import java.awt.*;

public class ViewResult extends javax.swing.JLayeredPane {
    private InGame inGame;
    private JLabel resultBackground;
    private JLabel resultTitleLabel;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton exitButton;
    boolean win;

    public ViewResult(InGame inGame, boolean win) {
        this.inGame = inGame;
        resultBackground = new JLabel();
        scoreLabel = new JLabel();
        exitButton = new JButton();
        this.win = win;
    }

    public void initializeComponents() {
        this.setLayout(null);

        this.setPreferredSize(new Dimension(1024, 720));

        this.setBackground(new java.awt.Color(0,0,0,0));

        resultBackground = new JLabel();
        ImageIcon backgroundImageResultBackgroundIcon = new ImageIcon("../ASSETS/NewGameOptions/text_nameInput_background.png");
        Image resizedBackgroundImageResultBackgroundIcon = backgroundImageResultBackgroundIcon.getImage().getScaledInstance(300, 65, Image.SCALE_SMOOTH);
        resultBackground.setIcon(new ImageIcon(resizedBackgroundImageResultBackgroundIcon));
        resultBackground.setBounds(350, 295, 300, 65);

        resultTitleLabel = new JLabel();
        ImageIcon resultTitleLabelBackgroundIcon = new ImageIcon("../ASSETS/InGame/result_neon.png");
        Image resizedResultTitleLabelBackgroundIcon = resultTitleLabelBackgroundIcon.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        resultTitleLabel.setIcon(new ImageIcon(resizedResultTitleLabelBackgroundIcon));
        resultTitleLabel.setBounds(300, 210, 450, 70);

        if (win) {
            resultLabel = new JLabel();
            ImageIcon resultLabelBackgroundIcon = new ImageIcon("../ASSETS/InGame/victory2.1_neon.png");
            Image resizedResultLabelBackgroundIcon = resultLabelBackgroundIcon.getImage().getScaledInstance(650, 80, Image.SCALE_SMOOTH);
            resultLabel.setIcon(new ImageIcon(resizedResultLabelBackgroundIcon));
            resultLabel.setBounds(185, 40, 650, 80);
        }
        else {
            resultLabel = new JLabel();
            ImageIcon resultLabelBackgroundIcon = new ImageIcon("../ASSETS/InGame/defeat_neon.png");
            Image resizedResultLabelBackgroundIcon = resultLabelBackgroundIcon.getImage().getScaledInstance(650, 80, Image.SCALE_SMOOTH);
            resultLabel.setIcon(new ImageIcon(resizedResultLabelBackgroundIcon));
            resultLabel.setBounds(185, 40, 650, 80);
        }

        scoreLabel.setText(String.format("%07d", inGame.notifyGetScore()));
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setBounds(428, 125, 200, 400);
        scoreLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 36));


        exitButton.setBackground(new java.awt.Color(0, 0, 0,0));
        exitButton.setBorder(null);
        ImageIcon iconExitButton = new ImageIcon("../ASSETS/InGame/button_exit.png");
        Image resizedImageExitButton = iconExitButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(resizedImageExitButton));
        exitButton.setContentAreaFilled(false);
        ImageIcon iconExitButton_pressed = new ImageIcon("../ASSETS/InGame/button_exit_pressed.png");
        Image resizedImageExitButton_pressed = iconExitButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        exitButton.setPressedIcon(new ImageIcon(resizedImageExitButton_pressed));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setBounds(300, 540, 400, 60);

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                exitButtonActionPerformed();
            }
        });

        inGame.add(resultBackground, Integer.valueOf(1));
        inGame.add(resultTitleLabel, Integer.valueOf(2));
        inGame.add(resultLabel, Integer.valueOf(2));
        inGame.add(scoreLabel, Integer.valueOf(2));
        inGame.add(exitButton, Integer.valueOf(2));

        inGame.revalidate();
    }

    public void exitButtonActionPerformed() {
        inGame.notifyResultExit();
    }
}

