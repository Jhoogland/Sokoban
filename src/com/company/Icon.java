package com.company;

import java.util.ArrayList;

/**
 * Created by Sefa on 18-12-2014.
 */
public abstract class Icon extends GameElement {
    private Box nextBox = null;

    protected Box setNextBox(Direction direction)
    {
        switch (direction)
        {
            case UP:
                this.nextBox = this.getBox().getNeighbor("Top");
                break;
            case DOWN:
                this.nextBox = this.getBox().getNeighbor("Bottom");
                break;
            case LEFT:
                this.nextBox = this.getBox().getNeighbor("Left");
                break;
            case RIGHT:
                this.nextBox = this.getBox().getNeighbor("Right");
                break;
        }

        return this.nextBox;
    }

    protected Box getNextBox()
    {
        return this.nextBox;
    }

}
