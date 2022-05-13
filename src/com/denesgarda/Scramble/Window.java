package com.denesgarda.Scramble;

import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Scramble.util.TextAreaOutputStream;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

public class Window extends JFrame {
    public JPanel panel;
    public JTextField textField;
    public JTextArea textArea;

    public Window(String title) {
        super(title);
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textArea.setLineWrap(true);
        TextAreaOutputStream out = new TextAreaOutputStream(textArea, "");
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));
        this.setSize(512, 512);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.setVisible(true);
        textField.requestFocus();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = textField.getText();
                    textField.setText("");
                    InputManager.processInput(input);
                }
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                    textField.requestFocus();
                    try {
                        textField.setCaretPosition(textField.getText().length() - 1);
                    } catch (Exception ignored) {}
                } else if (e.getKeyChar() != '\uFFFF') {
                    textField.setText(textField.getText() + e.getKeyChar());
                    textField.requestFocus();
                    try {
                        textField.setCaretPosition(textField.getText().length() - 1);
                    } catch (Exception ignored) {}
                }
            }
        });

        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {
                textField.setCaretPosition(textField.getDocument().getLength());
            }

            public void focusLost(FocusEvent fe) {

            }
        });
    }

    public void clear() {
        try {
            Thread.sleep(100);
            textArea.setText("");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Popup.error("InterruptedException", e.toString());
            System.exit(-1);
        }
    }
}
