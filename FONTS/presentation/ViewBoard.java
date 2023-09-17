package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
public class ViewBoard extends javax.swing.JPanel {
    //Data
    private final int maxBalls;
    private final int maxTurns;
    private final int offsetX;
    private final int offsetY;
    private final double scale;
    private JLabel[][] board;
    private AbstractMap.SimpleEntry<Integer,Integer>[][] coords;
    private AbstractMap.SimpleEntry<Integer,Integer>[][] coords_feedback;
    private AbstractMap.SimpleEntry<Integer,Integer>[] coords_number;
    private JLabel[] feedback;
    private String mode;

    public ViewBoard(int maxBalls, int maxTurns, String role) {
        this.mode = role;
        this.maxBalls = maxBalls;
        this.maxTurns = maxTurns;
        this.offsetX = 32;
        this.offsetY = 42;
        this.scale = 0.6;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1024, 720));
        this.setBackground(new java.awt.Color(0,0,0,0));
        init_feedback();
        init_board();
        calculate_number_coords();
        calculate_feedback_coords();
        calculate_feedback_code_coords();
    }

    public void init_board() {
        //Images
        //0
        ImageIcon iconBoard00 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/0,0.png");
        Image resizedBoard00 = iconBoard00.getImage().getScaledInstance((int) (iconBoard00.getIconWidth()*scale), (int) (iconBoard00.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoard0X = new ImageIcon("../ASSETS/InGame/board/"+mode+"/0,X.png");
        Image resizedBoard0X = iconBoard0X.getImage().getScaledInstance((int) (iconBoard0X.getIconWidth()*scale), (int) (iconBoard0X.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoard0end = new ImageIcon("../ASSETS/InGame/board/"+mode+"/0,end.png");
        Image resizedBoard0end = iconBoard0end.getImage().getScaledInstance((int) (iconBoard0end.getIconWidth()*scale), (int) (iconBoard0end.getIconHeight()*scale), Image.SCALE_SMOOTH);
        //X
        ImageIcon iconBoardX0 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/X,0.png");
        Image resizedBoardX0 = iconBoardX0.getImage().getScaledInstance((int) (iconBoardX0.getIconWidth()*scale), (int) (iconBoardX0.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardXX = new ImageIcon("../ASSETS/InGame/board/"+mode+"/X,X.png");
        Image resizedBoardXX = iconBoardXX.getImage().getScaledInstance((int) (iconBoardXX.getIconWidth()*scale), (int) (iconBoardXX.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardXend = new ImageIcon("../ASSETS/InGame/board/"+mode+"/X,end.png");
        Image resizedBoardXend = iconBoardXend.getImage().getScaledInstance((int) (iconBoardXend.getIconWidth()*scale), (int) (iconBoardXend.getIconHeight()*scale), Image.SCALE_SMOOTH);
        //end
        ImageIcon iconBoardend0 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/end,0.png");
        Image resizedBoardend0 = iconBoardend0.getImage().getScaledInstance((int) (iconBoardend0.getIconWidth()*scale), (int) (iconBoardend0.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardendX = new ImageIcon("../ASSETS/InGame/board/"+mode+"/end,X.png");
        Image resizedBoardendX = iconBoardendX.getImage().getScaledInstance((int) (iconBoardendX.getIconWidth()*scale), (int) (iconBoardendX.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardendend = new ImageIcon("../ASSETS/InGame/board/"+mode+"/end,end.png");
        Image resizedBoardendend = iconBoardendend.getImage().getScaledInstance((int) (iconBoardendend.getIconWidth()*scale), (int) (iconBoardendend.getIconHeight()*scale), Image.SCALE_SMOOTH);
        //j = 1
        ImageIcon iconBoard0jeq1 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/0,jeq1.png");
        Image resizedBoard0jeq1 = iconBoard0jeq1.getImage().getScaledInstance((int) (iconBoard0jeq1.getIconWidth()*scale), (int) (iconBoard0jeq1.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardXjeq1 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/X,jeq1.png");
        Image resizedBoardXjeq1 = iconBoardXjeq1.getImage().getScaledInstance((int) (iconBoardXjeq1.getIconWidth()*scale), (int) (iconBoardXjeq1.getIconHeight()*scale), Image.SCALE_SMOOTH);
        ImageIcon iconBoardendjeq1 = new ImageIcon("../ASSETS/InGame/board/"+mode+"/end,jeq1.png");
        Image resizedBoardendjeq1 = iconBoardendjeq1.getImage().getScaledInstance((int) (iconBoardendjeq1.getIconWidth()*scale), (int) (iconBoardendjeq1.getIconHeight()*scale), Image.SCALE_SMOOTH);
        //
        int sizeX = (int) ((iconBoard0jeq1.getIconWidth()*scale));
        if (maxBalls >= 2) sizeX = (int) ((iconBoard00.getIconWidth() + iconBoard0X.getIconWidth() * (maxBalls-2) + iconBoard0end.getIconWidth())*scale);
        int sizeY = (int) ((iconBoard00.getIconHeight() + iconBoardX0.getIconHeight() * (maxTurns-1) + iconBoardend0.getIconHeight())*scale);
        board = new JLabel[maxTurns+1][maxBalls];
        coords = new AbstractMap.SimpleEntry[maxTurns+1][maxBalls];

        if (maxBalls == 1) {
            int j = 0;
            for (int i = 0; i < maxTurns + 1; ++i) {
                board[i][j] = new JLabel();
                board[i][j].setBackground(new java.awt.Color(0, 0, 0, 0));
                board[i][j].setBorder(null);
                this.add(board[i][j]);
                if (i == 0) {
                    board[i][j].setIcon(new ImageIcon(resizedBoard0jeq1));
                    board[i][j].setLocation(1024 / 2 - sizeX / 2, 720 / 2 - sizeY / 2 - 50);
                    board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                    if (mode == "CM") coords[i][j] = new AbstractMap.SimpleEntry<>((1024 / 2 - sizeX / 2) + 48, (720 / 2 - sizeY / 2 - 50) + 3);
                    else coords[i][j] = new AbstractMap.SimpleEntry<>((1024 / 2 - sizeX / 2) + 74, (720 / 2 - sizeY / 2 - 50) + 3);
                }
                else if (i == maxTurns) {
                    board[i][j].setIcon(new ImageIcon(resizedBoardendjeq1));
                    board[i][j].setLocation(board[i - 1][j].getLocation().x, board[i - 1][j].getLocation().y + board[i - 1][j].getIcon().getIconHeight());
                    board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                    coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY+20);
                }
                else {
                    board[i][j].setIcon(new ImageIcon(resizedBoardXjeq1));
                    board[i][j].setLocation(board[i - 1][j].getLocation().x, board[i - 1][j].getLocation().y + board[i - 1][j].getIcon().getIconHeight());
                    board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                    if (mode == "CB" && i == 1) coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY+23);
                    else coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY);
                }
            }
        }
        else {
            for (int i = 0; i < maxTurns + 1; ++i) {
                for (int j = 0; j < maxBalls; ++j) {
                    board[i][j] = new JLabel();
                    board[i][j].setBackground(new java.awt.Color(0, 0, 0, 0));
                    board[i][j].setBorder(null);
                    this.add(board[i][j]);
                    if (i == 0) {
                        if (j == 0) {
                            board[i][j].setIcon(new ImageIcon(resizedBoard00));
                            board[i][j].setLocation(1024 / 2 - sizeX / 2, 720 / 2 - sizeY / 2 - 50);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            if (mode == "CM") coords[i][j] = new AbstractMap.SimpleEntry<>((1024 / 2 - sizeX / 2) + 48, (720 / 2 - sizeY / 2 - 50) + 3);
                            else coords[i][j] = new AbstractMap.SimpleEntry<>((1024 / 2 - sizeX / 2) + 74, (720 / 2 - sizeY / 2 - 50) + 3);
                        } else if (j == maxBalls - 1) {
                            board[i][j].setIcon(new ImageIcon(resizedBoard0end));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        } else {
                            board[i][j].setIcon(new ImageIcon(resizedBoard0X));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        }
                    } else if (i == maxTurns) {
                        if (j == 0) {
                            board[i][j].setIcon(new ImageIcon(resizedBoardend0));
                            board[i][j].setLocation(board[i - 1][j].getLocation().x, board[i - 1][j].getLocation().y + board[i - 1][j].getIcon().getIconHeight());
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            if (mode == "CM") coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY+20);
                            else coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY);
                        } else if (j == maxBalls - 1) {
                            board[i][j].setIcon(new ImageIcon(resizedBoardendend));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        } else {
                            board[i][j].setIcon(new ImageIcon(resizedBoardendX));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        }
                    } else {
                        if (j == 0) {
                            board[i][j].setIcon(new ImageIcon(resizedBoardX0));
                            board[i][j].setLocation(board[i - 1][j].getLocation().x, board[i - 1][j].getLocation().y + board[i - 1][j].getIcon().getIconHeight());
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            if (mode == "CB" && i == 1) coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY+23);
                            else coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i-1][j].getKey(), coords[i-1][j].getValue()+offsetY);
                        } else if (j == maxBalls - 1) {
                            board[i][j].setIcon(new ImageIcon(resizedBoardXend));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        } else {
                            board[i][j].setIcon(new ImageIcon(resizedBoardXX));
                            board[i][j].setLocation(board[i][j - 1].getLocation().x + board[i][j - 1].getIcon().getIconWidth(), board[i][j - 1].getLocation().y);
                            board[i][j].setSize(board[i][j].getIcon().getIconWidth(), board[i][j].getIcon().getIconHeight());
                            coords[i][j] = new AbstractMap.SimpleEntry<>(coords[i][j-1].getKey()+offsetX, coords[i][j-1].getValue());
                        }
                    }
                }
            }
        }
    }

    private void init_feedback() {
        //Feedback tiles
        ImageIcon iconFeedback = new ImageIcon("../ASSETS/InGame/feedback_tile_" + maxBalls + ".png");
        Image resizedFeedback = iconFeedback.getImage().getScaledInstance((int) (iconFeedback.getIconWidth()*scale), (int) (iconFeedback.getIconHeight()*scale), Image.SCALE_SMOOTH);
        //
        feedback = new JLabel[maxTurns];
        for (int f_it = 0; f_it < maxTurns; ++f_it) {
            feedback[f_it] = new JLabel();
            feedback[f_it].setBackground(new java.awt.Color(0, 0, 0, 0));
            feedback[f_it].setBorder(null);
            feedback[f_it].setIcon(new ImageIcon(resizedFeedback));
            feedback[f_it].setSize(feedback[f_it].getIcon().getIconWidth(), feedback[f_it].getIcon().getIconHeight());
            this.add(feedback[f_it]);
        }
    }

    private void calculate_feedback_coords() {
        if (mode == "CM") feedback[0].setLocation((int) (coords[0][maxBalls-1].getKey()+((53 + 60*scale/2 - feedback[0].getIcon().getIconWidth()/2))), (int) (coords[0][maxBalls-1].getValue()+(7 + 60*scale/2 - feedback[0].getIcon().getIconHeight()/2)));
        else feedback[0].setLocation((int) (coords[0][0].getKey()+((-60*scale/2 - feedback[0].getIcon().getIconWidth()/2))), (int) (coords[1][0].getValue()+(7 + 60*scale/2 - feedback[0].getIcon().getIconHeight()/2)));
        for (int f_it = 1; f_it < maxTurns; ++f_it) {
            feedback[f_it].setLocation(feedback[f_it-1].getLocation().x, feedback[f_it-1].getLocation().y + offsetY);
        }
    }

    private void calculate_number_coords() {
        coords_number = new AbstractMap.SimpleEntry[maxBalls];
        coords_number[0] = new AbstractMap.SimpleEntry<>(coords[maxTurns][0].getKey() + 10,coords[maxTurns][0].getValue()+offsetY + 23);
        for (int cn_it = 1; cn_it < maxBalls; ++cn_it) {
            coords_number[cn_it] = new AbstractMap.SimpleEntry<>(coords_number[cn_it-1].getKey() + offsetX, coords_number[cn_it-1].getValue());
        }
    }

    private void calculate_feedback_code_coords() {
        coords_feedback = new AbstractMap.SimpleEntry[maxTurns][maxBalls];
        for (int i = 0; i < maxTurns; ++i) {
            switch (maxBalls){
                case 1:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + 13*scale));
                    break;
                case 2:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 1*scale), (int)(feedback[i].getLocation().y + 14*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 35*scale), (int)(feedback[i].getLocation().y + 14*scale));
                    break;
                case 3:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + 3*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 30*scale), (int)(feedback[i].getLocation().y + 28*scale));
                    coords_feedback[i][2] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -1*scale), (int)(feedback[i].getLocation().y + 28*scale));
                    break;
                case 4:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + -3*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 31*scale), (int)(feedback[i].getLocation().y + 13*scale));
                    coords_feedback[i][2] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + 31*scale));
                    coords_feedback[i][3] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -3*scale), (int)(feedback[i].getLocation().y + 13*scale));
                    break;
                case 5:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + -3*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 31*scale), (int)(feedback[i].getLocation().y + 9*scale));
                    coords_feedback[i][2] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 25*scale), (int)(feedback[i].getLocation().y + 29*scale));
                    coords_feedback[i][3] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 3*scale), (int)(feedback[i].getLocation().y + 29*scale));
                    coords_feedback[i][4] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -1*scale), (int)(feedback[i].getLocation().y + 9*scale));
                    break;
                case 6:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 13*scale), (int)(feedback[i].getLocation().y + -3*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 27*scale), (int)(feedback[i].getLocation().y + 6*scale));
                    coords_feedback[i][2] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 27*scale), (int)(feedback[i].getLocation().y + 21*scale));
                    coords_feedback[i][3] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 13*scale), (int)(feedback[i].getLocation().y + 29*scale));
                    coords_feedback[i][4] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -1*scale), (int)(feedback[i].getLocation().y + 21*scale));
                    coords_feedback[i][5] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -1*scale), (int)(feedback[i].getLocation().y + 6*scale));
                    break;
                case 7:
                    coords_feedback[i][0] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 14*scale), (int)(feedback[i].getLocation().y + -3*scale));
                    coords_feedback[i][1] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 27*scale), (int)(feedback[i].getLocation().y + 4*scale));
                    coords_feedback[i][2] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 30*scale), (int)(feedback[i].getLocation().y + 18*scale));
                    coords_feedback[i][3] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 22*scale), (int)(feedback[i].getLocation().y + 29*scale));
                    coords_feedback[i][4] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 7*scale), (int)(feedback[i].getLocation().y + 29*scale));
                    coords_feedback[i][5] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + -1*scale), (int)(feedback[i].getLocation().y + 19*scale));
                    coords_feedback[i][6] = new AbstractMap.SimpleEntry<>((int)(feedback[i].getLocation().x + 2*scale), (int)(feedback[i].getLocation().y + 4*scale));
                    break;
            }
        }
    }

    public AbstractMap.SimpleEntry<Integer,Integer>[][] getCoordsColors() {
        return coords;
    }

    public AbstractMap.SimpleEntry<Integer,Integer>[][] getCoordsFeedback() {
        return coords_feedback;
    }

    public AbstractMap.SimpleEntry<Integer,Integer>[] getCoordsNumber() {
        return coords_number;
    }

}
