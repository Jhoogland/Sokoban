package com.company;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    public abstract void move();

    protected void setGhostPosition(Box nextBox)
    {
        this.getBox().removeGameElement(this);
        this.setBox(nextBox);
        nextBox.addGameElement(this);
    }

    protected void eatPacman()
    {
        if(this.getPacman().getInvincible())
        {
            PacmanFrame.getGameboard().resetPosition(this, this.getStartPosition());
        }
        else
        {
            PacmanFrame.getGameboard().resetEveryonesPosition();
            this.getPacman().setLife(this.getPacman().getLife() - 1);
        }
    }

    private Pacman getPacman()
    {
        return PacmanFrame.getGameboard().getPacman();
    }
}
