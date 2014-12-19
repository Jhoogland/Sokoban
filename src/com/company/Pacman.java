package com.company;

import java.awt.*;

/**
 * Created by Sefa on 15-12-2014.
 */
public class Pacman extends Poppetje {
    public int mouthAngle;

    public Pacman(Point p, int mouthAngle)
    {
        this.p = p;
        this.mouthAngle = mouthAngle;
    }

    public boolean isAtMuur(Richting richting)
    {
        int x = this.p.x;
        int y = this.p.y;
        int[][] map = SpelElement.getMap();

        /* CHECK NA!!!!!!! */

        switch (richting)
        {
            case BOVEN:
                x -= 1;
                break;
            case RECHTS:
                y += 1;
                break;
            case LINKS:
                y -= 1;
                break;
            case BENEDEN:
                x += 1;
                break;
        }

        System.out.println(x + " - " + y);


        if(map[x][y] == 1)
        {
            System.out.println("@muur");
            return true;
        }

        return false;
    }

}
