package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class PacmanFrame extends JFrame {
    private Speelbord speelbord = new Speelbord();
    private final int WIDTH = 600;
    private final int HEIGHT = 500;

    private JFrame frame = new JFrame("Pacman");
    private JPanel TopPanel = new JPanel();
    private JPanel BottomPanel = new JPanel();

    public void drawFrame()
    {
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addStructure();
        this.addMenu();
        this.drawInitialPacmanGrid();

        this.frame.setVisible(true);
    }

    private void addStructure()
    {
        this.TopPanel.setPreferredSize(new Dimension(this.WIDTH, 50));
        this.TopPanel.setLayout(new GridLayout(0, 2));

        this.BottomPanel.setPreferredSize(new Dimension(this.WIDTH, 450));
        this.BottomPanel.setLayout(new GridLayout(5, 5));
        this.BottomPanel.setBackground(Color.red);

        this.frame.add(TopPanel, BorderLayout.NORTH);
        this.frame.add(BottomPanel, BorderLayout.SOUTH);
    }

    private void addMenu()
    {
        JButton startButton = new JButton("Start");
        this.TopPanel.add(startButton);

        StartListener startListener = new StartListener();

        startButton.addActionListener(startListener);

        this.TopPanel.add(new JLabel("Score: 00"));
    }

    private void drawInitialPacmanGrid()
    {
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                int positie = speelbord.mapArray[x][y];
                this.BottomPanel.add(new JLabel("" + positie));
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

    public void starten(Speelbord speelbord){


    }
}
