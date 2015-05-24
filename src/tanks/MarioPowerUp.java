/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Chris
 */
public class MarioPowerUp extends PowerUp{
    ImageIcon mario = new ImageIcon(url + "MarioPowerUp.png");
    public MarioPowerUp(int x, int y) {
        super(x,y);
        this.powerUpImage = mario;
        powerUpSound = "Zod";
    }
    @Override
     public void doPowerUpThing(Graphics g, Board b){
        
        b.stageStart = true;
        b.startStage(g);
    }
}
