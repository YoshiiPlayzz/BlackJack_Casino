package de.joshua.hatzinger.nico.maurer.jonas.domnick;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spiel;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.LoadingScreen;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.StartWindow;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.Window;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class Main {


    public static void main(String[] args) {

        Spiel s = new Spiel();
        //SpielManger sm = new SpielManger(s, new String[]{"Zocker 1", "Zocker 2","Zocker 3","Zocker 4","Zocker 5","Zocker 6","Zocker 7","Zocker 8",}, true);
        //sm.startSpiel();

        new LoadingScreen();



    }
    public static void playSound() {
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(
                    Main.class.getResource("/images/8000_Euro.wav")));
            sound.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {e1.printStackTrace(); }
    }
}

