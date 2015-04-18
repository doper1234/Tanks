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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    int [][] mapFromFile;
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
    Image eagle;
    Image steel;
    Image trees;
    Image smallBricks;
    Image enemyIcon;
    Image blankGrey;
    Image zero;
    Image one;
    Image two;
    Image three;
    int input = 0;
    String tempInput;
    int enemiesDefeated = 20;
    private final int grid = 48;
    private final int halfGrid = 24;
    private int[][] currentMap;
    private int[][] newMap;
    private final int BLANK_SPACE = 0;
    private final int BRICK = 1;
    private final int STEEL_BLOCK = 2;
    private final int ENTITY_TREE = 3;
    private final int WATER = 5;
    boolean initializeMap = true;
    private Image brickA1;
    private Image brickA2;
    private Image water;
    private Image emptySpaces;
    int explosionTime = 0;
    final String url = "src/tanks/";
    //private final Rectangle brickBounds;
    ImageIcon i15;
    ImageIcon i16;
    ImageIcon i17;
    ImageIcon i19 = new ImageIcon(url + "miniExplosion1.png");
    ImageIcon i20 = new ImageIcon(url + "miniExplosion2.png");
    ImageIcon i21 = new ImageIcon(url + "miniExplosion3.png");
    ImageIcon i22 = new ImageIcon(url + "empty.png");
      
    private Image explosion;

    public Board() {
        do {
            tempInput = JOptionPane.showInputDialog("Type a map number (0-4): ");
        } while (tempInput == null);
        if (tempInput == null) {
            System.exit(0);
        }
        input = Integer.parseInt(tempInput);
        
        maps = new Maps();

        powerUps[0] = new StarPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[1] = new GunPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[2] = new LifePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[3] = new GrenadePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[4] = new ShovelPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[5] = new TimerPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        powerUps[6] = new HardHatPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
        currentPowerUp = powerUps[rand.nextInt(7)];
        enemy1 = new Enemy(400, grid, 1, this);
        enemy2 = new Enemy(grid*6, grid, 2, this);
        enemy3 = new Enemy(800, grid, 3, this);
        enemy4 = new Enemy(0, grid, 4, this);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);

        enemiesDefeated = enemiesDefeated - enemies.size();
        player1 = new Player1(768 / 4 + 48, 720 - 48, this);
        player2 = new Player2((768 / 4) - 144 +(768 / 2), 720 - 48, this);

        star = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        star2 = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        star3 = new StarPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        gun = new GunPowerUp(rand.nextInt(768) - 144, rand.nextInt(720) - 96);
        brick = new Bricks[12][13];
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon(url + "background.png");
        ImageIcon i2 = new ImageIcon(url + "boarder.png");
        ImageIcon i3 = new ImageIcon(url + "eagle.png");
        ImageIcon i4 = new ImageIcon(url + "steelblock.png");
        ImageIcon i5 = new ImageIcon(url + "trees.png");
        ImageIcon i6 = new ImageIcon(url + "smallbricks.png");
        ImageIcon i7 = new ImageIcon(url + "enemyicon.png");
        ImageIcon i8 = new ImageIcon(url + "blankgrey.png");
        ImageIcon i9 = new ImageIcon(url + "zero.png");
        ImageIcon i10 = new ImageIcon(url + "one.png");
        ImageIcon i11 = new ImageIcon(url + "two.png");
        ImageIcon i12 = new ImageIcon(url + "three.png");
        ImageIcon i13 = new ImageIcon(url + "BrickA1.png");
        ImageIcon i14 = new ImageIcon(url + "BrickA2.png");
        i15 = new ImageIcon(url + "water1.png");
        i16 = new ImageIcon(url + "water2.png");
        i17 = new ImageIcon(url + "water3.png");
        ImageIcon i18 = new ImageIcon (url + "emptySpacesWhatAreWeLivingFor.png");
        brickA1 = i13.getImage();
        brickA2 = i14.getImage();
        water = i15.getImage();
        zero = i9.getImage();
        one = i10.getImage();
        two = i11.getImage();
        three = i12.getImage();
        blankGrey = i8.getImage();
        enemyIcon = i7.getImage();
        steel = i4.getImage();
        trees = i5.getImage();
        smallBricks = i6.getImage();
        background = i.getImage();
        boarder = i2.getImage();
        eagle = i3.getImage();
        emptySpaces = i18.getImage();
        time = new Timer(5, this);

        playSound("Themes");
        //miniExplosionTimer = new Timer (1000,this);
        //miniExplosionTimer.start();

    }

    public void smallExplosion(Graphics g, int x, int y){
        /*boolean firstRunThrough = true;
        
        if(firstRunThrough == true){
            explosionTime = 0;
            explosion = i19.getImage();
            firstRunThrough = false;
        }        
        else if (explosionTime == 200){
            explosion = i20.getImage();
        }
        else if (explosionTime == 400){
            explosion = i21.getImage();
        }
        else if (explosionTime == 600){
            explosion = i22.getImage();
        }*/
        explosion = i21.getImage();
        g.drawImage(explosion, x, y, null);
        
        explosionTime++;
            
    }
    public void movingWater(){
        if(ticks % 300 == 0){
            water =  i17.getImage();
        }
        else if(ticks % 300 == 100){
            water = i16.getImage();
        }
        else if (ticks % 300 == 200){
           water = i15.getImage();
        }
    }
    public void map(Graphics g) throws IOException {
        if (initializeMap) {
            if (input >= 2 && input <= 5) {

                currentMap = maps.getMap(input);
            } else if (input == 1) {
                currentMap = maps.getMapFromFile(input);
            } else {
                currentMap = maps.getMap(0);
            }
            newMap = new int[currentMap.length][currentMap.length * 4];
            for (int i = 0; i < newMap.length; i++) {
                for (int x = 0; x < newMap.length; x++) {
                    for (int y = 0; y < 4; y++) {
                        newMap[i][x * 4 + y] = currentMap[i][x];
                    }
                }
            }
            initializeMap = false;
        }

        
        for (int i = 0; i < newMap.length; i++) {
            for (int x = 0; x < newMap.length; x++) {
                for (int y = 0; y < 4; y++) {
                    //newMap[i][x * 4 + y] = currentMap[i][x];
                    if (newMap[i][x * 4 + y] == 0) {
                        if (y == 0) {
                            g.drawImage(emptySpaces, x * 24 + 48, i * 24 + 48, null);
                        } else if (y == 1) {
                            g.drawImage(emptySpaces, x * 24 + 48 + 12, i * 24 + 48, null);
                        } else if (y == 2) {
                            g.drawImage(emptySpaces, x * 24 + 48, i * 24 + 48 + 12, null);
                        } else if (y == 3) {
                            g.drawImage(emptySpaces, x * 24 + 48 + 12, i * 24 + 48 + 12, null);
                        }
                        
                    }
                    if (newMap[i][x * 4 + y] == 1) {
                        if (y == 0) {
                            g.drawImage(brickA1, x * 24 + 48, i * 24 + 48, null);
                        } else if (y == 1) {
                            g.drawImage(brickA2, x * 24 + 48 + 12, i * 24 + 48, null);
                        } else if (y == 2) {
                            g.drawImage(brickA2, x * 24 + 48, i * 24 + 48 + 12, null);
                        } else if (y == 3) {
                            g.drawImage(brickA1, x * 24 + 48 + 12, i * 24 + 48 + 12, null);
                        }
                        
                    }
                    if(newMap[i][x*4 + y] == 2 ){
                        g.drawImage(steel, x * 24 + 48, i * 24 + 48, null);
                    }
                    
                    if(newMap[i][x * 4 + y] == 3){
                      g.drawImage(trees, x * 24 + 48, i * 24 + 48, null);  
                    }
                    
                    if(newMap[i][x * 4 + y] == 4){
                        g.drawImage(eagle, x * 24 + 48, i * 24 + 48, null);
                    }
                    
                    if(newMap[i][x * 4 + y] == 5){
                        g.drawImage(water, x * 24 + 48, i * 24 + 48, null);
                    }            
                                    
                    
                }
            }
        }
    }    

    public void moveBullets(Enemy t) {
        ArrayList bullets = Enemy.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            if (m.getVisible() == true) {
                m.move();
                    if (m.getBounds().intersects(player1.getBounds())) {
                        player1.lostALife();
                        player1.setX(768 / 4 + 48);
                        player1.setY(720 - 48);
                        player1.setLevel(1);
                        player1.setDominantImage(player1.up);
                        bullets.remove(m);
                        
                        
                    }
                
                
            } else {
                bullets.remove(m);
                

            }
        }

    }
    
    public void moveBullets(Player p) {
        ArrayList bullets = Player.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            if (m.getVisible() == true) {
                m.move();
                for(Enemy e: enemies){
                    if (m.getBounds().intersects(e.getBounds())) {
                        
                        bullets.remove(m);
                        
                    }
                    
                    if(newMap[m.getY()/26][(m.getX()*4)/26] == 1){
                        bullets.remove(m);
                        
                        
                        newMap[m.getY()/26][(m.getX()*4)/26 ] = 0;
                        
                    }
                }
                
                
            } else {
                bullets.remove(m);

            }
        }

    }

    public void refreshTank(Tank t, Graphics g2d) {
        g2d.drawImage(t.getDomiantImage(), t.getX(), t.getY(), null);
        ArrayList bullets = Enemy.getBullets();
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
    
    public void refreshTank(Player t, Graphics g2d) {
        g2d.drawImage(t.getDomiantImage(), t.getX(), t.getY(), null);
        ArrayList bullets = Player.getBullets();
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
        try {
            super.paint(g);
            
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.drawImage(background, 0, 0, null);
            g2d.drawImage(boarder, 0, 0, null);
            map(g2d);
            
            //paintBricks(brick, g2d);
            refreshTank(player1, g2d);
            refreshTank(player2, g2d);
            paintLives(player1, g2d);
            paintLives(player2, g2d);
            for (Enemy en : enemies) {
                refreshTank(en, g2d);
            }
            movingWater();
            g2d.drawImage(star.getDomiantImage(), star.getX(), star.getY(), null);
            g2d.drawImage(star2.getDomiantImage(), star2.getX(), star2.getY(), null);
            g2d.drawImage(star3.getDomiantImage(), star3.getX(), star3.getY(), null);
            g2d.drawImage(gun.getDomiantImage(), gun.getX(), gun.getY(), null);
            g2d.drawImage(currentPowerUp.getDomiantImage(), currentPowerUp.getX(), currentPowerUp.getY(), null);
            paintLevelNumber(g2d);
            
            for (int i = 0; i < enemies.size(); i++) {
                if (i % 2 == 0) {
                    g2d.drawImage(enemyIcon, 768 - 72, ((i * 24) + (3 * 24)) - (i * 12), null);
                } else {
                    g2d.drawImage(enemyIcon, 768 - 48, ((i * 24) + (3 * 24)) - ((i + 1) * 12), null);
                }
            }
            for (int i = 19; i >= enemies.size(); i--) {
                if (i % 2 == 0) {
                    g2d.drawImage(blankGrey, 768 - 72, ((i * 24) + (3 * 24)) - (i * 12), null);
                } else {
                    g2d.drawImage(blankGrey, 768 - 48, ((i * 24) + (3 * 24)) - ((i + 1) * 12), null);
                }
            }
            
            if (!time.isRunning()) {
                time.start();
            }
            ticks++;
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintLives(Player p, Graphics g) {
        int lives = p.getLives();
        int y;
        if (p == player1) {
            y = 0;
        } else {
            y = 72;
        }
        if (lives == 0) {
            g.drawImage(zero, 768 - 48, 24 * 18 + y, null);
        }
        if (lives == 1) {
            g.drawImage(one, 768 - 48, 24 * 18 + y, null);
        }
        if (lives == 2) {
            g.drawImage(two, 768 - 48, 24 * 18 + y, null);
        }
    }

    public void paintLevelNumber(Graphics g) {
        if (input < 10) {
            g.drawImage(blankGrey, 768 - 72, 24 * 18 + 168, null);
        }
        if (input == 0) {
            g.drawImage(zero, 768 - 48, 24 * 18 + 168, null);
        }
        if (input == 1) {
            g.drawImage(one, 768 - 48, 24 * 18 + 168, null);
        }
        if (input == 2) {
            g.drawImage(two, 768 - 48, 24 * 18 + 168, null);
        }
        if (input == 3) {
            g.drawImage(three, 768 - 48, 24 * 18 + 168, null);
        }
    }

    private void paintBricks(Bricks brick, Graphics g2d) {
        for (int i = 0; i < brick.destroyableBricks.length; i++) {
            for (int x = 0; x < brick.destroyableBricks.length; x++) {
                g2d.drawImage(brick.destroyableBricks[i][x], brick.getX() + (i * 12), brick.getY() + (x * 12), null);
            }
        }
    }

    boolean canPutTankOn(int x, int y) {
        int gridX = ((x*4)/26) ;
        int gridY = (y/26) ;
        
        
        int entity = newMap[gridY][gridX];
        
        return entity == BLANK_SPACE || entity == ENTITY_TREE;

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

        int[][] temp = maps.map1;
        Rectangle player = p.getBounds();
        Rectangle p1 = player1.getBounds();
        Rectangle p2 = player2.getBounds();
        Rectangle starPower = star.getBounds();
        Rectangle starPower2 = star2.getBounds();
        Rectangle starPower3 = star3.getBounds();
        Rectangle gunPower = gun.getBounds();
        Rectangle powerUp = currentPowerUp.getBounds();

        for (int i = 0; i < maps.map1.length - 1; i++) {
            for (int x = 0; x < maps.map1.length - 1; x++) {

                

            }
        }
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
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url + s + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
