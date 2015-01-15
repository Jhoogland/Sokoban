package com.company;

/**
 * Created by Sefa on 9-1-2015.
 */
public class SmartGhost extends Ghost {



    public SmartGhost() { }

    private void findPacman(Box current, Box pacman)
    {
        current = this.getBox();
        pacman = PacmanFrame.getGameboard().getPacman().getBox();





    }



}
