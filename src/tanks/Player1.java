/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.*;
import javax.swing.*;

public class Player1 extends Player {

    
    public Player1(int x, int y) {
        super(x, y);
        goLeft = KeyEvent.VK_LEFT;
        goRight = KeyEvent.VK_RIGHT;
        goUp = KeyEvent.VK_UP;
        goDown = KeyEvent.VK_DOWN;
        shoot = KeyEvent.VK_ENTER;
        up = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1f.png");
        up2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1f2.png");
        down = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1d.png");
        down2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1d2.png");
        left = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1l.png");
        left2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1l2.png");
        right = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1r.png");
        right2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl1r2.png");
        
        upA = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2f.png");
        up2A = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2f2.png");
        downA = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2d.png");
        down2A = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2d2.png");
        leftA = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2l.png");
        left2A = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2l2.png");
        rightA = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2r.png");
        right2A = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl2r2.png");
        
        upB = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3f.png");
        up2B = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3f2.png");
        downB = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3d.png");
        down2B = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3d2.png");
        leftB = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3l.png");
        left2B = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3l2.png");
        rightB = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3r.png");
        right2B = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl3r2.png");
        
        upC = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4f.png");
        up2C = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4f2.png");
        downC = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4d.png");
        down2C = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4d2.png");
        leftC = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4l.png");
        left2C = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4l2.png");
        rightC = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4r.png");
        right2C = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/tlvl4r2.png");
        setDominantImage(up);

    }

    

}
