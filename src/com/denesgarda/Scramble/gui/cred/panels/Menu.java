package com.denesgarda.Scramble.gui.cred.panels;

import com.denesgarda.Scramble.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public JPanel panel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton forgotPasswordButton;

    public Menu(JFrame parent) {
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = Main.client.enterEmail();
                String code = Main.client.confirmEmail(email);
                if (email != null) {
                    parent.setContentPane(new Details(parent, email, code).panel);
                    parent.revalidate();
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new Login(parent).panel);
                parent.revalidate();
            }
        });
    }
}
