package com.company;

import java.util.ArrayList;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Icon {
    private int score              = 0;
    private int life               = 3;
    private boolean isInvincible   = false;
    private boolean isActive       = false;

    public void move(Direction direction)
    {
        if(this.isActive)
        {
            Box nextNeighbor = this.setNextBox(direction);

            if (!nextNeighbor.containsInstanceOf("Wall"))
            {
                if(!(nextNeighbor.containsInstanceOf("Ghost")))
                {
                    this.setPacmanPosition();
                    this.eatFruit(nextNeighbor);
                }
                else if(nextNeighbor.containsInstanceOf("Ghost") && this.isInvincible)
                {
                    this.setPacmanPosition();
                    this.eatFruit(nextNeighbor);
                    this.eatGhost(nextNeighbor);
                }
                else
                {
                    collide();
                }
            }
        }
    }

    private void eatGhost(Box nextNeighbor)
    {
        ArrayList<GameElement> neighborElements = nextNeighbor.getGameElements();

        Ghost ghost;

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

        this.score += 200;
    }

    private void eatFruit(Box nextNeighbor)
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

            if(PacmanFrame.getGameboard().getCurrentAmountOfFruits() < 0)
            {
                PacmanFrame.getGameboard().resetEveryonesPosition();
                PacmanFrame.getGameboard().changeLvl();
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
        this.setLife(this.life -= 1);
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
        if(life == 0)
        {
            Gameboard gameboard = PacmanFrame.getGameboard();

            gameboard.resetTheGame();
            gameboard.getLevelHandler().setAllLvlsCleared(true);
        }

        this.life = life;
        PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + this.getLife() + "<br> </h3></html>");
    }

    public boolean getInvincible()
    {
        return this.isInvincible;
    }
    public void setInvincible(boolean isInvincible)
    {
        this.isInvincible = isInvincible;
    }

    public void setActive(boolean active) { this.isActive = active; }

}
