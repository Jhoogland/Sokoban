/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jhoog_000
 */
public class Leegvak extends Vakje{
    private int hoogte;
    private int breedte;
    private int x;
    private int y;
    
    public Leegvak(int hoogte, int breedte, int x, int y){
        super(20,20,x,y);
    }
    
    public Vakje geefVakje(){return this;}

    protected void paintComponent(Graphics g) {
        this.draw(g);
        draw(g);
    }
  
      public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, hoogte, breedte);
    
  }  
      public void zetHoogte(int hoogte){
          this.hoogte = hoogte;
      }
      public void zetBreedte(int breedte){
          this.breedte = breedte;
      }
      public void zetX(int x){
          this.x = x;
      }
      public void zetY(int y){
          this.y = y;
      }
}