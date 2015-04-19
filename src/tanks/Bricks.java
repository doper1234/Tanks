/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Anna
 */
public class Bricks extends Entity{
    Image destroyableBricks[][];
    ImageIcon A1 = new ImageIcon(url + "brickA1.png");
    ImageIcon A2 = new ImageIcon(url + "brickA2.png");
    ImageIcon B1 = new ImageIcon(url + "brickB1.png");
    ImageIcon B2 = new ImageIcon(url + "brickB2.png");
    ImageIcon emptyBrick = new ImageIcon(url + "emptyBrick.png");
    
    public Bricks(int x, int y) {
        super(x, y);
        destroyableBricks = new Image[4][4];
        for (int i = 0; i < destroyableBricks.length; i = i +1){
            for(int h = 0; h < destroyableBricks.length; h++){
                if (i % 2 == 0) {
                    if (h %2 == 0) {
                        destroyableBricks[h][i] = A1.getImage();
                    }
                    else{
                        destroyableBricks[h][i] = A2.getImage();
                    }
                    
                }
                else{
                   if (h %2 == 0) {
                        destroyableBricks[h][i] = A2.getImage();
                    }
                    else{
                        destroyableBricks[h][i] = A1.getImage();
                    } 
                }
                
                
            }
        }
    }
    
    
    public void brickDestroyed(int x, int y){
        destroyableBricks[x][y] = emptyBrick.getImage();
    }
    
    public Image[][] getDestroyableBricks(){
    
        return destroyableBricks;
    }
}
