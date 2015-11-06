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
public class GrenadePowerUp extends PowerUp {
    ImageIcon grenade = new ImageIcon(url + "grenade.png");
    public GrenadePowerUp(int x, int y) {
        super(x,y);
        this.powerUpImage = grenade;
        powerUpSound = "GotStar";
    }
    @Override
     public void doPowerUpThing(Player p, Enemy e){
        e.setVisible(false);
    }
}