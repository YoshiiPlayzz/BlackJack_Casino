package de.joshua.hatzinger.nico.maurer.jonas.domnick;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spiel;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.SpielManger;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.StartWindow;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.Window;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        Spiel s = new Spiel();
        //SpielManger sm = new SpielManger(s, new String[]{"Zocker 1", "Zocker 2","Zocker 3","Zocker 4","Zocker 5","Zocker 6","Zocker 7","Zocker 8",}, true);
        //sm.startSpiel();

            new StartWindow();


    }
}

