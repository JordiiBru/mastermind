package presentation;

import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewRecords extends javax.swing.JFrame {
    private PrincipalMenu principalMenu;
    private JLabel backgroundBackToPrincipalMenu;
    private JButton exitButton;
    private JLabel backgroundImageRecords;

    public ViewRecords(PrincipalMenu principalMenu) {
        this.principalMenu = principalMenu;
    }

    public void initializeComponents() {
        principalMenu.removeAll();

        backgroundBackToPrincipalMenu = new JLabel();
        exitButton = new JButton();

        backgroundImageRecords = new JLabel();
        ImageIcon backgroundImageRecordsIcon = new ImageIcon("../ASSETS/PrincipalMenu/records.png");
        Image resizedBackgroundImageRecordsIcon = backgroundImageRecordsIcon.getImage().getScaledInstance(850, 580, Image.SCALE_SMOOTH);
        backgroundImageRecords.setIcon(new ImageIcon(resizedBackgroundImageRecordsIcon));
        backgroundImageRecords.setBounds(90, 40, 850, 580);

        principalMenu.setLayout(null);

        exitButton.setBounds(50, 640, 250, 50);
        exitButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        exitButton.setBorder(null);
        ImageIcon iconExit = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir.png");
        Image resizedImageExit = iconExit.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(resizedImageExit));
        exitButton.setContentAreaFilled(false);
        ImageIcon iconExit_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir_pressed.png");
        Image resizedImageExit_pressed = iconExit_pressed.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH);
        exitButton.setPressedIcon(new ImageIcon(resizedImageExit_pressed));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                exitButtonActionPerformed();
            }
        });

        principalMenu.add(exitButton, Integer.valueOf(2));
        principalMenu.add(backgroundImageRecords, Integer.valueOf(1));

        principalMenu.revalidate();

        setRecords();
    }

    public void exitButtonActionPerformed() {
        //Background
        backgroundBackToPrincipalMenu.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundBackToPrincipalMenu.setBorder(null);
        ImageIcon iconBackground = new ImageIcon("../ASSETS/PrincipalMenu/fondo.png");
        Image resizedImageBackground = iconBackground.getImage().getScaledInstance(1024, 720, Image.SCALE_SMOOTH);
        backgroundBackToPrincipalMenu.setIcon(new ImageIcon(resizedImageBackground));
        backgroundBackToPrincipalMenu.setLocation(0,0);
        backgroundBackToPrincipalMenu.setSize(1024, 720);
        principalMenu.add(backgroundBackToPrincipalMenu, Integer.valueOf(3)); //Top layer

        principalMenu.initializeComponents();
    }

    private void setRecords() {
        Map<String, Map.Entry<String, Integer>> records = principalMenu.getRecords();

        int x = 180;
        int y = 100;
        int entrySize = 50;
        repaint();

        for (Map.Entry<String, Map.Entry<String, Integer>> r : records.entrySet()) {
            JLabel recordLabel = new JLabel();
            JLabel playerLabel = new JLabel();
            JLabel scoreLabel = new JLabel();

            recordLabel.setText(r.getKey());
            recordLabel.setVisible(true);
            recordLabel.setForeground(Color.WHITE);
            recordLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 26));
            recordLabel.setBounds(x, y, 200, 200);

            String playerName = r.getValue().getKey();
            playerLabel.setText(playerName == null || playerName.isEmpty() || playerName.equals("null") ? "-" : playerName);
            if (playerName.isEmpty() || (playerName.length() == 20 && playerName.replaceAll("\0", "").isEmpty())) {
                playerName = "[no-name]";
                playerLabel.setText(playerName);
            }
            playerLabel.setVisible(true);
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 26));
            playerLabel.setBounds(x + 280, y, 2000, 200);

            if(playerName == null || playerName.isEmpty()) scoreLabel.setText("-");
            else {
                Integer score = r.getValue().getValue();
                scoreLabel.setText(score != -1 ? String.valueOf(score) : "-");
            }
            scoreLabel.setVisible(true);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 26));
            scoreLabel.setBounds(x + 560, y, 200, 200);

            principalMenu.add(recordLabel, Integer.valueOf(2));
            principalMenu.add(playerLabel, Integer.valueOf(2));
            principalMenu.add(scoreLabel, Integer.valueOf(2));

            y += entrySize;
        }
        principalMenu.revalidate();
    }
}
