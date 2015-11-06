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
    int boardLimitX =624 ;
    int boardLimitY =624;
    int boardLimitStartX = 48;
    int boardLimitStartY = 24; 
    Image bullet;
    boolean visible = true;
    int bulletSpeed = 1;
    final String url = "src/tanks/";
    
    public Bullet(int startX, int startY, int direction, int speed){
        bulletSpeed = bulletSpeed * speed;
        x = startX;
        y = startY;
        this.direction = direction;
        ImageIcon bulletUp = new ImageIcon(url + "bulletup.png");
        ImageIcon bulletDown = new ImageIcon(url + "bulletdown.png");
        ImageIcon bulletLeft = new ImageIcon(url + "bulletleft.png");
        ImageIcon bulletRight = new ImageIcon(url + "bulletright.png");
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
        if (x >  boardLimitX || x < boardLimitStartX){
            visible = false;
           
        }
        if(y > boardLimitY || y < boardLimitStartY){
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
