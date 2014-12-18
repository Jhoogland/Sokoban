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
                y -= 1;
                break;
            case RECHTS:
                x += 0;
                break;
            case LINKS:
                x -= 1;
                break;
            case BENEDEN:
                y += 2;
                break;
        }

        if(map[x][y] == 1)
        {
            System.out.println("@muur");
            return true;
        }

        return false;
    }

}
