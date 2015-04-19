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
public class ShovelPowerUp extends PowerUp{
    ImageIcon shovel = new ImageIcon(url + "shovel.png");
    public ShovelPowerUp(int x, int y) {
        super(x,y);
        this.powerUpImage = shovel;
    }
    
}
