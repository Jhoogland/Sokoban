package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sefa on 16-12-2014.
 */
public class StartHandler implements ActionListener {

    public boolean started = false;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!this.started) {
            System.out.println("Started");
            PacmanFrame.starten();
            PacmanFrame.startButton.setText("Pause");
            this.started = true;
        }
        else
        {
            System.out.println("Paused");
            PacmanFrame.startButton.setText("Start");
            this.started = false;
        }
    }
}
