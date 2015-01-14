package com.company;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    private Pacman pacman;

    public KeyHandler(Pacman pacman)
    {
        this.pacman = pacman;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                this.pacman.move(Direction.UP);
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
            case KeyEvent.VK_RIGHT:
                this.pacman.move(Direction.RIGHT);
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
            case KeyEvent.VK_LEFT:
                this.pacman.move(Direction.LEFT);
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
            case KeyEvent.VK_DOWN:
                this.pacman.move(Direction.DOWN);
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
            case KeyEvent.VK_Z:
                PacmanFrame.getGameboard().start();
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
            case KeyEvent.VK_X:
                PacmanFrame.getGameboard().reset();
                this.pacman.setScore(0);
                PacmanFrame.score.setText("<Html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
                break;
        }
    }

    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
