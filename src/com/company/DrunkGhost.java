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
        Box nextBox = this.getRandomAccessibleBox();

        if(nextBox.containsInstanceOf("Pacman"))
        {
            this.eatPacman();
        }
        else
        {
            this.setGhostPosition(nextBox);
        }
    }

    private Box getRandomAccessibleBox()
    {
        HashMap<String, Box> accessibleNeighbors    = this.getBox().getAccessibleNeighbors();
        Random generator                            = new Random();
        Object[] values                             = accessibleNeighbors.values().toArray();
        Object randomValue                          = values[generator.nextInt(values.length)];
        Box nextBox                                 = (Box) randomValue;

        return nextBox;
    }
}
