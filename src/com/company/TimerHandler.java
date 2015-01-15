package com.company;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sefa on 14-1-2015.
 */
public class TimerHandler implements ActionListener {
    int delay;
    Gameboard gameboard;
    Timer timer;

    public TimerHandler(int delay, Gameboard gameboard)
    {
        this.delay = delay;
        this.gameboard = gameboard;
        this.timer = new Timer(delay, this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        gameboard.getDrunkGhost1().moveRandom();
        gameboard.getDrunkGhost2().moveRandom();
        gameboard.repaint();
    }

}
