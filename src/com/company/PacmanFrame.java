package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class PacmanFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 680;

    private static JFrame frame = new JFrame("Pacman");
    private JPanel TopPanel = new JPanel();
    private static Gameboard gameboard = new Gameboard();
    public static JButton startButton = new JButton("Start");
    public static JButton resetButton = new JButton("Reset");

    private StartHandler startHandler = new StartHandler();
    private ResetHandler resetHandler = new ResetHandler();

    public void drawFrame()
    {
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addStructure();
        this.addMenu();

        if(startButton.getText().equals("Pause")) {
            this.starten();
        }

        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setFocusTraversalKeysEnabled(false);
        this.frame.setResizable(false);
    }

    private void addStructure()
    {
        this.TopPanel.setPreferredSize(new Dimension(this.WIDTH, 50));
        this.TopPanel.setLayout(new GridLayout(0, 3));

        this.gameboard.setPreferredSize(new Dimension(this.WIDTH, 800));
        this.gameboard.setLayout(new BorderLayout());
        this.gameboard.setBackground(Color.gray);

        this.frame.add(TopPanel, BorderLayout.NORTH);
        this.frame.add(gameboard, BorderLayout.CENTER);
    }

    private void addMenu()
    {
        this.TopPanel.add(startButton);
        this.TopPanel.add(resetButton);

        this.startButton.setRequestFocusEnabled(false);
        this.resetButton.setRequestFocusEnabled(false);

        this.startButton.addActionListener(this.startHandler);
        this.resetButton.addActionListener(this.resetHandler);

        this.TopPanel.add(new JLabel("Score: 00"));
    }

    public static void starten()
    {
        frame.addKeyListener(gameboard);
    }
}
