/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Graphics;
import java.awt.Image;
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
        powerUpSound = "GotStar";
    }
    
    public void doPowerUpThing(Player p, Enemy e, Image im, int[][] map, Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < 4; y++) {
                    if (map[i][x * 4 + y] == 4) {
                        if(i > 0){
                            map[i - 1][x*4 + y] = 2;
                            if(x > 4){
                                map[i - 1][(x-1)*4 + y] = 2;
                                map[i][(x-1)*4 + y] = 2;
                                map[i + 1][(x-1)*4 + y] = 2;
                            }
                            if(x < map.length){
                               map[i - 1][(x+1)*4 + y] = 2;
                               map[i - 1][(x+2)*4 + y] = 2;
                               map[i][(x+2)*4 + y] = 2;
                               map[i + 1][(x+2)*4 + y] = 2;
                           }
                        }
                        
                           
                       
                        
                    }
                }
            }
        }
    }
    
}
