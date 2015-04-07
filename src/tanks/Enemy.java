/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

    public Enemy(int x, int y) {
        super(x, y);
        direction = 0;
        timer = new Timer(500, this);
        timer.start();
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
        if (moves % 2 == 0) {
            fire(1);
        }
        if (moves == 3) {
            dx = 0;
            dy = 0;
            moveEnemy();

        }
        if (direction == 4) {
            movingImage(ticks, right, right2);

        } else if (direction == 3) {
            movingImage(ticks, left, left2);

        } else if (direction == 2) {
            movingImage(ticks, down, down2);

        } else if (direction == 1) {
            movingImage(ticks, up, up2);
        }

        ticks++;
    }

}
