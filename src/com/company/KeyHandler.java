package com.company;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    private Pacman pacman = new Pacman();
    private Gameboard gameboard;
    SmartGhost ghost;

    public KeyHandler(Gameboard gameboard)
    {
        this.gameboard = gameboard;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        this.ghost = this.gameboard.timerHandler.getSmartGhost(1);
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                this.pacman.move(Direction.UP);
                ghost.findPacman();
                break;
            case KeyEvent.VK_RIGHT:
                this.pacman.move(Direction.RIGHT);
                ghost.findPacman();
                break;
            case KeyEvent.VK_LEFT:
                this.pacman.move(Direction.LEFT);
                ghost.findPacman();
                break;
            case KeyEvent.VK_DOWN:
                this.pacman.move(Direction.DOWN);
                ghost.findPacman();
                break;
            case KeyEvent.VK_Z:
                this.gameboard.startPause();
                break;
            case KeyEvent.VK_X:
                this.gameboard.resetTheGame();
                break;
        }



        if(this.pacman.getLife() == 0)
        {
            gameboard.resetTheGame();
        }

        if(gameboard.getHalfAmountOfEatenFruits())
        {
            gameboard.placeCherry();
            gameboard.setHalfAmountOfEatenFruits(false);
        }

        PacmanFrame.score.setText("<html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
    }

    public Pacman getPacman()
    {
        return this.pacman;
    }

    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
