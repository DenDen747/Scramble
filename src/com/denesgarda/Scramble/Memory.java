package com.denesgarda.Scramble;

import com.denesgarda.Prop4j.data.PropertiesFile;
import com.denesgarda.Scramble.util.Popup;

import java.util.ArrayList;
import java.util.Properties;

public class Memory {
    public static class SolidState {
        public static final String title = "Scramble";
    }

    public static int INPUT_ID;
    public static Window WINDOW;
    public static PropertiesFile CONFIG;
    public static PropertiesFile HIGH_SCORES;

    public static int wordLength = 10;
    public static int timeLimit = 30;

    public static ArrayList<String> words = new ArrayList<>();

    public static class Interoperational {
        public static String regex;
        public static ArrayList<Character> available;
        public static ArrayList<String> used = new ArrayList<>();
        public static int score = 0;
    }

    public static class Operation {
        public static void setInputID(int inputID) {
            try {
                Properties inputIDs = new Properties();
                inputIDs.load(Main.class.getResourceAsStream("inputIDs.properties"));
                String title = SolidState.title + " - " + inputIDs.getProperty(String.valueOf(inputID));
                WINDOW.setTitle(title);
                INPUT_ID = inputID;
            } catch (Exception e) {
                System.out.println("An internal error occurred. If this issue persists, try reinstalling the application.");
                Popup.error("Internal Error", "An internal error occurred. If this issue persists, try reinstalling the application.");
                System.exit(-1);
            }
        }
    }
}
