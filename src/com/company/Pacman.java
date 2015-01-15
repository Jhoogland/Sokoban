package com.company;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    public int mouthAngle = 90;
    private int score = 0;
    private int life = 3;

    public Pacman() { }

    public void move(Direction direction)
    {
        if(PacmanFrame.getGameboard().timerHandler.timer.isRunning())
        {
            switch (direction)
            {
                case UP:

                    Box checkNeighborUp = this.checkNeighbor(direction.UP);

                    if (!checkNeighborUp.containsInstanceOf("Wall"))
                    {
                        if(!(checkNeighborUp.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();
                            if(checkNeighborUp.containsInstanceOf("Fruit"))
                            {
                                Fruit fruit = (Fruit) checkNeighborUp.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;
                            }
                        }
                        else
                        {
                            collision();
                        }
                    }
                    break;
                case DOWN:

                    Box checkNeighborDown = this.checkNeighbor(direction.DOWN);

                    if (!(checkNeighborDown.containsInstanceOf("Wall")))
                    {
                        if(!(checkNeighborDown.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();
                            if(checkNeighborDown.containsInstanceOf("Fruit"))
                            {
                                Fruit fruit = (Fruit) checkNeighborDown.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;
                            }
                        }
                        else
                        {
                           collision();
                        }
                    }
                    break;
                case LEFT:

                    Box checkNeigborLeft = this.checkNeighbor(direction.LEFT);

                    if (!(checkNeigborLeft.containsInstanceOf("Wall")))
                    {
                        if(!(checkNeigborLeft.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();
                            if(checkNeigborLeft.containsInstanceOf("Fruit"))
                            {
                                Fruit fruit = (Fruit) checkNeigborLeft.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;
                            }
                        }
                        else
                        {
                            collision();
                        }
                    }
                    break;
                case RIGHT:

                    Box checkNeigborRight = this.checkNeighbor(direction.RIGHT);

                    if (!(checkNeigborRight.containsInstanceOf("Wall")))
                    {
                        if(!(checkNeigborRight.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();
                            if(checkNeigborRight.containsInstanceOf("Fruit"))
                            {
                                Fruit fruit = (Fruit) checkNeigborRight.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;
                            }
                        }
                        else
                        {
                            collision();
                        }
                    }
                    break;
            }
        }
    }

    private void setPacmanPosition()
    {
        this.getBox().removeGameElement(this);
        this.setBox(this.getNextBox());
        this.getNextBox().addGameElement(this);
    }
    private void collision()
    {
        PacmanFrame.getGameboard().reset();
        this.loseLife();
        PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + this.getLife() + "<br> </h3></html>");
    }

    public int getMouthAngle()
    {
        return this.mouthAngle;
    }

    public int getScore()
    {
        return this.score;
    }

    public int getLife()
    {
        return this.life;
    }

    public void setLife()
    {
        this.life = life;
    }

    public void loseLife()
    {
        life -= 1;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

}
