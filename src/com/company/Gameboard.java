package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel implements KeyListener{

    private final int GRIDSIZE = 10; //Set the gridsize
    private Box grid[][] = new Box[this.GRIDSIZE][this.GRIDSIZE]; // 2D Array thats holds all Boxes
    public Pacman pacman;

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = Ghost
    private int grid2[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 3, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        drawEverything();
        setNeighbors();
    }

    private void drawEverything()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++)
            {
                if(this.grid2[row][col] == 0)
                {
                    this.grid[row][col] = new Box();
                }
                else if(this.grid2[row][col] == 1)
                {
                    this.grid[row][col] = new Box(new Wall());
                }
                else if(this.grid2[row][col] == 2)
                {
                    this.pacman = new Pacman(90, this.grid[row][col]);
                    this.grid[row][col] = new Box(this.pacman);
                }
                else if(this.grid2[row][col] == 3)
                {
                    this.grid[row][col] = new Box(new Ghost());
                }
            }
        }
    }

    private void setNeighbors()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++) {

                int upRow =  row - 1;
                int downRow = row + 1;
                int leftCol = col - 1;
                int rightCol = col + 1;

                if (upRow >= 0)
                {
                    grid[row][col].addTopNeighbor(grid[upRow][col]);
                }
                if (downRow <= (this.GRIDSIZE -1))
                {
                    grid[row][col].addBottomNeighbor(grid[downRow][col]);
                }
                if(leftCol >=0 )
                {
                    grid[row][col].addLeftNeighbor(grid[row][leftCol]);
                }
                if (rightCol <= (this.GRIDSIZE - 1))
                {
                    grid[row][col].addRightNeighbor(grid[row][rightCol]);
                }


            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the gray borow that will divide the smaller 10row10 borowes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        // Draw the 10row10 borowes that make the grid.

        for (int row = 0; row < this.GRIDSIZE; row++)
        {
            for (int col = 0; col < this.GRIDSIZE; col++)
            {

                int newRow = row * 55;
                int newCol = col * 55;

                g.setColor(Color.black);
                g.fillRect(newRow, newCol, 50, 50);

                if (this.grid[col][row].getGameElement() instanceof Wall)
                {
                    g.setColor(Color.blue);
                    g.fillRect(newRow, newCol, 50, 50);


                }
                else if (this.grid[col][row].getGameElement() instanceof Pacman)
                {

                    g.setColor(Color.yellow);
                    g.fillArc(newRow, newCol, 50, 50, 90 / 2, 360 - 90);

                }
                else if (this.grid[col][row].getGameElement() instanceof Ghost)
                {
                    g.setColor(Color.red.darker());             // Ghosts are dark red.

                    // Body of Ghosts.
                    g.fillOval(newRow, newCol, 50, 50);

                    g.setColor(Color.black);  // Facial features of the Ghosts are black.

                    // Left eye of Ghost.
                    g.fillOval(newRow + 10, newCol + 20, 5, 5);

                    // Right eye of Ghost.
                    g.fillOval(newRow + 30, newCol + 20, 5, 5);


                    // Mouth of Ghost.
                    //g.drawLine(ghostPirowelY + 15, ghostPirowelrow + 50,
                    //      ghostPirowelY + 55, ghostPirowelrow + 50);

                }

            }
        }
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
