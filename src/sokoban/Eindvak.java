/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

/**
 *
 * @author Jhoog_000
 */
public class Eindvak extends Vakje{
    
    public Eindvak(int hoogte, int breedte, int x, int y){
        super(hoogte,breedte,x,y);
    }
   public Vakje geefVakje(){return this;}
}
