package com.denesgarda.Scramble;

import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.PropertiesUtil;
import com.denesgarda.Scramble.util.Sounds;
import com.denesgarda.Scramble.util.TextAreaOutputStream;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.Arrays;

public class GameFrame extends JFrame {
    public JPanel panel;
    public JTextArea information;
    public JTextArea words;
    public JLabel timer;
    public JTextField textField;
    private JLabel scoreCounter;

    public PrintStream informationOut;
    public PrintStream wordsOut;

    public GameFrame() {
        super("Scramble - Game");
        information.setEditable(false);
        words.setEditable(false);
        DefaultCaret caret = (DefaultCaret) information.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        caret = (DefaultCaret) words.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        information.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        words.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        timer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        scoreCounter.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        textField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        information.setLineWrap(true);
        words.setLineWrap(true);
        informationOut = new PrintStream(new TextAreaOutputStream(information, ""));
        wordsOut = new PrintStream(new TextAreaOutputStream(words, ""));
        this.setSize(512, 512);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.setVisible(true);
        textField.requestFocus();

        Memory.Interoperational.score = 0;
        Memory.Interoperational.used.clear();

        textField.setEditable(false);
        scoreCounter.setText("Score: " + Memory.Interoperational.score);
        informationOut.println("Available letters:\n" + Memory.Interoperational.available);

        GameFrame gameFrame = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timer.setText("Starting in 5");
                    Thread.sleep(1000);
                    timer.setText("Starting in 4");
                    Thread.sleep(1000);
                    timer.setText("Starting in 3");
                    Thread.sleep(1000);
                    timer.setText("Starting in 2");
                    Thread.sleep(1000);
                    timer.setText("Starting in 1");
                    Thread.sleep(1000);
                    timer.setText("Starting in 0");
                    Thread.sleep(1000);
                    textField.setEditable(true);

                    int time = Memory.timeLimit;

                    while (time > -1) {
                        if (time == 10) {
                            if (gameFrame.isVisible()) {
                                Sounds.playSound("ticking.wav");
                            }
                        }
                        timer.setText(time + " seconds left");
                        Thread.sleep(1000);
                        time--;
                    }

                    textField.setEditable(false);

                    if (gameFrame.isVisible()) {
                        Sounds.playSound("results.wav");
                        int matching = 0;
                        for (String word : Memory.words) {
                            if (word.matches(Memory.Interoperational.regex + "+")) {
                                matching++;
                            }
                        }

                        Popup.information("Results", "Final score: " + Memory.Interoperational.score + "\n\nTime limit: " + Memory.timeLimit + "\n\nAvailable Letters:\n" + Arrays.toString(Memory.Interoperational.available.toArray()) + "\n\n" + Memory.Interoperational.used.size() + " words guessed out of " + matching);
                        gameFrame.setVisible(false);
                    }
                } catch (Exception ignored) {}
            }
        }).start();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = textField.getText().toLowerCase();
                    textField.setText("");
                    if (input.equalsIgnoreCase("~")) {
                        int confirmation = Popup.yesNoConfirm("Exit", "Are you sure you want to exit?");
                        if (confirmation == 0) {
                            Memory.WINDOW.clear();
                            System.out.println("Thank you for playing.");
                            System.exit(0);
                        }
                    }
                    if (input.matches(Memory.Interoperational.regex + "+") && !Memory.Interoperational.used.contains(input) && input.length() >= 3 && Memory.words.contains(input)) {
                        Sounds.playSound("beep.wav");
                        Memory.Interoperational.used.add(input);
                        int points = 0;
                        if (input.length() == 3) {
                            points = 50;
                        } else if (input.length() == 4) {
                            points = 80;
                        } else if (input.length() == 5) {
                            points = 120;
                        } else if (input.length() == 6) {
                            points = 170;
                        } else if (input.length() == 7) {
                            points = 230;
                        } else if (input.length() == 8) {
                            points = 300;
                        } else {
                            points = 500;
                        }
                        wordsOut.println(input + " +" + points);
                        Memory.Interoperational.score += points;
                        scoreCounter.setText("Score: " + Memory.Interoperational.score);
                    }
                }
            }
        });
    }
}
