package com.company;

import java.util.*;

/**
 * Created by Sefa on 9-1-2015.
 */
public class  SmartGhost extends Ghost {

    private Queue<Box> boxesToInspect               = new LinkedList<Box>();
    private ArrayList<Box> visitedBoxes             = new ArrayList<Box>();
    private ArrayList<Box> currentBox               = new ArrayList<Box>();
    private ArrayList<Box> previousBox              = new ArrayList<Box>();
    private Stack<Box> movementStack                = new Stack<Box>();
    private boolean searching                       = false;

    @Override
    public void move()
    {
        if(!searching)
        {
            setSearching(true);
            findPacman();
        }
        if(movementStack.size() > 0)
        {
            Box box = movementStack.pop();

            if(!box.containsInstanceOf("Pacman"))
            {
                setGhostPosition(box);
            }
            else
            {
                this.eatPacman();
            }
        }
    }

    public void findPacman()
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

    public void setSearching(boolean searching)
    {
        this.searching = searching;
    }
}
