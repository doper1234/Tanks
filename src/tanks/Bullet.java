/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Anna
 */
public class Bullet {
    int x,y, direction;
    Image bullet;
    boolean visible = true;
    int bulletSpeed = 2;
    
    public Bullet(int startX, int startY, int direction, int speed){
        bulletSpeed = bulletSpeed * speed;
        x = startX;
        y = startY;
        this.direction = direction;
        ImageIcon bulletUp = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/bulletup.png");
        ImageIcon bulletDown = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/bulletdown.png");
        ImageIcon bulletLeft = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/bulletleft.png");
        ImageIcon bulletRight = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/bulletright.png");
        if (direction == 1){
            bullet = bulletUp.getImage();
        }
        if (direction == 2){
            bullet = bulletDown.getImage();
        }
        if (direction == 3){
            bullet = bulletLeft.getImage();
        }
        if (direction == 4){
            bullet = bulletRight.getImage();
        }
        visible = true;
    }
    
    public void move(){
        if (direction == 1){
           y = y - bulletSpeed; 
        }
        if (direction == 2){
           y = y + bulletSpeed;
        }
        if (direction == 3){
            x = x - bulletSpeed;
        }
        if (direction == 4){
            x = x + bulletSpeed;
        }
        if (x > 624 || x < 96){
            visible = false;
        }
        if(y > 624 || y < 48){
            visible = false;
        }
    }
    public int getX(){
        return x;
    }
            
    public int getY(){
        return y;
    }
    public Image getImage(){
        return bullet;
    }
    public boolean getVisible(){
        return visible;
    }
    
    public int getSpeed(){
        
        return bulletSpeed;
    }
    public void setSpeed(int i){
        bulletSpeed = bulletSpeed * i;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public Rectangle getBounds(){
        
        return new Rectangle(x,y, 12, 12);
    }
            
}
