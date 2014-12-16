package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sefa on 16-12-2014.
 */
public class StartListener implements ActionListener {

    public static boolean started = true;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(started) {
            System.out.println("Started");
            this.started = false;
        }
        else
        {
            System.out.println("Paused");
            this.started = true;
        }
    }
}
