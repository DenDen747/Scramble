package com.denesgarda.Scramble.util;

import com.denesgarda.Scramble.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sounds {
    public static synchronized void playSound(final String url) {
        try {
            InputStream audioSrc = Main.class.getResourceAsStream("/assets/sounds/" + url);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
