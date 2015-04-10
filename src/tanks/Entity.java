/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Anna
 */
public class Entity {
    int x, y;
    ImageIcon empty = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/empty.png");
    Image dominantImage;
    boolean visible = true;
    
    
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
        checkLocation();
        
        
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getDomiantImage() {
        return dominantImage;
    }

    public void setDominantImage(ImageIcon i) {
        dominantImage = i.getImage();

    }
    public void setEmptyImage(){
        dominantImage = empty.getImage();
    }
    public void setVisible(boolean b){
        visible = b;
    }
    
    public boolean getVisible(){
        return visible;
    }
    
    public Rectangle getBounds(){
        
        return new Rectangle(x,y, 42, 42);
    }
    
   public void checkLocation(){
        
       if (this.x <= 96){
            this.x =96;
            
        }
        if(this.x >= 768-144){
            this.x = 768 - 144;
            
        }
        if (this.y <= 48){
            this.y =48;
            
        }
        if(this.y >= 720-96){
            this.y = 720 - 96;
            
        }
    }

    
    
    
        
    
}
