/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Anna
 */

public class MiniExplosion extends Entity implements ActionListener {
    
        ImageIcon empty;
        ImageIcon miniExplosion1;
        ImageIcon miniExplosion2;
        ImageIcon miniExplosion3;
        Timer timer;
        int ticks = 0;
        public MiniExplosion(int x, int y){
            super(x,y);
            empty = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/empty.png");
            miniExplosion1 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/miniExplosion1.png");
            miniExplosion2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/miniExplosion2.png");
            miniExplosion3 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/miniExplosion3.png");
            timer = new Timer(100,this);
 
        }
        
    public void miniExplosion(Graphics g2d, int x, int y) {
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dominantImage = miniExplosion1.getImage();
        
        if (ticks== 1) {
            dominantImage = miniExplosion1.getImage();
        }
        else if (ticks == 2) {
            dominantImage = miniExplosion2.getImage();
        }
        else if (ticks == 3) {
            dominantImage = miniExplosion3.getImage();
        } else {
           // i = empty.getImage();
            timer.stop();
        }
        
    }
}
