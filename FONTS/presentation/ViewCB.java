package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;


public class ViewCB extends javax.swing.JLayeredPane {
    public InGame inGame;

    private JButton deleteCodeButton;
    private JButton acceptCodeButton;
    private JButton pauseButton;
    private JButton hintButton;
    private JButton surrenderCodeButton;
    private JLabel colorsPanel;

    private JLabel[] displayedCode;
    private String currentColor;
    private int maxBalls;
    private int maxTurns;
    private int currentTurn;
    private final AbstractMap.SimpleEntry<Integer,Integer>[][] coordsColors;
    private final AbstractMap.SimpleEntry<Integer,Integer>[][] coordsFeedback;
    private final AbstractMap.SimpleEntry<Integer,Integer>[] coordsButtonPositions;
    int feedbackSize = 7;

    //Color Buttons
    private JButton redButton;
    private JButton orangeButton;
    private JButton blueButton;
    private JButton yellowButton;
    private JButton whiteButton;
    private JButton greenButton;
    private JButton pinkButton;
    private JButton purpleButton;

    public ViewCB(InGame inGame, int maxBalls, int maxTurns, AbstractMap.SimpleEntry<Integer,Integer>[][] coordsColors, AbstractMap.SimpleEntry<Integer,Integer>[][] coordsFeedback, AbstractMap.SimpleEntry<Integer,Integer>[] coordsButtonPositions) {
        this.setBackground(new java.awt.Color(0,0,0,0));
        this.setPreferredSize(new Dimension(1024,720));
        this.maxBalls = maxBalls;
        this.maxTurns = maxTurns;
        this.coordsColors = coordsColors;
        this.coordsFeedback = coordsFeedback;
        this.coordsButtonPositions = coordsButtonPositions;
        this.inGame = inGame;
        this.currentTurn= inGame.getTurn();
        this.currentColor=null;
        this.displayedCode = new JLabel[maxBalls];

        this.initializeComponents();
        this.configurePositionButtons();
    }

    private void printSecretCode() {
        String[] secretCode = inGame.getSecretCode();
        for (int i = 0; i < maxBalls; ++i) {
            JLabel color = new JLabel();
            String path = "../ASSETS/InGame/"+secretCode[i].toLowerCase()+"_ball.png";
            ImageIcon iconColor = new ImageIcon(path);
            Image resizedImageColor = iconColor.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            color.setIcon(new ImageIcon(resizedImageColor));
            color.setBounds(coordsColors[0][i].getKey()-5, coordsColors[0][i].getValue()-5, 65,65);
            this.add(color);
        }
    }

    private void printColor(int codePos) {
        int x = coordsColors[currentTurn][codePos].getKey();
        int y = coordsColors[currentTurn][codePos].getValue();
        JLabel color = new JLabel();
        String path = "../ASSETS/InGame/"+currentColor+"_ball.png";
        ImageIcon iconColor = new ImageIcon(path);
        Image resizedImageColor = iconColor.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        color.setIcon(new ImageIcon(resizedImageColor));
        color.setBounds(x,y,50,50);
        displayedCode[codePos] = color;
        this.add(color,10);
    }

    private void printFeedback(int turn, boolean win) {
        Map.Entry<Integer,Integer> currentFd = inGame.getNewFeedback();
        AbstractMap.SimpleEntry<Integer, Integer>[] entry = new AbstractMap.SimpleEntry[0];
        if(win) entry = coordsFeedback[turn-1];
        if(!win) entry = coordsFeedback[turn-2];
        for (int i = 0; i < currentFd.getKey(); ++i) {
            int x = entry[i].getKey();
            int y = entry[i].getValue();
            JLabel pin = new JLabel();
            String path = "../ASSETS/InGame/feedback_red.png";
            ImageIcon iconColor = new ImageIcon(path);
            Image resizedImageColor = iconColor.getImage().getScaledInstance(feedbackSize, feedbackSize, Image.SCALE_SMOOTH);
            pin.setIcon(new ImageIcon(resizedImageColor));
            pin.setBounds(x,y,feedbackSize,feedbackSize);
            this.add(pin);
        }
        for (int i = 0; i < currentFd.getValue(); ++i) {
            int x = entry[i].getKey();
            int y = entry[i].getValue();
            JLabel pin = new JLabel();
            String path = "../ASSETS/InGame/feedback_white.png";
            ImageIcon iconColor = new ImageIcon(path);
            Image resizedImageColor = iconColor.getImage().getScaledInstance(feedbackSize, feedbackSize, Image.SCALE_SMOOTH);
            pin.setIcon(new ImageIcon(resizedImageColor));
            pin.setBounds(x,y,feedbackSize,feedbackSize);
            this.add(pin);
        }
    }

