package com.company;

import java.awt.*;
import java.util.*;

/**
 * Created by Sefa on 5-1-2015.
 */
public class Vak {

    public GameElement gameElement;
    public ArrayList<Vak> neighbors;

    public Vak(){}

    public Vak(GameElement gameElement)
    {
        this.gameElement = gameElement;
    }

}
