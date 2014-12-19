package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel implements KeyListener {

    public static Pacman pacman;

    private ArrayList<Point> ghosts = GameElement.initGhosts();
    private ArrayList<Point> walls = GameElement.initWalls();

    public static int boxWidth = 50;
    public static int boxHeight = 50;
    public static int border = 20;            // Size of blue border around grid.
    public static int boxBorder = 5;            // Size of gray border between boxes.
    public static int boxOuterBorder = 8;


    public Gameboard()
    {
        pacman = new Pacman(GameElement.initPacman(), 90);
    }

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
        g.fillRect(20, 20, 560, 560);

        // Draw the 10x10 boxes that make the grid.
        g.setColor(Color.black);
        for (int down = 28; down <= 535; down += 55)
        {
            for (int right = 28; right <= 535; right += 55)
            {
                g.fillRect(down, right, boxWidth, boxHeight);
            }
        }

        /* ----------- MUREN ---------- */

        for(Point wall : walls)
        {
            g.setColor(Color.blue);
            int wallsxPos = getPixel(wall.x);
            int wallsyPos = getPixel(wall.y);

            g.fillRect(wallsyPos, wallsxPos, boxWidth, boxHeight);
        }

        /* -------- GHOSTS ---------- */

        for (Point ghost : ghosts)    // Draw the Ghosts.
        {
            g.setColor(darkRed);             // Ghosts are dark red.
            int ghostPixelX = getPixel(ghost.x); // Get X-pixel of Ghost.
            int ghostPixelY = getPixel(ghost.y); // Get Y-pixel of Ghost.

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

        /* -------- PACMAN ----------- */

        g.setColor(Color.yellow);    // Pacman is yellow.
        getPixel(pacman.p.x);        // Get X-pixel of Pacman.
        getPixel(pacman.p.y);        // Get Y-Pixel of Pacman.

        // Draw Pacman using values generated above.
        g.fillArc(getPixel(pacman.p.y), getPixel(pacman.p.x),
                boxWidth, boxHeight, pacman.mouthAngle/2,
                360 - pacman.mouthAngle);
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case java.awt.event.KeyEvent.VK_UP:
                if(!pacman.isAtMuur(Direction.BOVEN))
                {
                    pacman.move(Direction.BOVEN);
                }
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                if(!pacman.isAtMuur(Direction.RECHTS))
                {
                    pacman.move(Direction.RECHTS);
                }
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                if(!pacman.isAtMuur(Direction.LINKS))
                {
                    pacman.move(Direction.LINKS);
                }
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                if(!pacman.isAtMuur(Direction.BENEDEN))
                {
                    pacman.move(Direction.BENEDEN);
                }
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


}
