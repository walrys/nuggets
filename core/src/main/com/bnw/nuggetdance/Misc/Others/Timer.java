package com.bnw.nuggetdance.Misc.Others;

import java.util.HashMap;

/**
 * Created by Brandon on 11/1/17.
 */

public class Timer {
    private HashMap<String, Integer> timerCounter;

    public Timer()  {
        this.timerCounter = new HashMap<String, Integer>();
    }

    // check if timer is up
    public boolean isTimerDone( String key )  {
        if (timerCounter.containsKey(key)) {
            if (timerCounter.get(key) <= 0) {
                timerCounter.remove(key);
                return true;
            } else  {
                return false;
            }
        }
        return true;
    }

    // reduce timer count
    public void reduceTimer( String key , int rate) {
        if (timerCounter.containsKey(key)) {
            int tempTimer = timerCounter.get(key);
            tempTimer -= rate;
            timerCounter.put(key, tempTimer);
        }
    }

    // set timer count
    public void setTimer(String key, int time) {
        timerCounter.put(key, time);
    }
}
