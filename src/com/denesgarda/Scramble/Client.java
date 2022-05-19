package com.denesgarda.Scramble;

import com.denesgarda.Scramble.util.ImageManager;
import com.denesgarda.Scramble.util.Popup;
import com.denesgarda.Socketeer.Connection;
import com.denesgarda.Socketeer.Queueable;
import com.denesgarda.Socketeer.SocketeerClient;
import com.denesgarda.Socketeer.event.Event;
import com.denesgarda.Socketeer.event.ServerConnectionCloseEvent;

import javax.swing.*;
import java.io.IOException;

public class Client extends SocketeerClient {
    public Connection connection;

    public Client() throws IOException {
        connect(false);
    }

    public void connect(boolean reconnection) {
        while (true) {
            try {
                connection = this.connect("localhost", 8888);
                if (reconnection) {
                    Popup.waring("Warning", "If the network error was a server-side issue, the current session may be invalid.\nA client restart may be required if errors occur.");
                }
                break;
            } catch (Exception e) {
                int option = JOptionPane.showOptionDialog(null, "Failed to connect to server.", "Connection Failed", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, ImageManager.getImageIcon("/assets/image/error.png"), new String[]{"Try Again", "Exit"}, "Try Again");
                if (option == 1) {
                    System.exit(-1);
                }
            }
        }
    }

    public void send(String message) {
        try {
            connection.send(message);
        } catch (Exception e) {
            Popup.error("Network Error", "Server connection error", true);
        }
    }

    public String[] autoQuery(String request, boolean overrideDefaultError, boolean quitIfError) {
        String[] res = query(request).split("\\|", -1);
        switch (res[0]) {
            case "1":
                if (!overrideDefaultError) {
                    Popup.error("Error", "An unknown error on the server occurred.", quitIfError);
                }
                break;
            case "2":
                Popup.error("Error", "The request sent to the server was unable to be handled.\nThis may be due to an outdated client.", quitIfError);
                break;
            case "3":
                Popup.error("Error", "This session was unable to be validated by the server.", true);
                break;
        }
        return res;
    }

    public String enterEmail() {
        String email = (String) JOptionPane.showInputDialog(null, "Enter email.", "", JOptionPane.PLAIN_MESSAGE, ImageManager.getImageIcon("/assets/image/email.png"), null, "");
        Main.client.send("2|" + email);
        return email;
    }

    public String confirmEmail(String email) {
        while (true) {
            String code = (String) JOptionPane.showInputDialog(null, "Enter the code sent to " + email + ".", "", JOptionPane.PLAIN_MESSAGE, ImageManager.getImageIcon("/assets/image/code.png"), null, "");
            String[] res = Main.client.autoQuery("3|" + email + "|" + code, true, false);
            if (res[0].equals("0")) {
                return code;
            } else {
                Popup.error("Incorrect code.", "The entered confirmation code was incorrect.", false);
            }
        }
    }

    public String query(String request) {
        String[] response = new String[1];
        try {
            connection.send(request);
        } catch (Exception e) {
            Popup.error("Network Error", "Server connection error", true);
        }
        connection.nextIn(new Queueable() {
            @Override
            public void nextIn(String s) throws IOException {
                response[0] = s;
                System.out.println("updated");
            }
        });
        while (response[0] == null) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                Popup.error("Error", e.getMessage(), true);
            }
            System.out.println("loop:" + response[0]);
        }
        return response[0];
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ServerConnectionCloseEvent) {
            int option = JOptionPane.showOptionDialog(null, "The connection to the server was lost.", "Connection Lost", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, ImageManager.getImageIcon("/assets/image/error.png"), new String[]{"Reconnect", "Exit"}, "Reconnect");
            if (option == 1) {
                System.exit(-1);
            } else {
                connect(true);
            }
        }
    }
}
