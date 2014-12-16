package com.company;

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
                System.out.println("BOVEN");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RECHTS");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LINKS");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("BENEDEN");
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
