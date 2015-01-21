package com.company;

import java.util.*;

/**
 * Created by Sefa on 9-1-2015.
 */
public class SmartGhost extends Ghost {

    private Queue<Box> boxesToInspect = new LinkedList<Box>();
    private ArrayList<Box> visitedBoxes = new ArrayList<Box>();
    private HashMap<Box, Box> connectedBoxes = new HashMap<Box, Box>();
    private ArrayList<Box> currentBox = new ArrayList<Box>();
    private ArrayList<Box> neighborBox = new ArrayList<Box>();
    private Stack<Box> movementStack = new Stack<Box>();
    private boolean searching = false;



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
        connectedBoxes.clear();
    }

    private void search()
    {
        while(!boxesToInspect.isEmpty())
        {
            Box current = boxesToInspect.remove();
            if(!visitedBoxes.contains(current)) {
                visitedBoxes.add(current);
                checkNeighbors(current);
            }
        }
    }

    private void checkNeighbors(Box box)
    {
        for(Box neighbor : box.getAccessibleNeighbors().values())
        {
            if(!neighbor.containsInstanceOf("Pacman"))
            {
                if (!neighborBox.contains(neighbor)) {
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
                setSearching(false);
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

            int index = neighborBox.indexOf(box);
            Box nextStep = currentBox.get(index);
            movementStack.add(nextStep);
            current = nextStep;
        }


    }

    private void useStack()
    {
        if(!movementStack.isEmpty()) {
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
