package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class BottomPanel extends JPanel implements KeyListener {

    public static Pacman pacman;
    private int amountOfGhosts = 1;
    private int amountOfWalls = 36;

    private ArrayList<Point> ghosts = SpelElement.initGhosts();
    private ArrayList<Point> walls = SpelElement.initWalls();

    public static int boxWidth = 70;
    public static int boxHeight = 70;
    public static int border = 20;            // Size of blue border around grid.
    public static int boxBorder = 5;            // Size of gray border between boxes.
    public static int boxOuterBorder = 8;


    public BottomPanel()
    {
        pacman = new Pacman(SpelElement.initPacman(), 90);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Create darker blues for the grid.
        Color myBlue = Color.blue;
        Color darkBlue = myBlue.darker();
        Color maxBlue = darkBlue.darker();
        Color tooBlue = maxBlue.darker();

        // Create different shades of red for the ghosts/end message.
        Color myRed = Color.red;
        Color darkRed = myRed.darker();
        Color lightRed = myRed.brighter();

        // Draw the blue outline around the grid.
        g.setColor(darkBlue);
        g.fillRect(0, 0, 800, 800);

        // Draw the gray box that will divide the smaller 10x10 boxes.
        g.setColor(Color.lightGray);
        g.fillRect(20, 20, 760, 760);

        // Draw the 10x10 boxes that make the grid.
        g.setColor(tooBlue);
        for (int down = 28; down <= 735; down += 75)
        {
            for (int right = 28; right <= 735; right += 75)
            {
                g.fillRect(down, right, boxWidth, boxHeight);
            }
        }

        /* ----------- MUREN ---------- */

        for(Point wall : walls)
        {
            g.setColor(Color.black);
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
            g.fillOval(ghostPixelY, ghostPixelX, 70, 70);

            g.setColor(Color.black);  // Facial features of the Ghosts are black.

            // Left eye of Ghost.
            g.fillOval(ghostPixelY + 15, ghostPixelX + 20, 10, 10);

            // Right eye of Ghost.
            g.fillOval(ghostPixelY + 45, ghostPixelX + 20, 10, 10);

            // Mouth of Ghost.
            g.drawLine(ghostPixelY + 15, ghostPixelX + 50,
                    ghostPixelY + 55, ghostPixelX + 50);
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
                if(!pacman.isAtMuur(Richting.BOVEN))
                {
                    pacman.p.x -= 1;
                }
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                if(!pacman.isAtMuur(Richting.RECHTS))
                {
                    pacman.p.y += 1;
                }
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                if(!pacman.isAtMuur(Richting.LINKS))
                {
                    pacman.p.y -= 1;
                }
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                if(!pacman.isAtMuur(Richting.BENEDEN))
                {
                    pacman.p.x += 1;
                }
                break;
            default:
                System.out.println("Ongeldige toets ingedrukt!");
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
