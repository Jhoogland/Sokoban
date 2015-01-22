package com.company;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sefa on 12-1-2015.
 */
public class KeyHandler implements KeyListener {

    private Pacman pacman = new Pacman();
    private Gameboard gameboard;
    private  boolean  arrowPressed = false;


    public KeyHandler(Gameboard gameboard)
    {
        this.gameboard = gameboard;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        boolean arrowUp = e.getKeyCode() == e.VK_UP;
        boolean arrowDown = e.getKeyCode() == e.VK_DOWN;
        boolean arrowLeft = e.getKeyCode() == e.VK_LEFT;
        boolean arrowRight = e.getKeyCode() == e.VK_RIGHT;
        boolean zPressed = e.getKeyCode() == e.VK_Z;
        boolean xPressed = e.getKeyCode() == e.VK_X;

        if(zPressed){
            this.gameboard.startPause();
        }else if(xPressed){
            this.gameboard.resetTheGame();
        }

        if(arrowUp||arrowDown ||arrowLeft||arrowRight)
        {
            PacmanFrame.getGameboard().repaint(); //smoothes the movement
            if (!arrowPressed)
            {
                if(arrowUp){
                    this.pacman.move(Direction.UP);
                    arrowPressed = true;

                }else if(arrowDown){
                    this.pacman.move(Direction.DOWN);
                    arrowPressed = true;

                }else if (arrowLeft){
                    this.pacman.move(Direction.LEFT);
                    arrowPressed = true;

                }else if (arrowRight){
                    this.pacman.move(Direction.RIGHT);
                    arrowPressed = true;
                }
            }
        }

        if(gameboard.getHalfAmountOfEatenFruits())
        {
            gameboard.placeCherry();
            gameboard.setHalfAmountOfEatenFruits(false);
        }
        PacmanFrame.score.setText("<html><h2 style='float: right;'>Score: " + this.pacman.getScore() + "<br> </h3></html>");
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            arrowPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) { }

    public Pacman getPacman()
    {
        return this.pacman;
    }



}
