package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel {

    private int gridSize = 10;
    private Vak grid[][] = new Vak[this.gridSize][this.gridSize];
    private HashMap grid2 = new HashMap();

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
                this.grid[x][y] = new Vak();
            }
        }
    }


    /*
    public void setNeighbors(){

        for(int x = 0;  x < this.gridSize; x++)
        {
            for(int y = 0; y < this.gridSize; y++)
            {
                if(y > 0){
                    grid[x][y].neighbors.add(grid[x][y-1]);
                }
                if(y < this.gridSize){
                    grid[x][y].neighbors.add(grid[x][y+1]);
                }
                if(x > 0){
                    grid[x][y].neighbors.add(grid[x-1][y]);
                }
                if(x < this.gridSize){
                    grid[x][y].neighbors.add(grid[x+1][y]);
                }
            }
        }
    }
*/
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Create darker blues for the grid.
        Color myGray = Color.lightGray;
        Color darkGray = myGray.darker();
        Color maxGray = darkGray.darker();
        Color tooGray = maxGray.darker();

        // Create different shades of red for the ghosts/end message.
        Color myRed = Color.red;
        Color darkRed = myRed.darker();
        Color lightRed = myRed.brighter();

        // Draw the blue outline around the grid.
        g.setColor(darkGray);
        g.fillRect(0, 0, 600, 600);

        // Draw the gray box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        // Draw the 10x10 boxes that make the grid.
        g.setColor(Color.black);
        for (int x = 0; x <= this.grid.length; x++)
        {
            for (int y = 0; y <= this.grid[0].length; y++) {

                System.out.println(x + " - " + y);
                g.fillRect((x * 55), (y * 55), 50, 50);
            }
        }


        /* ----------- MUREN ----------

        for(Wall wall : walls)
        {
            g.setColor(Color.blue);
            int wallsxPos = getPixel(wall.p.x);
            int wallsyPos = getPixel(wall.p.y);

            g.fillRect(wallsyPos, wallsxPos, boxWidth, boxHeight);
        }

        /* -------- GHOSTS ----------

        for (Ghost ghost : ghosts)    // Draw the Ghosts.
        {
            g.setColor(darkRed);             // Ghosts are dark red.
            int ghostPixelX = getPixel(ghost.p.x); // Get X-pixel of Ghost.
            int ghostPixelY = getPixel(ghost.p.y); // Get Y-pixel of Ghost.

            // Body of Ghosts.
            g.fillOval(ghostPixelY, ghostPixelX, 50, 50);

            g.setColor(Color.black);  // Facial features of the Ghosts are black.

            // Left eye of Ghost.
            g.fillOval(ghostPixelY + 10, ghostPixelX + 20, 5, 5);

            // Right eye of Ghost.
            g.fillOval(ghostPixelY + 30, ghostPixelX + 20, 5, 5);

            // Mouth of Ghost.
            //g.drawLine(ghostPixelY + 15, ghostPixelX + 50,
            //      ghostPixelY + 55, ghostPixelX + 50);
        }

        /* -------- PACMAN -----------

        g.setColor(Color.yellow);    // Pacman is yellow.
        getPixel(pacman.p.x);        // Get X-pixel of Pacman.
        getPixel(pacman.p.y);        // Get Y-Pixel of Pacman.

        // Draw Pacman using values generated above.
        g.fillArc(getPixel(pacman.p.y), getPixel(pacman.p.x),
                boxWidth, boxHeight, pacman.mouthAngle/2,
                360 - pacman.mouthAngle); */
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

    // Function that turns the 0-9 value into a valid pixel location on the grid.
    public static int getPixel(int generatedNumber)
    {
        return (generatedNumber * (boxWidth + boxBorder) + border
                + boxOuterBorder);
    }
*/

}
