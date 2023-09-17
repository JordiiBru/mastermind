package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ViewRanking extends javax.swing.JLayeredPane {
    private PrincipalMenu principalMenu;
    private JLabel backgroundBackToPrincipalMenu;
    private JButton rankingCMButton;
    private JButton rankingCBButton;
    private JButton exitButton;
    private JLabel backgroundImageRankingCM;
    private JLabel backgroundImageRankingCB;

    public ViewRanking(PrincipalMenu principalMenu)  {
        this.principalMenu = principalMenu;
    }

    public void initializeComponents() {
        principalMenu.removeAll();

        rankingCMButton = new JButton();
        rankingCBButton = new JButton();
        exitButton = new JButton();
        backgroundBackToPrincipalMenu = new JLabel();

        backgroundImageRankingCB = new JLabel();
        ImageIcon backgroundImageRankingCBIcon = new ImageIcon("../ASSETS/PrincipalMenu/ranking_CodeBreaker.png");
        Image resizedBackgroundImageRankingCBIcon = backgroundImageRankingCBIcon.getImage().getScaledInstance(850, 580, Image.SCALE_SMOOTH);
        backgroundImageRankingCB.setIcon(new ImageIcon(resizedBackgroundImageRankingCBIcon));
        backgroundImageRankingCB.setBounds(90, 40, 850, 580);
        backgroundImageRankingCB.setVisible(false);

        backgroundImageRankingCM = new JLabel();
        ImageIcon backgroundImageRankingCMIcon = new ImageIcon("../ASSETS/PrincipalMenu/ranking_CodeMaker.png");
        Image resizedBackgroundImageRankingCMIcon = backgroundImageRankingCMIcon.getImage().getScaledInstance(850, 580, Image.SCALE_SMOOTH);
        backgroundImageRankingCM.setIcon(new ImageIcon(resizedBackgroundImageRankingCMIcon));
        backgroundImageRankingCM.setBounds(90, 40, 850, 580);

        principalMenu.setLayout(null);

        rankingCBButton.setBounds(535, 47, 350, 40);
        rankingCBButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        rankingCBButton.setBorder(null);
        ImageIcon iconRankingCBButton = new ImageIcon("../ASSETS/PrincipalMenu/button_CB_ranking.png");
        Image resizedImageRankingCBButton = iconRankingCBButton.getImage().getScaledInstance(350, 40, Image.SCALE_SMOOTH);
        rankingCBButton.setIcon(new ImageIcon(resizedImageRankingCBButton));
        rankingCBButton.setContentAreaFilled(false);
        ImageIcon iconRankingCBButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_CB_ranking_pressed.png");
        Image resizedImageRankingCBButton_pressed = iconRankingCBButton_pressed.getImage().getScaledInstance(350, 40, Image.SCALE_SMOOTH);
        rankingCBButton.setPressedIcon(new ImageIcon(resizedImageRankingCBButton_pressed));
        rankingCBButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        rankingCBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                rankingCBButtonActionPerformed();
            }
        });

        rankingCMButton.setBounds(135, 47, 350, 40);
        rankingCMButton.setBackground(new java.awt.Color(0, 0, 0, 0));
        rankingCMButton.setBorder(null);
        ImageIcon iconRankingCMButton = new ImageIcon("../ASSETS/PrincipalMenu/button_CM_ranking.png");
        Image resizedImageRankingCMButton = iconRankingCMButton.getImage().getScaledInstance(350, 40, Image.SCALE_SMOOTH);
        rankingCMButton.setIcon(new ImageIcon(resizedImageRankingCMButton));
        rankingCMButton.setContentAreaFilled(false);
        ImageIcon iconRankingCMButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_CM_ranking_pressed.png");
        Image resizedImageRankingCMButton_pressed = iconRankingCMButton_pressed.getImage().getScaledInstance(350, 40, Image.SCALE_SMOOTH);
        rankingCMButton.setPressedIcon(new ImageIcon(resizedImageRankingCMButton_pressed));
        rankingCMButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankingCMButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                rankingCMButtonActionPerformed();
            }
        });

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

        // Adding components to the JLayeredPane in custom layers
        principalMenu.add(rankingCBButton, Integer.valueOf(2));
        principalMenu.add(rankingCMButton, Integer.valueOf(2));
        principalMenu.add(exitButton, Integer.valueOf(2));
        principalMenu.add(backgroundImageRankingCM, Integer.valueOf(1));
        principalMenu.add(backgroundImageRankingCB, Integer.valueOf(1));

        principalMenu.revalidate();

        //Set CodeMaker ranking by default
        rankingCMButton.setFocusable(false);
        rankingCMButton.setEnabled(false);
        rankingCMButton.setBorderPainted(false);
        setRanking("CM");
    }


    public void rankingCMButtonActionPerformed() {
        setRanking("CM");
        backgroundImageRankingCB.setVisible(false);
        backgroundImageRankingCM.setVisible(true);

        rankingCMButton.setFocusable(false);
        rankingCMButton.setEnabled(false);
        rankingCMButton.setBorderPainted(false);

        rankingCBButton.setFocusable(true);
        rankingCBButton.setEnabled(true);
        rankingCBButton.setBorderPainted(true);
    }

    public void rankingCBButtonActionPerformed() {
        setRanking("CB");
        backgroundImageRankingCB.setVisible(true);
        backgroundImageRankingCM.setVisible(false);

        rankingCBButton.setFocusable(false);
        rankingCBButton.setEnabled(false);
        rankingCBButton.setBorderPainted(false);

        rankingCMButton.setFocusable(true);
        rankingCMButton.setEnabled(true);
        rankingCMButton.setBorderPainted(true);
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

    private void setRanking(String rankingType) {
        //ArrayList<Pair<String, Integer>> ranking = new ArrayList<>();

        List<Map.Entry<String, Integer>> ranking;
        if (rankingType.equals("CM")) {
            ranking = principalMenu.getRankingCM();
        } else {
            ranking = principalMenu.getRankingCB();
        }

        //Before adding the ranking components, remove the existing ones.
        Component[] components = principalMenu.getComponents();
        for (Component component : components) {
            if (component.getName() != null && component.getName().equals("rankingComponent")) {
                principalMenu.remove(component);
            }
        }

        repaint();
        int x;
        int y = 50;
        int entrySize = 40;
        int position = 1;

        for (Map.Entry<String, Integer> r : ranking) {
            JLabel playerLabel = new JLabel();
            JLabel scoreLabel = new JLabel();
            String positionString = String.valueOf(position);
            if (position < 11) {
                x = 130;
                playerLabel.setBounds(x, y, 200, 200);
                scoreLabel.setBounds(x + 280, y, 200, 200);
            } else {
                x = 550;
                playerLabel.setBounds(x, y - 400, 200, 200);
                scoreLabel.setBounds(x + 280, y - 400, 200, 200);
            }

            repaint();
            String name = r.getKey();
            if (name.isEmpty() || (name.length() == 20 && name.replaceAll("\0", "").isEmpty())) {
                name = "[no-name]";
            }
            playerLabel.setText(positionString + "  " + name);
            playerLabel.setName("rankingComponent");
            playerLabel.setVisible(true);
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 24));

            scoreLabel.setText(String.valueOf(r.getValue()));
            scoreLabel.setName("rankingComponent");
            scoreLabel.setBackground(Color.WHITE);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new java.awt.Font("Comics Sans", Font.BOLD, 24));

            principalMenu.add(playerLabel, Integer.valueOf(2));
            principalMenu.add(scoreLabel, Integer.valueOf(2));

            y += entrySize;
            position++;
        }

        principalMenu.revalidate();
    }
}



