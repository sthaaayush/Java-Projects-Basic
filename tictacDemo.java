package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class tictacDemo implements ActionListener {
    JFrame frame = new JFrame("TIC-TAC Game");
    JPanel mainPane = new JPanel();
    JButton[] btn = new JButton[9];
    JButton P1, P2, Start, Restart;
    JLabel display = new JLabel("**------------------------------------------**");
    String player = null;

    tictacDemo() {
        frame.setSize(450, 450);
        frame.setResizable(false);
        frame.setLayout(null);
        //Bottom display for winner or invalid clicks messages
        display.setBounds(60, 360, 300, 30);
        display.setFont(new Font("Calibri Bold", Font.CENTER_BASELINE, 20));
        frame.add(display);

        //Main game panel to be playerd
        mainPane.setLayout(new GridLayout(3, 3));
        mainPane.setBounds(60, 40, 300, 300);

        //Player one indicator
        P1 = new JButton("X");
        P1.setBounds(5, 5, 50, 50);
        P1.setBackground(Color.red);
        P1.addActionListener(this);
        P1.setFont(new Font("Arial Black", Font.BOLD, 18));
        P1.setEnabled(false);
        frame.add(P1);

        //Player two indicator
        P2 = new JButton("O");
        P2.setBounds(365, 5, 50, 50);
        P2.setBackground(Color.red);
        P2.addActionListener(this);
        P2.setFont(new Font("Arial Black", Font.BOLD, 18));
        P2.setEnabled(false);
        frame.add(P2);

        //Start button to enable keys for playing
        Start = new JButton("Start");
        Start.setBounds(160, 0, 100, 40);
        Start.setFont(new Font("Arial Black", Font.BOLD, 18));
        Start.addActionListener(this);
        Start.setBackground(Color.GREEN);
        frame.add(Start);

        //Resets progess to start new frame
        Restart = new JButton("Restart");
        Restart.setBounds(140, 0, 140, 40);
        Restart.setFont(new Font("Arial Black", Font.BOLD, 18));
        Restart.addActionListener(this);
        Restart.setBackground(Color.GREEN);

        //Creates all buttons in main panel to register players marks
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton();
            btn[i].addActionListener(this);
            btn[i].setBackground(Color.cyan);
            btn[i].setFont(new Font("Arial Black", Font.BOLD, 60));
            btn[i].setEnabled(false);
            mainPane.add(btn[i]);
        }

        //Adding main panel to frame and enabling frame visibility
        frame.add(mainPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new tictacDemo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Start button that enables rest of playable button and change into restart button afterwards
        if (e.getSource() == Start) {
            P1.setEnabled(true);
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(true);
            }
            player = "X";
            P1.setBackground(Color.green);
            frame.remove(Start);
            frame.add(Restart);
        } else if (e.getSource() == Restart) {
            frame.dispose();
            new tictacDemo();
        }

        //Manages the rules for the game
        for (int i = 0; i < btn.length; i++) {
            if (e.getSource() == btn[i]) {
                //Prevents overlap clicking
                if (btn[i].getText().equals("X") || btn[i].getText().equals("O")) {
                    display.setText("!!!!!!!! Invalid Click !!!!!!!!");
                }

                //Block another player button and mark when its not their turn
                else {
                    btn[i].setText(player);
                    if (P1.isEnabled()) {
                        P1.setEnabled(false);
                        P2.setEnabled(true);
                        P2.setBackground(Color.green);
                        P1.setBackground(Color.red);
                        player = "O";
                    } else if (P2.isEnabled()) {
                        P2.setEnabled(false);
                        P1.setEnabled(true);
                        P1.setBackground(Color.green);
                        P2.setBackground(Color.red);
                        player = "X";
                    }
                }
            }
        }

        //This Piece of code checks for winner horizontal, vertical and diagonal match
        String a1, b1, c1;
        for (int i = 0; i < btn.length; i++) {
            //Checks for Horizontal match
            if (i % 3 == 0 && i <= 6) {
                a1 = btn[i].getActionCommand();
                b1 = btn[i + 1].getActionCommand();
                c1 = btn[i + 2].getActionCommand();
                if (a1.equals("X") && b1.equals("X") && c1.equals("X")) {
                    btn[i].setBackground(Color.green);
                    btn[i + 1].setBackground(Color.green);
                    btn[i + 2].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! X HAVE WON*^*");
                    break;
                } else if (a1.equals("O") && b1.equals("O") && c1.equals("O")) {
                    btn[i].setBackground(Color.green);
                    btn[i + 1].setBackground(Color.green);
                    btn[i + 2].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! O HAVE WON*^*");
                    break;
                }
            }
            //Checks for vertical match
            if (i <= 2) {
                a1 = btn[i].getActionCommand();
                b1 = btn[i + 3].getActionCommand();
                c1 = btn[i + 6].getActionCommand();
                if (a1.equals("X") && b1.equals("X") && c1.equals("X")) {
                    btn[i].setBackground(Color.green);
                    btn[i + 3].setBackground(Color.green);
                    btn[i + 6].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! X HAVE WON*^*");
                    break;
                } else if (a1.equals("O") && b1.equals("O") && c1.equals("O")) {
                    btn[i].setBackground(Color.green);
                    btn[i + 3].setBackground(Color.green);
                    btn[i + 6].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! O HAVE WON*^*");
                    break;
                }
            }
            //Checks for diagonal match
            else {
                a1 = btn[0].getActionCommand();
                b1 = btn[4].getActionCommand();
                c1 = btn[8].getActionCommand();
                if (a1.equals("X") && b1.equals("X") && c1.equals("X")) {
                    btn[0].setBackground(Color.green);
                    btn[4].setBackground(Color.green);
                    btn[8].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! X HAVE WON*^*");
                    break;
                } else if (a1.equals("O") && b1.equals("O") && c1.equals("O")) {
                    btn[0].setBackground(Color.green);
                    btn[4].setBackground(Color.green);
                    btn[8].setBackground(Color.green);
                    display.setText("*^*HURRAY !!! O HAVE WON*^*");
                    break;
                } else {
                    a1 = btn[2].getActionCommand();
                    c1 = btn[6].getActionCommand();
                    if (a1.equals("X") && b1.equals("X") && c1.equals("X")) {
                        btn[2].setBackground(Color.green);
                        btn[4].setBackground(Color.green);
                        btn[6].setBackground(Color.green);
                        display.setText("*^*HURRAY !!! X HAVE WON*^*");
                        break;
                    } else if (a1.equals("O") && b1.equals("O") && c1.equals("O")) {
                        btn[2].setBackground(Color.green);
                        btn[4].setBackground(Color.green);
                        btn[6].setBackground(Color.green);
                        display.setText("*^*HURRAY !!! O HAVE WON*^*");
                        break;
                    }
                }
            }
        }
    }
}

