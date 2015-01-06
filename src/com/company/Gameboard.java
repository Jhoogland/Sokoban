package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel {

    private int gridSize = 10;
    private Vak grid[][] = new Vak[this.gridSize][this.gridSize];

    public static Queue<GameElement> gameElements = new LinkedList<GameElement>();

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

    public static int border = 20;            // Size of blue border around grid.
    public static int boxBorder = 5;            // Size of gray border between boxes.
    public static int boxOuterBorder = 8;

    public Gameboard()
    {
        drawEverything();
        //setNeighbors();
    }

    public void drawEverything()
    {
        for(int x = 0;  x < this.gridSize; x++)
        {
            for(int y = 0; y < this.gridSize; y++)
            {
                if(this.grid2[x][y] == 0)
                {
                    this.grid[x][y] = new Vak();
                }
                else if(this.grid2[x][y] == 1)
                {
                    this.grid[x][y] = new Vak(new Wall());
                }
                else if(this.grid2[x][y] == 2)
                {
                    this.grid[x][y] = new Vak(new Pacman(90));
                }
                else if(this.grid2[x][y] == 3)
                {
                    this.grid[x][y] = new Vak(new Ghost());
                }
            }
        }
    }

    public void setNeighbors()
    {
        for(int x = 0;  x < this.gridSize; x++)
        {
            for(int y = 0; y < this.gridSize; y++)
            {
                if(y > 0)
                {
                    grid[x][y].neighbors.add(grid[x][y-1]);
                }
                if(y < this.gridSize)
                {
                    grid[x][y].neighbors.add(grid[x][y+1]);
                }
                if(x > 0)
                {
                    grid[x][y].neighbors.add(grid[x-1][y]);
                }
                if(x < this.gridSize)
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
        for (int x = 0; x < this.gridSize; x++)
        {
            for (int y = 0; y < this.gridSize; y++)
            {
                int newX = x * 55;
                int newY = y * 55;

                g.setColor(Color.black);
                g.fillRect(newX, newY, 50, 50);

                if (this.grid[x][y].gameElement instanceof Wall)
                {
                    g.setColor(Color.blue);
                    g.fillRect(newX, newY, 50, 50);

                    System.out.println("Wall");
                }
                else if (this.grid[x][y].gameElement instanceof Pacman)
                {

                    g.setColor(Color.yellow);
                    g.fillArc(newX, newY, 50, 50, 90 / 2, 360 - 90);
                }
                else if (this.grid[x][y].gameElement instanceof Ghost)
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
