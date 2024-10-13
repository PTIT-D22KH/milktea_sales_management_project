/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.Timer;
import java.util.TimerTask;
import utils.SessionManager;

/**
 *
 * @author P51
 */
public class TimeCounterController {
    private static Timer timer;
    public interface Callback {
       public abstract void onTick(int second);
    }
    public static void start(Callback callback) {
        stop();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long startTime = SessionManager.getSession().getStartTime().getTime();
                int elapsedTimeMiliSec = (int) (System.currentTimeMillis() - startTime);
                int elapsedTimeSec = elapsedTimeMiliSec / 1000;
                callback.onTick(elapsedTimeSec);
            }
            
        }, 1000, 1000);
    }
    public static void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
