package com.company;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Sefa on 9-1-2015.
 */
public class DrunkGhost extends Ghost {

    @Override
    public void move()
    {
        HashMap<String, Box> neighbors  = this.getBox().getNeighbors();
        Random generator                = new Random();
        Object[] values                 = neighbors.values().toArray();
        Object randomValue              = values[generator.nextInt(values.length)];
        Box nextBox                     = (Box) randomValue;
        if(!(nextBox.containsInstanceOf("Wall")))
        {
            if (nextBox.containsInstanceOf("Pacman")  && PacmanFrame.getGameboard().getPacman().getInvincible() == false)
            {
                PacmanFrame.getGameboard().resetEveryonesPosition();
                PacmanFrame.getGameboard().getPacman().setLife(PacmanFrame.getGameboard().getPacman().getLife() - 1);
                PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + PacmanFrame.getGameboard().getPacman().getLife() + "<br> </h3></html>");
            }
            else if(nextBox.containsInstanceOf("Pacman") && PacmanFrame.getGameboard().getPacman().getInvincible())
            {
                //RESET GHOST POSITION
            }
            else
            {
                this.setGhostPosition(nextBox);
            }

        }
    }
}
