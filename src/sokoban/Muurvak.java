/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jhoog_000
 */
public class Muurvak extends Vakje {
    private int hoogte;
    private int breedte;
    private int x;
    private int y;
    
    public Muurvak(int hoogte, int breedte, int x, int y){
     super(20,20,x,y);
    }
    
  public Vakje geefVakje(){return this;} 
  
  protected void paintComponent(Graphics g) {
        this.draw(g);
        draw(g);
    }
  
      public void draw(Graphics g) {
        try {
          g.drawImage(ImageIO.read(new File("C:\\Users\\Jhoog_000\\Documents\\NetBeansProjects\\Sokoban\\src\\SokobanMuur.png")), x, y,breedte,hoogte, null);
        } catch (IOException ex) {
            Logger.getLogger(Speler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
 