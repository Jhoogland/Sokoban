package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public abstract class GameElement {
    private Box box;
    private Box startPositie = null;

    public GameElement() { }

    /* Getters & Setters */

    public Box getBox()
    {
        return this.box;
    }

    public void setBox(Box box)
    {
        this.box = box;
    }
    public Box getStartPositie(){return  this.startPositie;}
    public void setStartPositie(Box box){this.startPositie = box;}
}
