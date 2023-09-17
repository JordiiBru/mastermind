package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPause extends javax.swing.JPanel {
    private InGame inGame;
    private JButton continueButton;
    private JButton saveAndExitButton;
    private JButton exitButton;
    private JLabel pauseImage;
    private JLabel backgroundPauseImage;

    public ViewPause(InGame inGame)  {
        this.inGame = inGame;
    }

    public void initializeComponents() {
        this.setLayout(null);

        continueButton = new JButton();
        saveAndExitButton = new JButton();
        exitButton = new JButton();
        pauseImage = new JLabel();

        this.setBackground(new java.awt.Color(0,0,0,0));
        this.setPreferredSize(new Dimension(1024, 720));

        backgroundPauseImage = new JLabel();
        ImageIcon backgroundPauseImageIcon = new ImageIcon("../ASSETS/InGame/background_pause_neon.png");
        Image resizedImageBackgroundPauseImageIcon = backgroundPauseImageIcon.getImage().getScaledInstance(1024, 720, Image.SCALE_SMOOTH);
        backgroundPauseImage.setIcon(new ImageIcon(resizedImageBackgroundPauseImageIcon));
        backgroundPauseImage.setBounds(0, -5, 1024, 720);

        ImageIcon iconPauseImage = new ImageIcon("../ASSETS/InGame/pausa.png");
        Image resizedImagePauseImage = iconPauseImage.getImage().getScaledInstance(480, 100, Image.SCALE_SMOOTH);
        pauseImage.setIcon(new ImageIcon(resizedImagePauseImage));
        pauseImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseImage.setBounds(270, 50, 480, 100);

        continueButton.setBounds(300, 230, 400, 70);
        continueButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        continueButton.setBorder(null);
        ImageIcon iconContinueButton = new ImageIcon("../ASSETS/InGame/button_continuar.png");
        Image resizedImageContinueButton = iconContinueButton.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        continueButton.setIcon(new ImageIcon(resizedImageContinueButton));
        continueButton.setContentAreaFilled(false);
        ImageIcon iconContinueButton_pressed = new ImageIcon("../ASSETS/InGame/button_continuar_pressed.png");
        Image resizedImageContinueButton_pressed = iconContinueButton_pressed.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        continueButton.setPressedIcon(new ImageIcon(resizedImageContinueButton_pressed));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                continueButtonActionPerformed();
            }
        });

        saveAndExitButton.setBounds(300, 350, 400, 70);
        saveAndExitButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        saveAndExitButton.setBorder(null);
        ImageIcon iconSaveAndExitButton = new ImageIcon("../ASSETS/InGame/button_saveandexit.png");
        Image resizedImageSaveAndExitButton = iconSaveAndExitButton.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        saveAndExitButton.setIcon(new ImageIcon(resizedImageSaveAndExitButton));
        saveAndExitButton.setContentAreaFilled(false);
        ImageIcon iconSaveAndExitButton_pressed = new ImageIcon("../ASSETS/InGame/button_saveandexit_pressed.png");
        Image resizedImageSaveAndExitButton_pressed = iconSaveAndExitButton_pressed.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        saveAndExitButton.setPressedIcon(new ImageIcon(resizedImageSaveAndExitButton_pressed));
        saveAndExitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveAndExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                saveAndExitButtonActionPerformed();
            }
        });

        exitButton.setBounds(300, 470, 400, 70);
        exitButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        exitButton.setBorder(null);
        ImageIcon iconExitButton = new ImageIcon("../ASSETS/InGame/button_exit.png");
        Image resizedImageExitButton = iconExitButton.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(resizedImageExitButton));
        exitButton.setContentAreaFilled(false);
        ImageIcon iconExitButton_pressed = new ImageIcon("../ASSETS/InGame/button_exit_pressed.png");
        Image resizedImageExitButton_pressed = iconExitButton_pressed.getImage().getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        exitButton.setPressedIcon(new ImageIcon(resizedImageExitButton_pressed));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                exitButtonActionPerformed();
            }
        });

        inGame.add(backgroundPauseImage, Integer.valueOf(1));
        inGame.add(continueButton, Integer.valueOf(2));
        inGame.add(saveAndExitButton, Integer.valueOf(2));
        inGame.add(exitButton, Integer.valueOf(2));
        inGame.add(pauseImage, Integer.valueOf(2));

        inGame.revalidate();
    }


    public void continueButtonActionPerformed() {
        inGame.remove(backgroundPauseImage);
        inGame.remove(continueButton);
        inGame.remove(saveAndExitButton);
        inGame.remove(exitButton);
        inGame.remove(pauseImage);
        inGame.notifyResume();
    }

    public void saveAndExitButtonActionPerformed() {
        inGame.notifySaveAndExit();
    }

    public void exitButtonActionPerformed() {
        inGame.notifyExit();
    }

}
