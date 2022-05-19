package com.denesgarda.Scramble.gui.main;

import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.ImageManager;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {
    public Window() {
        super("Scramble v" + Main.VERSION);
        this.setSize(512, 512);
        this.setIconImage(ImageManager.getImageIcon("/assets/image/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Main.inGame) {
                    boolean confirmed = Popup.confirmQuit();
                    if (!confirmed) {
                        Window.super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    } else {
                        Window.super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                } else {
                    Window.super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
    }

    public void title(String title) {
        this.setTitle("Scramble v" + Main.VERSION + " - " + title);
    }
}
