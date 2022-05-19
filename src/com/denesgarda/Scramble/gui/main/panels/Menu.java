package com.denesgarda.Scramble.gui.main.panels;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.gui.main.Window;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Menu {
    public JPanel panel;
    private JLabel title;
    private JButton competitiveButton;
    private JButton practiceButton;
    private JButton accountButton;
    private JButton privateGameButton;
    private JButton quitButton;

    public Menu(Window parent) {
        parent.title("Menu");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] res = Main.client.autoQuery("6|" + Main.config.username + "|" + Main.config.password, false, false);
                parent.setContentPane(new Account(parent, Main.config.username, res[1], res[2], res[3], res[4]).panel);
                parent.revalidate();
            }
        });
        practiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new PracticeSettings(parent).panel);
                parent.revalidate();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
