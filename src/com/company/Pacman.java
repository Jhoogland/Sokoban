package com.company;

import java.util.ArrayList;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    private int score              = 0;
    private int life               = 3;
    private boolean isInvincible   = false;

    public void move(Direction direction)
    {
        if(PacmanFrame.getGameboard().timerHandler.timer.isRunning())
        {
            Box nextNeighbor = this.setNextBox(direction);

            if (!nextNeighbor.containsInstanceOf("Wall"))
            {
                if(!(nextNeighbor.containsInstanceOf("Ghost")))
                {
                    this.setPacmanPosition();
                    this.eatFruits(nextNeighbor);
                }
                else if(nextNeighbor.containsInstanceOf("Ghost") && this.isInvincible == true)
                {
                    ArrayList<GameElement> neighborElements = nextNeighbor.getGameElements();
                    this.setPacmanPosition();
                    this.score += 200;
                    Ghost ghost;

                    this.eatFruits(nextNeighbor);

                    if(nextNeighbor.getGameElements().size() == 1)
                    {
                        ghost = (Ghost)neighborElements.get(0);
                        nextNeighbor.removeGameElement(ghost);
                    }
                    else
                    {
                        for(GameElement ge : neighborElements)
                        {
                            nextNeighbor.removeGameElement(ge);
                            ge.setBox(ge.getStartPosition());
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

    public void eatFruits(Box nextNeighbor)
    {
        ArrayList<GameElement> neighborElements = nextNeighbor.getGameElements();
        Fruit fruit;
        if(nextNeighbor.containsInstanceOf("Fruit"))
        {
            if(nextNeighbor.containsInstanceOf("SuperFruit") && this.isInvincible == false)
            {
                this.isInvincible = true;
            }

            fruit = (Fruit) neighborElements.get(0);
            nextNeighbor.removeGameElement(fruit);

            PacmanFrame.getGameboard().setCurrentAmountOfFruits(PacmanFrame.getGameboard().getCurrentAmountOfFruits() - 1);

            if((PacmanFrame.getGameboard().getStartingAmountOfFruits() / 2) == PacmanFrame.getGameboard().getCurrentAmountOfFruits())
            {
                PacmanFrame.getGameboard().setHalfAmountOfEatenFruits(true);
            }

            this.score = fruit.getValue() + this.score;
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
