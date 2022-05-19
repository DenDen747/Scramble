package com.denesgarda.Scramble.gui.cred.panels;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
    public JPanel panel;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton backButton;

    public Login(JFrame parent) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new Menu(parent).panel);
                parent.revalidate();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next(parent);
            }
        });
        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    next(parent);
                }
            }
        });
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    next(parent);
                }
            }
        });
    }

    public void next(JFrame parent) {
        if (!username.getText().isBlank() && !password.getText().isBlank()) {
            String res = Main.client.query("5|" + username.getText() + "|" + password.getText());
            if (res.equals("0")) {
                Main.config.username = username.getText();
                Main.config.password = password.getText();
                Main.config.write();
                parent.setVisible(false);
                Main.openWindow();
            } else {
                Popup.error("Invalid Credentials", "Invalid username or password.", false);
            }
        }
    }
}
