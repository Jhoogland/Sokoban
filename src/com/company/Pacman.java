package com.company;

import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Poppetje {
    public int mouthAngle;


    public Pacman(int mouthAngle, Box box) {
        this.mouthAngle = mouthAngle;
        this.box = box;
    }


    public boolean isAtMuur(Direction direction)
    {
        Box neighbor = null;

        switch (direction)
        {
            case UP:
                neighbor = this.box.getNeighbor("Top");
                break;
            case DOWN:
                neighbor = this.box.getNeighbor("Bottom");
                break;
            case LEFT:
                neighbor = this.box.getNeighbor("Left");
                break;
            case RIGHT:
                neighbor = this.box.getNeighbor("Right");
                break;
        }

        if(neighbor.getGameElement() instanceof Wall )
        {
            System.out.println("@muur");
            return true;
        }

        return false;
    }

    public void move(Direction direction)
    {
        switch (direction)
        {
            case UP:
                if(!isAtMuur(direction.UP))
                {
                }
                break;
            case DOWN:
                if(!isAtMuur(direction.DOWN))
                {
                }
                break;
            case LEFT:
                if(!isAtMuur(direction.LEFT))
                {
                }
                break;
            case RIGHT:
                if(!isAtMuur(direction.RIGHT))
                {
                }
                break;
        }
    }


}
