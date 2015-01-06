package com.company;

import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Box {

    public GameElement gameElement;
    public ArrayList<Box> neighbors;

    public Box(){}

    public Box(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }

}
