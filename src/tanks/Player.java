/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Anna
 */
public class Player extends Tank {

    static final int UPWARDS_DIRECTION = 1;
    static final int DOWNWARDS_DIRECTION = 2;
    static final int LEFTWARDS_DIRECTION = 3;
    static final int RIGHTWARDS_DIRECTION = 4;
    int goLeftInput;
    int goRightInput;
    int goUpInput;
    int goDownInput;
    int shoot;
    int lives = 2;
    boolean invincible = true;
    static ArrayList playerBullets;
    boolean dead = false;

    public Player(int x, int y, Board board) {
        super(x, y, board);
        level = 1;
        playerBullets = new ArrayList();

    }

    public boolean isInvinsible() {

        return invincible;
    }

    public void fire(int level) {
        Bullet bullet = new Bullet(x, y, direction, 1);
        playerBullets.add(bullet);
        playSound("PlayerShoot");

        //if(level > 1 && bullets.size() <2){
        //  bullets.add(bullet);
        //}
        //int ammo;
    }

    public static ArrayList getBullets() {
        return playerBullets;
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public void lostALife() {
        if (lives <= 0) {
            this.setVisible(false);
            this.setDominantImage(empty);
            dead = true;

        }
        if (lives > 0) {
            lives--;
            this.setSpawning();

        }

    }

    public void gotALife() {
        if (lives <= 9) {
            lives++;
        }
    }

    public void setLevel(int l) {
        if (l > 4) {
            level = 4;
        } else {
            level = l;
        }

    }

    public void keyPressed(KeyEvent e) {
        if (spawning == false && dead == false) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }

            if (key == goLeftInput) {
                moveLeft();

            }
            if (key == goRightInput) {
                moveRight();
            }
            if (key == goUpInput) {

                moveUp();

            }
            if (key == goDownInput) {
                moveDown();
            }
            if (key == shoot) {
                fire(level);

            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        
        if (key == goLeftInput) {
            dx = 0;
            arrowPressed = 0;
        }
        if (key == goRightInput) {
            dx = 0;
            arrowPressed = 0;
        }
        if (key == goUpInput) {
            dy = 0;
            arrowPressed = 0;
        }
        if (key == goDownInput) {
            dy = 0;
            arrowPressed = 0;
        }

    }

    public void moveUp() {
        if (spawning == false && dead == false) {
            if (arrowPressed == 0) {
                arrowPressed = 3;
            }
            if (arrowPressed == 3) {
                dy = -1;
                if (level == 1) {
                    movingImage(moveUp, up, up2);
                }
                if (level == 2) {
                    movingImage(moveUp, upA, up2A);
                }
                if (level == 3) {
                    movingImage(moveUp, upB, up2B);
                }
                if (level == 4) {
                    movingImage(moveUp, upC, up2C);
                }
                moveUp++;
                direction = Player.UPWARDS_DIRECTION;
            }
        }
    }

    public void moveDown() {
        if (spawning == false && dead == false) {
            if (arrowPressed == 0) {
                arrowPressed = 4;
            }
            if (arrowPressed == 4) {
                dy = 1;
                if (level == 1) {
                    movingImage(moveDown, down, down2);

                }
                if (level == 2) {
                    movingImage(moveDown, downA, down2A);

                }
                if (level == 3) {
                    movingImage(moveDown, downB, down2B);
                }
                if (level == 4) {
                    movingImage(moveDown, downC, down2C);
                }
                direction = Player.DOWNWARDS_DIRECTION;
                moveDown++;

            }
        }
    }

    public void moveLeft() {

        if (spawning == false && dead == false) {
            if (arrowPressed == 0) {
                arrowPressed = 1;
            }
            if (arrowPressed == 1) {
                dx = -1;
                if (level == 1) {
                    movingImage(moveLeft, left, left2);
                }
                if (level == 2) {
                    movingImage(moveLeft, leftA, left2A);
                }
                if (level == 3) {
                    movingImage(moveLeft, leftB, left2B);
                }
                if (level == 4) {
                    movingImage(moveLeft, leftC, left2C);
                }
                moveLeft++;
                direction = Player.LEFTWARDS_DIRECTION;

            }
        }

    }

    public void moveRight() {
        if (spawning == false && dead == false) {
            if (arrowPressed == 0) {
                arrowPressed = 2;
            }
            if (arrowPressed == 2) {
                dx = 1;
                if (level == 1) {
                    movingImage(moveRight, right, right2);
                }
                if (level == 2) {
                    movingImage(moveRight, rightA, right2A);
                }
                if (level == 3) {
                    movingImage(moveRight, rightB, right2B);
                }
                if (level == 4) {
                    movingImage(moveRight, rightC, right2C);
                }

                moveRight++;
                direction = Player.RIGHTWARDS_DIRECTION;

            }
        }
    }

    public void stopMoving() {

        dy = 0;
        arrowPressed = 0;

    }
    
    

}
