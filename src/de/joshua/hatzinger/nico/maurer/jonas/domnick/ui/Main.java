package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
//        Window w = new Window();
//        new Countdown(10, TimeUnit.SECONDS, true);
//        w.setGuthabenFont(100);

        new StartWindow();

    }

    public static void playSound() {
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(
                    Main.class.getResource("/images/8000_Euro.wav")));
            sound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }
}

