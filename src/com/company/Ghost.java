package com.company;

import java.util.*;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    private Queue<Box> boxesToInspect               = new LinkedList<Box>();
    private ArrayList<Box> visitedBoxes             = new ArrayList<Box>();
    private ArrayList<Box> currentBoxes             = new ArrayList<Box>();
    private ArrayList<Box> previousBoxes            = new ArrayList<Box>();
    protected Stack<Box> movementStack              = new Stack<Box>();
    protected boolean searching                     = false;

    protected abstract void move();

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

    protected void findPacman()
    {
        reset();
        createInitialState();
        search();
    }

    private void createInitialState()
    {
        Box box = this.getBox();
        boxesToInspect.add(box);
    }

    private void reset()
    {
        boxesToInspect.clear();
        visitedBoxes.clear();
        movementStack.clear();
        currentBoxes.clear();
        previousBoxes.clear();
    }

    private void search()
    {
        while(!boxesToInspect.isEmpty())
        {
            Box current = boxesToInspect.remove();
            visitedBoxes.add(current);
            checkNeighbors(current);
        }
    }

    private void checkNeighbors(Box box)
    {
        for(Box neighbor : box.getAccessibleNeighbors().values())
        {
            if(neighbor.containsInstanceOf("Pacman"))
            {
                if (!visitedBoxes.contains(neighbor))
                {
                    boxesToInspect.add(neighbor);
                    currentBoxes.add(neighbor);
                    previousBoxes.add(box);
                }
                buildMovementStack(neighbor);
                setSearching(false);
                break;
            }
            else
            {
                if (!visitedBoxes.contains(neighbor))
                {
                    boxesToInspect.add(neighbor);
                    currentBoxes.add(neighbor);
                    previousBoxes.add(box);
                }
            }
        }
    }

    private void buildMovementStack(Box box)
    {
        Box current = box;

        while(current != this.getBox() && !movementStack.contains(current))
        {
            movementStack.push(current);
            int index    = currentBoxes.indexOf(current);
            Box nextStep = previousBoxes.get(index);
            current = nextStep;
        }
    }


    private Pacman getPacman()
    {
        return PacmanFrame.getGameboard().getPacman();
    }

    public void setSearching(boolean searching)
    {
        this.searching = searching;
    }

}
