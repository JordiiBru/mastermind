package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


public class PrincipalMenu extends javax.swing.JLayeredPane {
    private PresentationController presentationController;
    private JButton newGameButton;
    private JButton continueGameButton;
    private JButton rankingButton;
    private JButton recordsButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private ViewRanking viewRanking;
    private ViewRecords viewRecords;

    public PrincipalMenu(PresentationController presentationController) {
        this.presentationController = presentationController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newGameButton = new JButton();
        continueGameButton = new JButton();
        rankingButton = new JButton();
        recordsButton = new JButton();
        exitButton = new JButton();
        titleLabel = new JLabel();
        viewRanking = new ViewRanking(this);
        viewRecords = new ViewRecords(this);
        initializeComponents();
    }

    public void initializeComponents() {
        this.removeAll(); // Clean the principalMenu panel before adding the buttons.

        this.setPreferredSize(new Dimension(1024, 720));

        this.setBackground(new java.awt.Color(0,0,0,0));

        ImageIcon iconTitle = new ImageIcon("../ASSETS/PrincipalMenu/title.png");
        Image resizedImageTitle = iconTitle.getImage().getScaledInstance(925, 115, Image.SCALE_SMOOTH);
        titleLabel.setIcon(new ImageIcon(resizedImageTitle));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // We add the alignment to the center for the title
        titleLabel.setVisible(true);

        this.add(Box.createVerticalStrut(50)); // Space at the top
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(70)); // Add space between the title and the buttons

        newGameButton.setBackground(new java.awt.Color(0, 0, 0,0));
        newGameButton.setBorder(null);
        ImageIcon iconNewGameButton = new ImageIcon("../ASSETS/PrincipalMenu/button_NuevaPartida.png");
        Image resizedImageNewGameButton = iconNewGameButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        newGameButton.setIcon(new ImageIcon(resizedImageNewGameButton));
        newGameButton.setContentAreaFilled(false);
        ImageIcon iconNewGameButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_NuevaPartida_pressed.png");
        Image resizedImageNewGameButton_pressed = iconNewGameButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        newGameButton.setPressedIcon(new ImageIcon(resizedImageNewGameButton_pressed));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));

        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                newGameButtonActionPerformed();
            }
        });
        this.add(newGameButton);

        if (presentationController.savedGameExist()) {
            ImageIcon iconContinueGameButton = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida.png");
            Image resizedImageContinueGameButton = iconContinueGameButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setIcon(new ImageIcon(resizedImageContinueGameButton));
            ImageIcon iconContinueGameButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida_pressed.png");
            Image resizedImageContinueGameButton_pressed = iconContinueGameButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setPressedIcon(new ImageIcon(resizedImageContinueGameButton_pressed));
            continueGameButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent event) {
                    continueGameButtonActionPerformed();
                }
            });
        }
        else {
            ImageIcon iconContinueGameButton = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida_pressed.png");
            Image resizedImageContinueGameButton = iconContinueGameButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setIcon(new ImageIcon(resizedImageContinueGameButton));
        }
        continueGameButton.setBackground(new java.awt.Color(0, 0, 0,0));
        continueGameButton.setBorder(null);
        continueGameButton.setContentAreaFilled(false);
        continueGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));
        this.add(continueGameButton);

        rankingButton.setBackground(new java.awt.Color(0, 0, 0,0)); //tmp
        rankingButton.setBorder(null);
        ImageIcon iconRankingButton = new ImageIcon("../ASSETS/PrincipalMenu/button_Ranking.png");
        Image resizedImageRankingButton = iconRankingButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        rankingButton.setIcon(new ImageIcon(resizedImageRankingButton));
        rankingButton.setContentAreaFilled(false);
        ImageIcon iconRankingButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_Ranking_pressed.png");
        Image resizedImageRankingButton_pressed = iconRankingButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        rankingButton.setPressedIcon(new ImageIcon(resizedImageRankingButton_pressed));
        rankingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));

        rankingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                rankingButtonActionPerformed();
            }
        });
        this.add(rankingButton);

        recordsButton.setBackground(new java.awt.Color(0, 0, 0,0)); //tmp
        recordsButton.setBorder(null);
        ImageIcon iconRecordsButton = new ImageIcon("../ASSETS/PrincipalMenu/button_Records.png");
        Image resizedImageRecordsButton = iconRecordsButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        recordsButton.setIcon(new ImageIcon(resizedImageRecordsButton));
        recordsButton.setContentAreaFilled(false);
        ImageIcon iconRecordsButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_Records_pressed.png");
        Image resizedImageRecordsButton_pressed = iconRecordsButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        recordsButton.setPressedIcon(new ImageIcon(resizedImageRecordsButton_pressed));
        recordsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));

        recordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                recordsButtonActionPerformed();
            }
        });
        this.add(recordsButton);

        exitButton.setBackground(new java.awt.Color(0, 0, 0,0)); //tmp
        exitButton.setBorder(null);
        ImageIcon iconExitButton = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir.png");
        Image resizedImageExitButton = iconExitButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(resizedImageExitButton));
        exitButton.setContentAreaFilled(false);
        ImageIcon iconExitButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_Salir_pressed.png");
        Image resizedImageExitButton_pressed = iconExitButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        exitButton.setPressedIcon(new ImageIcon(resizedImageExitButton_pressed));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(15));

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                exitButtonActionPerformed();
            }
        });
        this.add(exitButton);

        this.add(Box.createVerticalGlue()); // We add space at the end
    }

    public List<Map.Entry<String, Integer>> getRankingCM() {
        return presentationController.getRankingCM();
    }
    public List<Map.Entry<String, Integer>> getRankingCB() {
        return presentationController.getRankingCB();
    }
    public Map<String, Map.Entry<String, Integer>> getRecords() {
        return presentationController.getRecords();
    }

    public void newGameButtonActionPerformed() {
        presentationController.synchronizationPrincipalMenuToNewGameOptions();
    }

    public void continueGameButtonActionPerformed() {
        presentationController.loadSavedGame();
        presentationController.synchronizationPrincipalMenuToInGame();
    }

    public void rankingButtonActionPerformed() {
        titleLabel.setVisible(false);
        viewRanking.initializeComponents();
    }

    public void recordsButtonActionPerformed() {
        titleLabel.setVisible(false);
        viewRecords.initializeComponents();
    }

    public void exitButtonActionPerformed() {
        presentationController.exitGame();
        System.exit(0);
    }

    public void updateContinueButton() {
        if (presentationController.savedGameExist()) {
            ImageIcon iconContinueGameButton = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida.png");
            Image resizedImageContinueGameButton = iconContinueGameButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setIcon(new ImageIcon(resizedImageContinueGameButton));
            ImageIcon iconContinueGameButton_pressed = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida_pressed.png");
            Image resizedImageContinueGameButton_pressed = iconContinueGameButton_pressed.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setPressedIcon(new ImageIcon(resizedImageContinueGameButton_pressed));
            continueGameButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent event) {
                    continueGameButtonActionPerformed();
                }
            });
        }
        else {
            ImageIcon iconContinueGameButton = new ImageIcon("../ASSETS/PrincipalMenu/button_ContinuarPartida_pressed.png");
            Image resizedImageContinueGameButton = iconContinueGameButton.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
            continueGameButton.setIcon(new ImageIcon(resizedImageContinueGameButton));
        }
        continueGameButton.setBackground(new java.awt.Color(0, 0, 0,0));
        continueGameButton.setBorder(null);
        continueGameButton.setContentAreaFilled(false);
        continueGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
