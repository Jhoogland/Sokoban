package com.company;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    public Ghost() { }

    protected void setGhostPosition(Box nextBox)
    {
        this.getBox().removeGameElement(this);
        this.setBox(nextBox);
        nextBox.addGameElement(this);
    }

    public void moveRandom() { }

}
