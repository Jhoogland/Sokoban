package com.company;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Sefa on 15-12-2014.
 */
public class PacmanFrame extends JFrame {
    private final int WIDTH             = 1000;
    private final int HEIGHT            = 600;

    public static JFrame frame          = new JFrame("Pacman");
    public static JPanel TopPanel       = new JPanel();
    private static Gameboard gameboard  = new Gameboard();
    public static JLabel score          = new JLabel("<html><h2 style='float: right;'>Score: 0<br> </h3></html>");
    public static JLabel life           = new JLabel("<html><h2 style='float: right;'>Life: 3<br> </h3></html>");
    public static JLabel time           = new JLabel("<html><h2 style='float: right;'>Time: 0 <br> </h3></html>");
    public static JLabel level          = new JLabel("<html><h2 style='float: right;'>Level: 1 <br> </h3></html>");

    public void drawFrame()
    {
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addStructure();
        this.addMenu();

        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setFocusTraversalKeysEnabled(false);
        this.frame.setResizable(false);
    }

    private void addStructure()
    {
        this.TopPanel.setPreferredSize(new Dimension(this.WIDTH, 100));
        this.TopPanel.setLayout(new GridLayout(0, 5));

        this.gameboard.setPreferredSize(new Dimension(this.WIDTH, 800));
        this.gameboard.setLayout(new BorderLayout());
        this.gameboard.setBackground(Color.gray);

        this.frame.add(TopPanel, BorderLayout.NORTH);
        this.frame.add(gameboard, BorderLayout.CENTER);
    }

    private void addMenu()
    {
        this.TopPanel.add(new JLabel(
                "<html>" +
                        "<div style='padding: 15;'>" +
                        "<h2 style='margin: 0; padding: 0;'>Instructies:</h2> " +
                        "- Beweeg door de pijltjes toetsen in te drukken " +
                        "<br> - 'Z' = start/pause " +
                        "<br> - 'X' = reset" +
                        "</div>" +
                        "</html>"));

        this.TopPanel.add(this.score);
        this.TopPanel.add(this.life);
        this.TopPanel.add(this.time);
        this.TopPanel.add(this.level);
    }

    public static Gameboard getGameboard()
    {
        return gameboard;
    }

}
