package com.company;

/**
 * Created by Sefa on 9-1-2015.
 */
public class  SmartGhost extends Ghost {

    @Override
    protected void move()
    {
        if(!this.searching)
        {
            setSearching(true);
            findPacman();
        }
        if(this.movementStack.size() > 0)
        {
            Box box = this.movementStack.pop();

            setGhostPosition(box);
            if(box.containsInstanceOf("Pacman"))
            {
                this.eatPacman();
            }
        }
    }

}
