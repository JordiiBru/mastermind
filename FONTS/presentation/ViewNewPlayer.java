package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNewPlayer extends javax.swing.JPanel {

    //Data
    private final NewGameOptions newGameOptions;
    private final JLabel background = new JLabel();
    private String role = "CB";
    private String name = "";
    private final JLabel backgroundImageIntroduce = new JLabel();
    private final JLabel backgroundImageName = new JLabel();
    private final JTextField inputName = new JTextField();
    private final JLabel backgroundImageRole = new JLabel();
    private final JButton codeMaker = new JButton();
    private final JButton codeBreaker = new JButton();
    private final JButton exit = new JButton();
    private final JButton confirm = new JButton();

    public ViewNewPlayer(NewGameOptions newGameOptions) {
        this.newGameOptions = newGameOptions;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1024, 720));
        this.setBackground(new java.awt.Color(0,0,0,0));
        initialize();
    }

    private void initialize() {
        int buttonSizeX = 300;
        int buttonSizeY = 60;
        //Introduce Label
        backgroundImageIntroduce.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundImageIntroduce.setBorder(null);
        ImageIcon iconBackgroundImageIntroduce = new ImageIcon("../ASSETS/NewGameOptions/text_introduce.png");
        Image resizedImageBackgroundImageIntroduce = iconBackgroundImageIntroduce.getImage().getScaledInstance((int) (iconBackgroundImageIntroduce.getIconWidth()*0.65), (int) (iconBackgroundImageIntroduce.getIconHeight()*0.65), Image.SCALE_SMOOTH);
        backgroundImageIntroduce.setIcon(new ImageIcon(resizedImageBackgroundImageIntroduce));
        backgroundImageIntroduce.setLocation(208,20);
        backgroundImageIntroduce.setSize((int) (iconBackgroundImageIntroduce.getIconWidth()*0.65), (int) (iconBackgroundImageIntroduce.getIconHeight()*0.65));
        this.add(backgroundImageIntroduce);
        //Name Input
        inputName.setBackground(new java.awt.Color(64, 64, 64,255));
        inputName.setBorder(null);
        inputName.setLocation(235,125);
        inputName.setSize((int) (938*0.60), (int) (155*0.60));
        inputName.setForeground(Color.WHITE);
        inputName.setFont(new java.awt.Font("Serif", Font.BOLD, 50));
        this.add(inputName);
        //Name Background Label
        backgroundImageName.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundImageName.setBorder(null);
        ImageIcon iconBackgroundImageName = new ImageIcon("../ASSETS/NewGameOptions/text_nameInput_background.png");
        Image resizedImageBackgroundImageName = iconBackgroundImageName.getImage().getScaledInstance((int) (iconBackgroundImageName.getIconWidth()*0.65), (int) (iconBackgroundImageName.getIconHeight()*0.65), Image.SCALE_SMOOTH);
        backgroundImageName.setIcon(new ImageIcon(resizedImageBackgroundImageName));
        backgroundImageName.setLocation(208,120);
        backgroundImageName.setSize((int) (iconBackgroundImageName.getIconWidth()*0.65), (int) (iconBackgroundImageName.getIconHeight()*0.65));
        this.add(backgroundImageName);
        //Role Label
        backgroundImageRole.setBackground(new java.awt.Color(0, 0, 0,0));
        backgroundImageRole.setBorder(null);
        ImageIcon iconBackgroundImageRole = new ImageIcon("../ASSETS/NewGameOptions/text_selec_role.png");
        Image resizedImageBackgroundImageRole = iconBackgroundImageRole.getImage().getScaledInstance((int) (iconBackgroundImageRole.getIconWidth()*0.8), (int) (iconBackgroundImageRole.getIconHeight()*0.8), Image.SCALE_SMOOTH);
        backgroundImageRole.setIcon(new ImageIcon(resizedImageBackgroundImageRole));
        backgroundImageRole.setLocation(285,300);
        backgroundImageRole.setSize((int) (iconBackgroundImageRole.getIconWidth()*0.8), (int) (iconBackgroundImageIntroduce.getIconHeight()*0.8));
        this.add(backgroundImageRole);
        //Button CodeMaker
        codeMaker.setBackground(new java.awt.Color(0, 0, 0,0));
        codeMaker.setBorder(null);
        ImageIcon iconCodeMaker = new ImageIcon("../ASSETS/NewGameOptions/button_CM.png");
        Image resizedImageCodeMaker = iconCodeMaker.getImage().getScaledInstance(buttonSizeX +100, buttonSizeY +40, Image.SCALE_SMOOTH);
        ImageIcon iconCodeBreaker = new ImageIcon("../ASSETS/NewGameOptions/button_CB_pressed.png");
        Image resizedImageCodeBreaker = iconCodeBreaker.getImage().getScaledInstance(buttonSizeX +100, buttonSizeY +40, Image.SCALE_SMOOTH);
        ImageIcon iconCodeBreaker2 = new ImageIcon("../ASSETS/NewGameOptions/button_CB.png");
        Image resizedImageCodeBreaker2 = iconCodeBreaker2.getImage().getScaledInstance(buttonSizeX +100, buttonSizeY +40, Image.SCALE_SMOOTH);
        codeMaker.setIcon(new ImageIcon(resizedImageCodeMaker));
        codeMaker.setContentAreaFilled(false);
        ImageIcon iconCodeMaker_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_CM_pressed.png");
        Image resizedImageCodeMaker_pressed = iconCodeMaker_pressed.getImage().getScaledInstance(buttonSizeX +100, buttonSizeY +40, Image.SCALE_SMOOTH);
        codeMaker.setPressedIcon(new ImageIcon(resizedImageCodeMaker_pressed));
        codeMaker.setLocation(20,420);
        codeMaker.setSize(buttonSizeX +100, buttonSizeY +40);
        codeMaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codeMaker.setIcon(new ImageIcon(resizedImageCodeMaker_pressed));
                codeBreaker.setIcon(new ImageIcon(resizedImageCodeBreaker2));
                role = "CM";
            }
        });
        this.add(codeMaker);
        //Button CodeBreaker
        codeBreaker.setBackground(new java.awt.Color(0, 0, 0,0));
        codeBreaker.setBorder(null);
        codeBreaker.setIcon(new ImageIcon(resizedImageCodeBreaker));
        codeBreaker.setContentAreaFilled(false);
        ImageIcon iconCodeBreaker_pressed = new ImageIcon("../ASSETS/NewGameOptions/button_CB_pressed.png");
        Image resizedImageCodeBreaker_pressed = iconCodeBreaker_pressed.getImage().getScaledInstance(buttonSizeX +100, buttonSizeY +40, Image.SCALE_SMOOTH);
        codeBreaker.setPressedIcon(new ImageIcon(resizedImageCodeBreaker_pressed));
        codeBreaker.setLocation(600,420);
        codeBreaker.setSize(buttonSizeX +100, buttonSizeY +40);
        codeBreaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codeBreaker.setIcon(new ImageIcon(resizedImageCodeBreaker_pressed));
                codeMaker.setIcon(new ImageIcon(resizedImageCodeMaker));
                role = "CB";
            }
        });
        this.add(codeBreaker);
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
                newGameOptions.goBack();
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
                StringBuilder sb = new StringBuilder(inputName.getText());
                sb.setLength(20);
                name = String.valueOf(sb);
                newGameOptions.synchronizationPlayerToDifficulty(role, name);
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
