package sokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClickListener implements ActionListener {
    private Speelveld speelveld;
  
    
    public void actionPerformed(ActionEvent event){
        System.out.println("I was clicked");
       
    }
}
