package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public JLabel label;
    public JButton retry_button;
    public JPanel panel;
    public JButton[] buttons;
    public int x_or_y = 0;

    GUI() {
       super("Tic-Tac-Toe");
       createGUI();
    }

    public void createGUI() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(450,650));

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER,25,25));

        Dimension button_d = new Dimension(100,100);
        Font button_f = new Font("",Font.ITALIC,45);

        label = new JLabel("",SwingConstants.CENTER);
        //label.setOpaque(true);  /// if opaque = false -> setBackground don't work
        label.setPreferredSize(new Dimension(400,65));
        //label.setBackground(Color.RED);
        label.setFont(new Font("",Font.ITALIC,30));
        panel.add(label);

        GameListener a = new GameListener();

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(button_f);
            buttons[i].setPreferredSize(button_d);
            buttons[i].addActionListener(a);
            panel.add(buttons[i]);
        }

        retry_button = new JButton("Retry");
        retry_button.setPreferredSize(new Dimension(150,65));
        retry_button.setFont(new Font("",Font.ITALIC,30));
        retry_button.addActionListener(e -> {
            for (JButton button : buttons) {
                button.setEnabled(true);
                button.setText("");
                label.setText("");
                x_or_y = 0;
            }
        });
        panel.add(retry_button);



        setContentPane(panel);
    }

    public void Winner(){
        String s = "X";
        for (int i=0; i<=1; i++) {
            if ((buttons[0].getText().equals(s)) && (buttons[1].getText().equals(s)) && (buttons[2].getText().equals(s)) ||
                    (buttons[3].getText().equals(s)) && (buttons[4].getText().equals(s)) && (buttons[5].getText().equals(s)) ||
                    (buttons[6].getText().equals(s)) && (buttons[7].getText().equals(s)) && (buttons[8].getText().equals(s)) ||
                    (buttons[0].getText().equals(s)) && (buttons[3].getText().equals(s)) && (buttons[6].getText().equals(s)) ||
                    (buttons[1].getText().equals(s)) && (buttons[4].getText().equals(s)) && (buttons[7].getText().equals(s)) ||
                    (buttons[2].getText().equals(s)) && (buttons[5].getText().equals(s)) && (buttons[8].getText().equals(s)) ||
                    (buttons[0].getText().equals(s)) && (buttons[4].getText().equals(s)) && (buttons[8].getText().equals(s)) ||
                    (buttons[2].getText().equals(s)) && (buttons[4].getText().equals(s)) && (buttons[6].getText().equals(s))) {
                label.setText(s + " win");
            }
            s = "O";
        }
    }

    class GameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton b = (JButton) e.getSource();
            if (x_or_y % 2 == 0) {
                b.setText("X");
            } else {
                b.setText("O");
            }
            b.setEnabled(false);
            x_or_y++;

            Winner();

            if (!label.getText().equals("")) {
                for(JButton button : buttons){
                    button.setEnabled(false);
                }
            }

        }
    }
}
