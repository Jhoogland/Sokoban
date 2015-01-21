package com.company;

import java.util.*;

/**
 * Created by Sefa on 9-1-2015.
 */
public class  SmartGhost extends Ghost {

    private Queue<Box> boxesToInspect               = new LinkedList<Box>();
    private ArrayList<Box> visitedBoxes             = new ArrayList<Box>();
    private ArrayList<Box> currentBox               = new ArrayList<Box>();
    private ArrayList<Box> neighborBox              = new ArrayList<Box>();
    private Stack<Box> movementStack                = new Stack<Box>();
    private boolean searching                       = false;

    @Override
    public void move()
    {
        useStack();
    }

    public void findPacman()
    {
        createInitialState();
        search();
    }

    private void createInitialState()
    {
        reset();
        Box box = this.getBox();
        boxesToInspect.add(box);
    }

    private void reset()
    {
        boxesToInspect.clear();
        visitedBoxes.clear();
        movementStack.clear();
        currentBox.clear();
        neighborBox.clear();

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
            if(!neighbor.containsInstanceOf("Pacman"))
            {
                if (!currentBox.contains(neighbor))
                {
                    boxesToInspect.add(neighbor);
                    currentBox.add(box);
                    neighborBox.add(neighbor);
                }
            }
            else
            {
                if (!neighborBox.contains(neighbor))
                {
                    boxesToInspect.add(neighbor);
                    currentBox.add(box);
                    neighborBox.add(neighbor);
                }

                buildMovementStack(neighbor);
                break;
            }
        }
    }

    private void buildMovementStack(Box box)
    {
        Box current = box;
        movementStack.add(current);

        while(current != this.getBox())
        {
            int index    = currentBox.indexOf(current);
            Box nextStep = neighborBox.get(index);
            movementStack.add(nextStep);
        }
    }

    private void useStack()
    {
        if(!movementStack.isEmpty())
        {
            Box box = movementStack.pop();
            setGhostPosition(box);
        }
    }

    public void setSearching(boolean searching)
    {
        this.searching = searching;
    }

    public boolean isSearching()
    {
        return searching;
    }
}
