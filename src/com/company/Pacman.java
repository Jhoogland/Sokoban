package com.company;

import java.util.ArrayList;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    public int mouthAngle          = 90;
    private int score              = 0;
    private int life               = 3;

    private boolean isInvincible   = false;

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
                                if(checkNeighborUp.containsInstanceOf("SuperFruit"))
                                {
                                    this.isInvincible = true;
                                }

                                Fruit fruit = (Fruit) checkNeighborUp.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }

                                this.score = fruit.getValue() + this.score;
                            }
                        }
                        else if(checkNeighborUp.containsInstanceOf("Ghost") && this.isInvincible == true)
                        {
                            ArrayList<GameElement> neighborElements = checkNeighborUp.getGameElements();
                            this.setPacmanPosition();
                            this.score += 200;
                            Ghost ghost;
                            Fruit fruit;
                            if(checkNeighborUp.containsInstanceOf("Fruit"))
                            {
                                fruit = (Fruit) neighborElements.get(0);
                                checkNeighborUp.removeGameElement(fruit);

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }

                            if(checkNeighborUp.getGameElements().size() == 1)
                            {
                                ghost = (Ghost)neighborElements.get(0);
                                checkNeighborUp.removeGameElement(ghost);
                            }
                            else
                            {
                                for(GameElement ge : neighborElements)
                                {
                                    checkNeighborUp.removeGameElement(ge);
                                    ge.setBox(ge.getStartPositie());
                                }
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
                                if(checkNeighborDown.containsInstanceOf("SuperFruit"))
                                {
                                    System.out.println("true!");
                                    this.isInvincible = true;
                                }

                                Fruit fruit = (Fruit) checkNeighborDown.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }
                        }
                        else if(checkNeighborDown.containsInstanceOf("Ghost") && this.isInvincible == true)
                        {
                            ArrayList<GameElement> neighborElements = checkNeighborDown.getGameElements();
                            this.setPacmanPosition();
                            this.score += 200;
                            Ghost ghost;
                            Fruit fruit;
                            if(checkNeighborDown.containsInstanceOf("Fruit"))
                            {
                                fruit = (Fruit) neighborElements.get(0);
                                checkNeighborDown.removeGameElement(fruit);

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }

                            if(checkNeighborDown.getGameElements().size() == 1)
                            {
                                ghost = (Ghost)neighborElements.get(0);
                                checkNeighborDown.removeGameElement(ghost);
                            }
                            else
                            {
                                for(GameElement ge : neighborElements)
                                {
                                    checkNeighborDown.removeGameElement(ge);
                                    ge.setBox(ge.getStartPositie());
                                }
                            }
                        }
                        else
                        {
                           collision();
                        }
                    }
                    break;
                case LEFT:

                    Box checkNeighborLeft = this.checkNeighbor(direction.LEFT);

                    if (!(checkNeighborLeft.containsInstanceOf("Wall")))
                    {
                        if(!(checkNeighborLeft.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();
                            if(checkNeighborLeft.containsInstanceOf("Fruit"))
                            {
                                if(checkNeighborLeft.containsInstanceOf("SuperFruit"))
                                {
                                    System.out.println("true!");
                                    this.isInvincible = true;
                                }

                                Fruit fruit = (Fruit) checkNeighborLeft.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }
                        }
                        else if(checkNeighborLeft.containsInstanceOf("Ghost") && this.isInvincible == true)
                        {
                            ArrayList<GameElement> neighborElements = checkNeighborLeft.getGameElements();
                            this.setPacmanPosition();
                            this.score += 200;
                            Ghost ghost;
                            Fruit fruit;
                            if(checkNeighborLeft.containsInstanceOf("Fruit"))
                            {
                                fruit = (Fruit) neighborElements.get(0);
                                checkNeighborLeft.removeGameElement(fruit);

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }

                            if(checkNeighborLeft.getGameElements().size() == 1)
                            {
                                ghost = (Ghost)neighborElements.get(0);
                                checkNeighborLeft.removeGameElement(ghost);
                            }
                            else
                            {
                                for(GameElement ge : neighborElements)
                                {
                                    checkNeighborLeft.removeGameElement(ge);
                                    ge.setBox(ge.getStartPositie());
                                }
                            }
                        }
                        else
                        {
                            collision();
                        }
                    }
                    break;
                case RIGHT:

                    Box checkNeighborRight = this.checkNeighbor(direction.RIGHT);

                    if (!(checkNeighborRight.containsInstanceOf("Wall")))
                    {
                        if(!(checkNeighborRight.containsInstanceOf("Ghost")))
                        {
                            this.setPacmanPosition();

                            if(checkNeighborRight.containsInstanceOf("Fruit"))
                            {
                                if(checkNeighborRight.containsInstanceOf("SuperFruit"))
                                {
                                    this.isInvincible = true;
                                }

                                Fruit fruit = (Fruit) checkNeighborRight.getGameElements().get(0);
                                this.getBox().removeGameElement(fruit);
                                this.score = fruit.getValue() + this.score;

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }
                        }
                        else if(checkNeighborRight.containsInstanceOf("Ghost") && this.isInvincible == true)
                        {
                            ArrayList<GameElement> neighborElements = checkNeighborRight.getGameElements();
                            this.setPacmanPosition();
                            this.score += 200;
                            Ghost ghost;
                            Fruit fruit;
                            if(checkNeighborRight.containsInstanceOf("Fruit"))
                            {
                                fruit = (Fruit) neighborElements.get(0);
                                checkNeighborRight.removeGameElement(fruit);

                                PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                                if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                                {
                                    PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                                }
                            }
                            if(checkNeighborRight.getGameElements().size() == 1)
                            {
                                ghost = (Ghost)neighborElements.get(0);
                                checkNeighborRight.removeGameElement(ghost);
                            }
                            else
                            {
                                for(GameElement ge : neighborElements)
                                {
                                    checkNeighborRight.removeGameElement(ge);
                                    ge.setBox(ge.getStartPositie());
                                }
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

    public void setLife(int life)
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

    public boolean getInvincible()
    {
        return this.isInvincible;
    }

    public void setInvincible(boolean isInvincible)
    {
        this.isInvincible = isInvincible;
    }

}
