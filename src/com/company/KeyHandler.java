package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sefa Yavuz on 16-12-2014.
 */
public class KeyHandler implements KeyListener
{
    Richting richting;

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (richting)
        {
            case BOVEN:
                System.out.println("BOVEN");
                break;
            case RECHTS:
                System.out.println("RECHTS");
                break;
            case LINKS:
                System.out.println("LINKS");
                break;
            default:
                System.out.println("BENEDEN");
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
