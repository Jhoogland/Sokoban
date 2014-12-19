package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class GameElement {

    public Point p;
    public static ArrayList<Point> walls = new ArrayList<Point>();
    public static ArrayList<Point> ghosts = new ArrayList<Point>();

    private static int[][]map =
            {{1,1,1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,1,0,0,0,1},
            {1,0,1,0,0,1,0,0,0,1},
            {1,0,0,0,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,1,1,1},
            {1,0,0,0,0,0,3,3,3,1},
            {1,1,1,1,1,1,1,1,1,1}};


    public static ArrayList<Point> initWalls()
    {
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 1) {
                    Point potentialPoint = new Point(x, y);
                    walls.add(potentialPoint);
                }
            }
        }

        return walls;
    }

    public static Point initPacman()
    {
        Point pacmanPoint = null;
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 2) {
                     pacmanPoint = new Point(x,y);
                }
            }
        }

        return pacmanPoint;
    }

    public static ArrayList<Point> initGhosts()
    {
        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map[0].length; y++)
            {
                if(map[x][y] == 3) {
                    Point potentialPoint = new Point(x, y);
                    ghosts.add(potentialPoint);
                }
            }
        }

        return ghosts;
    }

    public static int getNeighbor(int x, int y){
        return map[x][y];
    }
}
