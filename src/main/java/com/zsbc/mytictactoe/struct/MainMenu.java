package com.zsbc.mytictactoe.struct;

import com.zsbc.mytictactoe.Program;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class MainMenu implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel title;

    private JLabel player1TextFieldText;
    private JLabel player2TextFieldText;
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JButton playGameButton;

    private JLabel settingsLabel;

    private JLabel timerSettingLabel;
    private JButton timerSettingButton;

    private int moveX = 20;

    public MainMenu() {

        this.frame = new JFrame("ZSBC TicTacToe");
        this.panel = new JPanel();

        setupGUI();
    }

    private void setupGUI() {
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setBackground(Color.white);

        title = new JLabel();
        title.setText("Tic Tac Toe");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setBounds(65 + moveX, -10,250, 100);


        //Player 1
        player1TextFieldText = new JLabel();
        player1TextFieldText.setText("Enter Player 1's Name");
        player1TextFieldText.setFont(new Font("Serif", Font.BOLD, 15));
        player1TextFieldText.setBounds(65 + moveX, 60,250, 100);

        player1TextField = new JTextField();
        player1TextField.setBounds(65 + moveX, 130,150, 25);

        //Player 2
        player2TextFieldText = new JLabel();
        player2TextFieldText.setText("Enter Player 2's Name");
        player2TextFieldText.setFont(new Font("Serif", Font.BOLD, 15));
        player2TextFieldText.setBounds(65 + moveX, 130,250, 100);

        player2TextField = new JTextField();
        player2TextField.setBounds(65 + moveX, 200,150, 25);

        playGameButton = new JButton("Play Game");
        playGameButton.setBounds(85 + moveX, 255, 100, 50);
        playGameButton.setBackground(Color.GREEN);
        playGameButton.addActionListener(this);

        settingsLabel = new JLabel("Settings");
        settingsLabel.setBounds(105 + moveX,355,100,50);
        settingsLabel.setFont(new Font("Serif", Font.BOLD, 15));

        timerSettingLabel = new JLabel("Timer: ");
        timerSettingLabel.setBounds(65 + moveX,390,100,50);
        timerSettingLabel.setFont(new Font("Serif", Font.BOLD, 15));
        timerSettingButton = new JButton("Off");
        timerSettingButton.setBackground(Color.RED);
        timerSettingButton.setBounds(120 + moveX,405,75,25);
        timerSettingButton.setFont(new Font("Serif", Font.BOLD, 15));
        timerSettingButton.addActionListener(this);
        timerSettingButton.setFocusable(false);

        panel.setLayout(null);
        panel.add(title);
        panel.add(player1TextFieldText);
        panel.add(player2TextFieldText);
        panel.add(player1TextField);
        panel.add(player2TextField);
        panel.add(playGameButton);
        panel.add(settingsLabel);
        panel.add(timerSettingLabel);
        panel.add(timerSettingButton);

        frame.setVisible(true);
    }

    public boolean isTimerButtonEnabled() {
        return timerSettingButton.getText().equalsIgnoreCase("On");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(timerSettingButton)) {
            timerSettingButton.setFocusable(false);
            if(!isTimerButtonEnabled()) {
                timerSettingButton.setText("On");
                timerSettingButton.setBackground(Color.GREEN);
            }
            else {
                timerSettingButton.setText("Off");
                timerSettingButton.setBackground(Color.RED);
            }
            return;
        }
        if(e.getSource().equals(playGameButton)) {
            playGameButton.setFocusable(false);
            if (player1TextField.getText().isEmpty() || player2TextField.getText().isEmpty()) {
                return;
            }

            Program.getDebug().print("Success, attempting to launch game!");
            frame.dispose();

            Player player1 = new Player(player1TextField.getText());
            Player player2 = new Player(player2TextField.getText());

            new TicTacToe(player1, player2, isTimerButtonEnabled());
        }
    }
}
