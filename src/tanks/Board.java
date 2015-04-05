/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanks;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Anna
 */
public class Board extends JPanel implements ActionListener{

    Player1 player1;
    Player2 player2;
    Enemy1 enemy1;
    Enemy1 enemyA;
    StarPowerUp star;
    StarPowerUp star2;
    StarPowerUp star3;
    GunPowerUp gun;
    PowerUp [] powerUps = new PowerUp[7];
    ArrayList<Enemy> enemies = new ArrayList<>();
    PowerUp currentPowerUp;
    Bricks[][] brick;
    Image background;
    Image boarder;
    Image player1Display;
    Timer time;
    int ticks = 0;
    Timer miniExplosionTimer;
    Random rand = new Random();
    MiniExplosion mE;
    
    public Board(){
        powerUps[0] = new StarPowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[1] = new GunPowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[2] = new LifePowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[3] = new GrenadePowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[4] = new ShovelPowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[5] = new TimerPowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        powerUps[6] = new HardHatPowerUp(rand.nextInt(768)-50,rand.nextInt(720)-50);
        currentPowerUp = powerUps[rand.nextInt(7)];
        enemyA = new Enemy1(100,100);
        enemy1 = new Enemy1(300,300);
        enemies.add(enemyA);
        enemies.add(enemy1);
        player1 = new Player1(768/4, 720/2);
        player2 = new Player2((768/4)+(768/2),720/2);
        
        star = new StarPowerUp(rand.nextInt(768)-144,rand.nextInt(720)-96);
        star2 = new StarPowerUp(rand.nextInt(768)-144,rand.nextInt(720)-96);
        star3 = new StarPowerUp(rand.nextInt(768)-144,rand.nextInt(720)-96);
        gun = new GunPowerUp(rand.nextInt(768)-144,rand.nextInt(720)-96);
        brick = new Bricks[12][13];
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/background.png");
        ImageIcon i2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/boarder.png");
        background = i.getImage();
        boarder = i2.getImage();
        time = new Timer(5, this);
        time.start();
        //miniExplosionTimer = new Timer (1000,this);
        //miniExplosionTimer.start();

    }
    
    /*public void map(){
        Graphics g2d = getGraphics();
        int[][] map1 = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        
        };
        
        for(int i = 0; i < brick.length; i ++){
            for (int x = 0; x < brick.length; x++){
                brick[i][x] = new Bricks(0,0);
            }
        }
        for(int i = 0; i < brick.length; i ++){
            for (int x = 0; x < brick.length; x++){
                
                if (map1[i][x] == 1){
                    brick[0][0] = new Bricks(0,0);
                    paintBricks(brick[0][0], g2d);
                }
                
            }
        }
        
        
    }*/
    
    public void moveBullets(Tank t){
        ArrayList bullets = Tank.getBullets();
        Graphics paper = getGraphics();
        for (int i = 0; i < bullets.size(); i++){
            Bullet m = (Bullet)bullets.get(i);
            if (m.getVisible()){
            m.move();
            }
            else{
                bullets.remove(m);
                
            }
        }
        
        
    }
    public void refreshTank(Tank t, Graphics g2d){
        g2d.drawImage(t.getDomiantImage(), t.getX(), t.getY(), null);
        ArrayList bullets = t.getBullets();
        for (int i = 0; i < bullets.size(); i++){
            Bullet m = (Bullet)bullets.get(i);
            System.out.println("fire");
            if(m.getDirection() == 1){
            g2d.drawImage(m.getImage(), m.getX() + (t.getDomiantImage().getWidth(this)/2) - 6, m.getY(), null);
            }
            if(m.getDirection() == 2){
            g2d.drawImage(m.getImage(), m.getX() + (t.getDomiantImage().getWidth(this)/2) - 6, m.getY() + t.getDomiantImage().getHeight(this), null);    
            }
            if(m.getDirection() == 3){
            g2d.drawImage(m.getImage(), m.getX(), m.getY() + (t.getDomiantImage().getHeight(this)/2) - 6, null);    
            }
            if(m.getDirection() == 4){ 
            g2d.drawImage(m.getImage(), m.getX() + t.getDomiantImage().getWidth(this), m.getY() + (t.getDomiantImage().getHeight(this)/2) - 6, null);    
            }
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       checkCollisions(player1);
       checkCollisions(player2);
       moveBullets(player1);
       moveBullets(player2);
       player1.moveX();
       player1.moveY();
       player2.moveX();
       player2.moveY();
       for (int i = 0; i == enemies.size(); i++){
           
           moveBullets(enemies.get(i));
           
           
       }
       enemy1.moveX();
       enemy1.moveY();
       enemyA.moveX();
       enemyA.moveY();
       //map();
       repaint();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(background, 0, 0, null);
        g2d.drawImage(boarder, 0,0 , null);
        g2d.drawImage(star.getDomiantImage(), star.getX(), star.getY(), null);
        g2d.drawImage(star2.getDomiantImage(), star2.getX(), star2.getY(), null);
        g2d.drawImage(star3.getDomiantImage(), star3.getX(), star3.getY(), null);
        g2d.drawImage(gun.getDomiantImage(), gun.getX(), gun.getY(), null);
        g2d.drawImage(currentPowerUp.getDomiantImage(), currentPowerUp.getX(), currentPowerUp.getY(), null);
        //paintBricks(brick, g2d);
        refreshTank(player1, g2d);
        refreshTank(player2, g2d);
        refreshTank(enemy1, g2d);
        refreshTank(enemyA, g2d);
        

    }

    private void paintBricks(Bricks brick, Graphics g2d) {
        for (int i = 0; i < brick.destroyableBricks.length; i++){
          for (int x = 0; x < brick.destroyableBricks.length; x++){
            g2d.drawImage(brick.destroyableBricks[i][x], brick.getX()+(i*12), brick.getY() +(x*12), null);
          }   
        }
    }
    private class AL extends KeyAdapter{
        public void keyReleased(KeyEvent e){
            player1.keyReleased(e);
            player2.keyReleased(e);
            
        }
        public void keyPressed(KeyEvent e){
            player1.keyPressed(e);
            player2.keyPressed(e);
            
        }
    }
    public void checkCollisions(Player p){
        
        Rectangle p1 = p.getBounds();
        Rectangle starPower = star.getBounds();
        Rectangle starPower2 = star2.getBounds();
        Rectangle starPower3 = star3.getBounds();
        Rectangle gunPower = gun.getBounds();
        Rectangle powerUp = currentPowerUp.getBounds();
        Rectangle e1 = enemy1.getBounds();
        
        
        
        if(e1.intersects(p1)){
            p.cantMove();
            enemy1.cantMove();
            
            
            
        }
        if (p1.intersects(powerUp) && currentPowerUp.getVisible() == true) {
            currentPowerUp.setVisible(false);
            currentPowerUp.setEmptyImage();
            
        }
        if (p1.intersects(starPower) && star.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star.setVisible(false);
            star.setEmptyImage();
        }
        if (p1.intersects(starPower2) && star2.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star2.setVisible(false);
            star2.setEmptyImage();
        }
        if (p1.intersects(starPower3) && star3.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star3.setVisible(false);
            star3.setEmptyImage();
        }
        if (p1.intersects(gunPower) && gun.getVisible() == true) {
            p.setLevel(4);
            gun.setVisible(false);
            gun.setEmptyImage();
        }
        
    }
    
    
}


