package com.company;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    private Pacman pacman;

    public KeyHandler(Pacman pacman)
    {
        this.pacman = pacman;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                this.pacman.move(Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                this.pacman.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                this.pacman.move(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                this.pacman.move(Direction.DOWN);
                break;
            case KeyEvent.VK_Z:
                PacmanFrame.getGameboard().start();
                break;
            case KeyEvent.VK_X:
                PacmanFrame.getGameboard().reset();
                this.pacman.setScore(0);
                break;
        }


        PacmanFrame.score.setText("<html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
    }

    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
