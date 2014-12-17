package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class BottomPanel extends JPanel implements KeyListener {

    Speelbord speelbord = new Speelbord();

    public static Pacman pacman;
    private int amountOfGhosts = 1;

    ArrayList<Point> ghosts;

    public int boxWidth = 70;
    public int boxHeight = 70;
    public int border = 20;            // Size of blue border around grid.
    public int boxBorder = 5;            // Size of gray border between boxes.
    public int boxOuterBorder = 8;


    public BottomPanel()
    {
        //Maak ghosts arraylist
        ghosts = new ArrayList<Point>();

        //Check hoeveel ghosts er moeten worden aagnemaakt
        for(int i = 0; i < this.amountOfGhosts; i++)
        {

        }

        pacman = new Pacman(new Point(0, 0), 90);
        getPixel(pacman.p.x);
        getPixel(pacman.p.y);
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

        g.setColor(Color.yellow);    // Pacman is yellow.
        //getPixel(pacman.p.x);        // Get X-pixel of Pacman.
        //getPixel(pacman.p.y);        // Get Y-Pixel of Pacman.

        // Draw Pacman using values generated above.
        g.fillArc(getPixel(pacman.p.x), getPixel(pacman.p.y),
                boxWidth, boxHeight, pacman.mouthAngle/2,
                360 - pacman.mouthAngle);
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case java.awt.event.KeyEvent.VK_UP:
                BottomPanel.pacman.p.y -= 1;
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                BottomPanel.pacman.p.x += 1;
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                BottomPanel.pacman.p.x -= 1;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                BottomPanel.pacman.p.y += 1;
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
    public int getPixel(int generatedNumber)
    {
        return (generatedNumber * (boxWidth + boxBorder) + border
                + boxOuterBorder);
    }


}
