package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Sefa on 9-1-2015.
 */
public class DrunkGhost extends Ghost {

    public DrunkGhost() { }

    public void moveRandom()
    {
        HashMap<String, Box> neighbors  = this.getBox().getNeighbors();
        Random generator                = new Random();
        Object[] values                 = neighbors.values().toArray();
        Object randomValue              = values[generator.nextInt(values.length)];
        Box nextBox                     = (Box) randomValue;
        if(!(nextBox.containsInstanceOf("Wall")))
        {
            if (nextBox.containsInstanceOf("Pacman"))
            {
                PacmanFrame.getGameboard().reset();
                PacmanFrame.getGameboard().getPacman().loseLife();
                PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + PacmanFrame.getGameboard().getPacman().getLife() + "<br> </h3></html>");
            }
            else
            {
                this.setGhostPosition(nextBox);
            }

        }
    }
}
