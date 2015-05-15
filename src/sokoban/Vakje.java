/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;
    
import java.util.HashMap;

/**
 *
 * @author Jhoog_000
 */
public abstract class Vakje {
    
    private int hoogte;
    private int breedte;
    private int x;
    private int y;
    private HashMap<String, Vakje> buren = new HashMap<>();
    
    public Vakje(int hoogte, int breedte, int x, int y){
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.x = x;
        this.y = y;
        
    }
    public abstract Vakje geefVakje();
    
    
}
