package com.denesgarda.Scramble;

import com.denesgarda.Prop4j.data.PropertiesFile;
import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.PropertiesUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Memory.WINDOW = new Window(Memory.SolidState.title);
        Memory.WINDOW.textField.setEditable(false);

        System.out.println("Loading config...");
        File config = new File("scramble.properties");
        if (!config.exists()) {
            System.out.println("Config file not found. Generating new one...");
            try {
                boolean successful = config.createNewFile();
                if (!successful) {
                    System.out.println("Failed to generate config file.");
                    Popup.error("Config Error", "Failed to generate config file.");
                    System.exit(-1);
                }
            } catch (IOException e) {
                System.out.println("Failed to generate config file.");
                Popup.error("Config Error", "Failed to generate config file.");
                System.exit(-1);
            }
        }
        Memory.CONFIG = new PropertiesFile("scramble.properties");
        System.out.println("Successfully loaded config.");

        System.out.println("Applying config...");
        Memory.WINDOW.textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        System.out.println("Successfully applied config.");

        Memory.WINDOW.clear();
        Memory.WINDOW.textField.setEditable(true);

        System.out.println(InputManager.Strings.s0);
        Memory.Operation.setInputID(0);
    }
}
