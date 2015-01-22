package com.company;

import java.util.*;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    private Queue<Box> boxesToInspect               = new LinkedList<Box>();
    private ArrayList<Box> visitedBoxes             = new ArrayList<Box>();
    private ArrayList<Box> currentBox               = new ArrayList<Box>();
    private ArrayList<Box> previousBox              = new ArrayList<Box>();
    protected Stack<Box> movementStack                = new Stack<Box>();
    protected boolean searching                     = false;

    public abstract void move();

    protected void setGhostPosition(Box nextBox)
    {
        this.getBox().removeGameElement(this);
        this.setBox(nextBox);
        nextBox.addGameElement(this);
    }

    protected void eatPacman()
    {
        PacmanFrame.getGameboard().resetEveryonesPosition();
        this.getPacman().setLife(this.getPacman().getLife() - 1);
    }

    protected void flee()
    {
        PacmanFrame.getGameboard().resetPosition(this, this.getStartPosition());
        setGhostPosition(this.getFleeBox(this.getBox()));
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
        currentBox.clear();
        previousBox.clear();
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
                    currentBox.add(neighbor);
                    previousBox.add(box);
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
                    currentBox.add(neighbor);
                    previousBox.add(box);
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
            int index    = currentBox.indexOf(current);
            Box nextStep = previousBox.get(index);
            current = nextStep;
        }
    }

    private Box getFleeBox(Box box)
    {
        ArrayList<Box> boxes = null;
        boxes.addAll(box.getAccessibleNeighbors().values());

        boxes.remove(movementStack.peek());
        int random = new Random().nextInt(boxes.size());

        return boxes.get(random);

    }

    public void setSearching(boolean searching)
    {
        this.searching = searching;
    }

    private Pacman getPacman()
    {
        return PacmanFrame.getGameboard().getPacman();
    }
}
