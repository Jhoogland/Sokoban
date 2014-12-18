package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class PacmanFrame extends JFrame {
    private Speelbord speelbord = new Speelbord();
    private final int WIDTH = 815;
    private final int HEIGHT = 800;

    private JFrame frame = new JFrame("Pacman");
    private JPanel TopPanel = new JPanel();
    private BottomPanel bottomPanel = new BottomPanel();
    private JButton startButton = new JButton("Start");

    private StartHandler startHandler = new StartHandler();

    public void drawFrame()
    {
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addStructure();
        this.addMenu();
        //this.drawInitialPacmanGrid();
        this.starten();

        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setFocusTraversalKeysEnabled(false);
    }

    private void addStructure()
    {
        this.TopPanel.setPreferredSize(new Dimension(this.WIDTH, 50));
        this.TopPanel.setLayout(new GridLayout(0, 2));

        this.bottomPanel.setPreferredSize(new Dimension(this.WIDTH, 800));
        this.bottomPanel.setLayout(new BorderLayout());
        this.bottomPanel.setBackground(Color.red);

        this.frame.add(TopPanel, BorderLayout.NORTH);
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addMenu()
    {
        this.TopPanel.add(startButton);

        this.startButton.addActionListener(this.startHandler);

        this.TopPanel.add(new JLabel("Score: 00"));
    }

    private void drawInitialPacmanGrid()
    {
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                int positie = speelbord.mapArray[x][y];
                this.bottomPanel.add(new JLabel("" + positie));
                if(positie == 0)
                {
                    //Toon looppad
                }
                else if(positie == 1)
                {
                    //Toon muur
                }
                else if(positie == 2){
                    //Toon pacman
                }
                else if(positie == 3){
                    //Toon spookje
                }
            }
        }
    }

    public void starten() {
        KeyHandler keyHandler = new KeyHandler();
        this.frame.addKeyListener(bottomPanel);
    }
}
