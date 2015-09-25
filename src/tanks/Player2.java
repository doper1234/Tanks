/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Anna
 */
public class Player2 extends Player{
    
    public Player2(int x, int y, Board board, boolean playingOnline){
        
        
        super(x, y, board);
        spawnX = (768 / 4) - 144 +(768 / 2);
        spawnY = 720 - 48;
        
        if (playingOnline == true) {
            goLeftInput = KeyEvent.VK_LEFT;
            goRightInput = KeyEvent.VK_RIGHT;
            goUpInput = KeyEvent.VK_UP;
            goDownInput = KeyEvent.VK_DOWN;
            
            
        }
        else{
            goLeftInput = KeyEvent.VK_A;
            goRightInput = KeyEvent.VK_D;
            goUpInput = KeyEvent.VK_W;
            goDownInput = KeyEvent.VK_S;
            
            
            
        }
        shoot = KeyEvent.VK_SPACE;
        up = new ImageIcon (url + "t2lvl1f.png");
        up2 = new ImageIcon( url +"t2lvl1f2.png");
        down = new ImageIcon(url +"t2lvl1d.png");
        down2 = new ImageIcon(url + "t2lvl1d2.png");
        left = new ImageIcon(url + "t2lvl1l.png");
        left2 = new ImageIcon(url + "t2lvl1l2.png");
        right = new ImageIcon(getClass().getResource("t2lvl1r.png"));
        right2 = new ImageIcon(url + "t2lvl1r2.png");
        
        upA = new ImageIcon (url + "t2lvl2f.png");
        up2A = new ImageIcon(url + "t2lvl2f2.png");
        downA = new ImageIcon(url + "t2lvl2d.png");
        down2A = new ImageIcon(url + "t2lvl2d2.png");
        leftA = new ImageIcon(url + "t2lvl2l.png");
        left2A = new ImageIcon(url + "t2lvl2l2.png");
        rightA = new ImageIcon(url + "t2lvl2r.png");
        right2A = new ImageIcon(url + "t2lvl2r2.png");
        
        upB = new ImageIcon (url + "t2lvl3f.png");
        up2B = new ImageIcon(url + "t2lvl3f2.png");
        downB = new ImageIcon(url + "t2lvl3d.png");
        down2B = new ImageIcon(url + "t2lvl3d2.png");
        leftB = new ImageIcon(url + "t2lvl3l.png");
        left2B = new ImageIcon(url + "t2lvl3l2.png");
        rightB = new ImageIcon(url + "t2lvl3r.png");
        right2B = new ImageIcon(url + "t2lvl3r2.png");
        
        upC = new ImageIcon (url + "t2lvl4f.png");
        up2C = new ImageIcon(url + "t2lvl4f2.png");
        downC = new ImageIcon(url + "t2lvl4d.png");
        down2C = new ImageIcon(url + "t2lvl4d2.png");
        leftC = new ImageIcon(url + "t2lvl4l.png");
        left2C = new ImageIcon(url + "t2lvl4l2.png");
        rightC = new ImageIcon(url + "t2lvl4r.png");
        right2C = new ImageIcon(url + "t2lvl4r2.png");
        setDominantImage(up);
    }
        
    
    
}

