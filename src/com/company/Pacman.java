package com.company;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    public int mouthAngle = 90;

    public Pacman() { }

    public Pacman(Box box)
    {
        super.setBox(box);
    }

    public void move(Direction direction)
    {
        switch (direction)
        {
            case UP:
                if(!(this.checkNeighbor(direction.UP) instanceof Wall))
                {
                    this.setPacmanPosition();
                }
                break;
            case DOWN:
                if(!(this.checkNeighbor(direction.DOWN) instanceof Wall))
                {
                    this.setPacmanPosition();
                }
                break;
            case LEFT:
                if(!(this.checkNeighbor(direction.LEFT) instanceof Wall))
                {
                    this.setPacmanPosition();
                }
                break;
            case RIGHT:
                if(!(this.checkNeighbor(direction.RIGHT) instanceof Wall))
                {
                    this.setPacmanPosition();
                }
                break;
        }
    }

    private void setPacmanPosition()
    {
        this.getBox().setGameElement(null);
        this.setBox(this.getNeighbor());
        this.getNeighbor().setGameElement(this);
    }

}
