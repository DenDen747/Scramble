package com.denesgarda.Scramble.gui.cred.panels;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Details {
    public JPanel panel;
    private JTextField createUsername;
    private JButton createAccountButton;
    private JButton backButton;
    private JPasswordField createPassword;
    private JPasswordField confirmPassword;

    public Details(JFrame parent, String email, String code) {
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next(parent, email, code);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new Menu(parent).panel);
                parent.revalidate();
            }
        });
        createUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    next(parent, email, code);
                }
            }
        });
        createPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    next(parent, email, code);
                }
            }
        });
        confirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    next(parent, email, code);
                }
            }
        });
    }

    public void next(JFrame parent, String email, String code) {
        if (createPassword.getText().equals(confirmPassword.getText())) {
            String[] res = Main.client.autoQuery("4|" + email + "|" + code + "|" + createUsername.getText() + "|" + createPassword.getText(), true, false);
            if (res[0].equals("0")) {
                Popup.info("Account Created", "Your account has been created. Please log in.");
                parent.setContentPane(new Menu(parent).panel);
                parent.revalidate();
            } else if (res[0].equals("1")) {
                switch (res[1]) {
                    case "0" -> Popup.error("Error", "Email verification error.", false);
                    case "1" -> Popup.error("Error", "Invalid username format.", false);
                    case "2" -> Popup.error("Error", "Username taken.", false);
                    case "3" -> Popup.error("Error", "Invalid password format.", false);
                    case "4" -> Popup.error("Error", "Email taken.", false);
                    default -> Popup.error("Error", "Internal error.", false);
                }
            } else {
                Popup.error("Error", "Internal error.", false);
            }
        }
    }
}
