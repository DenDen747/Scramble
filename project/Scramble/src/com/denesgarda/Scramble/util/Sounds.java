package com.denesgarda.Scramble.util;

import com.denesgarda.Scramble.Main;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sounds {
    public static synchronized void playSound(final String url) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("data/sounds/" + url));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
