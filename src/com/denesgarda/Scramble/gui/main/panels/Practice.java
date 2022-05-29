package com.denesgarda.Scramble.gui.main.panels;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.gui.main.Window;
import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.Sounds;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Practice {
    public JPanel panel;
    private JTextField textField;
    private JLabel timerTitle;
    private JLabel timer;
    private JPanel letters;
    private JTextArea words;
    private JLabel score;
    private JButton quitButton;
    private JLabel id;
    private JScrollPane wordsScrollPane;

    public Practice(Window parent, int l, final int time) {
        Main.inGame = true;
        String[] res = Main.client.autoQuery("7|" + Main.config.username + "|" + Main.config.password + "|" + l + "|" + time, false, false);
        parent.title("Singleplayer");
        DefaultCaret caret = (DefaultCaret) words.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        id.setText("Game ID: " + res[1]);
        timerTitle.setText("Revealing letters in");
        final int[] s = {0};
        score.setText("Points: " + s[0]);
        words.setEditable(false);
        textField.requestFocus();
        textField.setEditable(false);
        ArrayList<String> used = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timer.setText("3");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("2");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("1");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("0");
                    Sounds.playSound("start.wav");
                    letters.setLayout(new BoxLayout(letters, BoxLayout.PAGE_AXIS));
                    for (int i = 0; i < l; i++) {
                        Thread.sleep(150);
                        JLabel letter = new JLabel(res[2].substring(1, res[2].length() - 1).split(", ")[i]);
                        letter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
                        letter.setAlignmentX(Component.CENTER_ALIGNMENT);
                        letter.setBackground(new Color(43, 200, 20));
                        letters.add(letter);
                        Sounds.playSound("pop.wav");
                        letters.revalidate();
                    }
                    Thread.sleep(1000);
                    timerTitle.setText("Starting in");
                    timer.setText("5");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("4");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("3");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("2");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("1");
                    Sounds.playSound("tick.wav");
                    Thread.sleep(1000);
                    timer.setText("0");
                    Sounds.playSound("correct.wav");
                    textField.setEditable(true);
                    textField.requestFocus();
                    Thread.sleep(1000);
                    timerTitle.setText("Time remaining");
                    int t = time;
                    while (t > -1) {
                        if (t == 9) {
                            Sounds.playSound("ticking.wav");
                        }
                        timer.setText(String.valueOf(t));
                        Thread.sleep(1000);
                        t--;
                    }
                    textField.setEditable(false);

                    JFrame jFrame = new JFrame();
                    jFrame.setSize(256, 128);
                    jFrame.setResizable(false);
                    jFrame.setLocationRelativeTo(null);
                    JPanel jPanel = new JPanel();
                    jFrame.add(jPanel);
                    jPanel.setLayout(new BorderLayout());
                    JLabel label = new JLabel("GAME OVER");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                    jPanel.add(label);
                    jFrame.revalidate();
                    jFrame.setVisible(true);
                    Sounds.playSound("results.wav");
                    Thread.sleep(150);
                    label.setForeground(Color.RED);
                    Thread.sleep(150);
                    label.setForeground(Color.BLACK);
                    Thread.sleep(150);
                    label.setForeground(Color.RED);
                    Thread.sleep(150);
                    label.setForeground(Color.BLACK);
                    Thread.sleep(150);
                    label.setForeground(Color.RED);
                    Thread.sleep(150);
                    label.setForeground(Color.BLACK);
                    Thread.sleep(150);
                    label.setForeground(Color.RED);
                    Thread.sleep(150);
                    label.setForeground(Color.BLACK);
                    Thread.sleep(150);
                    label.setForeground(Color.RED);
                    Thread.sleep(150);
                    label.setForeground(Color.BLACK);
                    Thread.sleep(1200);
                    jFrame.setVisible(false);
                    String[] resultsResponse = Main.client.autoQuery("9|" + Main.config.username + "|" + Main.config.password + "|" + res[1] + "|" + s[0] + "|" + used + "|" + time, false, false);
                    System.out.println(Arrays.toString(resultsResponse));
                    Main.inGame = false;
                    String c;
                    if (Double.parseDouble(resultsResponse[5]) >= 0) {
                        c = "+" + resultsResponse[5];
                    } else {
                        c = resultsResponse[5];
                    }
                    Popup.info("Game Results", "Game ID: " + res[1] + "\nPoints: " + s[0] + "\n" + used.size() + " words guessed out of " + resultsResponse[1] + "\nTime: " + time + "\nAvailable characters: " + res[2] + "\nScore: " + resultsResponse[2] + "\n\nRating: " + resultsResponse[3] + " -> " + resultsResponse[4] + "\nChange in rating: " + c);
                    parent.setContentPane(new Menu(parent).panel);
                    parent.revalidate();
                } catch (Exception ignored) {}
            }
        });
        thread.start();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String word = textField.getText().toLowerCase();
                    textField.setText("");
                    if (!used.contains(word)) {
                        int gained = Integer.parseInt(Main.client.autoQuery("8|" + Main.config.username + "|" + Main.config.password + "|" + res[1] + "|" + word, false, false)[1]);
                        if (gained > 0) {
                            used.add(word);
                            words.append(word + " +" + gained + "\n");
                            s[0] += gained;
                            score.setText("Points: " + s[0]);
                            if (gained > 200) {
                                Sounds.playSound("bonus.wav");
                            } else {
                                Sounds.playSound("beep.wav");
                            }
                        }
                    }
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Popup.confirmQuit()) {
                    thread.interrupt();
                    parent.setContentPane(new Menu(parent).panel);
                    parent.revalidate();
                }
            }
        });
    }
}
