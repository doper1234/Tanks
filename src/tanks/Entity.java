/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author Anna
 */
public class Entity {
    int x, y;
    final int boardBeginningX = 48;
    final int boardBeginningY = 48;
    final int boardEndX = 642;
    final int boardEndY = 0;
    final String url = "src/tanks/";
    ImageIcon empty = new ImageIcon(url + "empty.png");
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
        
        return new Rectangle(x,y, 48, 48);
    }
    
   public void checkLocation(){
        
       if (this.x <= 48){
            this.x =48;
            
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
   
   public void playSound(String s) {
        
       try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url + s + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error with playing sound.");
        }
    }

    
    
    
        
    
}
