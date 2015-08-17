/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.*;
import javax.swing.*;

public class Player1 extends Player {

    
    public Player1(int x, int y, Board board, boolean playingOnline) {
        super(x, y, board);
        spawnX = 768 / 4 + 48;
        spawnY = 720 - 48;
        goLeft = KeyEvent.VK_LEFT;
        goRight = KeyEvent.VK_RIGHT;
        goUp = KeyEvent.VK_UP;
        goDown = KeyEvent.VK_DOWN;
        if(playingOnline == true){
            shoot = KeyEvent.VK_SPACE;
        }
        else{
            shoot = KeyEvent.VK_ENTER;
        }
        
        up = new ImageIcon(url + "tlvl1f.png");
        up2 = new ImageIcon(url + "tlvl1f2.png");
        down = new ImageIcon(url + "tlvl1d.png");
        down2 = new ImageIcon(url + "tlvl1d2.png");
        left = new ImageIcon(url + "tlvl1l.png");
        left2 = new ImageIcon(url + "tlvl1l2.png");
        right = new ImageIcon(url + "tlvl1r.png");
        right2 = new ImageIcon(url + "tlvl1r2.png");
        
        upA = new ImageIcon(url + "tlvl2f.png");
        up2A = new ImageIcon(url + "tlvl2f2.png");
        downA = new ImageIcon(url + "tlvl2d.png");
        down2A = new ImageIcon(url + "tlvl2d2.png");
        leftA = new ImageIcon(url + "tlvl2l.png");
        left2A = new ImageIcon(url + "tlvl2l2.png");
        rightA = new ImageIcon(url + "tlvl2r.png");
        right2A = new ImageIcon(url + "tlvl2r2.png");
        
        upB = new ImageIcon(url + "tlvl3f.png");
        up2B = new ImageIcon(url + "tlvl3f2.png");
        downB = new ImageIcon(url + "tlvl3d.png");
        down2B = new ImageIcon(url + "tlvl3d2.png");
        leftB = new ImageIcon(url + "tlvl3l.png");
        left2B = new ImageIcon(url + "tlvl3l2.png");
        rightB = new ImageIcon(url + "tlvl3r.png");
        right2B = new ImageIcon(url + "tlvl3r2.png");
        
        upC = new ImageIcon(url + "tlvl4f.png");
        up2C = new ImageIcon(url + "tlvl4f2.png");
        downC = new ImageIcon(url + "tlvl4d.png");
        down2C = new ImageIcon(url + "tlvl4d2.png");
        leftC = new ImageIcon(url + "tlvl4l.png");
        left2C = new ImageIcon(url + "tlvl4l2.png");
        rightC = new ImageIcon(url + "tlvl4r.png");
        right2C = new ImageIcon(url + "tlvl4r2.png");
        setDominantImage(up);

    }

    

}
