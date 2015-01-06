package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel {

    private final int GRIDSIZE = 10; //Set the gridsize
    private Box grid[][] = new Box[this.GRIDSIZE][this.GRIDSIZE]; // 2D Array thats holds all Boxes

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = Ghost
    private int grid2[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        drawEverything();
        //setNeighbors();
    }

    public void drawEverything()
    {
        for(int x = 0;  x < this.GRIDSIZE; x++)
        {
            for(int y = 0; y < this.GRIDSIZE; y++)
            {
                if(this.grid2[x][y] == 0)
                {
                    this.grid[x][y] = new Box();
                }
                else if(this.grid2[x][y] == 1)
                {
                    this.grid[x][y] = new Box(new Wall());
                }
                else if(this.grid2[x][y] == 2)
                {
                    this.grid[x][y] = new Box(new Pacman(90));
                }
                else if(this.grid2[x][y] == 3)
                {
                    this.grid[x][y] = new Box(new Ghost());
                }
            }
        }
    }

    public void setNeighbors()
    {
        for(int x = 0;  x < this.GRIDSIZE; x++)
        {
            for(int y = 0; y < this.GRIDSIZE; y++)
            {
                if(y > 0)
                {
                    grid[x][y].neighbors.add(grid[x][y-1]);
                }
                if(y < this.GRIDSIZE)
                {
                    grid[x][y].neighbors.add(grid[x][y+1]);
                }
                if(x > 0)
                {
                    grid[x][y].neighbors.add(grid[x-1][y]);
                }
                if(x < this.GRIDSIZE)
                {
                    grid[x][y].neighbors.add(grid[x+1][y]);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the gray box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        // Draw the 10x10 boxes that make the grid.
        for (int x = 0; x < this.GRIDSIZE; x++)
        {
            for (int y = 0; y < this.GRIDSIZE; y++)
            {
                int newX = x * 55;
                int newY = y * 55;

                g.setColor(Color.black);
                g.fillRect(newX, newY, 50, 50);

                if (this.grid[y][x].gameElement instanceof Wall)
                {
                    g.setColor(Color.blue);
                    g.fillRect(newX, newY, 50, 50);

                    System.out.println("Wall");
                }
                else if (this.grid[y][x].gameElement instanceof Pacman)
                {

                    g.setColor(Color.yellow);
                    g.fillArc(newX, newY, 50, 50, 90 / 2, 360 - 90);
                }
                else if (this.grid[y][x].gameElement instanceof Ghost)
                {
                    g.setColor(Color.red.darker());             // Ghosts are dark red.

                    // Body of Ghosts.
                    g.fillOval(newX, newY, 50, 50);

                    g.setColor(Color.black);  // Facial features of the Ghosts are black.

                    // Left eye of Ghost.
                    g.fillOval(newX + 10, newY + 20, 5, 5);

                    // Right eye of Ghost.
                    g.fillOval(newX + 30, newY + 20, 5, 5);

                    // Mouth of Ghost.
                    //g.drawLine(ghostPixelY + 15, ghostPixelX + 50,
                    //      ghostPixelY + 55, ghostPixelX + 50);
                }
            }
        }
    }

    /*
    @Override
    public void keyPressed(java.awt.event.KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case java.awt.event.KeyEvent.VK_UP:
                pacman.move(Direction.BOVEN);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                pacman.move(Direction.RECHTS);
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                pacman.move(Direction.LINKS);
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                pacman.move(Direction.BENEDEN);
                break;
        }

        repaint();
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }
    public void keyTyped(java.awt.event.KeyEvent e) { }

*/

}