    private void cleanCode() {
        for (int i = 0; i < maxBalls; ++i) {
            if (displayedCode[i] != null) this.remove(displayedCode[i]);
        }
        this.revalidate();
        this.repaint();
    }

    private JButton getButtonFromString(String color) {
        switch (color) {
            case "orange": {
                return orangeButton;
            }
            case "blue": {
                return blueButton;
            }
            case "red": {
                return redButton;
            }
            case "yellow": {
                return yellowButton;
            }
            case "green": {
                return greenButton;
            }
            case "white": {
                return whiteButton;
            }
            case "pink": {
                return pinkButton;
            }
            case "purple": {
                return purpleButton;
            }
        }
        return null;
    }

    private void initColorButtons() {
        int ballSize = 65;
        int ballY = 580;
        int ballXInit = 332;
        int ballOff = 42;
        // Orange button
        orangeButton = new JButton();
        orangeButton.setBackground(new java.awt.Color(0,0,0,0));
        orangeButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconOrange = new ImageIcon("../ASSETS/InGame/orange_ball.png");
        Image resizedImageOrange = iconOrange.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        orangeButton.setIcon(new ImageIcon(resizedImageOrange));
        orangeButton.setContentAreaFilled(false);
        orangeButton.setBounds(ballXInit,ballY,ballSize,ballSize);
        orangeButton.setFocusPainted(false);
        orangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconOrange = new ImageIcon("../ASSETS/InGame/orange_ball_pressed.png");
                Image resizedImageOrange = iconOrange.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                orangeButton.setIcon(new ImageIcon(resizedImageOrange));
                String newColor = "orange";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconOrange = new ImageIcon("../ASSETS/InGame/orange_ball.png");
                    resizedImageOrange = iconOrange.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    orangeButton.setIcon(new ImageIcon(resizedImageOrange));
                    currentColor = null;
                }
                else {
                    currentColor = "orange";
                }
            }
        });

        // Blue button
        blueButton = new JButton();
        blueButton.setBackground(new java.awt.Color(0,0,0,0));
        blueButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconBlue = new ImageIcon("../ASSETS/InGame/blue_ball.png");
        Image resizedImageBlue = iconBlue.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        blueButton.setIcon(new ImageIcon(resizedImageBlue));
        blueButton.setContentAreaFilled(false);
        blueButton.setBounds(ballXInit+ballOff,ballY,ballSize,ballSize);
        blueButton.setFocusPainted(false);
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconBlue = new ImageIcon("../ASSETS/InGame/blue_ball_pressed.png");
                Image resizedImageBlue = iconBlue.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                blueButton.setIcon(new ImageIcon(resizedImageBlue));
                String newColor = "blue";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconBlue = new ImageIcon("../ASSETS/InGame/blue_ball.png");
                    resizedImageBlue = iconBlue.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    blueButton.setIcon(new ImageIcon(resizedImageBlue));
                    currentColor = null;
                }
                else {
                    currentColor = "blue";
                }
            }
        });

        // Green button
        greenButton = new JButton();
        greenButton.setBackground(new java.awt.Color(0,0,0,0));
        greenButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconGreen = new ImageIcon("../ASSETS/InGame/green_ball.png");
        Image resizedImageGreen = iconGreen.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        greenButton.setIcon(new ImageIcon(resizedImageGreen));
        greenButton.setContentAreaFilled(false);
        greenButton.setBounds(ballXInit+2*ballOff,ballY,ballSize,ballSize);
        greenButton.setFocusPainted(false);
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconGreen = new ImageIcon("../ASSETS/InGame/green_ball_pressed.png");
                Image resizedImageGreen = iconGreen.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                greenButton.setIcon(new ImageIcon(resizedImageGreen));
                String newColor = "green";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconGreen = new ImageIcon("../ASSETS/InGame/green_ball.png");
                    resizedImageGreen = iconGreen.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    greenButton.setIcon(new ImageIcon(resizedImageGreen));
                    currentColor = null;
                }
                else {
                    currentColor = "green";
                }
            }
        });


        // Pink button
        pinkButton = new JButton();
        pinkButton.setBackground(new java.awt.Color(0,0,0,0));
        pinkButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconPink = new ImageIcon("../ASSETS/InGame/pink_ball.png");
        Image resizedImagePink = iconPink.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        pinkButton.setIcon(new ImageIcon(resizedImagePink));
        pinkButton.setContentAreaFilled(false);
        pinkButton.setBounds(ballXInit+3*ballOff,ballY,ballSize,ballSize);
        pinkButton.setFocusPainted(false);
        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconPink = new ImageIcon("../ASSETS/InGame/pink_ball_pressed.png");
                Image resizedImagePink = iconPink.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                pinkButton.setIcon(new ImageIcon(resizedImagePink));
                String newColor = "pink";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconPink = new ImageIcon("../ASSETS/InGame/pink_ball.png");
                    resizedImagePink = iconPink.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    pinkButton.setIcon(new ImageIcon(resizedImagePink));
                    currentColor = null;
                }
                else {
                    currentColor = "pink";
                }
            }
        });

        // Red button
        redButton = new JButton();
        redButton.setBackground(new java.awt.Color(0,0,0,0));
        redButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconRed = new ImageIcon("../ASSETS/InGame/red_ball.png");
        Image resizedImageRed = iconRed.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        redButton.setIcon(new ImageIcon(resizedImageRed));
        redButton.setContentAreaFilled(false);
        redButton.setBounds(ballXInit+4*ballOff,ballY,ballSize,ballSize);
        redButton.setFocusPainted(false);
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconRed = new ImageIcon("../ASSETS/InGame/red_ball_pressed.png");
                Image resizedImageRed = iconRed.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                redButton.setIcon(new ImageIcon(resizedImageRed));
                String newColor = "red";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconRed = new ImageIcon("../ASSETS/InGame/red_ball.png");
                    resizedImageRed = iconRed.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    redButton.setIcon(new ImageIcon(resizedImageRed));
                    currentColor = null;
                }
                else {
                    currentColor = "red";
                }
            }
        });

        // White button
        whiteButton = new JButton();
        whiteButton.setBackground(new java.awt.Color(0,0,0,0));
        whiteButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconWhite = new ImageIcon("../ASSETS/InGame/white_ball.png");
        Image resizedImageWhite = iconWhite.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        whiteButton.setIcon(new ImageIcon(resizedImageWhite));
        whiteButton.setContentAreaFilled(false);
        whiteButton.setBounds(ballXInit+5*ballOff,ballY,ballSize,ballSize);
        whiteButton.setFocusPainted(false);
        whiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconWhite = new ImageIcon("../ASSETS/InGame/white_ball_pressed.png");
                Image resizedImageWhite = iconWhite.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                whiteButton.setIcon(new ImageIcon(resizedImageWhite));
                String newColor = "white";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconWhite = new ImageIcon("../ASSETS/InGame/white_ball.png");
                    resizedImageWhite = iconWhite.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    whiteButton.setIcon(new ImageIcon(resizedImageWhite));
                    currentColor = null;
                }
                else {
                    currentColor = "white";
                }
            }
        });

        // Yellow button
        yellowButton = new JButton();
        yellowButton.setBackground(new java.awt.Color(0,0,0,0));
        yellowButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconYellow = new ImageIcon("../ASSETS/InGame/yellow_ball.png");
        Image resizedImageYellow = iconYellow.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        yellowButton.setIcon(new ImageIcon(resizedImageYellow));
        yellowButton.setContentAreaFilled(false);
        yellowButton.setBounds(ballXInit+6*ballOff,ballY,ballSize,ballSize);
        yellowButton.setFocusPainted(false);
        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconYellow = new ImageIcon("../ASSETS/InGame/yellow_ball_pressed.png");
                Image resizedImageYellow = iconYellow.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                yellowButton.setIcon(new ImageIcon(resizedImageYellow));
                String newColor = "yellow";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconYellow = new ImageIcon("../ASSETS/InGame/yellow_ball.png");
                    resizedImageYellow = iconYellow.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    yellowButton.setIcon(new ImageIcon(resizedImageYellow));
                    currentColor = null;
                }
                else {
                    currentColor = "yellow";
                }
            }
        });

        // Purple button
        purpleButton = new JButton();
        purpleButton.setBackground(new java.awt.Color(0,0,0,0));
        purpleButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconPurple = new ImageIcon("../ASSETS/InGame/purple_ball.png");
        Image resizedImagePurple = iconPurple.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
        purpleButton.setIcon(new ImageIcon(resizedImagePurple));
        purpleButton.setContentAreaFilled(false);
        purpleButton.setBounds(ballXInit+7*ballOff,ballY,ballSize,ballSize);
        purpleButton.setFocusPainted(false);
        purpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon iconPurple = new ImageIcon("../ASSETS/InGame/purple_ball_pressed.png");
                Image resizedImagePurple = iconPurple.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                purpleButton.setIcon(new ImageIcon(resizedImagePurple));
                String newColor = "purple";
                if (!newColor.equals(currentColor) && currentColor != null) {
                    String path = "../ASSETS/InGame/" + currentColor + "_ball.png";
                    ImageIcon icon = new ImageIcon(path);
                    Image resizedImage = icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    getButtonFromString(currentColor).setIcon(new ImageIcon(resizedImage));
                    currentColor = newColor;
                } else if(currentColor != null) {
                    iconPurple = new ImageIcon("../ASSETS/InGame/purple_ball.png");
                    resizedImagePurple = iconPurple.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_SMOOTH);
                    purpleButton.setIcon(new ImageIcon(resizedImagePurple));
                    currentColor = null;
                }
                else {
                    currentColor = "purple";
                }
            }
        });
    }

    private void initButtons() {
        // Initialize main buttons
        pauseButton = new JButton();
        deleteCodeButton = new JButton();
        acceptCodeButton = new JButton();
        hintButton = new JButton();
        surrenderCodeButton = new JButton();

        // Pause button
        pauseButton.setBackground(new java.awt.Color(0,0,0,0));
        pauseButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconPause = new ImageIcon("../ASSETS/InGame/button_pause.png");
        Image resizedImagePause = iconPause.getImage().getScaledInstance(190, 40, Image.SCALE_SMOOTH);
        pauseButton.setIcon(new ImageIcon(resizedImagePause));
        pauseButton.setContentAreaFilled(false);
        ImageIcon iconPausePressed = new ImageIcon("../ASSETS/InGame/button_pause_pressed.png");
        Image resizedImagePausePressed = iconPausePressed.getImage().getScaledInstance(190, 40, Image.SCALE_SMOOTH);
        pauseButton.setPressedIcon(new ImageIcon(resizedImagePausePressed));
        pauseButton.setFocusPainted(false);
        pauseButton.setBounds(800,10,190,40);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inGame.notifyPause();
            }
        });

        // Correct button
        acceptCodeButton.setBackground(new java.awt.Color(0,0,0,0));
        acceptCodeButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconAcceptCode = new ImageIcon("../ASSETS/InGame/button_confirm.png");
        Image resizedImageAcceptCode = iconAcceptCode.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        acceptCodeButton.setIcon(new ImageIcon(resizedImageAcceptCode));
        acceptCodeButton.setContentAreaFilled(false);
        ImageIcon iconSurrenderAcceptCode = new ImageIcon("../ASSETS/InGame/button_confirm_pressed.png");
        Image resizedImageAcceptCodePressed = iconSurrenderAcceptCode.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        acceptCodeButton.setPressedIcon(new ImageIcon(resizedImageAcceptCodePressed));
        acceptCodeButton.setBounds(725,590,45,45);
        acceptCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inGame.notifyConfirmCode()){
                    printSecretCode();
                    printFeedback(inGame.getTurn(), maxTurns == inGame.getTurn());
                    inGame.notifyWin();
                }
                else if (inGame.validCode()) {
                    ++currentTurn;
                    displayedCode = new JLabel[maxBalls];
                    if (currentTurn > maxTurns) {
                        printFeedback(inGame.getTurn(),true);
                        printSecretCode();
                        inGame.notifyLose();
                    }
                    else {
                        printFeedback(inGame.getTurn(),false);
                    }
                }

            }
        });

        // Clean button
        deleteCodeButton.setBackground(new java.awt.Color(0,0,0,0));
        deleteCodeButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconDeleteCode = new ImageIcon("../ASSETS/InGame/button_delete.png");
        Image resizedImageDeleteCode = iconDeleteCode.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        deleteCodeButton.setIcon(new ImageIcon(resizedImageDeleteCode));
        deleteCodeButton.setContentAreaFilled(false);
        ImageIcon iconSurrenderDeleteCode = new ImageIcon("../ASSETS/InGame/button_delete_pressed.png");
        Image resizedImageDeleteCodePressed = iconSurrenderDeleteCode.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        deleteCodeButton.setPressedIcon(new ImageIcon(resizedImageDeleteCodePressed));
        deleteCodeButton.setBounds(260,590,45,45);
        deleteCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inGame.notifyCleanCode();
                cleanCode();
            }
        });

        // Hint button
        hintButton.setBackground(new java.awt.Color(0,0,0,0));
        hintButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconHint = new ImageIcon("../ASSETS/InGame/button_hint.png");
        Image resizedImageHint = iconHint.getImage().getScaledInstance(190, 40, Image.SCALE_SMOOTH);
        hintButton.setIcon(new ImageIcon(resizedImageHint));
        hintButton.setContentAreaFilled(false);
        ImageIcon iconHintPressed = new ImageIcon("../ASSETS/InGame/button_hint_pressed.png");
        Image resizedImageHintPressed = iconHintPressed.getImage().getScaledInstance(190, 40, Image.SCALE_SMOOTH);
        hintButton.setPressedIcon(new ImageIcon(resizedImageHintPressed));
        hintButton.setFocusPainted(false);
        hintButton.setBounds(800,660,190,40);
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inGame.notifyAskForHint();
                String[] code = inGame.getPlayerCurrentCode();
                printCode(code);
            }
        });

        // Surrender button
        surrenderCodeButton.setBackground(new java.awt.Color(0,0,0,0));
        surrenderCodeButton.setBorder(new EmptyBorder(10,10,10,10));
        ImageIcon iconSurrender = new ImageIcon("../ASSETS/InGame/button_surrender.png");
        Image resizedImageSurrender = iconSurrender.getImage().getScaledInstance(300, 40, Image.SCALE_SMOOTH);
        surrenderCodeButton.setIcon(new ImageIcon(resizedImageSurrender));
        surrenderCodeButton.setContentAreaFilled(false);
        ImageIcon iconSurrenderPressed = new ImageIcon("../ASSETS/InGame/button_surrender_pressed.png");
        Image resizedImageSurrenderPressed = iconSurrenderPressed.getImage().getScaledInstance(300, 40, Image.SCALE_SMOOTH);
        surrenderCodeButton.setPressedIcon(new ImageIcon(resizedImageSurrenderPressed));
        surrenderCodeButton.setFocusPainted(false);
        surrenderCodeButton.setBounds(15,660,300,40);
        surrenderCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inGame.notifySurrender();
            }
        });

        initColorButtons();
    }

    private void initLabels() {
        // Initialize labels
        colorsPanel = new JLabel();
        colorsPanel.setBorder(null);
        colorsPanel.setBackground(new java.awt.Color(0,0,0,0));
        ImageIcon iconColorsPanel = new ImageIcon("../ASSETS/InGame/color_positions.png");
        Image resizedImageColorsPanel = iconColorsPanel.getImage().getScaledInstance(600, 70, Image.SCALE_SMOOTH);
        colorsPanel.setIcon(new ImageIcon(resizedImageColorsPanel));
        colorsPanel.setBounds(217,578,600,70);
    }

    private void initializeComponents() {
        this.removeAll();
        this.setLayout(null);

        initLabels();
        initButtons();

        // Add elements to the principal JPanel
        this.add(surrenderCodeButton,4);
        this.add(hintButton,2);
        this.add(deleteCodeButton,1);
        this.add(acceptCodeButton,1);
        this.add(blueButton,1);
        this.add(orangeButton,2);
        this.add(pinkButton,3);
        this.add(whiteButton,4);
        this.add(greenButton,5);
        this.add(yellowButton,6);
        this.add(redButton,7);
        this.add(colorsPanel,11);
        this.add(pauseButton,8);
        this.add(purpleButton,7);
    }

    private void configurePositionButtons() {
        for (int i = 1; i <= maxBalls; ++i) {
            JButton positionButton = new JButton();
            positionButton.setBackground(new java.awt.Color(0,0,0,0));
            positionButton.setBorder(new EmptyBorder(10,10,10,10));
            String path = ("../ASSETS/InGame/positions/"+i+".png");
            ImageIcon iconPause = new ImageIcon(path);
            Image resizedImagePause = iconPause.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            positionButton.setIcon(new ImageIcon(resizedImagePause));
            positionButton.setContentAreaFilled(false);
            positionButton.setBounds(coordsButtonPositions[i-1].getKey(),coordsButtonPositions[i-1].getValue(),30,30);
            positionButton.setFocusPainted(false);
            positionButton.setName(String.valueOf(i));
            positionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    printColor(Integer.parseInt(positionButton.getName())-1);
                    if(currentColor != null) inGame.notifyNewColor(Integer.parseInt(positionButton.getName())-1,currentColor);
                }
            });
            this.add(positionButton);
        }
    }

    private void printCode(String[] code) {
        for(int i = 0; i < code.length; ++i){
            int x = coordsColors[currentTurn][i].getKey();
            int y = coordsColors[currentTurn][i].getValue();
            JLabel color = new JLabel();
            String path = "../ASSETS/InGame/"+code[i].toLowerCase()+"_ball.png";
            ImageIcon iconColor = new ImageIcon(path);
            Image resizedImageColor = iconColor.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            color.setIcon(new ImageIcon(resizedImageColor));
            color.setBounds(x,y,50,50);
            if (displayedCode[i] != null)this.remove(displayedCode[i]);
            displayedCode[i] = color;
            this.add(color);
        }
        this.revalidate();
        this.repaint();
    }

    public void printBoard(String[][] domainBoard, List<Map.Entry<Integer,Integer>> domainFeedback) {
        for(int i = 0; i < domainBoard.length && domainBoard[i][0] != null; ++i) {
            for(int j = 0; j < domainBoard[0].length; ++j) {
                int x = coordsColors[i+1][j].getKey();
                int y = coordsColors[i+1][j].getValue();
                JLabel color = new JLabel();
                String path = "../ASSETS/InGame/"+domainBoard[i][j].toLowerCase()+"_ball.png";
                ImageIcon iconColor = new ImageIcon(path);
                Image resizedImageColor = iconColor.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                color.setIcon(new ImageIcon(resizedImageColor));
                color.setBounds(x,y,50,50);
                this.add(color);
            }
            AbstractMap.SimpleEntry<Integer, Integer>[] entry = coordsFeedback[i];
            for (int j = 0; j < domainFeedback.get(i).getKey() && domainFeedback.get(i).getKey() != null; ++j) {
                int x = entry[j].getKey();
                int y = entry[j].getValue();
                JLabel pin = new JLabel();
                String path = "../ASSETS/InGame/feedback_red.png";
                ImageIcon iconColor = new ImageIcon(path);
                Image resizedImageColor = iconColor.getImage().getScaledInstance(feedbackSize, feedbackSize, Image.SCALE_SMOOTH);
                pin.setIcon(new ImageIcon(resizedImageColor));
                pin.setBounds(x,y,feedbackSize,feedbackSize);
                this.add(pin);
            }
            for (int j = 0; j < domainFeedback.get(i).getValue() && domainFeedback.get(i).getValue() != null; ++j) {
                int x = entry[i].getKey();
                int y = entry[i].getValue();
                JLabel pin = new JLabel();
                String path = "../ASSETS/InGame/feedback_white.png";
                ImageIcon iconColor = new ImageIcon(path);
                Image resizedImageColor = iconColor.getImage().getScaledInstance(feedbackSize, feedbackSize, Image.SCALE_SMOOTH);
                pin.setIcon(new ImageIcon(resizedImageColor));
                pin.setBounds(x,y,feedbackSize,feedbackSize);
                this.add(pin);
            }
        }
    }
}
