package com.company;

import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    public Gameboard gameboard = new Gameboard();

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                gameboard.getPacman().move(Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                gameboard.getPacman().move(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                gameboard.getPacman().move(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                gameboard.getPacman().move(Direction.DOWN);
                break;
            case KeyEvent.VK_Z:
                System.out.println("started");
                break;
            case KeyEvent.VK_X:
                System.out.println("Reset");
                break;
        }

        gameboard.repaint();
    }

    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
