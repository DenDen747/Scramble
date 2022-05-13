package com.denesgarda.Scramble;

import com.denesgarda.Prop4j.data.PropertiesFile;
import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.PropertiesUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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

        File highScores = new File("highScores.properties");
        if (!highScores.exists()) {
            System.out.println("High scores file not found. Generating new one...");
            try {
                boolean successful = highScores.createNewFile();
                if (!successful) {
                    System.out.println("Failed to generate high scores file.");
                    Popup.error("Config Error", "Failed to generate high scores file.");
                    System.exit(-1);
                }
            } catch (IOException e) {
                System.out.println("Failed to generate high scores file.");
                Popup.error("Config Error", "Failed to generate high scores file.");
                System.exit(-1);
            }
        }
        Memory.HIGH_SCORES = new PropertiesFile("highScores.properties");
        System.out.println("Successfully loaded high scores.");

        System.out.println("Applying config...");
        Memory.WINDOW.textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, Integer.parseInt(PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14"))));
        System.out.println("Successfully applied config.");

        try {
            System.out.println("Loading words...");
            InputStream is = Main.class.getResourceAsStream("data/words.txt");
            Scanner scanner2 = new Scanner(is);
            scanner2.useDelimiter("\\Z");
            while (scanner2.hasNext()) {
                String word = scanner2.nextLine();
                if (word.matches("[a-zA-Z]+")) {
                    Memory.words.add(word.toLowerCase());
                }
            }
            scanner2.close();
            System.out.println("Words successfully loaded.");
        } catch (Exception e) {
            System.out.println("Failed to load words. If this issue persists, try reinstalling the application.");
            Popup.error("Internal Error", "Failed to load words. If this issue persists, try reinstalling the application.");
            System.exit(-1);
        }

        Memory.WINDOW.clear();
        Memory.WINDOW.textField.setEditable(true);

        System.out.println(InputManager.Strings.s0);
        Memory.Operation.setInputID(0);
    }
}
