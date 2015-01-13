package com.company;

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
        GameElement nextGameElement     = nextBox.getGameElement();
        if(!(nextGameElement instanceof Wall))
        {
            if ((nextGameElement instanceof Pacman))
            {
                PacmanFrame.getGameboard().reset();
            }
            else
            {
                this.getBox().setGameElement(null);
                this.setBox(nextBox);
                nextBox.setGameElement(this);
            }

        }
    }
}
