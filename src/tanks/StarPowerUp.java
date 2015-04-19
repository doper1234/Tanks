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
public class StarPowerUp extends PowerUp{
    ImageIcon star = new ImageIcon(url + "star.png");
    public StarPowerUp(int x, int y) {
        super(x, y);
        this.powerUpImage = star;
        
    }
    
    
    
    
    
}
