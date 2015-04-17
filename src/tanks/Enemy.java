/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Anna
 */
public class Enemy extends Tank implements ActionListener {

    Timer timer;
    Random move = new Random();
    private int ticks;
    int level = 1;
    

    public Enemy(int x, int y, int l, Board board) {
        super(x, y, board);
        direction = 0;
        timer = new Timer(1000, this);
        timer.start();
        level = l;
        up = new ImageIcon (url + "elvl1f.png");
        up2 = new ImageIcon(url + "elvl1f2.png");
        down = new ImageIcon(url + "elvl1d.png");
        down2 = new ImageIcon(url + "elvl1d2.png");
        left = new ImageIcon(url + "elvl1l.png");
        left2 = new ImageIcon(url + "elvl12.png");
        right = new ImageIcon(url + "elvl1r.png");
        right2 = new ImageIcon(url + "elvl1r2.png");
        setDominantImage(down);
        
        upA = new ImageIcon (url + "elvl2f.png");
        up2A = new ImageIcon(url + "elvl2f2.png");
        downA = new ImageIcon(url + "elvl2d.png");
        down2A = new ImageIcon(url + "elvl2d2.png");
        leftA = new ImageIcon(url + "elvl2l.png");
        left2A = new ImageIcon(url + "elvl22.png");
        rightA = new ImageIcon(url + "elvl2r.png");
        right2A = new ImageIcon(url + "elvl2r2.png");
        
        upB = new ImageIcon (url + "elvl3f.png");
        up2B = new ImageIcon(url + "elvl3f2.png");
        downB = new ImageIcon(url + "elvl3d.png");
        down2B = new ImageIcon(url + "elvl3d2.png");
        leftB = new ImageIcon(url + "elvl3l.png");
        left2B = new ImageIcon(url + "elvl32.png");
        rightB = new ImageIcon(url + "elvl3r.png");
        right2B = new ImageIcon(url + "elvl3r2.png");
        
        upC = new ImageIcon (url + "elvl4f.png");
        up2C = new ImageIcon(url + "elvl4f2.png");
        downC = new ImageIcon(url + "elvl4d.png");
        down2C = new ImageIcon(url + "elvl4d2.png");
        leftC = new ImageIcon(url + "elvl4l.png");
        left2C = new ImageIcon(url + "elvl42.png");
        rightC = new ImageIcon(url + "elvl4r.png");
        right2C = new ImageIcon(url + "elvl4r2.png");
        
    }

    public void moveEnemy() {

        direction = move.nextInt(4) + 1;
        if (direction == 4) {
            dx = 1;
            //right

        } else if (direction == 3) {
            dx = -1;
            //left

        } else if (direction == 2) {
            dy = 1;
            //down

        } else if (direction == 1) {
            dy = -1; // up

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int moves = move.nextInt(6);
        if (moves == 0) {
            fire(1);
        }
        if (moves == 3) {
            dx = 0;
            dy = 0;
            moveEnemy();

        }
        if (direction == 4) {

            if (level == 1) {
                movingImage(ticks, right, right2);
            }
            if (level == 2) {
                movingImage(ticks, rightA, right2A);
            }
            if (level == 3) {
                movingImage(ticks, rightB, right2B);
            }
            if (level == 4) {
                movingImage(ticks, rightC, right2C);
            }

        } else if (direction == 3) {

            if (level == 1) {
                movingImage(ticks, left, left2);
            }
            if (level == 2) {
                movingImage(ticks, leftA, left2A);
            }
            if (level == 3) {
                movingImage(ticks, leftB, left2B);
            }
            if (level == 4) {
                movingImage(ticks, leftC, left2C);
            }

        } else if (direction == 2) {
            if (level == 1) {
                movingImage(ticks, down, down2);

            }
            if (level == 2) {
                movingImage(ticks, downA, down2A);

            }
            if (level == 3) {
                movingImage(ticks, downB, down2B);
            }
            if (level == 4) {
                movingImage(ticks, downC, down2C);
            }

        } else if (direction == 1) {
            if (level == 1) {
                movingImage(ticks, up, up2);
            }
            if (level == 2) {
                movingImage(ticks, upA, up2A);
            }
            if (level == 3) {
                movingImage(ticks, upB, up2B);
            }
            if (level == 4) {
                movingImage(ticks, upC, up2C);
            }

        }

        ticks++;
    }

}
