package com.denesgarda.Scramble.gui.main.panels;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.gui.main.Window;
import com.denesgarda.Scramble.util.ImageManager;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account {
    public JPanel panel;
    private JLabel username;
    private JLabel id;
    private JLabel ranking;
    private JLabel tier;
    private JLabel rating;
    private JButton changeUsernameButton;
    private JButton changeEmailButton;
    private JButton changePasswordButton;
    private JButton logOutButton;
    private JButton deleteAccountButton;
    private JLabel tierLabel;
    private JLabel ratingLabel;
    private JLabel rankingLabel;
    private JButton backButton;
    private JButton competitiveStatsButton;
    private JButton singleplayerStatsButton;

    public Account(Window parent, String usr, String id, String tier, String ranking, String rating) {
        parent.title("Account");
        this.username.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        this.username.setText(usr);
        this.id.setText("ID: " + id);
        tierLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        rankingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        ratingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        this.tier.setText(tier);
        this.ranking.setText(ranking);
        this.rating.setText(rating);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new Menu(parent).panel);
                parent.revalidate();
            }
        });
        singleplayerStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] res = Main.client.autoQuery("10|" + Main.config.username + "|" + Main.config.password, false, false);
                Popup.info("Singleplayer Stats", "Games Played: " + res[1] + "\nAverage Score: " + res[2] + "\nHigh Score: " + res[3]);
            }
        });
        changeUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    String newUsername = (String) JOptionPane.showInputDialog(null, "Enter new username. (Only A-Z, 0-9, _, and . | Min 3 chars | Max 16 chars)", "", JOptionPane.PLAIN_MESSAGE, ImageManager.getImageIcon("/assets/image/new-username.png"), null, "");
                    if (newUsername != null) {
                        String[] res = Main.client.autoQuery("11|" + Main.config.username + "|" + Main.config.password + "|" + newUsername, true, false);
                        if (res[0].equals("0")) {
                            Main.config.username = newUsername;
                            Main.config.write();
                            Popup.info("Username changed.", "Your username has been changed.");
                            username.setText(newUsername);
                        } else {
                            switch (res[1]) {
                                case "0" -> Popup.error("Error", "Invalid username format.", false);
                                case "1" -> Popup.error("Error", "Username taken.", false);
                                default -> Popup.error("Error", "Internal error.", false);
                            }
                            continue;
                        }
                    }
                    break;
                }
            }
        });
    }
}
