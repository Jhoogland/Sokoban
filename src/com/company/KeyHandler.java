package com.company;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                PacmanFrame.getGameboard().getPacman().move(Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                PacmanFrame.getGameboard().getPacman().move(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                PacmanFrame.getGameboard().getPacman().move(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                PacmanFrame.getGameboard().getPacman().move(Direction.DOWN);
                break;
            case KeyEvent.VK_Z:
                PacmanFrame.getGameboard().start();
                break;
            case KeyEvent.VK_X:
                PacmanFrame.getGameboard().reset();
                break;
        }
    }

    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
