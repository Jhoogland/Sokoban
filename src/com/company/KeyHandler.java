package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sefa Yavuz on 16-12-2014.
 */
public class KeyHandler implements KeyListener
{
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                BottomPanel.pacman.p.y -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                BottomPanel.pacman.p.x += 1;
                break;
            case KeyEvent.VK_LEFT:
                BottomPanel.pacman.p.x -= 1;
                break;
            case KeyEvent.VK_DOWN:
                BottomPanel.pacman.p.x += 1;
                break;
            default:
                System.out.println("Ongeldige toets ingedrukt!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
