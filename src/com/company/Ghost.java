package com.company;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    public abstract void move();

    protected void setGhostPosition(Box nextBox)
    {
        this.getBox().removeGameElement(this);
        this.setBox(nextBox);
        nextBox.addGameElement(this);
    }

}
