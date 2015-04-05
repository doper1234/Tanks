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
class LifePowerUp extends PowerUp {
    ImageIcon life = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/life.png");
    public LifePowerUp(int x, int y) {
        super(x,y);
        this.powerUpImage = life;
    }
    
}
