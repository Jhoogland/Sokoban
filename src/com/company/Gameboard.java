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
        Box a = grid[1][1].getNeighbors().get("Right");
        System.out.println(a.gameElement);

    }

    public void drawEverything()
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
                    this.grid[row][col] = new Box(new Pacman(90));
                }
                else if(this.grid2[row][col] == 3)
                {
                    this.grid[row][col] = new Box(new Ghost());
                }
            }
        }
    }

    public void setNeighbors()
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

                int newrow = row * 55;
                int newY = col * 55;

                g.setColor(Color.black);
                g.fillRect(newrow, newY, 50, 50);

                if (this.grid[col][row].gameElement instanceof Wall)
                {
                    g.setColor(Color.blue);
                    g.fillRect(newrow, newY, 50, 50);


                }
                else if (this.grid[col][row].gameElement instanceof Pacman)
                {

                    g.setColor(Color.yellow);
                    g.fillArc(newrow, newY, 50, 50, 90 / 2, 360 - 90);

                }
                else if (this.grid[col][row].gameElement instanceof Ghost)
                {
                    g.setColor(Color.red.darker());             // Ghosts are dark red.

                    // Body of Ghosts.
                    g.fillOval(newrow, newY, 50, 50);

                    g.setColor(Color.black);  // Facial features of the Ghosts are black.

                    // Left eye of Ghost.
                    g.fillOval(newrow + 10, newY + 20, 5, 5);

                    // Right eye of Ghost.
                    g.fillOval(newrow + 30, newY + 20, 5, 5);


                    // Mouth of Ghost.
                    //g.drawLine(ghostPirowelY + 15, ghostPirowelrow + 50,
                    //      ghostPirowelY + 55, ghostPirowelrow + 50);

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
