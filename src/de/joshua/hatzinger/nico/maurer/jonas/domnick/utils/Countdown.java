package de.joshua.hatzinger.nico.maurer.jonas.domnick.utils;

import java.util.concurrent.TimeUnit;

public class Countdown {


    //Einfache Thread.sleep() Ausführung

    private final long actual_time;
    private final int time;
    private boolean started;
    private boolean ended;


    public Countdown(int time, TimeUnit u) {
        this.time = time;
        this.actual_time = u.toMillis(time);
        this.started = false;
        this.ended = false;
    }

    public Countdown(int time, TimeUnit u, boolean start) {
        this.time = time;
        this.actual_time = u.toMillis(time);
        this.started = false;
        this.ended = false;
        if (start) {
            startTimer();
        }
    }

    public void startTimer() {
        started = true;
        System.out.println("Started countdown");
        try {
            Thread.sleep(actual_time);
            ended = true;
        } catch (InterruptedException e) {
            System.out.println("An error occured!");
        }
    }

    public void reset() {
        started = false;
        ended = false;
    }

    public int getTime() {
        return time;
    }

    public long getActual_time() {
        return actual_time;
    }

    public boolean isEnded() {
        return ended;
    }

    public boolean isStarted() {
        return started;
    }
}
