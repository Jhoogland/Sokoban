package com.company;

import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Box {

    public GameElement gameElement;
    private HashMap<String, Box> neighbors = new HashMap<String, Box>();


    public Box(){}

    public Box(GameElement gameElement)
    {

        this.gameElement = gameElement;
    }

    public HashMap<String, Box> getNeighbors(){

        return neighbors;
    }



    public void addTopNeighbor(Box box){

        neighbors.put("Top", box);
    }
    public void addBottomNeighbor(Box box){

        neighbors.put("Bottom", box);
    }
    public void addLeftNeighbor(Box box){

        neighbors.put("Left", box);
    }
    public void addRightNeighbor(Box box){

        neighbors.put("Right", box);
    }

}
