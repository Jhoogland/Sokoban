package com.company;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    public int mouthAngle = 90;
    private int score = 0;

    public Pacman() { }

    public void move(Direction direction)
    {
        if(PacmanFrame.getGameboard().timer.isRunning())
        {
            switch (direction)
            {
                case UP:
                    if (!(this.checkNeighbor(direction.UP) instanceof Wall))
                    {
                        if(!(this.checkNeighbor(direction.UP) instanceof Ghost))
                        {
                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case DOWN:
                    if (!(this.checkNeighbor(direction.DOWN) instanceof Wall))
                    {
                        if(!(this.checkNeighbor(direction.DOWN) instanceof Ghost))
                        {
                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case LEFT:
                    if (!(this.checkNeighbor(direction.LEFT) instanceof Wall))
                    {
                        if(!(this.checkNeighbor(direction.LEFT) instanceof Ghost))
                        {
                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case RIGHT:
                    if (!(this.checkNeighbor(direction.RIGHT) instanceof Wall))
                    {
                        if(!(this.checkNeighbor(direction.RIGHT) instanceof Ghost))
                        {
                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
            }
        }
    }

    private void setPacmanPosition()
    {
        this.getBox().setGameElement(null);
        this.setBox(this.getNextBox());
        this.getNextBox().setGameElement(this);
    }

    public int getMouthAngle()
    {
        return this.mouthAngle;
    }

    public int getScore()
    {
        return this.score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
