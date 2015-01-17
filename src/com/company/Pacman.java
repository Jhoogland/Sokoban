package com.company;

import java.util.ArrayList;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    private int mouthAngle          = 90;
    private int score              = 0;
    private int life               = 3;

    private boolean isInvincible   = false;

    public Pacman() { }

    public void move(Direction direction)
    {
        if(PacmanFrame.getGameboard().timerHandler.timer.isRunning())
        {
            Box checkNeighbor = this.checkNeighbor(direction);

            if (!checkNeighbor.containsInstanceOf("Wall"))
            {
                if(!(checkNeighbor.containsInstanceOf("Ghost")))
                {
                    this.setPacmanPosition();
                    if(checkNeighbor.containsInstanceOf("Fruit"))
                    {
                        if(checkNeighbor.containsInstanceOf("SuperFruit"))
                        {
                            this.isInvincible = true;
                        }

                        Fruit fruit = (Fruit) checkNeighbor.getGameElements().get(0);
                        this.getBox().removeGameElement(fruit);
                        PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                        if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                        {
                            PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                        }

                        this.score = fruit.getValue() + this.score;
                    }
                }
                else if(checkNeighbor.containsInstanceOf("Ghost") && this.isInvincible == true)
                {
                    ArrayList<GameElement> neighborElements = checkNeighbor.getGameElements();
                    this.setPacmanPosition();
                    this.score += 200;
                    Ghost ghost;
                    Fruit fruit;
                    if(checkNeighbor.containsInstanceOf("Fruit"))
                    {
                        fruit = (Fruit) neighborElements.get(0);
                        checkNeighbor.removeGameElement(fruit);

                        PacmanFrame.getGameboard().setAmountOfFruits(PacmanFrame.getGameboard().getAmountOfFruits() - 1);

                        if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getAmountOfFruits())
                        {
                            PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
                        }
                    }

                    if(checkNeighbor.getGameElements().size() == 1)
                    {
                        ghost = (Ghost)neighborElements.get(0);
                        checkNeighbor.removeGameElement(ghost);
                    }
                    else
                    {
                        for(GameElement ge : neighborElements)
                        {
                            checkNeighbor.removeGameElement(ge);
                            ge.setBox(ge.getStartPositie());
                        }
                    }
                }
                else
                {
                    collide();
                }
            }
        }
    }

    private void setPacmanPosition()
    {
        this.getBox().removeGameElement(this);
        this.setBox(this.getNextBox());
        this.getNextBox().addGameElement(this);
    }

    private void collide()
    {
        PacmanFrame.getGameboard().resetEveryonesPosition();
        this.life -= 1;
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
    public void setScore(int score)
    {
        this.score = score;
    }

    public int getLife()
    {
        return this.life;
    }

    public void setLife(int life)
    {
        this.life = life;
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
