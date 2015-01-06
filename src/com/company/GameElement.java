package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class GameElement {

    public Point p;
    public static ArrayList<Wall> walls = new ArrayList<Wall>();
    public static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

    private static int[][]map =
            {{1,1,1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,1,0,0,0,1},
            {1,0,1,0,0,1,0,0,0,1},
            {1,0,0,0,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,1,1,1},
            {1,0,0,0,0,3,4,5,6,1},
            {1,1,1,1,1,1,1,1,1,1}};

    /*

        0 = Looppad
        1 = Wall
        2 = Pacman
        3 = Spookje 1
        4 = Spookje 2
        5 = Spookje 3
        6 = Spookje 4

     */
/*
    public static ArrayList<Wall> initWalls()
    {
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 1) {
                    Point potentialPoint = new Point(x, y);
                    Wall wallPoint = new Wall(potentialPoint);
                    walls.add(wallPoint);
                }
            }
        }

        return walls;
    }
*/
    public static Pacman initPacman()
    {
        Pacman pacmanPoint = null;
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 2) {
                    Point potentialPoint = new Point(x, y);
                    pacmanPoint = new Pacman(potentialPoint, 90);
                }
            }
        }
        return pacmanPoint;
    }

    public static ArrayList<Ghost> initGhosts()
    {
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5 || map[x][y] == 6) {
                    Point potentialPoint = new Point(x, y);
                    Ghost ghostPoint = new Ghost(potentialPoint);
                    ghosts.add(ghostPoint);
                }
            }
        }

        return ghosts;
    }

    public static int getNeighbor(int x, int y){
        return map[x][y];
    }
}
