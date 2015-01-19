package com.company;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by admin on 1/16/2015.
 */
public class Stopwatch {

    public int lvlTimer = 0;
    public int invincibleTimer = 0;
    public Timer timer;

    public void startTimer()
    {
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                lvlTimer +=1;
                PacmanFrame.time.setText("<html><h2 style='float: right;'>Time: " + lvlTimer + " <br> </h3></html>");

                if(PacmanFrame.getGameboard().getPacman().getInvincible())
                {
                    invincibleTimer++;
                }

            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timertask, 1, 1000);
    }

    public void stopTimer()
    {
        timer.cancel();
    }

}
