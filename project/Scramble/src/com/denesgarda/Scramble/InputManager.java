package com.denesgarda.Scramble;

import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.PropertiesUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
            } else if (Memory.INPUT_ID == 3) {
                Manage.i3(input);
            } else if (Memory.INPUT_ID == 4) {
                Manage.i4(input);
            } else if (Memory.INPUT_ID == 5) {
                Manage.i5(input);
            }
        }
    }

    public static class Manage {
        public static void i0(String input) {
            if (input.equalsIgnoreCase("1")) {
                Memory.WINDOW.clear();
                char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
                char[] vowels = {'a', 'e', 'i', 'o', 'u'};
                ArrayList<Character> selected = new ArrayList<>();
                for (int i = 0; i < Memory.wordLength; i++) {
                    char chosen;
                    do {
                        int cv = new Random().nextInt(5);
                        if (cv == 0 || cv == 1 || cv == 2) {
                            chosen = consonants[new Random().nextInt(consonants.length)];
                        } else {
                            chosen = vowels[new Random().nextInt(vowels.length)];
                        }
                    } while (selected.contains(chosen));
                    selected.add(chosen);
                }
                Memory.Interoperational.regex = Arrays.toString(selected.toArray()).replace(", ", "");
                Memory.Interoperational.available = selected;
                new GameFrame();
                System.out.println(Strings.s0);
                Memory.Operation.setInputID(0);
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
                Memory.WINDOW.clear();
                System.out.println(Strings.s3());
                Memory.Operation.setInputID(3);
            } else if (input.equalsIgnoreCase("2")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s4());
                Memory.Operation.setInputID(4);
            } else if (input.equalsIgnoreCase("3")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s5());
                Memory.Operation.setInputID(5);
            } else if (input.equalsIgnoreCase("`")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s0);
                Memory.Operation.setInputID(0);
            }
        }

        public static void i3(String input) {
            if (input.equalsIgnoreCase("`")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s2());
                Memory.Operation.setInputID(2);
            } else {
                try {
                    int size = Integer.parseInt(input);
                    if (size >= 8 && size <= 50) {
                        Memory.WINDOW.textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, size));
                        Memory.CONFIG.setProperty("font-size", String.valueOf(size));
                        Memory.WINDOW.clear();
                        System.out.println(Strings.s2());
                        Memory.Operation.setInputID(2);
                    }
                } catch (Exception ignored) {}
            }
        }

        public static void i4(String input) {
            if (input.equalsIgnoreCase("`")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s2());
                Memory.Operation.setInputID(2);
            } else {
                try {
                    int length = Integer.parseInt(input);
                    if (length >= 3 && length <= 10) {
                        Memory.wordLength = length;
                        Memory.WINDOW.clear();
                        System.out.println(Strings.s2());
                        Memory.Operation.setInputID(2);
                    }
                } catch (Exception ignored) {}
            }
        }

        public static void i5(String input) {
            if (input.equalsIgnoreCase("`")) {
                Memory.WINDOW.clear();
                System.out.println(Strings.s2());
                Memory.Operation.setInputID(2);
            } else {
                try {
                    int limit = Integer.parseInt(input);
                    if (limit >= 5 && limit <= 300) {
                        Memory.timeLimit = limit;
                        Memory.WINDOW.clear();
                        System.out.println(Strings.s2());
                        Memory.Operation.setInputID(2);
                    }
                } catch (Exception ignored) {}
            }
        }
    }

    public static class Strings {
        public static String s0 = "Welcome to Scramble\n\n[1] Play\n[2] How to play\n[3] Options\n[~] Exit (\"~\" can be used anytime to exit.)";
        public static String s1 = "How to play\n\nYou are given a combination of a set amount of random letters. You have to try to make as many actual words with the letters as possible in a set amount of time.\n\n[ENTER] Back";
        public static String s2() { return "Options\n\n---General---\n[1] Font size: " + PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14") + "\n---Gameplay---\n[2] Word length: " + Memory.wordLength + "\n[3] Time limit (seconds): " + Memory.timeLimit + "\n\n[`] Back"; }
        public static String s3() { return "Enter a new value\n\nFont size (Default: 14 | min: 8 | max: 50):\n" + PropertiesUtil.getPropertyNotNull(Memory.CONFIG, "font-size", "14") + " -> __\n\n[`] Back"; }
        public static String s4() { return "Enter a new value\n\nWord length (Default: 8 | min: 4 | max: 12):\n" + Memory.wordLength + " -> __\n\n[`] Back"; }
        public static String s5() { return "Enter a new value\n\nTime limit (Default: 30 | min: 5 | max: 300):\n" + Memory.timeLimit + " -> __ (seconds)\n\n[`] Back"; }
    }
}
