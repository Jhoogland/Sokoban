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

    public TimerHandler timerHandler    = new TimerHandler(200, this);
    private KeyHandler keyHandler       = new KeyHandler(this);
    public static Stopwatch stopwatch   = new Stopwatch();

    private int currentAmountOfFruits;
    private int startingAmountOfFruits;
    private boolean halfAmountOfEatenFruits = false;

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = DrunkGhost1
    // 4 = DrunkGhost2
    // 5 = SmartGhost1
    // 6 = SmartGhost2
    // 7 = Fruit
    // 8 = SuperFruit
    private int gridStructure[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 7, 7, 7, 7, 7, 7, 7, 7, 1, 8, 7, 7, 7, 7, 7, 7, 8, 1},
            {1, 7, 1, 1, 1, 7, 1, 1, 1, 7, 1, 7, 1, 1, 7, 1, 1, 1, 7, 1},
            {1, 7, 1, 1, 1, 7, 1, 1, 1, 7, 1, 7, 1, 1, 7, 1, 1, 1, 7, 1},
            {1, 7, 1, 1, 1, 7, 1, 1, 1, 7, 1, 7, 1, 1, 7, 1, 1, 1, 7, 1},
            {1, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1},
            {1, 7, 1, 1, 1, 7, 1, 1, 7, 1, 7, 1, 7, 1, 7, 1, 1, 1, 7, 1},
            {1, 7, 1, 1, 1, 7, 1, 1, 7, 1, 7, 1, 7, 1, 7, 1, 1, 1, 7, 1},
            {1, 8, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 1, 7, 3, 4, 5, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        PacmanFrame.frame.addKeyListener(keyHandler);
        createEverything();
        setNeighbors();
        System.out.println(grid[5][5].getAccessibleNeighbors().toString());

    }

    protected void resetEveryonesPosition()
    {
        if(timerHandler.timer.isRunning())
        {
            resetPosition(this.keyHandler.getPacman(), this.grid[1][1]);
            resetPosition(this.timerHandler.getGhost("DrunkGhost1"), this.grid[8][15]);
            resetPosition(this.timerHandler.getGhost("DrunkGhost2"), this.grid[8][16]);
            resetPosition(this.timerHandler.getGhost("SmartGhost1"), this.grid[8][17]);
            resetPosition(this.timerHandler.getGhost("SmartGhost2"), this.grid[8][18]);

            repaint();
        }
    }

    protected void resetTheGame()
    {
        if(timerHandler.timer.isRunning())
        {
            resetPosition(this.keyHandler.getPacman(), this.grid[1][1]);
            resetPosition(this.timerHandler.getGhost("DrunkGhost1"), this.grid[8][15]);
            resetPosition(this.timerHandler.getGhost("DrunkGhost2"), this.grid[8][16]);
            resetPosition(this.timerHandler.getGhost("SmartGhost1"), this.grid[8][17]);
            resetPosition(this.timerHandler.getGhost("SmartGhost2"), this.grid[8][18]);
            this.resetFruits();

            this.keyHandler.getPacman().setScore(0);
            this.keyHandler.getPacman().setLife(3);
            this.keyHandler.getPacman().setInvincible(false);
            this.stopwatch.lvlTimer = 0;

            PacmanFrame.score.setText("<html><h2 style='float: right;'>Score: " + this.keyHandler.getPacman().getScore() + "<br> </h3></html>");
            PacmanFrame.life.setText("<html><h2 style='float: right;'>Life: " + PacmanFrame.getGameboard().getPacman().getLife() + "<br> </h3></html>");
        }
    }

    protected void startPause()
    {
        if(!timerHandler.timer.isRunning())
        {
            timerHandler.timer.start();
            stopwatch.startTimer();
            keyHandler.getPacman().setActive(true);
        }
        else
        {
            timerHandler.timer.stop();
            stopwatch.stopTimer();
            keyHandler.getPacman().setActive(false);
        }
    }

    private void createEverything()
    {
        for(int row = 0;  row < this.GRIDROW; row++)
        {
            for(int col = 0; col < this.GRIDCOL; col++)
            {
                if(gridStructure[row][col] == 0) // Box
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
}
