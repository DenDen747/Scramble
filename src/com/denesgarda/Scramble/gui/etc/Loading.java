package com.denesgarda.Scramble.gui.etc;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.ImageManager;

import javax.swing.*;

public class Loading extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JProgressBar progressBar;

    public Loading() {
        super("Loading");
        progressBar.setValue(0);
        this.setSize(256, 80);
        this.setResizable(false);
        this.setIconImage(ImageManager.getImageIcon("/assets/image/icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void update() {
        progressBar.setValue(progressBar.getValue() + 20);
    }
}
