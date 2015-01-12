package com.company;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Sefa on 9-1-2015.
 */
public class DrunkGhost extends Ghost {

    public DrunkGhost() { }

    public DrunkGhost(Box box)
    {
        super.setBox(box);
    }

    public void moveRandom()
    {
        HashMap<String, Box> neighbors  = this.getBox().getNeighbors();
        Random generator                = new Random();
        Object[] values                 = neighbors.values().toArray();
        Object randomValue              = values[generator.nextInt(values.length)];
        if(!(randomValue instanceof Wall))
        {
            if (randomValue instanceof Pacman)
            {
                PacmanFrame.getGameboard().timer.stop();
            }
            else
            {
                this.getBox().setGameElement(null);
                this.setBox(this.getNeighbor());
                this.getNeighbor().setGameElement(this);
            }

        }
    }
}
