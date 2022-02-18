package com.denesgarda.Scramble;

import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.PropertiesUtil;

public class InputManager {
    public static void processInput(String input) {
        if (input.equalsIgnoreCase("~")) {
            int confirmation = Popup.yesNoConfirm("Exit", "Are you sure you want to exit?");
            if (confirmation == 0) {
                Memory.WINDOW.clear();
                System.out.println("Thank you for playing.");
                System.exit(0);
            }
        } else {
            if (Memory.INPUT_ID == 0) {
                Manage.i0(input);
            } else if (Memory.INPUT_ID == 1) {
                Manage.i1(input);
            } else if (Memory.INPUT_ID == 2) {
                Manage.i2(input);
            }
        }
    }

    public static class Manage {
        public static void i0(String input) {
            if (input.equalsIgnoreCase("1")) {
                Memory.WINDOW.clear();
                System.out.println("Not yet implemented.");
            } else if (input.equalsIgnoreCase("2")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s1);
                Memory.Operation.setInputID(1);
            } else if (input.equalsIgnoreCase("3")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s2());
                Memory.Operation.setInputID(2);
            }
        }

        public static void i1(String input) {
            Memory.WINDOW.clear();
            System.out.println(Strings.s0);
            Memory.Operation.setInputID(0);
        }

        public static void i2(String input) {
            if (input.equalsIgnoreCase("1")) {

            } else if (input.equalsIgnoreCase("2")) {

            } else if (input.equalsIgnoreCase("3")) {

            } else {
                Memory.WINDOW.clear();
                System.out.println(Strings.s0);
                Memory.Operation.setInputID(0);
            }
        }
    }

    public static class Strings {
        public static String s0 = "Welcome to Scramble\n\n[1] Play\n[2] How to play\n[3] Options\n[~] Exit (\"~\" can be used anytime to exit.)";
        public static String s1 = "How to play\n\nYou are given a combination of a set amount of random letters. You have to try to make as many actual words with the letters as possible in a set amount of time.\n\n[ENTER] Back";
        public static String s2() { return "Options\n\n---General---\n[1] Font size: " + PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14") + "\n---Gameplay---\n[2] Word length: " + Memory.wordLength + "\n[3] Time limit (seconds): " + Memory.timeLimit + "\n\n[ENTER] Back"; }
    }
}
