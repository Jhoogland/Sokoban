package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class PacmanFrame extends JFrame {
    private final int WIDTH = 815;
    private final int HEIGHT = 800;

    private JFrame frame = new JFrame("Pacman");
    private JPanel TopPanel = new JPanel();
    private Gameboard gameboard = new Gameboard();
    private JButton startButton = new JButton("Start");

    private StartHandler startHandler = new StartHandler();

    public void drawFrame()
    {
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addStructure();
        this.addMenu();
        this.starten();

        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setFocusTraversalKeysEnabled(false);
    }

    private void addStructure()
    {
        this.TopPanel.setPreferredSize(new Dimension(this.WIDTH, 50));
        this.TopPanel.setLayout(new GridLayout(0, 2));

        this.gameboard.setPreferredSize(new Dimension(this.WIDTH, 800));
        this.gameboard.setLayout(new BorderLayout());
        this.gameboard.setBackground(Color.red);

        this.frame.add(TopPanel, BorderLayout.NORTH);
        this.frame.add(gameboard, BorderLayout.SOUTH);
    }

    private void addMenu()
    {
        this.TopPanel.add(startButton);

        this.startButton.addActionListener(this.startHandler);

        this.TopPanel.add(new JLabel("Score: 00"));
    }

    public void starten() {
        this.frame.addKeyListener(gameboard);
    }
}
