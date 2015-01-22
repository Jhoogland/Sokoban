package com.company;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public abstract class GameElement {
    private Box box;
    private Box startPosition = null;

    /* Getters & Setters */

    public Box getBox()
    {
        return this.box;
    }

    public void setBox(Box box)
    {
        this.box = box;
    }
    public Box getStartPosition()
    {
        return  this.startPosition;
    }
    public void setStartPosition(Box box)
    {
        this.startPosition = box;
    }
}
