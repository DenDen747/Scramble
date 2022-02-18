package com.denesgarda.Scramble.util;

import javax.swing.*;

public class Popup {
    public static void error(String title, String body) {
        JOptionPane.showMessageDialog(null, body, title, JOptionPane.ERROR_MESSAGE);
    }

    public static int yesNoConfirm(String title, String body) {
        return JOptionPane.showConfirmDialog(null, body, title, JOptionPane.YES_NO_OPTION);
    }

    public static void information(String title, String body) {
        JOptionPane.showMessageDialog(null, body, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
