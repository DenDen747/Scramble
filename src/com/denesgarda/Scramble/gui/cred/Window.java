package com.denesgarda.Scramble.gui.cred;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.ImageManager;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("Scramble v" + Main.VERSION);
        this.setSize(512, 256);
        this.setResizable(false);
        this.setIconImage(ImageManager.getImageIcon("/assets/image/icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
