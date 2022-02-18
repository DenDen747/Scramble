package com.denesgarda.Scramble.util;

import com.denesgarda.Scramble.Memory;

import javax.swing.*;

public class Popup {
    public static void error(String title, String body) {
        JOptionPane.showMessageDialog(Memory.WINDOW, body, title, JOptionPane.ERROR_MESSAGE);
    }

    public static int yesNoConfirm(String title, String body) {
        return JOptionPane.showConfirmDialog(Memory.WINDOW, body, title, JOptionPane.YES_NO_OPTION);
    }
}
