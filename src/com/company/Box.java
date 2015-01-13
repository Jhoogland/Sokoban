package com.company;

import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Box {

    private ArrayList<GameElement> gameElements = new ArrayList<GameElement>();
    private GameElement gameElement;
    private HashMap<String, Box> neighbors = new HashMap<String, Box>();

    public Box(){}

    public Box(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }

    /* All these methods adds the neighbor of a Box into an HashMap; */


    public void addNeighbor(String neighbor, Box box)
    {
        if(neighbor.equals("Top"))
        {
            this.neighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Bottom"))
        {
            this.neighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Left"))
        {
            this.neighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Right"))
        {
            this.neighbors.put(neighbor, box);
        }
    }

    /*  Getters & Setters */

    public Box getNeighbor(String key)
    {
        return neighbors.get(key);
    }

    public HashMap<String, Box> getNeighbors(){return neighbors;}
    public GameElement getGameElement()
    {
        return this.gameElement;
    }

    public void setGameElement(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }

    public void addGameElements(GameElement gameElement) { this.gameElements.add(gameElement); }
    public ArrayList<GameElement> getGameElements() { return this.gameElements; }
}
