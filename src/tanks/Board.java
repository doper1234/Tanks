/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import sun.applet.Main;

/**
 *
 * @author Anna
 */
public class Board extends JPanel implements ActionListener {

    Maps maps;
    Player1 player1;
    Player2 player2;
    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;
    Enemy enemy4;
    StarPowerUp star;
    StarPowerUp star2;
    StarPowerUp star3;
    GunPowerUp gun;
    PowerUp[] powerUps = new PowerUp[7];
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
    Image flag;
    Image steel;
    Image trees;
    Image smallBricks;

    public Board() {
        maps = new Maps();
        powerUps[0] = new StarPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[1] = new GunPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[2] = new LifePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[3] = new GrenadePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[4] = new ShovelPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[5] = new TimerPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[6] = new HardHatPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        currentPowerUp = powerUps[rand.nextInt(7)];
        enemy1 = new Enemy(300, 300, 1);
        enemy2 = new Enemy(100, 100, 2);
        enemy3 = new Enemy(200, 100, 3);
        enemy4 = new Enemy(200, 200, 4);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        player1 = new Player1(768 / 4, 720 - 48);
        player2 = new Player2((768 / 4) + (768 / 2), 720 -48);

        star = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        star2 = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        star3 = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        gun = new GunPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        brick = new Bricks[12][13];
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/background.png");
        ImageIcon i2 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/boarder.png");
        ImageIcon i3 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/eagle.png");
        ImageIcon i4 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/steelblock.png");
        ImageIcon i5 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/trees.png");
        ImageIcon i6 = new ImageIcon("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/smallbricks.png");
        steel = i4.getImage();
        trees = i5.getImage();
        smallBricks = i6.getImage();
        background = i.getImage();
        boarder = i2.getImage();
        flag = i3.getImage();
        time = new Timer(5, this);
        time.start();
        playSound("Themes");
        //miniExplosionTimer = new Timer (1000,this);
        //miniExplosionTimer.start();

    }

