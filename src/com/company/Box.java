package com.company;

import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Box {

    private GameElement gameElement;
    private HashMap<String, Box> neighbors = new HashMap<String, Box>();

    public Box(){}

    public Box(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }

    /* All these methods adds the neighbor of a Box into an HashMap; */

    public void addTopNeighbor(Box box)
    {
        neighbors.put("Top", box);
    }

    public void addBottomNeighbor(Box box)
    {
        neighbors.put("Bottom", box);
    }

    public void addLeftNeighbor(Box box)
    {
        neighbors.put("Left", box);
    }

    public void addRightNeighbor(Box box)
    {
        neighbors.put("Right", box);
    }

    /*  Getters & Setters */

    public Box getNeighbor(String key)
    {
        return neighbors.get(key);
    }

    public GameElement getGameElement()
    {
        return this.gameElement;
    }

    public void setGameElement(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }
}
