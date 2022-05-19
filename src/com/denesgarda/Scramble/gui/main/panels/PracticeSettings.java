package com.denesgarda.Scramble.gui.main.panels;

import com.denesgarda.Scramble.gui.main.Window;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticeSettings {
    private JSpinner letters;
    public JPanel panel;
    private JSpinner time;
    private JButton startButton;
    private JButton backButton;

    public PracticeSettings(Window parent) {
        parent.title("Configure Singleplayer");
        letters.setValue(10);
        time.setValue(30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setContentPane(new Menu(parent).panel);
                parent.revalidate();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Integer.parseInt(String.valueOf((int) letters.getValue())) < 6 || Integer.parseInt(String.valueOf((int) letters.getValue())) > 20) {
                        Popup.error("Invalid Value", "Letters value must be between 6 and 20.", false);
                    } else if (Integer.parseInt(String.valueOf((int) time.getValue())) < 5 || Integer.parseInt(String.valueOf((int) time.getValue())) > 900) {
                        Popup.error("Invalid Value", "Time value must be between 5 and 900.", false);
                    } else {
                        parent.setContentPane(new Practice(parent, (int) letters.getValue(), (int) time.getValue()).panel);
                        parent.revalidate();
                    }
                } catch (Exception ex) {
                    Popup.error("Invalid Value", "Please enter integer values.", false);
                }
            }
        });
    }
}
