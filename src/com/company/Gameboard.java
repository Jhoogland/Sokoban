package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel implements KeyListener {

    private final int BOXSIZE      = 50;
    private final int BOXGAP       = 5;

    private final int GRIDSIZE     = 10; //Set the gridsize
    private Box grid[][]           = new Box[this.GRIDSIZE][this.GRIDSIZE]; // 2D Array thats holds all Boxes

    private GameElement pacman      = new Pacman();
    private GameElement drunkGhost1 = new DrunkGhost();
    private GameElement drunkGhost2 = new DrunkGhost();
    private GameElement smartGhost1 = new SmartGhost();
    private GameElement smartGhost2 = new SmartGhost();

    public Timer timer;

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = DrunkGhost1
    // 4 = DrunkGhost2
    // 5 = SmartGhost1
    // 6 = SmartGhost2
    private int grid2[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 3, 4, 5, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        createEverything();
        setNeighbors();
    }

    private void createEverything()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++)
            {
                if(this.grid2[row][col] == 0) // Box
                {
                    this.grid[row][col] = new Box();
                }
                else if(this.grid2[row][col] == 1) // Wall
                {
                    this.grid[row][col] = new Box(new Wall());
                }
                else if(this.grid2[row][col] == 2) // Pacman
                {
                    this.grid[row][col] = new Box(this.pacman);
                    this.pacman.setBox(this.grid[row][col]);
                }
                else if(this.grid2[row][col] == 3) // DrunkGhost 1
                {
                    this.grid[row][col] = new Box(this.drunkGhost1);
                    this.drunkGhost1.setBox(this.grid[row][col]);
                }
                else if(this.grid2[row][col] == 4) // DrunkGhost 2
                {
                    this.grid[row][col] = new Box(this.drunkGhost2);
                    this.drunkGhost2.setBox(this.grid[row][col]);
                }
                else if(this.grid2[row][col] == 5) // SmartGhost 1
                {
                    this.grid[row][col] = new Box(this.smartGhost1);
                    this.smartGhost1.setBox(this.grid[row][col]);
                }
                else if(this.grid2[row][col] == 6) // SmartGhost 2
                {
                    this.grid[row][col] = new Box(this.smartGhost2);
                    this.smartGhost2.setBox(this.grid[row][col]);
                }
            }
        }
    }

    private void setNeighbors()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++)
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
                if (downRow <= (this.GRIDSIZE -1))
                {
                    grid[row][col].addNeighbor("Bottom", grid[downRow][col]);
                }
                if(leftCol >=0 )
                {
                    grid[row][col].addNeighbor("Left", grid[row][leftCol]);
                }
                if (rightCol <= (this.GRIDSIZE - 1))
                {
                    grid[row][col].addNeighbor("Right", grid[row][rightCol]);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the white box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        // Draw the 10x10 boxes that make the grid.
        for (int row = 0; row < this.GRIDSIZE; row++)
        {
            for (int col = 0; col < this.GRIDSIZE; col++)
            {
                int newBoxSize = this.BOXSIZE + this.BOXGAP;

                int newRow = row * newBoxSize;
                int newCol = col * newBoxSize;

                g.setColor(Color.black);
                g.fillRect(newRow, newCol, this.BOXSIZE, this.BOXSIZE);

                if (this.grid[col][row].getGameElement() instanceof Wall) // Draw all Walls
                {
                    g.setColor(Color.blue);
                    g.fillRect(newRow, newCol, this.BOXSIZE, this.BOXSIZE);
                }
                else if (this.grid[col][row].getGameElement() instanceof Pacman) // Draw Pacman
                {
                    g.setColor(Color.yellow);
                    g.fillArc(newRow, newCol, this.BOXSIZE, this.BOXSIZE, 90 / 2, 360 - 90);
                }
                else if (this.grid[col][row].getGameElement() instanceof Ghost) // Draw all Ghost each with a unique color
                {
                    if(this.grid[col][row].getGameElement().equals(this.drunkGhost1))
                    {
                        g.setColor(Color.RED);
                    }
                    else if(this.grid[col][row].getGameElement().equals(this.drunkGhost2))
                    {
                        g.setColor(Color.ORANGE);
                    }
                    else if(this.grid[col][row].getGameElement().equals(this.smartGhost1))
                    {
                        g.setColor(Color.CYAN);
                    }
                    else if(this.grid[col][row].getGameElement().equals(this.smartGhost2))
                    {
                        g.setColor(Color.PINK);
                    }

                    // Body of Ghosts.
                    g.fillOval(newRow, newCol, this.BOXSIZE, this.BOXSIZE);

                    g.setColor(Color.black);  // Facial features of the Ghosts are black.

                    // Left eye of Ghost.
                    g.fillOval(newRow + 15, newCol + 20, 5, 5);

                    // Right eye of Ghost.
                    g.fillOval(newRow + 30, newCol + 20, 5, 5);
                }
            }
        }
    }

    public GameElement getPacman()
    {
        return this.pacman;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case java.awt.event.KeyEvent.VK_UP:
                pacman.move(Direction.UP);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                pacman.move(Direction.RIGHT);
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                pacman.move(Direction.LEFT);
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                pacman.move(Direction.DOWN);
                break;

        }

        repaint();
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }
    public void keyTyped(java.awt.event.KeyEvent e) { }
}
