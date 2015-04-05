/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Anna
 */
public class PowerUp extends Entity implements ActionListener{
    Timer flickerTimer;
    Random rand = new Random();
    ImageIcon powerUpImage;
    int ticks = 0;
    public PowerUp(int x, int y){
        super(x,y);
        flickerTimer = new Timer(300,this);
        flickerTimer.start();
        
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.getVisible() == false){
            
            this.setEmptyImage();
            flickerTimer.stop();
        }
         
        else if (ticks % 2 == 0){
            setDominantImage(powerUpImage);
            
        }
        else{
            this.setEmptyImage();
        }
                
        ticks++;
    }
}
