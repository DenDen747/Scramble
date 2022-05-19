package com.denesgarda.Scramble.util;

import com.denesgarda.Scramble.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class ImageManager {
    public static ImageIcon getImageIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(Main.class.getResourceAsStream(path)));
        } catch (IOException e) {
            Popup.error("Error Occurred", "Local resources were unable to be loaded.", true);
            return null;
        }
    }
}
