package com.zsbc.mytictactoe.struct;

import com.zsbc.mytictactoe.Program;
import com.zsbc.mytictactoe.enums.Icon;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

@Getter
@Setter
public class TicTacToe implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private Player player1;
    private Player player2;

    private Turn turn;

    private Map board;
    private ArrayList usedButtons;

    private JLabel announcementText;
    private JLabel timerText;
    private JButton rematchButton;
    private Font font;

    private java.util.Timer timer;
    private TimerTask timerTask;
    private int countdown = 10;
    private boolean wonOrDraw = false;

    private boolean timerSetting;

    public TicTacToe(Player player1, Player player2, boolean timerSetting) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1.setIcon(Icon.X);
        this.player2.setIcon(Icon.O);
        this.announcementText = new JLabel();
        this.timerText = new JLabel();
        this.font = new Font("Serif", Font.BOLD, 40);

        this.timerSetting = timerSetting;

        frame = new JFrame("ZSBC TicTacToe");
        panel = new JPanel();

        setupGUI();
        startTimer(null);
    }

    private void startTimer(TimerTask oldTimerTask) {
        if (!timerSetting) {
            return;
        }
        if (oldTimerTask != null) {
            oldTimerTask.cancel();
            countdown = 10;
        }
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                if (wonOrDraw) {
                    timerTask.cancel();
                    return;
                }
                timerText.setText(String.valueOf(countdown));
                if (countdown < 1) {
                    timerTask.cancel();
                    setTurn(getPlayerNotOnTurn());
                    return;
                }
                countdown--;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void registerActionListeners() {
        for (int i = 1; i <= 9; i++) {
            getButton(i).addActionListener(this);
        }
        getRematchButton().addActionListener(this);
    }

    private void setupGUI() {
        frame.setSize(615, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setBackground(Color.gray);


        panel.setLayout(null);
        this.rematchButton = new JButton("Rematch");
        panel.add(rematchButton);
        panel.add(announcementText);
        panel.add(timerText);
        setupBoard();

        frame.setVisible(true);
    }

    private void setupBoard() {
        board = new HashMap();
        for (int i = 1; i <= 9; i++) {
            board.put(i, new JButton(""));
            JButton button = (JButton) board.get(i);
            button.setBackground(Color.DARK_GRAY);
            button.setFont(font);
            button.setFocusable(false);
            panel.add(button);
        }

        usedButtons = new ArrayList<>();

        getButton(1).setBounds(0, 55, 200, 200);
        getButton(2).setBounds(200, 55, 200, 200);
        getButton(3).setBounds(400, 55, 200, 200);
        getButton(4).setBounds(0, 255, 200, 200);
        getButton(5).setBounds(200, 255, 200, 200);
        getButton(6).setBounds(400, 255, 200, 200);
        getButton(7).setBounds(0, 455, 200, 200);
        getButton(8).setBounds(200, 455, 200, 200);
        getButton(9).setBounds(400, 455, 200, 200);
        getRematchButton().setBounds(475, 8, 95, 40);
        getRematchButton().setFocusable(false);

        announcementText.setBounds(10, -75, 500, 200);
        announcementText.setFont(new Font("Serif", Font.BOLD, 30));
        timerText.setBounds(440, -75, 500, 200);
        timerText.setFont(new Font("Serif", Font.BOLD, 30));
        getRematchButton().setFont(new Font("Serif", Font.BOLD, 15));
        getRematchButton().setBackground(Color.PINK);

        registerActionListeners();
        this.turn = new Turn(getRandomTurnForPlayer());

        Player player = turn.getPlayer();
        announcementText.setForeground(player.getIcon().getColor());
        announcementText.setText(player.getName() + " (" + player.getIcon().getName() + ")'s Turn");

        timerText.setForeground(Color.CYAN);
    }

    private Player getRandomTurnForPlayer() {
        int random = new Random().nextInt(2);
        if(random == 1) {
            return player1;
        }
        else {
            return player2;
        }

    }

    private JButton getButton(int buttonNumber) {
        return (JButton) board.get(buttonNumber);
    }

    private boolean checkIfWin(Player player) {
        String mark = player.getIcon().getName();

        String button1Mark = getButtonMark(getButton(1));
        String button2Mark = getButtonMark(getButton(2));
        String button3Mark = getButtonMark(getButton(3));
        String button4Mark = getButtonMark(getButton(4));
        String button5Mark = getButtonMark(getButton(5));
        String button6Mark = getButtonMark(getButton(6));
        String button7Mark = getButtonMark(getButton(7));
        String button8Mark = getButtonMark(getButton(8));
        String button9Mark = getButtonMark(getButton(9));

        //WIN (1,2,3)
        if (button1Mark.equalsIgnoreCase(mark) && button2Mark.equalsIgnoreCase(mark) && button3Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (4,5,6)
        if (button4Mark.equalsIgnoreCase(mark) && button5Mark.equalsIgnoreCase(mark) && button6Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (7,8,9)
        if (button7Mark.equalsIgnoreCase(mark) && button8Mark.equalsIgnoreCase(mark) && button9Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (1,4,7)
        if (button1Mark.equalsIgnoreCase(mark) && button4Mark.equalsIgnoreCase(mark) && button7Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (2,5,8)
        if (button2Mark.equalsIgnoreCase(mark) && button5Mark.equalsIgnoreCase(mark) && button8Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (3,6,9)
        if (button3Mark.equalsIgnoreCase(mark) && button6Mark.equalsIgnoreCase(mark) && button9Mark.equalsIgnoreCase(mark)) {
            return true;
        }

        //WIN (1,5,9)
        if (button1Mark.equalsIgnoreCase(mark) && button5Mark.equalsIgnoreCase(mark) && button9Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        //WIN (3,5,7)
        if (button3Mark.equalsIgnoreCase(mark) && button5Mark.equalsIgnoreCase(mark) && button7Mark.equalsIgnoreCase(mark)) {
            return true;
        }
        return false;
    }

    private boolean checkIfDraw(Player player) {
        int buttonsClicked = 0;
        for (int i = 1; i <= 9; i++) {
            JButton button = getButton(i);
            if (!button.getText().isEmpty()) {
                buttonsClicked++;
            }
        }
        if (buttonsClicked >= 9 && !checkIfWin(player)) {
            return true;
        }
        return false;
    }

    private String getButtonMark(JButton button) {
        return button.getText();
    }

    private void rematch() {
        Point location = frame.getLocation();
        frame.dispose();
        TicTacToe game = new TicTacToe(player1, player2, timerSetting);
        game.getFrame().setLocation(location);
    }

    private void setTurn(Player player) {
        turn.setPlayer(player);
        announcementText.setForeground(player.getIcon().getColor());
        announcementText.setText(player.getName() + " (" + player.getIcon().getName() + ")'s Turn");
        startTimer(timerTask);
    }

    private Player getPlayerNotOnTurn() {
        return turn.getPlayer().equals(player1) ? player2 : player1;
    }

    private void resetTimer() {
        if (!timerSetting) {
            return;
        }
        countdown = 10;
        timerText.setText(String.valueOf(countdown));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getRematchButton())) {
            getRematchButton().setFocusable(false);
            rematch();
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (e.getSource().equals(getButton(i))) {
                JButton button = getButton(i);
                if (usedButtons.contains(button)) {
                    announcementText.setBounds(10, -75, 500, 200);
                    announcementText.setForeground(Color.RED);
                    announcementText.setText("Spot already used!");
                    return;
                }
                Player player = turn.getPlayer();
                button.setForeground(player.getIcon().getColor());
                button.setText(player.getIcon().getName());
                usedButtons.add(button);

                if (checkIfWin(player)) {
                    wonOrDraw = true;
                    resetTimer();
                    player.addScore(1);
                    announcementText.setForeground(player.getIcon().getColor());
                    announcementText.setText(player.getName() + " (" + player.getIcon().getName() + ") has won! (Score: " + player.getScore() + ")");
                    for (int j = 1; j <= 9; j++) {
                        JButton jButton = getButton(j);
                        jButton.setEnabled(false);
                    }
                    return;
                }
                if (checkIfDraw(player)) {
                    wonOrDraw = true;
                    resetTimer();
                    announcementText.setForeground(Color.ORANGE);
                    announcementText.setText("Game is a draw");
                    for (int j = 1; j <= 9; j++) {
                        JButton jButton = getButton(j);
                        jButton.setEnabled(false);
                    }
                    return;
                }
                announcementText.setBounds(10, -75, 500, 200);
                setTurn(getPlayerNotOnTurn());
            }
        }
    }
}