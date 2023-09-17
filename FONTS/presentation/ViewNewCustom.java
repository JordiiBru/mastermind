package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNewCustom extends javax.swing.JPanel {

    //Data
    private final NewGameOptions newGameOptions;
    private final JLabel background = new JLabel();
    private final String[] values = new String[]{"5guess","false","4","false","8"};
    private final JLabel backgroundImageValues = new JLabel();
    private final JLabel backgroundAlg = new JLabel();
    private final JButton fiveGuess = new JButton();
    private final JButton genetic = new JButton();
    private final JLabel backgroundRepCol = new JLabel();
    private final JButton repColTrue = new JButton();
    private final JButton repColFalse = new JButton();
    private final JLabel backgroundEmpCol = new JLabel();
    private final JButton empColTrue = new JButton();
    private final JButton empColFalse = new JButton();
    private final JLabel backgroundNumCol = new JLabel();
    private final JLabel infoNumCol = new JLabel();
    private final JLabel textNumCol = new JLabel();
    private final JButton addNumCol = new JButton();
    private final JButton subNumCol = new JButton();
    private final JLabel backgroundMaxTur = new JLabel();
    private final JLabel infoMaxTur = new JLabel();
    private final JLabel textMaxTur = new JLabel();
    private final JButton addMaxTur = new JButton();
    private final JButton subMaxTur = new JButton();
    private final JButton exit = new JButton();
    private final JButton confirm = new JButton();
    public ViewNewCustom(NewGameOptions newGameOptions) {
        this.newGameOptions = newGameOptions;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1024, 720));
        this.setBackground(new java.awt.Color(0,0,0,0));
        initialize();
    }

    private void initialize() {
        int buttonSizeX = 300;
        int buttonSizeY = 60;
        double scaleLabel = 0.75;
        double scaleButton = 0.75;
        //Shared Button images
        ImageIcon iconFiveGuess = new ImageIcon("../ASSETS/NewGameOptions/button_5guess.png");
        Image resizedFiveGuess = iconFiveGuess.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconGenetic = new ImageIcon("../ASSETS/NewGameOptions/button_genetic.png");
        Image resizedGenetic = iconGenetic.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconTrue = new ImageIcon("../ASSETS/NewGameOptions/button_true.png");
        Image resizedTrue = iconTrue.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconTrue_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_true_pressed.png");
        Image resizedTrue_pressed = iconTrue_pressed.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconFalse = new ImageIcon("../ASSETS/NewGameOptions/button_false.png");
        Image resizedFalse = iconFalse.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconFalse_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_false_pressed.png");
        Image resizedFalse_pressed = iconFalse_pressed.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        ImageIcon iconAdd = new ImageIcon("../ASSETS/NewGameOptions/button_add.png");
        Image resizedAdd = iconAdd.getImage().getScaledInstance((int) (iconAdd.getIconWidth()*scaleLabel), (int) (iconAdd.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        ImageIcon iconAdd_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_add_pressed.png");
        Image resizedAdd_pressed = iconAdd_pressed.getImage().getScaledInstance((int) (iconAdd.getIconWidth()*scaleLabel), (int) (iconAdd.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        ImageIcon iconSub = new ImageIcon("../ASSETS/NewGameOptions/button_sub.png");
        Image resizedSub = iconSub.getImage().getScaledInstance((int) (iconSub.getIconWidth()*scaleLabel), (int) (iconSub.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        ImageIcon iconSub_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_sub_pressed.png");
        Image resizedSub_pressed = iconSub_pressed.getImage().getScaledInstance((int) (iconSub.getIconWidth()*scaleLabel), (int) (iconSub.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        //Selection Label
        backgroundImageValues.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundImageValues.setBorder(null);
        ImageIcon iconBackgroundImageValues = new ImageIcon("../ASSETS/NewGameOptions/text_selecValues.png");
        Image resizedImageBackgroundImageValues = iconBackgroundImageValues.getImage().getScaledInstance((int) (iconBackgroundImageValues.getIconWidth()*0.65), (int) (iconBackgroundImageValues.getIconHeight()*0.65), Image.SCALE_SMOOTH);
        backgroundImageValues.setIcon(new ImageIcon(resizedImageBackgroundImageValues));
        backgroundImageValues.setLocation(208,20);
        backgroundImageValues.setSize((int) (iconBackgroundImageValues.getIconWidth()*0.65), (int) (iconBackgroundImageValues.getIconHeight()*0.65));
        this.add(backgroundImageValues);
        //Algorithm Label
        backgroundAlg.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundAlg.setBorder(null);
        ImageIcon iconBackgroundAlg = new ImageIcon("../ASSETS/NewGameOptions/text_alg.png");
        Image resizedImageBackgroundAlg = iconBackgroundAlg.getImage().getScaledInstance((int) (iconBackgroundAlg.getIconWidth()*scaleLabel), (int) (iconBackgroundAlg.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        backgroundAlg.setIcon(new ImageIcon(resizedImageBackgroundAlg));
        backgroundAlg.setLocation(94,150);
        backgroundAlg.setSize((int) (iconBackgroundAlg.getIconWidth()*scaleLabel), (int) (iconBackgroundAlg.getIconHeight()*scaleLabel));
        this.add(backgroundAlg);
        //Button 5Guess
        fiveGuess.setBackground(new java.awt.Color(0, 0, 0,0));
        fiveGuess.setBorder(null);
        fiveGuess.setContentAreaFilled(false);
        ImageIcon iconFiveGuess_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_5guess_pressed.png");
        Image resizedFiveGuess_pressed = iconFiveGuess_pressed.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        fiveGuess.setIcon(new ImageIcon(resizedFiveGuess_pressed));
        fiveGuess.setPressedIcon(new ImageIcon(resizedFiveGuess_pressed));
        fiveGuess.setLocation(20,220);
        fiveGuess.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        fiveGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fiveGuess.setIcon(new ImageIcon(resizedFiveGuess_pressed));
                genetic.setIcon(new ImageIcon(resizedGenetic));
                values[0] = "5guess";
            }
        });
        this.add(fiveGuess);
        //Button Genetic
        genetic.setBackground(new java.awt.Color(0, 0, 0,0));
        genetic.setBorder(null);
        genetic.setIcon(new ImageIcon(resizedGenetic));
        genetic.setContentAreaFilled(false);
        ImageIcon iconGenetic_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_genetic_pressed.png");
        Image resizedGenetic_pressed = iconGenetic_pressed.getImage().getScaledInstance((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton), Image.SCALE_SMOOTH);
        genetic.setPressedIcon(new ImageIcon(resizedGenetic_pressed));
        genetic.setLocation(265,220);
        genetic.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        genetic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genetic.setIcon(new ImageIcon(resizedGenetic_pressed));
                fiveGuess.setIcon(new ImageIcon(resizedFiveGuess));
                values[0] = "genetic";
            }
        });
        this.add(genetic);
        //Repeated Colors Label
        backgroundRepCol.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundRepCol.setBorder(null);
        ImageIcon iconBackgroundRepCol = new ImageIcon("../ASSETS/NewGameOptions/text_repCol.png");
        Image resizedImageBackgroundRepCol = iconBackgroundRepCol.getImage().getScaledInstance((int) (iconBackgroundRepCol.getIconWidth()*scaleLabel), (int) (iconBackgroundRepCol.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        backgroundRepCol.setIcon(new ImageIcon(resizedImageBackgroundRepCol));
        backgroundRepCol.setLocation(608,150);
        backgroundRepCol.setSize((int) (iconBackgroundRepCol.getIconWidth()*scaleLabel), (int) (iconBackgroundRepCol.getIconHeight()*scaleLabel));
        this.add(backgroundRepCol);
        //Button True
        repColTrue.setBackground(new java.awt.Color(0, 0, 0,0));
        repColTrue.setBorder(null);
        repColTrue.setIcon(new ImageIcon(resizedTrue));
        repColTrue.setContentAreaFilled(false);
        repColTrue.setPressedIcon(new ImageIcon(resizedTrue_pressed));
        repColTrue.setLocation(534,220);
        repColTrue.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        repColTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repColTrue.setIcon(new ImageIcon(resizedTrue_pressed));
                repColFalse.setIcon(new ImageIcon(resizedFalse));
                values[1] = "true";
            }
        });
        this.add(repColTrue);
        //Button False
        repColFalse.setBackground(new java.awt.Color(0, 0, 0,0));
        repColFalse.setBorder(null);
        repColFalse.setIcon(new ImageIcon(resizedFalse_pressed));
        repColFalse.setContentAreaFilled(false);
        repColFalse.setPressedIcon(new ImageIcon(resizedFalse_pressed));
        repColFalse.setLocation(779,220);
        repColFalse.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        repColFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repColFalse.setIcon(new ImageIcon(resizedFalse_pressed));
                repColTrue.setIcon(new ImageIcon(resizedTrue));
                values[1] = "false";
            }
        });
        this.add(repColFalse);
        //Empty Colors Label
        backgroundEmpCol.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundEmpCol.setBorder(null);
        ImageIcon iconBackgroundEmpCol = new ImageIcon("../ASSETS/NewGameOptions/text_empCol.png");
        Image resizedImageBackgroundEmpCol = iconBackgroundEmpCol.getImage().getScaledInstance((int) (iconBackgroundEmpCol.getIconWidth()*scaleLabel), (int) (iconBackgroundEmpCol.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        backgroundEmpCol.setIcon(new ImageIcon(resizedImageBackgroundEmpCol));
        backgroundEmpCol.setLocation(352,300);
        backgroundEmpCol.setSize((int) (iconBackgroundEmpCol.getIconWidth()*scaleLabel), (int) (iconBackgroundEmpCol.getIconHeight()*scaleLabel));
        this.add(backgroundEmpCol);
        //Button True
        empColTrue.setBackground(new java.awt.Color(0, 0, 0,0));
        empColTrue.setBorder(null);
        empColTrue.setIcon(new ImageIcon(resizedTrue));
        empColTrue.setContentAreaFilled(false);
        empColTrue.setPressedIcon(new ImageIcon(resizedTrue_pressed));
        empColTrue.setLocation(277,370);
        empColTrue.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        empColTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empColTrue.setIcon(new ImageIcon(resizedTrue_pressed));
                empColFalse.setIcon(new ImageIcon(resizedFalse));
                values[3] = "true";
            }
        });
        this.add(empColTrue);
        //Button False
        empColFalse.setBackground(new java.awt.Color(0, 0, 0,0));
        empColFalse.setBorder(null);
        empColFalse.setIcon(new ImageIcon(resizedFalse_pressed));
        empColFalse.setContentAreaFilled(false);
        empColFalse.setPressedIcon(new ImageIcon(resizedFalse_pressed));
        empColFalse.setLocation(522,370);
        empColFalse.setSize((int) (buttonSizeX*scaleButton), (int) (buttonSizeY*scaleButton));
        empColFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empColFalse.setIcon(new ImageIcon(resizedFalse_pressed));
                empColTrue.setIcon(new ImageIcon(resizedTrue));
                values[3] = "false";
            }
        });
        this.add(empColFalse);
        //Number Colors Label
        backgroundNumCol.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundNumCol.setBorder(null);
        ImageIcon iconBackgroundNumCol = new ImageIcon("../ASSETS/NewGameOptions/text_numCol.png");
        Image resizedImageBackgroundNumCol = iconBackgroundNumCol.getImage().getScaledInstance((int) (iconBackgroundNumCol.getIconWidth()*scaleLabel), (int) (iconBackgroundNumCol.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        backgroundNumCol.setIcon(new ImageIcon(resizedImageBackgroundNumCol));
        backgroundNumCol.setLocation(94,450);
        backgroundNumCol.setSize((int) (iconBackgroundNumCol.getIconWidth()*scaleLabel), (int) (iconBackgroundNumCol.getIconHeight()*scaleLabel));
        this.add(backgroundNumCol);
        //Number Colors Text Label
        textNumCol.setBackground(new java.awt.Color(64, 64, 64,255));
        textNumCol.setBorder(null);
        textNumCol.setLocation(237,525);
        textNumCol.setSize((int) (108*0.70), (int) (85*0.70));
        textNumCol.setForeground(Color.WHITE);
        textNumCol.setFont(new java.awt.Font("Serif", Font.BOLD, 45));
        textNumCol.setText(values[2]);
        textNumCol.revalidate();
        this.add(textNumCol);
        //Number Colors Info Label
        infoNumCol.setBackground(new java.awt.Color(0, 0, 0,0));
        infoNumCol.setBorder(null);
        ImageIcon iconBackgroundInfoNumCol = new ImageIcon("../ASSETS/NewGameOptions/text_number_preview.png");
        Image resizedImageBackgroundInfoNumCol = iconBackgroundInfoNumCol.getImage().getScaledInstance((int) (iconBackgroundInfoNumCol.getIconWidth()*scaleLabel), (int) (iconBackgroundInfoNumCol.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        infoNumCol.setIcon(new ImageIcon(resizedImageBackgroundInfoNumCol));
        infoNumCol.setLocation(214,520);
        infoNumCol.setSize((int) (iconBackgroundInfoNumCol.getIconWidth()*scaleLabel), (int) (iconBackgroundInfoNumCol.getIconHeight()*scaleLabel));
        this.add(infoNumCol);
        //Button Add Number Colors
        addNumCol.setBackground(new java.awt.Color(0, 0, 0,0));
        addNumCol.setBorder(null);
        addNumCol.setIcon(new ImageIcon(resizedAdd));
        addNumCol.setContentAreaFilled(false);
        addNumCol.setPressedIcon(new ImageIcon(resizedAdd_pressed));
        addNumCol.setLocation(315,520);
        addNumCol.setSize((int) (iconAdd.getIconWidth()*scaleLabel), (int) (iconAdd.getIconHeight()*scaleLabel));
        addNumCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[2] = String.valueOf(Integer.parseInt(values[2]) + 1);
                if (Integer.parseInt(values[2]) > 7) values[2] = "7";
                textNumCol.setText(values[2]);
                textNumCol.revalidate();
            }
        });
        this.add(addNumCol);
        //Button Sub Number Colors
        subNumCol.setBackground(new java.awt.Color(0, 0, 0,0));
        subNumCol.setBorder(null);
        subNumCol.setIcon(new ImageIcon(resizedSub));
        subNumCol.setContentAreaFilled(false);
        subNumCol.setPressedIcon(new ImageIcon(resizedSub_pressed));
        subNumCol.setLocation(131,545);
        subNumCol.setSize((int) (iconSub.getIconWidth()*scaleLabel), (int) (iconSub.getIconHeight()*scaleLabel));
        subNumCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[2] = String.valueOf(Integer.parseInt(values[2]) - 1);
                if (Integer.parseInt(values[2]) < 1) values[2] = "1";
                textNumCol.setText(values[2]);
                textNumCol.revalidate();
            }
        });
        this.add(subNumCol);
        //Max Turns Label
        backgroundMaxTur.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundMaxTur.setBorder(null);
        ImageIcon iconBackgroundMaxTur = new ImageIcon("../ASSETS/NewGameOptions/text_maxTur.png");
        Image resizedImageBackgroundMaxTur = iconBackgroundMaxTur.getImage().getScaledInstance((int) (iconBackgroundMaxTur.getIconWidth()*scaleLabel), (int) (iconBackgroundMaxTur.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        backgroundMaxTur.setIcon(new ImageIcon(resizedImageBackgroundMaxTur));
        backgroundMaxTur.setLocation(608,450);
        backgroundMaxTur.setSize((int) (iconBackgroundMaxTur.getIconWidth()*scaleLabel), (int) (iconBackgroundMaxTur.getIconHeight()*scaleLabel));
        this.add(backgroundMaxTur);
        //Max Turns Text Label
        textMaxTur.setBackground(new java.awt.Color(64, 64, 64,255));
        textMaxTur.setBorder(null);
        textMaxTur.setLocation(751,525);
        textMaxTur.setSize((int) (108*0.70), (int) (85*0.70));
        textMaxTur.setForeground(Color.WHITE);
        textMaxTur.setFont(new java.awt.Font("Serif", Font.BOLD, 45));
        textMaxTur.setText(values[4]);
        textMaxTur.revalidate();
        this.add(textMaxTur);
        //Max Turns  Info Label
        infoMaxTur.setBackground(new java.awt.Color(0, 0, 0,0));
        infoMaxTur.setBorder(null);
        ImageIcon iconBackgroundInfoMaxTur = new ImageIcon("../ASSETS/NewGameOptions/text_number_preview.png");
        Image resizedImageBackgroundInfoMaxTur = iconBackgroundInfoMaxTur.getImage().getScaledInstance((int) (iconBackgroundInfoMaxTur.getIconWidth()*scaleLabel), (int) (iconBackgroundInfoMaxTur.getIconHeight()*scaleLabel), Image.SCALE_SMOOTH);
        infoMaxTur.setIcon(new ImageIcon(resizedImageBackgroundInfoMaxTur));
        infoMaxTur.setLocation(728,520);
        infoMaxTur.setSize((int) (iconBackgroundInfoMaxTur.getIconWidth()*scaleLabel), (int) (iconBackgroundInfoMaxTur.getIconHeight()*scaleLabel));
        this.add(infoMaxTur);
        //Button Add Max Turns
        addMaxTur.setBackground(new java.awt.Color(0, 0, 0,0));
        addMaxTur.setBorder(null);
        addMaxTur.setIcon(new ImageIcon(resizedAdd));
        addMaxTur.setContentAreaFilled(false);
        addMaxTur.setPressedIcon(new ImageIcon(resizedAdd_pressed));
        addMaxTur.setLocation(829,520);
        addMaxTur.setSize((int) (iconAdd.getIconWidth()*scaleLabel), (int) (iconAdd.getIconHeight()*scaleLabel));
        addMaxTur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[4] = String.valueOf(Integer.parseInt(values[4]) + 1);
                if (Integer.parseInt(values[4]) > 9) {
                    textMaxTur.setLocation(736,525);
                    values[4] = "10";
                }
                else textMaxTur.setLocation(751,525);
                textMaxTur.setText(values[4]);
                textMaxTur.revalidate();
            }
        });
        this.add(addMaxTur);
        //Button Sub Max Turns
        subMaxTur.setBackground(new java.awt.Color(0, 0, 0,0));
        subMaxTur.setBorder(null);
        subMaxTur.setIcon(new ImageIcon(resizedSub));
        subMaxTur.setContentAreaFilled(false);
        subMaxTur.setPressedIcon(new ImageIcon(resizedSub_pressed));
        subMaxTur.setLocation(645,545);
        subMaxTur.setSize((int) (iconSub.getIconWidth()*scaleLabel), (int) (iconSub.getIconHeight()*scaleLabel));
        subMaxTur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[4] = String.valueOf(Integer.parseInt(values[4]) - 1);
                if (Integer.parseInt(values[4]) < 1) values[4] = "1";
                textMaxTur.setText(values[4]);
                textMaxTur.setLocation(751,525);
                textMaxTur.revalidate();
            }
        });
        this.add(subMaxTur);
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
                newGameOptions.synchronizationCustomToDifficulty();
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
                newGameOptions.startGame("custom", values);
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
