package com.company;

import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Box {

    private ArrayList<GameElement> gameElements         = new ArrayList<GameElement>();
    private HashMap<String, Box> neighbors              = new HashMap<String, Box>();
    private HashMap<String, Box> accessibleNeighbors    = new HashMap<String, Box>();

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

    public void addAccessibleNeighbor(String neighbor, Box box)
    {
        if(neighbor.equals("Top"))
        {
            this.accessibleNeighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Bottom"))
        {
            this.accessibleNeighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Left"))
        {
            this.accessibleNeighbors.put(neighbor, box);
        }
        else if(neighbor.equals("Right"))
        {
            this.accessibleNeighbors.put(neighbor, box);
        }
    }

    public boolean containsInstanceOf(String element)
    {
        boolean instance = false;
        for(GameElement ge: gameElements)
        {
            if(element.equals("Fruit"))
            {
                if (ge instanceof Fruit)
                {
                    instance = true;
                }
            }
            if(element.equals("Ghost"))
            {
                if (ge instanceof Ghost)
                {
                    instance = true;
                }
            }
            if(element.equals("Wall"))
            {
                if (ge instanceof Wall)
                {
                    instance = true;
                }
            }
            if(element.equals("Pacman"))
            {
                if (ge instanceof Pacman)
                {
                    instance = true;
                }
            }
            if(element.equals("SuperFruit"))
            {
                if(ge instanceof SuperFruit)
                {
                    instance = true;
                }
            }
            if(element.equals("Cherry"))
            {
                if(ge instanceof Cherry)
                {
                    instance = true;
                }
            }
        }

        return instance;
    }

    /*  Getters & Setters */

    public Box getNeighbor(String key)
    {
        return neighbors.get(key);
    }
    public HashMap<String, Box> getAccessibleNeighbors(){return accessibleNeighbors;}

    public void addGameElement(GameElement gameElement) { this.gameElements.add(gameElement); }
    public void removeGameElement(GameElement gameElement){ this.gameElements.remove(gameElement);}
    public ArrayList<GameElement> getGameElements() { return this.gameElements; }
}
