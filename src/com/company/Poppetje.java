package com.company;

/**
 * Created by Sefa on 18-12-2014.
 */
public abstract class Poppetje extends GameElement {
    private Box neighbor = null;


    public GameElement checkNeighbor(Direction direction)
    {
        switch (direction)
        {
            case UP:
                this.setNeighbor(getBox().getNeighbor("Top"));
                break;
            case DOWN:
                this.setNeighbor(getBox().getNeighbor("Bottom"));
                break;
            case LEFT:
                this.setNeighbor(getBox().getNeighbor("Left"));
                break;
            case RIGHT:
                this.setNeighbor(getBox().getNeighbor("Right"));
                break;
        }

        return this.getNeighbor().getGameElement();
    }

    public void setNeighbor(Box box)
    {
        this.neighbor = box;
    }

    public Box getNeighbor()
    {
        return this.neighbor;
    }

    public void move(Direction direction) {}
}
