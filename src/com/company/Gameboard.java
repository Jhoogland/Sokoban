package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel {

    private final int BOXSIZE           = 50;
    private final int BOXGAP            = 0;

    private final int GRIDROW           = 10; //Set the gridsize
    private final int GRIDCOL           = 20;
    private Box grid[][]                = new Box[this.GRIDROW][this.GRIDCOL]; // 2D Array thats holds all Boxes

    public TimerHandler timerHandler    = new TimerHandler(400, this);
    private KeyHandler keyHandler       = new KeyHandler(this);
    public static Stopwatch stopwatch   = new Stopwatch();
    private LevelHandler levelHandler   = new LevelHandler();

    private int currentAmountOfFruits;
    private int startingAmountOfFruits;
    private boolean halfAmountOfEatenFruits = false;
    private int gridStructure[][];

    private boolean isGameReset         = false;

    public Gameboard()
    {
        PacmanFrame.frame.addKeyListener(keyHandler);
        this.changeLvl();
    }

    public void changeLvl()
    {

        this.currentAmountOfFruits = 0;
        this.startingAmountOfFruits = 0;

        if(this.levelHandler.getCurrentLvl() == 0)
        {
            this.levelHandler.setCurrentLvl(1);
            this.levelHandler.setGridStructure(this.levelHandler.getGridStructure(1));
        }
        else if(this.levelHandler.getCurrentLvl() == 1)
        {
            this.timerHandler.setDelay(100);
            this.levelHandler.setCurrentLvl(2);
            this.levelHandler.setGridStructure(this.levelHandler.getGridStructure(2));
        }
        else if(this.levelHandler.getCurrentLvl() == 2)
        {
            this.timerHandler.setDelay(600);
            this.levelHandler.setCurrentLvl(3);
            this.levelHandler.setGridStructure(this.levelHandler.getGridStructure(3));
        }
        else
        {
            this.levelHandler.setAllLvlsCleared(true);
        }

        createEverything();
        setNeighbors();
    }

    protected void resetEveryonesPosition()
    {
        Pacman pacman = this.keyHandler.getPacman();
        Ghost drunkGhost1 = this.timerHandler.getGhost("DrunkGhost1");
        Ghost drunkGhost2 = this.timerHandler.getGhost("DrunkGhost2");
        Ghost smartGhost1 = this.timerHandler.getGhost("SmartGhost1");
        Ghost smartGhost2 = this.timerHandler.getGhost("SmartGhost2");

        if(timerHandler.timer.isRunning())
        {
            resetPosition(pacman, pacman.getStartPosition());
            resetPosition(drunkGhost1, drunkGhost1.getStartPosition());
            resetPosition(drunkGhost2, drunkGhost2.getStartPosition());
            resetPosition(smartGhost1, smartGhost1.getStartPosition());
            resetPosition(smartGhost2, smartGhost2.getStartPosition());
        }
    }

    protected void resetTheGame()
    {
        Pacman pacman = this.keyHandler.getPacman();

        resetEveryonesPosition();
        this.resetFruits();
        pacman.setLife(3);
        pacman.setInvincible(false);

        if(isGameReset)
        {
            this.currentAmountOfFruits = 0;
            this.startingAmountOfFruits = 0;

            pacman.setScore(0);
            this.stopwatch.lvlTimer = 0;
            this.timerHandler.timer.stop();
        }

        LevelHandler levelHandler = this.levelHandler;

        levelHandler.setCurrentLvl(1);
        levelHandler.setGridStructure(levelHandler.getGridStructure(1));
        levelHandler.setAllLvlsCleared(false);

        PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + pacman.getLife() + "<br> </h3></html>");

        repaint();
    }


    protected void startPause()
    {
        if(!this.timerHandler.timer.isRunning())
        {
            if(this.isGameReset)
            {
                resetTheGame();
                isGameReset = false;
            }
            else
            {
                this.timerHandler.timer.start();
                this.stopwatch.startTimer();
                this.keyHandler.getPacman().setActive(true);
            }
        }
        else
        {
            this.timerHandler.timer.stop();
            this.stopwatch.stopTimer();
            this.keyHandler.getPacman().setActive(false);
        }
    }

    private void createEverything()
    {

        this.gridStructure = this.levelHandler.getGridStructure(0);
        for(int row = 0;  row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                if(this.gridStructure[row][col] == 0) // Box
                {
                    this.grid[row][col] = new Box();
                }
                else if(this.gridStructure[row][col] == 1) // Wall
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Wall());
                }
                else if(this.gridStructure[row][col] == 2) // Pacman
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.keyHandler.getPacman());
                    this.keyHandler.getPacman().setBox(this.grid[row][col]);
                    this.keyHandler.getPacman().setStartPosition(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 3) // DrunkGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.timerHandler.getGhost("DrunkGhost1"));
                    this.timerHandler.getGhost("DrunkGhost1").setBox(this.grid[row][col]);
                    this.timerHandler.getGhost("DrunkGhost1").setStartPosition(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 4) // DrunkGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.timerHandler.getGhost("DrunkGhost2"));
                    this.timerHandler.getGhost("DrunkGhost2").setBox(this.grid[row][col]);
                    this.timerHandler.getGhost("DrunkGhost2").setStartPosition(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 5) // SmartGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.timerHandler.getGhost("SmartGhost1"));
                    this.timerHandler.getGhost("SmartGhost1").setBox(this.grid[row][col]);
                    this.timerHandler.getGhost("SmartGhost1").setStartPosition(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 6) // SmartGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.timerHandler.getGhost("SmartGhost2"));
                    this.timerHandler.getGhost("SmartGhost2").setBox(this.grid[row][col]);
                    this.timerHandler.getGhost("SmartGhost2").setStartPosition(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 7) // Fruit
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Fruit());

                    this.currentAmountOfFruits++;
                    this.startingAmountOfFruits++;
                }
                else if(this.gridStructure[row][col] == 8) // SuperFruit
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new SuperFruit());

                    this.currentAmountOfFruits++;
                    this.startingAmountOfFruits++;
                }
            }
        }
    }

    private void setNeighbors()
    {
        for(int row = 0;  row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                // Neighbors from each box
                int upRow =  row - 1;   // TOP
                int downRow = row + 1;  // BOTTOM
                int leftCol = col - 1;  // LEFT
                int rightCol = col + 1; // RIGHT

                // Checks whether the row values are correct with the condition and adds them the Neighbor HashMap in the Box class
                if (upRow >= 0)
                {
                    grid[row][col].addNeighbor("Top", grid[upRow][col]);
                    if(!grid[upRow][col].containsInstanceOf("Wall"))
                    {
                        grid[row][col].addAccessibleNeighbor("Top", grid[upRow][col]);
                    }

                }
                if (downRow <= (this.GRIDROW -1))
                {
                    grid[row][col].addNeighbor("Bottom", grid[downRow][col]);
                    if(!grid[downRow][col].containsInstanceOf("Wall"))
                    {
                        grid[row][col].addAccessibleNeighbor("Bottom", grid[downRow][col]);
                    }
                }
                if(leftCol >=0 )
                {
                    grid[row][col].addNeighbor("Left", grid[row][leftCol]);
                    if(!grid[row][leftCol].containsInstanceOf("Wall"))
                    {
                        grid[row][col].addAccessibleNeighbor("Left", grid[row][leftCol]);
                    }
                }
                if (rightCol <= (this.GRIDCOL - 1))
                {
                    grid[row][col].addNeighbor("Right", grid[row][rightCol]);
                    if(!grid[row][rightCol].containsInstanceOf("Wall"))
                    {
                        grid[row][col].addAccessibleNeighbor("Right", grid[row][rightCol]);
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw the white box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 600);

        // Draw the 10x10 boxes that make the grid.
        for (int row = 0; row < this.GRIDROW; row++)
        {
            for (int col = 0; col < this.GRIDCOL; col++) {
                int newBoxSize = this.BOXSIZE + this.BOXGAP;
                int newRow = row * newBoxSize;
                int newCol = col * newBoxSize;

                g.setColor(Color.black);
                g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

                ArrayList elements = grid[row][col].getGameElements();
                if(elements.size() == 1)
                {
                    paintGameElement(g, (GameElement) elements.get(0), newRow, newCol);
                }
                if(elements.size() > 1)
                {
                    int lastIndex = elements.size()-1;
                    paintGameElement(g, (GameElement) elements.get(lastIndex), newRow, newCol);
                }

                if(this.levelHandler.getAllLvlsCleared())
                {
                    Graphics g2 = g;

                    if(this.timerHandler.timer.isRunning())
                    {
                        this.timerHandler.timer.stop();
                        this.stopwatch.stopTimer();
                        this.keyHandler.getPacman().setActive(false);
                    }

                    g2.setColor(Color.white);
                    g2.fillRect(350, 150, 300, 200);

                    g2.setColor(Color.black);
                    g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                    g2.drawString("GAME OVER", 430, 200);

                    g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g2.drawString("Uw score: " + this.getPacman().getScore(), 430, 250);
                    g2.drawString("Uw tijd: " + this.stopwatch.lvlTimer, 430, 300);

                    this.isGameReset = true;
                }
            }
        }
    }

    private void paintGameElement(Graphics g, GameElement ge, int newRow, int newCol)
    {
        if(ge instanceof Wall)
        {
            g.setColor(Color.blue);
            g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);
        }
        else if(ge.equals(this.keyHandler.getPacman()))
        {
            g.setColor(Color.yellow);
            g.fillArc(newCol, newRow, this.BOXSIZE, this.BOXSIZE, 90 / 2, 360 - 90);
        }
        else if(ge instanceof Fruit)
        {
            if(ge instanceof SuperFruit)
            {
                g.setColor(Color.green);
            }
            else if(ge instanceof Cherry)
            {
                g.setColor(Color.red.darker());
            }
            else
            {
                g.setColor(Color.white);
            }

            g.fillOval(newCol + 20, newRow + 20, this.BOXSIZE / 4, this.BOXSIZE / 4);
        }
        else if(ge instanceof Ghost)
        {
            if(this.keyHandler.getPacman().getInvincible())
            {
                g.setColor(Color.lightGray);
            }
            else
            {
                if (ge.equals(this.timerHandler.getGhost("DrunkGhost1")))
                {
                    g.setColor(Color.RED);
                }
                else if (ge.equals(this.timerHandler.getGhost("DrunkGhost2")))
                {
                    g.setColor(Color.ORANGE);
                }
                else if (ge.equals(this.timerHandler.getGhost("SmartGhost1")))
                {
                    g.setColor(Color.CYAN);
                }
                else if (ge.equals(this.timerHandler.getGhost("SmartGhost2")))
                {
                    g.setColor(Color.PINK);
                }
            }

            // Body of Ghosts.
            g.fillOval(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

            g.setColor(Color.black);  // Facial features of the Ghosts are black.

            // Left eye of Ghost.
            g.fillOval(newCol + 15, newRow + 20, 5, 5);

            // Right eye of Ghost.
            g.fillOval(newCol + 30, newRow + 20, 5, 5);
        }
    }

    private void insertInitialElement(int row, int col, GameElement ge)
    {
        grid[row][col].addGameElement(ge);
    }

    private void resetFruits()
    {
        this.currentAmountOfFruits = 0;
        this.startingAmountOfFruits = 0;
        for(int row = 0; row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                if(this.gridStructure[row][col] == 7) // Fruit
                {
                    this.grid[row][col].getGameElements().clear();
                    insertInitialElement(row, col, new Fruit());

                    this.currentAmountOfFruits++;
                    this.startingAmountOfFruits++;
                }
                else if(this.gridStructure[row][col] == 8) // SuperFruit
                {
                    this.grid[row][col].getGameElements().clear();
                    insertInitialElement(row, col, new SuperFruit());

                    this.currentAmountOfFruits++;
                    this.startingAmountOfFruits++;
                }
            }
        }
    }

    public void placeCherry()
    {
        ArrayList<Box> boxes = this.findEmptyBoxes();

        Random random = new Random();

        Box cherryBox = boxes.get(random.nextInt(boxes.size()));

        Cherry cherry = new Cherry(100);

        cherry.setBox(cherryBox);
        cherryBox.addGameElement(cherry);
    }

    private ArrayList<Box> findEmptyBoxes()
    {
        ArrayList<Box> emptyBoxes = new ArrayList<Box>();

        for (int row = 0; row < this.GRIDROW; row++)
        {
            for (int col = 0; col < this.GRIDCOL; col++)
            {
                if(this.grid[row][col].getGameElements().size() == 0)
                {
                    emptyBoxes.add(this.grid[row][col]);
                }
            }
        }
        return emptyBoxes;
    }

    protected void resetPosition(Icon icon, Box grid)
    {
        icon.getBox().removeGameElement(icon);
        icon.setBox(grid);
        icon.getBox().addGameElement(icon);
    }

    public Pacman getPacman()
    {
        return this.keyHandler.getPacman();
    }
    public int getCurrentAmountOfFruits() { return this.currentAmountOfFruits; }
    public void setCurrentAmountOfFruits(int currentAmountOfFruits) { this.currentAmountOfFruits = currentAmountOfFruits; }

    public int getStartingAmountOfFruits() { return this.startingAmountOfFruits; }
    public boolean getHalfAmountOfEatenFruits() { return this.halfAmountOfEatenFruits; }
    public void setHalfAmountOfEatenFruits(boolean fruit) { this.halfAmountOfEatenFruits = fruit; }

    public LevelHandler getLevelHandler() { return this.levelHandler; }
}
