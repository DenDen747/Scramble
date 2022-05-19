package com.denesgarda.Scramble;

import com.denesgarda.JVersionManager.Version;
import com.denesgarda.JarData.data.Serialized;
import com.denesgarda.Scramble.gui.cred.panels.Menu;
import com.denesgarda.Scramble.gui.main.Window;
import com.denesgarda.Scramble.gui.etc.Loading;
import com.denesgarda.Scramble.memory.Config;
import com.denesgarda.Scramble.util.ImageManager;
import com.denesgarda.Scramble.util.OS;
import com.denesgarda.Scramble.util.Popup;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;

public class Main {
    public static final Version VERSION = new Version("1.0.0");

    public static Window window;
    public static Config config;
    public static Client client;

    public static boolean inGame = false;

    public static void main(String[] args) {
        Loading loading = new Loading();

        String folderPath;
        String filePath;
        OS.OSType os = OS.getOperatingSystemType();
        if (os == OS.OSType.Windows) {
            folderPath = System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "Scramble";
        } else if (os == OS.OSType.MacOS) {
            folderPath = System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + "Scramble";
        } else {
            folderPath = System.getProperty("user.home") + File.separator + "Scramble";
        }
        filePath = folderPath + File.separator + "config.sc";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            try {
                boolean successful = folder.mkdir();
                if (!successful) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Popup.error("Config Error", "Failed to generate config folder.", true);
            }
        }

        loading.update();

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean successful = file.createNewFile();
                if (!successful) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Popup.error("Config Error", "Failed to generate config file.", true);
            }
        }

        config = new Config(file);

        loading.update();

        config.read();

        loading.update();

        try {
            client = new Client();
        } catch (Exception e) {
            Popup.error("Network Error", "Failed to connect to server.", true);
        }

        loading.update();

        String[] res = client.autoQuery("0|" + VERSION, false, true);
        if (res.length != 1) {
            int option = JOptionPane.showOptionDialog(null, "A newer version is available. To continue, please update and use a newer one.", "Update Available", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, ImageManager.getImageIcon("/assets/image/update.png"), new String[]{"Update"}, "Update");
            if (option == 0) {
                try {
                    Desktop.getDesktop().browse(new URI(res[2]));
                } catch (Exception e) {
                    Popup.error("Error", "Failed to update.", true);
                }
            }
            System.exit(0);
        }

        loading.update();

        loading.setVisible(false);

        String[] v = client.query("1|" + config.username + "|" + config.password).split("\\|", -1);
        if (v[0].equals("0")) {
            openWindow();
        } else {
            com.denesgarda.Scramble.gui.cred.Window lsw = new com.denesgarda.Scramble.gui.cred.Window();
            lsw.setContentPane(new Menu(lsw).panel);
            lsw.revalidate();
        }
    }

    public static void openWindow() {
        window = new Window();
        window.setContentPane(new com.denesgarda.Scramble.gui.main.panels.Menu(window).panel);
        window.revalidate();
    }
}
