package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel {

    private final int BOXSIZE      = 50;
    private final int BOXGAP       = 0;

    private final int GRIDROW      = 10; //Set the gridsize
    private final int GRIDCOL      = 20;
    private Box grid[][]           = new Box[this.GRIDROW][this.GRIDCOL]; // 2D Array thats holds all Boxes

    private Pacman pacman          = new Pacman();
    private DrunkGhost drunkGhost1 = new DrunkGhost();
    private DrunkGhost drunkGhost2 = new DrunkGhost();
    private SmartGhost smartGhost1 = new SmartGhost();
    private SmartGhost smartGhost2 = new SmartGhost();

    public TimerHandler timerHandler = new TimerHandler(200, this);
    private KeyHandler keyHandler  = new KeyHandler(this.pacman);

    private int amountOfFruits;

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = DrunkGhost1
    // 4 = DrunkGhost2
    // 5 = SmartGhost1
    // 6 = SmartGhost2
    // 7 = Fruit
    // 8 = SuperFruit
    private int gridStructure[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 7, 7, 7, 7, 7, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 5, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        PacmanFrame.frame.addKeyListener(keyHandler);
        createEverything();
        setNeighbors();
        System.out.println(grid[1][8].getGameElements().toString());
    }

    protected void reset()
    {
        if(timerHandler.timer.isRunning())
        {
            resetPosition(this.pacman, this.grid[1][1]);
            resetPosition(this.drunkGhost1, this.grid[8][15]);
            resetPosition(this.drunkGhost2, this.grid[8][16]);
            resetPosition(this.smartGhost1, this.grid[8][17]);
            resetPosition(this.smartGhost2, this.grid[8][18]);

            repaint();
        }
    }

    protected void start()
    {
        if(!timerHandler.timer.isRunning())
        {
            timerHandler.timer.start();
        }
        else
        {
            timerHandler.timer.stop();
        }
    }

    private void createEverything()
    {
        for(int row = 0;  row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                if(gridStructure[row][col] == 0) // Box
                {
                    this.grid[row][col] = new Box();
                }
                else if(this.gridStructure[row][col] == 1) // Wall
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Wall());
                }
                else if(this.gridStructure[row][col] == 2) // Pacman
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.pacman);
                    this.pacman.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 3) // DrunkGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.drunkGhost1);
                    this.drunkGhost1.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 4) // DrunkGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.drunkGhost2);
                    this.drunkGhost2.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 5) // SmartGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.smartGhost1);
                    this.smartGhost1.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 6) // SmartGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.smartGhost2);
                    this.smartGhost2.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 7) // Fruit
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Fruit());

                    this.amountOfFruits++;
                }
                else if(this.gridStructure[row][col] == 8) // SuperFruit
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new SuperFruit());

                    this.amountOfFruits++;
                }
            }
        }
    }

    private void setNeighbors()
    {
        for(int row = 0;  row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                // Neighbors from each box
                int upRow =  row - 1;   // TOP
                int downRow = row + 1;  // BOTTOM
                int leftCol = col - 1;  // LEFT
                int rightCol = col + 1; // RIGHT

                // Checks whether the row values are correct with the condition and adds them the Neighbor HashMap in the Box class
                if (upRow >= 0)
                {
                    grid[row][col].addNeighbor("Top", grid[upRow][col]);
                }
                if (downRow <= (this.GRIDROW -1))
                {
                    grid[row][col].addNeighbor("Bottom", grid[downRow][col]);
                }
                if(leftCol >=0 )
                {
                    grid[row][col].addNeighbor("Left", grid[row][leftCol]);
                }
                if (rightCol <= (this.GRIDCOL - 1))
                {
                    grid[row][col].addNeighbor("Right", grid[row][rightCol]);
                }
            }
        }
    }

    private void insertInitialElement(int row, int col, GameElement ge)
    {
        grid[row][col].addGameElement(ge);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw the white box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 600);

        // Draw the 10x10 boxes that make the grid.
        for (int row = 0; row < this.GRIDROW; row++)
        {
            for (int col = 0; col < this.GRIDCOL; col++) {
                int newBoxSize = this.BOXSIZE + this.BOXGAP;

                int newRow = row * newBoxSize;
                int newCol = col * newBoxSize;

                g.setColor(Color.black);
                g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

                ArrayList elements = grid[row][col].getGameElements();

                if(elements.size() == 1)
                {
                    paintGameElement(g, (GameElement) elements.get(0), newRow, newCol);
                }
                if(elements.size() > 1)
                {
                    int lastIndex = elements.size()-1;
                    paintGameElement(g, (GameElement) elements.get(lastIndex), newRow, newCol);
                }
            }
        }
    }

    private void paintGameElement(Graphics g, GameElement ge, int newRow, int newCol)
    {
        if(ge instanceof Wall)
        {
            g.setColor(Color.blue);
            g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);
        }
        else if(ge.equals(this.pacman))
        {
            g.setColor(Color.yellow);
            g.fillArc(newCol, newRow, this.BOXSIZE, this.BOXSIZE, 90 / 2, 360 - 90);
        }
        else if(ge instanceof Fruit)
        {
            g.setColor(Color.white);

            g.fillOval(newCol + 20, newRow + 20, this.BOXSIZE / 4, this.BOXSIZE / 4);
        }
        else if(ge instanceof Ghost)
        {
            if(ge.equals(this.drunkGhost1))
            {
                g.setColor(Color.RED);
            }
            else if(ge.equals(this.drunkGhost2))
            {
                g.setColor(Color.ORANGE);
            }
            else if(ge.equals(this.smartGhost1))
            {
                g.setColor(Color.CYAN);
            }
            else if(ge.equals(this.smartGhost2))
            {
                g.setColor(Color.PINK);
            }

            // Body of Ghosts.
            g.fillOval(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

            g.setColor(Color.black);  // Facial features of the Ghosts are black.

            // Left eye of Ghost.
            g.fillOval(newCol + 15, newRow + 20, 5, 5);

            // Right eye of Ghost.
            g.fillOval(newCol + 30, newRow + 20, 5, 5);
        }

    }

    private void resetPosition(Icon icon, Box grid)
    {
        icon.getBox().removeGameElement(icon);
        icon.setBox(grid);
        icon.getBox().addGameElement(icon);
    }

    public Pacman getPacman()
    {
        return this.pacman;
    }

    public Ghost getDrunkGhost1() { return this.drunkGhost1; }
    public Ghost getDrunkGhost2() { return this.drunkGhost2; }

    public int getAmountOfFruits() { return this.amountOfFruits; }
    public void setAmountOfFruits(int amountOfFruits) { this.amountOfFruits = amountOfFruits; }
}
