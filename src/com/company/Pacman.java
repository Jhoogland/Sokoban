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

                    GameElement checkNeighborUp = this.checkNeighbor(direction.UP);

                    if (!(checkNeighborUp instanceof Wall))
                    {
                        if(!(checkNeighborUp instanceof Ghost))
                        {
                            if(checkNeighborUp instanceof Fruit)
                            {
                                this.score = ((Fruit) checkNeighborUp).getValue() + this.score;
                            }

                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case DOWN:

                    GameElement checkNeighborDown = this.checkNeighbor(direction.DOWN);

                    if (!(checkNeighborDown instanceof Wall))
                    {
                        if(!(checkNeighborDown instanceof Ghost))
                        {
                            if(checkNeighborDown instanceof Fruit)
                            {
                                this.score = ((Fruit) checkNeighborDown).getValue() + this.score;
                            }

                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case LEFT:

                    GameElement checkNeigborLeft = this.checkNeighbor(direction.LEFT);

                    if (!(checkNeigborLeft instanceof Wall))
                    {
                        if(!(checkNeigborLeft instanceof Ghost))
                        {
                            if(checkNeigborLeft instanceof Fruit)
                            {
                                this.score = ((Fruit) checkNeigborLeft).getValue() + this.score;
                            }

                            this.setPacmanPosition();
                        }
                        else
                        {
                            PacmanFrame.getGameboard().reset();
                        }
                    }
                    break;
                case RIGHT:

                    GameElement checkNeigborRight = this.checkNeighbor(direction.RIGHT);

                    if (!(checkNeigborRight instanceof Wall))
                    {
                        if(!(checkNeigborRight instanceof Ghost))
                        {
                            if(checkNeigborRight instanceof Fruit)
                            {
                                this.score = ((Fruit) checkNeigborRight).getValue() + this.score;
                            }

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
