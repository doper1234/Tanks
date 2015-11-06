/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Anna
 */
public abstract class Tank extends Entity  implements ActionListener{

    int dx, dy, direction, arrowPressed;
    int moveUp, moveDown, moveLeft, moveRight;
    
    ImageIcon up;
    ImageIcon up2;
    ImageIcon down;
    ImageIcon down2;
    ImageIcon left;
    ImageIcon left2;
    ImageIcon right;
    ImageIcon right2;
    
    ImageIcon upA;
    ImageIcon up2A;
    ImageIcon downA;
    ImageIcon down2A;
    ImageIcon leftA;
    ImageIcon left2A;
    ImageIcon rightA;
    ImageIcon right2A;
    
    ImageIcon upB;
    ImageIcon up2B;
    ImageIcon downB;
    ImageIcon down2B;
    ImageIcon leftB;
    ImageIcon left2B;
    ImageIcon rightB;
    ImageIcon right2B;
    
    ImageIcon upC;
    ImageIcon up2C;
    ImageIcon downC;
    ImageIcon down2C;
    ImageIcon leftC;
    ImageIcon left2C;
    ImageIcon rightC;
    ImageIcon right2C;
    
    ImageIcon spawn1 = new ImageIcon(url + "spawn1.png");
    ImageIcon spawn2 = new ImageIcon(url + "spawn2.png");;
    ImageIcon spawn3 = new ImageIcon(url + "spawn3.png");;
    ImageIcon spawn4 = new ImageIcon(url + "spawn4.png");;
    
    Timer moveTimer;
    Timer spawnTimer;
    private final Board gameBoard;
    int spawn = 0;
    boolean spawning = true;
    int spawnX, spawnY;
    int level;

    public Tank(int x, int y, Board board ) {
        super(x, y);
        gameBoard = board;
        
        direction = 1;
        arrowPressed = 0;
        moveUp = 0;
        moveDown = 0;
        moveLeft = 0;
        moveRight = 0;
        spawnTimer = new Timer(1 ,this);
        moveTimer = new Timer(1000, this);
        spawnTimer.start();
        

    }

    public void movingImage(int move, ImageIcon i1, ImageIcon i2) {

        if (move % 2 == 0) {
            setDominantImage(i1);
        } 
        else {
            setDominantImage(i2);
        }
        
        

    }

    public void setSpawning(){
        spawning = true;
        spawnTimer.start();
        
    }

    public void moveX() {
        int newX = x + dx;
        if(!canMove(newX, y)){
            
            return;
        }
        x = newX;
        /*if (x >= 768 - dominantImage.getHeight(null) - 10) {
         x = 768 - dominantImage.getHeight(null) - 10;
         }
         if (x <= 0) {
         x = 0;
         }*/
        checkLocation();
    }

    public void moveY() {
        int newY = y + dy;
        if(!canMove(x, newY)){
            return;
        }
        y = newY;
        /*
         if (y >= 720 - 87) {
         y = 720 - 87;
         }
         if (y <= 0) {
         y = 0;
         }*/
        checkLocation();

    }
    
    public void cantMove(){
        dx = 0;
        dy = 0;
        
    }

    public int getDirection() {
        return direction;
    }

    private boolean canMove(int x, int y) {
        
        return gameBoard.canPutTankOn(x, y);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void spawn(){
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == spawnTimer){
            this.x = spawnX;
            this.y = spawnY;
            if (spawn == 1){
                spawning = true;
                this.setDominantImage(spawn1);
            }
            else if (spawn == 10){
                this.setDominantImage(spawn2);
            }
            
            else if (spawn == 20){
                this.setDominantImage(spawn3);
            }
            
            else if (spawn == 30){
                this.setDominantImage(spawn4);
            }
            else if (spawn == 40){
                this.setDominantImage(spawn1);
            }
            else if (spawn == 50){
                this.setDominantImage(spawn2);
            }
            
            else if (spawn == 60){
                this.setDominantImage(spawn3);
            }
            
            else if (spawn >= 70){
                this.setDominantImage(spawn4);
                spawning = false;
                spawn = 0;
                spawnTimer.stop();
            }
            if(!spawnTimer.isRunning()){
                if(this.level == 1){
                    this.setDominantImage(up);
                }
                else if(this.level == 2){
                    this.setDominantImage(upA);
                }
                else if(this.level == 3){
                    this.setDominantImage(upB);
                }
                else{
                  this.setDominantImage(upC);
                }
                moveTimer.start();
                
            }
            
            
            spawn++;
        }
    }
    
    public void setDirection(int d){
        direction = d;
    }
    
    public void setDominantImageUp(){
        setDominantImage(up);
    }
    public void setDominantImageDown(){
        setDominantImage(down);
    }
    public void setDominantImageLeft(){
        setDominantImage(left);
    }
    public void setDominantImageRight(){
        setDominantImage(right);
    }
}