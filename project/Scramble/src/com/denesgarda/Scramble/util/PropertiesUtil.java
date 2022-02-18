package com.denesgarda.Scramble.util;

import com.denesgarda.Prop4j.data.PropertiesFile;

public class PropertiesUtil {
    public static String getPropertyNotNull(PropertiesFile propertiesFile, String key, String defaultValue) {
        try {
            if (propertiesFile.getProperty(key) == null) {
                propertiesFile.setProperty(key, defaultValue);
                return defaultValue;
            } else {
                return propertiesFile.getProperty(key);
            }
        } catch (Exception e) {
            System.out.println("A config error has occurred. A relaunch is required.");
            Popup.error("Config Error", "A config error has occurred. A relaunch is required.");
            System.exit(-1);
        }
        return null;
    }
}
