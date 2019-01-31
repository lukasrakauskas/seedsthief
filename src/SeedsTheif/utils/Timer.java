package SeedsTheif.utils;

import org.rspeer.runetek.api.commons.StopWatch;

import java.time.Duration;

public class Timer {
    static private Duration previous = Duration.ZERO;
    static private StopWatch timer = null;

    static void start() {
        timer = StopWatch.start();
    }

    static void pause() {
        previous = previous.plus(timer.getElapsed());
        timer = null;
    }

    static void resume() {
        timer = StopWatch.start();
    }

    static Duration getElapsed() {
        return previous.plus(timer.getElapsed());
    }

    static String getFormatted() {
        long s = previous.plus(timer.getElapsed()).getSeconds();
        return String.format("%d:%02d:%02d", s/3600, (s%3600)/60, (s%60));
    }
}
