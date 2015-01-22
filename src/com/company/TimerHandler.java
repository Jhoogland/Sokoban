package com.company;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sefa on 14-1-2015.
 */
public class TimerHandler implements ActionListener {
    private int delay;
    public Gameboard gameboard;
    public Timer timer;

    private DrunkGhost drunkGhost1 = new DrunkGhost();
    private DrunkGhost drunkGhost2 = new DrunkGhost();
    private SmartGhost smartGhost1 = new SmartGhost();
    private SmartGhost smartGhost2 = new SmartGhost();

    public TimerHandler(int delay, Gameboard gameboard)
    {
        this.delay       = delay;
        this.gameboard   = gameboard;
        this.timer       = new Timer(delay, this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
//        if(gameboard.getPacman().getInvincible())
//        {
//            drunkGhost1.flee();
//            drunkGhost2.flee();
//            smartGhost1.flee();
//            smartGhost2.flee();
//        }
//        else
//        {
//        }

        drunkGhost1.move();
        drunkGhost2.move();
        smartGhost1.move();
        smartGhost2.move();


        if(gameboard.stopwatch.invincibleTimer == 10)
        {
            gameboard.getPacman().setInvincible(false);
            gameboard.stopwatch.invincibleTimer = 0;
        }


        gameboard.repaint();
    }

    public Ghost getGhost(String ghost)
    {
        Ghost returnGhost = null;

        if(ghost.equals("DrunkGhost1"))
        {
            returnGhost = this.drunkGhost1;
        }
        else if(ghost.equals("DrunkGhost2"))
        {
            returnGhost = this.drunkGhost2;
        }
        else if(ghost.equals("SmartGhost1"))
        {
            returnGhost = this.smartGhost1;
        }
        else if(ghost.equals("SmartGhost2"))
        {
            returnGhost = this.smartGhost2;
        }

        return returnGhost;
    }

    public SmartGhost getSmartGhost(int ghost)
    {
        SmartGhost returnGhost = null;
        if(ghost == 1 )
        {
            returnGhost = this.smartGhost1;
        }
        else if(ghost == 2)
        {
            returnGhost = this.smartGhost2;
        }
        return returnGhost;
    }

    public void setDelay(int delay){
        this.delay=delay;
    }
}
