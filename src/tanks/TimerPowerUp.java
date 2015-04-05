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
public class TimerPowerUp extends PowerUp{
    ImageIcon timer = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/timer.png");
    public TimerPowerUp(int x, int y) {
        super(x,y);
        this.powerUpImage = timer;
    }
    
}
