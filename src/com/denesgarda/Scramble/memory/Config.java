package com.denesgarda.Scramble.memory;

import com.denesgarda.JarData.data.Serialized;
import com.denesgarda.JarData.data.statics.Serialization;
import com.denesgarda.Scramble.Main;
import com.denesgarda.Scramble.util.Popup;

import java.io.*;

public class Config implements Serializable {
    public String username = "";
    public String password = "";

    private final File file;

    public Config(File file) {
        this.file = file;
    }

    public void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                Main.config = (Config) new Serialized(reader.readLine()).deSerialize();
            } catch (Exception e) {
                Main.config = new Config(file);
            }
        } catch (Exception e) {
            Popup.error("Config Error", "Failed to read config file.", true);
        }
    }

    public void write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(Serialization.serialize(Main.config).getData());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            Popup.error("Config Error", "Failed to write config file.", true);
        }
    }
}
