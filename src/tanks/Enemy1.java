/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import javax.swing.ImageIcon;

/**
 *
 * @author Anna
 */
public class Enemy1 extends Enemy{

    public Enemy1(int x, int y) {
        super(x, y);
        up = new ImageIcon ("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1f.png");
        up2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1f2.png");
        down = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1d.png");
        down2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1d2.png");
        left = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1l.png");
        left2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl12.png");
        right = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1r.png");
        right2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/elvl1r2.png");
        setDominantImage(down);
    }
    
}