    public void map(Graphics g) {
        
        int[][] currentMap = maps.getMap1();

        for (int i = 0; i < currentMap.length; i++) {
            for (int x = 0; x < currentMap.length; x++) {
                if(currentMap[i][x] == 1){
                    g.drawImage(smallBricks, x* 24 +48, i*24 + 48, null);
                }
                if(currentMap[i][x] == 2){
                    g.drawImage(steel, x* 24 +48, i*24 + 48, null);
                }
                if(currentMap[i][x] == 3){
                    g.drawImage(trees, x* 24 +48, i*24 + 48, null);
                }
                if(currentMap[i][x] == 4){
                    g.drawImage(flag, x* 24 +48, i*24 + 48, null);
                }
            }
        }
        /*
        for (int i = 0; i < brick.length; i++) {
            for (int x = 0; x < brick.length; x++) {

                if (map1[i][x] == 1) {
                    brick[0][0] = new Bricks(0, 0);
                    paintBricks(brick[0][0], g2d);
                }

            }
        }*/

    }
    public void moveBullets(Tank t) {
        ArrayList bullets = Tank.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            if (m.getVisible()) {
                m.move();
            } else {
                bullets.remove(m);

            }
        }

    }

    public void refreshTank(Tank t, Graphics g2d) {
        g2d.drawImage(t.getDomiantImage(), t.getX(), t.getY(), null);
        ArrayList bullets = t.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            if (m.getDirection() == 1) {
                g2d.drawImage(m.getImage(), m.getX() + (t.getDomiantImage().getWidth(this) / 2) - 6, m.getY(), null);
            }
            if (m.getDirection() == 2) {
                g2d.drawImage(m.getImage(), m.getX() + (t.getDomiantImage().getWidth(this) / 2) - 6, m.getY() + t.getDomiantImage().getHeight(this), null);
            }
            if (m.getDirection() == 3) {
                g2d.drawImage(m.getImage(), m.getX(), m.getY() + (t.getDomiantImage().getHeight(this) / 2) - 6, null);
            }
            if (m.getDirection() == 4) {
                g2d.drawImage(m.getImage(), m.getX() + t.getDomiantImage().getWidth(this), m.getY() + (t.getDomiantImage().getHeight(this) / 2) - 6, null);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Enemy en : enemies) {
            moveBullets(en);
            en.moveX();
            en.moveY();
        }
        checkCollisions(player1);
        checkCollisions(player2);
        moveBullets(player1);
        moveBullets(player2);
        player1.moveX();
        player1.moveY();
        player2.moveX();
        player2.moveY();

        //map();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, null);
        g2d.drawImage(boarder, 0, 0, null);
        g2d.drawImage(flag, 748/ 2, 720 -96, null);
        //paintBricks(brick, g2d);
        refreshTank(player1, g2d);
        refreshTank(player2, g2d);
        for (Enemy en : enemies) {
            refreshTank(en, g2d);
        }
        map(g2d);
        g2d.drawImage(star.getDomiantImage(), star.getX(), star.getY(), null);
        g2d.drawImage(star2.getDomiantImage(), star2.getX(), star2.getY(), null);
        g2d.drawImage(star3.getDomiantImage(), star3.getX(), star3.getY(), null);
        g2d.drawImage(gun.getDomiantImage(), gun.getX(), gun.getY(), null);
        g2d.drawImage(currentPowerUp.getDomiantImage(), currentPowerUp.getX(), currentPowerUp.getY(), null);
        

    }

    private void paintBricks(Bricks brick, Graphics g2d) {
        for (int i = 0; i < brick.destroyableBricks.length; i++) {
            for (int x = 0; x < brick.destroyableBricks.length; x++) {
                g2d.drawImage(brick.destroyableBricks[i][x], brick.getX() + (i * 12), brick.getY() + (x * 12), null);
            }
        }
    }

    private class AL extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);

        }

        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);

        }
    }

    public void checkCollisions(Player p) {

        Rectangle player = p.getBounds();
        Rectangle p1 = player1.getBounds();
        Rectangle p2 = player2.getBounds();
        Rectangle starPower = star.getBounds();
        Rectangle starPower2 = star2.getBounds();
        Rectangle starPower3 = star3.getBounds();
        Rectangle gunPower = gun.getBounds();
        Rectangle powerUp = currentPowerUp.getBounds();

        for (Enemy en : enemies) {
            Rectangle e1 = en.getBounds();
            if (e1.intersects(player)) {
                int tempX = p.dx;
                int tempY = p.dy;
                if (tempY > 0) {
                    p.dy = 0;
                    p.y = p.y - 2;
                    en.dy = 0;
                }
                if (tempY < 0) {
                    p.dy = 0;
                    p.y = p.y + 2;
                    en.dy = 0;
                }
                if (tempX > 0) {
                    p.dx = 0;
                    p.x = p.x - 2;
                    en.dx = 0;
                }
                if (tempX < 0) {
                    p.dx = 0;
                    p.x = p.x + 2;
                    en.dx = 0;
                }

            }
        }

        if (p2.intersects(p1)) {
            playSound("Points");
            System.out.println(player2.getDomiantImage().getWidth(this) + ", " + player2.getDomiantImage().getHeight(this));
            int tempX = player1.dx;
            int tempY = player1.dy;
            if (tempY > 0) {
                player1.dy = 0;
                player1.y = player2.y - player2.getDomiantImage().getHeight(this) - 1;

            }
            if (tempY < 0) {
                player1.dy = 0;
                player1.y = player2.y + player2.getDomiantImage().getHeight(this) + 1;
            }
            if (tempX > 0) {
                player1.dx = 0;
                player1.x = player2.x - player2.getDomiantImage().getWidth(this) - 1;

            }
            if (tempX < 0) {
                player1.dx = 0;
                player1.x = player2.x + player2.getDomiantImage().getWidth(this) + 1;

            }

        }
        if (p1.intersects(p2)) {
            int tempX = player2.dx;
            int tempY = player2.dy;
            if (tempY > 0) {
                player2.dy = 0;
                player2.y = player2.y - 2;

            }
            if (tempY < 0) {
                player2.dy = 0;
                player2.y = player2.y + 2;
            }
            if (tempX > 0) {
                player2.dx = 0;
                player2.x = player2.x - 2;

            }
            if (tempX < 0) {
                player2.dx = 0;
                player2.x = player2.x + 2;

            }

        }
        if (player.intersects(powerUp) && currentPowerUp.getVisible() == true) {
            currentPowerUp.setVisible(false);
            currentPowerUp.setEmptyImage();
            playSound("Points");

        }
        if (player.intersects(starPower) && star.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star.setVisible(false);
            star.setEmptyImage();
            playSound("Points");
        }
        if (player.intersects(starPower2) && star2.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star2.setVisible(false);
            star2.setEmptyImage();
            playSound("Points");
        }
        if (player.intersects(starPower3) && star3.getVisible() == true) {
            p.setLevel(p.getLevel() + 1);
            star3.setVisible(false);
            star3.setEmptyImage();
            playSound("Points");
        }
        if (player.intersects(gunPower) && gun.getVisible() == true) {
            p.setLevel(4);
            gun.setVisible(false);
            gun.setEmptyImage();
            playSound("Points");
        }

    }

    public void playSound(String s) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/Anna/Documents/NetBeansProjects/Tanks/src/tanks/" + s + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
