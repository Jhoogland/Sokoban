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
        Box nextBox = this.getRandomBox();

        if(nextBox.containsInstanceOf("Pacman"))
        {
            if(this.getPacman().getInvincible())
            {
                PacmanFrame.getGameboard().resetPosition(this, this.getStartPosition());
            }
            else
            {
                PacmanFrame.getGameboard().resetPosition(this.getPacman(), this.getPacman().getStartPosition());
                this.getPacman().setLife(this.getPacman().getLife() - 1);
            }
        }
        else
        {
            this.setGhostPosition(nextBox);
        }
    }

    private Box getRandomBox()
    {
        HashMap<String, Box> accessibleNeighbors    = this.getBox().getAccessibleNeighbors();
        Random generator                            = new Random();
        Object[] values                             = accessibleNeighbors.values().toArray();
        Object randomValue                          = values[generator.nextInt(values.length)];
        Box nextBox                                 = (Box) randomValue;

        return nextBox;
    }
}
