/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.*;


public final class Board extends JPanel implements ActionListener {

    String iP = Frame.iP;
    Board board;
    int keyPressed;
    int keyReleased;
    Maps maps;
    int stageTime;
    int[][] mapFromFile;

    Player1 player1;
    Player2 player2;

    Enemy player1Online;
    
    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;
    Enemy enemy4;
    Enemy enemy5;
    Enemy enemy6;
    Enemy enemy7;
    Enemy enemy8;
    Enemy enemy9;
    Enemy enemy10;
    Enemy enemy11;
    Enemy enemy12;
    Enemy enemy13;
    Enemy enemy14;
    Enemy enemy15;
    Enemy enemy16;
    Enemy enemy17;
    Enemy enemy18;
    Enemy enemy19;
    Enemy enemy20;

    StarPowerUp star;
    StarPowerUp star2;
    StarPowerUp star3;
    GunPowerUp gun;
    boolean repeat = true;
    PowerUp[] powerUps = new PowerUp[8];
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
    Image four;
    Image five;
    Image six;
    Image seven;
    Image eight;
    Image nine;

    MiniExplosion miniEx;
    int miniExX = 0;
    int miniExY = 0;
    int input = 1;
    int points = 0;
    String tempInput;
    int enemiesDefeated = 20;
    int enemiesSpawned = 20;
    int spawnRate = 300;
    private final int grid = 48;
    private final int halfGrid = 24;
    private int[][] currentMap;
    private int[][] newMap;

    private final int BLANK_SPACE = 0;
    private final int BRICK = 1;
    private final int STEEL_BLOCK = 2;
    private final int ENTITY_TREE = 3;
    private final int EAGLE = 4;
    private final int WATER = 5;
    private final int SLIDING_TILE = 6;

    private final int spawn1X = 48;
    private final int spawn2X = 332;
    private final int spawn3X = 616;

    boolean initializeMap = true;
    private final Image brickA1;
    private final Image brickA2;
    private Image water;
    private final Image emptySpaces;
    int spawnTicks;
    int explosionTime = 0;
    boolean firstRunThrough = true;
    boolean stageStart = false;
    final String url = "src/tanks/";
    //private final Rectangle brickBounds;
    boolean flagIsDestroyed = false;
    boolean powerUpNextStage = false;
    ImageIcon i15;
    ImageIcon i16;
    ImageIcon i17;
    ImageIcon i19 = new ImageIcon(getClass().getResource("miniExplosion1.png"));
    ImageIcon i20 = new ImageIcon(getClass().getResource( "miniExplosion2.png"));
    ImageIcon i21 = new ImageIcon(getClass().getResource("miniExplosion3.png"));
    ImageIcon i22 = new ImageIcon(getClass().getResource("empty.png"));
    ImageIcon i23 = new ImageIcon(getClass().getResource("surrender.png"));
    ImageIcon i32 = new ImageIcon(getClass().getResource("stagescreen.png"));

    private Image explosion;
    private final Image surrender;
    private final Image slidingTile;
    private final Image gameOver;
    private final Image stageScreen = i32.getImage();
    private int spawn = 0;
    private int setExplosion = 0;
    PowerUp starPowerUp;
    PowerUp gunPowerUp;
    PowerUp lifePowerUp;
    PowerUp grenadePowerUp;
    PowerUp shovelPowerUp;
    PowerUp timerPowerUp;
    PowerUp hardHatPowerUp;
    boolean gameIsOver = false;
    boolean twoPlayerGame;

    Socket connectToServer;
    BufferedReader reader;
    PrintWriter writer;
    Thread readerThread;
    boolean playingOnline;
    int yourPlayerNumber;
    
    static final int KEYPRESSED = 0;
    static final int KEYRELEASED = 1;

    public Board(int howManyPlayers, boolean online) {
//        do {
//            tempInput = JOptionPane.showInputDialog("Enter Stage Number:");
//        } while (tempInput == null);
//        if (tempInput == null) {
//            System.exit(0);
//        }
//        input = Integer.parseInt(tempInput);
        if (howManyPlayers == 1) {
            twoPlayerGame = false;
        } else if (howManyPlayers == 2) {
            twoPlayerGame = true;
        }
        if(online == true){
            go();
        }

        maps = new Maps();

//        powerUps[0] = new StarPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[1] = new GunPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[2] = new LifePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[3] = new GrenadePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[4] = new ShovelPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[5] = new TimerPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[6] = new HardHatPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        powerUps[7] = new MarioPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
//        currentPowerUp = powerUps[7];
//        starPowerUp = powerUps[0];
//        gunPowerUp = powerUps[1];
//        lifePowerUp = powerUps[2];
//        grenadePowerUp = powerUps[3];
//        shovelPowerUp = powerUps[4];
//        timerPowerUp = powerUps[5];
//        hardHatPowerUp = powerUps[6];

        
        //initializeEnemies();
        player1 = new Player1(0, 0, this, online);
        if (twoPlayerGame == true) {
            player2 = new Player2(0, 0, this, online);
        }

        brick = new Bricks[12][13];
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon(getClass().getResource("background.png"));
        ImageIcon i2 = new ImageIcon(getClass().getResource( "boarder.png"));

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
        ImageIcon i25 = new ImageIcon(url + "four.png");
        ImageIcon i26 = new ImageIcon(url + "five.png");
        ImageIcon i27 = new ImageIcon(url + "six.png");
        ImageIcon i28 = new ImageIcon(url + "seven.png");
        ImageIcon i29 = new ImageIcon(url + "eight.png");
        ImageIcon i30 = new ImageIcon(url + "nine.png");

        ImageIcon i13 = new ImageIcon(url + "BrickA1.png");
        ImageIcon i14 = new ImageIcon(url + "BrickA2.png");

        i15 = new ImageIcon(url + "water1.png");
        i16 = new ImageIcon(url + "water2.png");
        i17 = new ImageIcon(url + "water3.png");
        ImageIcon i18 = new ImageIcon(url + "emptySpacesWhatAreWeLivingFor.png");
        ImageIcon i24 = new ImageIcon(url + "slidingtile.png");
        ImageIcon i31 = new ImageIcon(url + "gameover.png");

        gameOver = i31.getImage();
        slidingTile = i24.getImage();
        surrender = i23.getImage();
        brickA1 = i13.getImage();
        brickA2 = i14.getImage();
        water = i15.getImage();
        zero = i9.getImage();
        one = i10.getImage();
        two = i11.getImage();
        three = i12.getImage();
        four = i25.getImage();
        five = i26.getImage();
        six = i27.getImage();
        seven = i28.getImage();
        eight = i29.getImage();
        nine = i30.getImage();
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

        //startThread(online);

    }
    
    public void go(){
        board = this;
        startThread(true);
        System.out.println(readerThread.isAlive());
    }
    
    public void sendEnemyData(){
        int enemyID = 10;
        int eShoot = 0;
        for(Enemy e: enemies){
            if(e.isShooting()){
                eShoot = 1;
            }
            System.out.println("Sent enemy " +enemyID);
            writer.println(enemyID + "," + e.getX() + "," + e.getY() + "," + eShoot + "," + e.getDirection());
            writer.flush();
            enemyID++;
            eShoot = 0;
        }
    }
  
    
   
    public void startThread(boolean start) {

        setUpNetWorking();
        playingOnline = true;
        readerThread = new Thread(new IncomingReader());
        readerThread.start();

    }
    
    public void handleKeyPressed(int key, Player p){
        
        if (key == p.goUpInput) {
            p.moveUp();
        }
        else if (key == p.goDownInput) {
            p.moveDown();
        }
        else if (key == p.goLeftInput) {
            p.moveLeft();
        }
        else if (key == p.goRightInput) {
            p.moveRight();
        }
        else if(key == p.shoot){
            p.fire(p.getLevel());
            
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
            
        }
    }
    
    public void handleKeyReleased(int key, Player p){
        if (key == p.goUpInput || key == p.goDownInput || key == p.goLeftInput || key == p.goRightInput ) {
            p.stopMoving();
        }
        
    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String message;
            int playerNumber = 0;
            int xLocation = 0;
            int yLocation = 3;
            int shoot = 10;
            int direction = 0;
            try {

                while ((message = reader.readLine()) != null) {

                    System.out.println(message);
                    String[] result = reader.readLine().split(",");
                    if(result.length == 5){
                        System.out.println("length is 5");
                        playerNumber = Integer.parseInt(result[0]);
                        xLocation = Integer.parseInt(result[1]);
                        yLocation = Integer.parseInt(result[2]);
                        shoot = Integer.parseInt(result[3]);
                        direction = Integer.parseInt(result[4]);
                    }
                    
                    if (message.equalsIgnoreCase("you are player 1")) {
                        yourPlayerNumber = 1;
                        System.out.println("created player 1");
                    }
                    if (message.equalsIgnoreCase("you are player 2")) {
                        yourPlayerNumber = 2;
                        createPlayer();
                        System.out.println("created player 2");
                    }
                    
                    if(yourPlayerNumber == 1){
                        if(playerNumber == 2){
                            if(player2 == null){
                                createPlayer();
                            }
                            updatePlayer(player2, xLocation, yLocation, shoot, direction);
                        }
                    }
                    if(yourPlayerNumber == 2){
        
                        if(playerNumber == 1){
                            updatePlayer(player1, xLocation, yLocation, shoot, direction);
                        }
                        if(playerNumber > 9){
                           //recieveEnemies(playerNumber, xLocation, yLocation, shoot, direction); 
                        }
                        
                    }
                    
                }
            } catch (IOException | NumberFormatException ex) {
            }
        }
    }
    
    public void updatePlayer(Player p, int xLocation, int yLocation, int shoot, int direction){
        
        p.setX(xLocation);
        p.setY(yLocation);
        p.setDirection(direction);
        if (shoot == 1) {
            p.fire(p.getLevel());

        }
        if (direction == Player.UPWARDS_DIRECTION) {
            p.setDominantImageUp();
        }
        if (direction == Player.DOWNWARDS_DIRECTION) {
            p.setDominantImageDown();
        }
        if (direction == Player.LEFTWARDS_DIRECTION) {
            p.setDominantImageLeft();
        }
        if (direction == Player.RIGHTWARDS_DIRECTION) {
            p.setDominantImageRight();
        }
    }
    
    public void recieveEnemies(int en, int xLocation, int yLocation, int shoot, int direction){
        int e = en - 10;
        
        if (e == 0){
           if(enemy1 == null){
               enemy1 = new Enemy(0, 0, 1, this);
               enemies.add(enemy1);
           } 
           enemy1.setX(xLocation);
           enemy1.setY(yLocation);
           if(shoot == 1){
               enemy1.fire(e);
           }
           
           
           
        }
        
    }

    public void setUpNetWorking() {

        
        try {
            connectToServer = new Socket(iP, 3074);
            InputStreamReader streamReader = new InputStreamReader(connectToServer.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(connectToServer.getOutputStream());
            System.out.println("Connected to game");
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: disconnected from the server");
        }

    }

    public void createPlayer() {
        player2 = new Player2(0, 0, this, true);
        player2.setSpawning();
        twoPlayerGame = true;

    }

    public void initializeEnemies() {

        enemiesSpawned = 20;
        enemy1 = new Enemy(0, 0, 1, true, this);
        enemy2 = new Enemy(0, 0, 1, true, this);
        enemy3 = new Enemy(0, 0, 1, true, this);
        //enemy4 = new Enemy(0, 0, 1, this);
        //enemy5 = new Enemy(0, 0, 4, this);

        spawnEnemy(enemy1, 1);
        spawnEnemy(enemy2, 1);
        spawnEnemy(enemy3, 1);
        //spawnEnemy(enemy4, 1);
        //spawnEnemy(enemy5, 4);

        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        //enemies.add(enemy4);
        //enemies.add(enemy5);
//
//        for(Enemy en: enemies){
//            en.setVisible(true);
//        }

        enemiesDefeated = 0;
    }

    public void gameOver(Graphics g) {
        if (flagIsDestroyed) {
            eagle = i23.getImage();
        }
        // TODO check why this line  is so important
        g.drawImage(gameOver, (768 / 2) - 96, 720 / 2 - 96, null);
        player1.cantMove();
        if (twoPlayerGame == true) {
            player2.cantMove();
        }

        gameIsOver = true;

    }

    public void smallExplosion(Graphics g, int x, int y) {

        if (firstRunThrough == true) {
            explosionTime = 0;
            explosion = i19.getImage();
            firstRunThrough = false;
        } else if (explosionTime == 10) {
            explosion = i20.getImage();
        } else if (explosionTime == 20) {
            explosion = i21.getImage();
        } else if (explosionTime == 30) {
            explosion = i22.getImage();
            firstRunThrough = true;
            return;
        }
        g.drawImage(explosion, x, y, null);

        explosionTime++;

    }

    public void movingWater() {
        if (ticks % 300 == 0) {
            water = i17.getImage();
        } else if (ticks % 300 == 100) {
            water = i16.getImage();
        } else if (ticks % 300 == 200) {
            water = i15.getImage();
        }
    }

    public void map(Graphics g) throws IOException {
        if (initializeMap) {
            if (input >= 0 && input < 36) {

                currentMap = maps.getMapFromFile(input);
            } else if (input >= 36) {
                currentMap = maps.getMapFromFile(input % 35);
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
                            g.drawImage(emptySpaces, x * 24 + 48, i * 24 + 48 + 12, null);
                        } else if (y == 2) {
                            g.drawImage(emptySpaces, x * 24 + 48 + 12, i * 24 + 48, null);

                        } else if (y == 3) {
                            g.drawImage(emptySpaces, x * 24 + 48 + 12, i * 24 + 48 + 12, null);
                        }

                    }
                    if (newMap[i][x * 4 + y] == 1) {
                        if (y == 0) {
                            g.drawImage(brickA1, x * 24 + 48, i * 24 + 48, null);
                        } else if (y == 1) {
                            g.drawImage(brickA2, x * 24 + 48, i * 24 + 48 + 12, null);
                        } else if (y == 2) {
                            g.drawImage(brickA2, x * 24 + 48 + 12, i * 24 + 48, null);

                        } else if (y == 3) {
                            g.drawImage(brickA1, x * 24 + 48 + 12, i * 24 + 48 + 12, null);
                        }

                    }
                    if (newMap[i][x * 4 + y] == 2) {
                        g.drawImage(steel, x * 24 + 48, i * 24 + 48, null);
                    }

                    if (newMap[i][x * 4 + y] == 3) {
                        g.drawImage(trees, x * 24 + 48, i * 24 + 48, null);
                    }

                    if (newMap[i][x * 4 + y] == 4) {
                        g.drawImage(eagle, x * 24 + 48, i * 24 + 48, null);
                    }

                    if (newMap[i][x * 4 + y] == 5) {
                        g.drawImage(water, x * 24 + 48, i * 24 + 48, null);
                    }

                    if (newMap[i][x * 4 + y] == 6) {
                        g.drawImage(slidingTile, x * 24 + 48, i * 24 + 48, null);
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
                    player1.direction = 1;
                    playSound("PlayerDestroyed");
                    bullets.remove(m);

                }
                if (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 1) {
                    bullets.remove(m);

                    newMap[m.getY() / 26][(m.getX() * 4) / 26] = 0;
                    if ((((m.getX() * 4) / 26) + 1) < 96 && (m.getY() / 26 + 1) < 96) {

                        if (newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] == 1) {
                            newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] = 0;
                            newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26] = 0;
                            newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] = 0;
                        } else if (newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] == 1) {
                            newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] = 0;
                        }

                    }

                }
                if (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 2) {
                    bullets.remove(m);

                }
                if (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 4
                        || newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] == 4
                        || newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26] == 4
                        || newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] == 4) {

                    //Rectangle flagBounds = new Rectangle(m.getY() / 26, m.getX() / 26, eagle.getWidth(this), eagle.getHeight(this));
                    bullets.remove(m);
                    //JOptionPane.showMessageDialog(null, "You're fucked");
                    flagIsDestroyed = true;

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
                enemies.stream().map((e) -> {
                    if (m.getBounds().intersects(e.getBounds())) {

                        bullets.remove(m);
                        //smallExplosion(getGraphics(), e.getX(), e.getY());
                        if(e.getBlinking() == true && e.getVisible() == true){
                            System.out.println("spawning powerup");
                            spawnPowerUp();
                        }
                        e.setVisible(false);
                        
                        playSound("EnemyDestroyed");
                        enemiesDefeated++;
                        points = points + 100 * e.level;
                        if (points == 20000) {
                            p.gotALife();
                            playSound("GotLife");
                        }
                        //JOptionPane.showMessageDialog(null, enemiesDefeated + " enemies defeated");

                    }
                    return e;
                }).map((_item) -> {
                    if (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 1) {
                        bullets.remove(m);

                        newMap[m.getY() / 26][(m.getX() * 4) / 26] = 0;
                        if ((((m.getX() * 4) / 26) + 1) < 96 && (m.getY() / 26 + 1) < 96) {

                            if (newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] == 1) {
                                newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] = 0;
                                newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26] = 0;
                                newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] = 0;
                            } else if (newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] == 1) {
                                newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] = 0;
                            }

                        }

                    }
                    return _item;
                }).map((_item) -> {
                    if (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 2) {
                        bullets.remove(m);
                        if (p.getLevel() == 4) {
                            newMap[m.getY() / 26][(m.getX() * 4) / 26] = 0;
                            newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] = 0;
                            newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26] = 0;
                            newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] = 0;
                        } else {
                            miniEx = new MiniExplosion(m.getX(), m.getY());
                            miniExX = m.getX();
                            miniExY = m.getY();
                            getGraphics().drawImage(miniEx.getDomiantImage(), m.getX(), m.getY(), null);

                            playSound("BulletHitWall");
                        }
                    }
                    return _item;
                }).filter((_item) -> (newMap[m.getY() / 26][(m.getX() * 4) / 26] == 4
                        || newMap[m.getY() / 26][(m.getX() * 4) / 26 + 1] == 4
                        || newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26] == 4
                        || newMap[m.getY() / 26 + 1][(m.getX() * 4) / 26 + 1] == 4)).map((_item) -> {
                            //Rectangle flagBounds = new Rectangle(m.getY() / 26, m.getX() / 26, eagle.getWidth(this), eagle.getHeight(this));
                            bullets.remove(m);
                            return _item;
                        }).forEach((_item) -> {
                    //JOptionPane.showMessageDialog(null, "You're fucked");
                        flagIsDestroyed = true;
                });

            } else {
                bullets.remove(m);
                playSound("BulletHitWall");

            }
        }

    }

    public void refreshTank(Tank t, Graphics g2d) {
        g2d.drawImage(t.getDomiantImage(), t.getX(), t.getY(), null);
        ArrayList bullets = Enemy.getBullets();
        for (Object bullet : bullets) {
            Bullet m = (Bullet) bullet;
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
        for (Object bullet : bullets) {
            Bullet m = (Bullet) bullet;
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

    public void spawnEnemy(Enemy e, int level) {
        int x;
        if (spawn % 3 == 0) {
            e.spawnX = spawn1X;

        } else if (spawn % 3 == 1) {
            e.spawnX = spawn2X;

        } else {
            e.spawnX = spawn3X;

        }
        e = new Enemy(e.spawnX, e.spawnY, level, this);
        e.setVisible(true);

        enemiesSpawned--;
        spawn++;
    }

    public void startStage(Graphics g) {
        g.drawImage(stageScreen, 0, 0, null);
        paintLevelNumber(g, 768 / 2 + 50, 720 / 2 - 23, 768 / 2 + 74, input + 1);
        stageStart = true;
        if (stageTime == 500) {
            initializeMap = true;
            //initializeEnemies();
            input++;
            stageTime = 0;
            stageStart = false;
            playSound("themes");
            player1.setSpawning();
            if (twoPlayerGame == true) {
                player2.setSpawning();
            }

            spawnTicks = 0;
            System.out.println(points);
            powerUpNextStage = false;

        }

        stageTime++;
    }

    public void checkEnemy(Enemy e, Graphics g) {
        if (e != null) {
            if (e.getVisible() == false) {

                
                enemies.remove(e);
                

                smallExplosion(g, e.getX(), e.getY());
                if(setExplosion % 10 == 0){
                    
                    e.setX(1000);
                    e.setY(1000);
                }
                setExplosion++;

            }
        }
    }
    
    public void spawnPowerUp(){
        int go = 0;
        if (go == 0) {

            int random = rand.nextInt(7);
            powerUps[0] = new StarPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[1] = new GunPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[2] = new LifePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[3] = new GrenadePowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[4] = new ShovelPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[5] = new TimerPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[6] = new HardHatPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            powerUps[7] = new MarioPowerUp(rand.nextInt(768) - 50, rand.nextInt(720) - 50);
            currentPowerUp = powerUps[random];
            currentPowerUp.x = rand.nextInt(768) - 50;
            currentPowerUp.y = rand.nextInt(720) - 50;
            go++;
        }
        
        
    }

    public void spawnNewEnemies() {
        if (spawnTicks == 0) {
            enemy1 = new Enemy(0, 0, 1, true, this);
            spawnEnemy(enemy1, 1);
            enemies.add(enemy1);

            enemy2 = new Enemy(0, 0, 1, true, this);
            spawnEnemy(enemy2, 1);
            enemies.add(enemy2);

            enemy3 = new Enemy(0, 0, 1, true, this);
            spawnEnemy(enemy3, 1);
            enemies.add(enemy3);
        }

        if (spawnTicks == spawnRate) {
            enemy4 = new Enemy(0, 0, 1, this);
            spawnEnemy(enemy4, 1);
            enemies.add(enemy4);
        }
        if (spawnTicks == spawnRate * 2) {
            enemy5 = new Enemy(0, 0, 1, this);
            spawnEnemy(enemy5, 1);
            enemies.add(enemy5);
        }
        if (spawnTicks == spawnRate * 3) {
            enemy6 = new Enemy(0, 0, 2, this);
            spawnEnemy(enemy6, 2);
            enemies.add(enemy6);
        }
        if (spawnTicks == spawnRate * 4) {
            enemy7 = new Enemy(0, 0, 2, this);
            spawnEnemy(enemy7, 2);
            enemies.add(enemy7);
        }
        if (spawnTicks == spawnRate * 5) {
            enemy8 = new Enemy(0, 0, 2, this);
            spawnEnemy(enemy8, 2);
            enemies.add(enemy8);
        }
        if (spawnTicks == spawnRate * 6) {
            enemy9 = new Enemy(0, 0, 2, this);
            spawnEnemy(enemy9, 2);
            enemies.add(enemy9);
        }
        if (spawnTicks == spawnRate * 7) {
            enemy10 = new Enemy(0, 0, 2, this);
            spawnEnemy(enemy10, 2);
            enemies.add(enemy10);
        }
        if (spawnTicks == spawnRate * 8) {
            enemy11 = new Enemy(0, 0, 3, this);
            spawnEnemy(enemy11, 3);
            enemies.add(enemy11);
        }
        if (spawnTicks == spawnRate * 9) {
            enemy12 = new Enemy(0, 0, 3, this);
            spawnEnemy(enemy12, 3);
            enemies.add(enemy12);
        }
        if (spawnTicks == spawnRate * 10) {
            enemy13 = new Enemy(0, 0, 3, this);
            spawnEnemy(enemy13, 3);
            enemies.add(enemy13);
        }
        if (spawnTicks == spawnRate * 11) {
            enemy14 = new Enemy(0, 0, 3, this);
            spawnEnemy(enemy14, 3);
            enemies.add(enemy14);
        }
        if (spawnTicks == spawnRate * 12) {
            enemy15 = new Enemy(0, 0, 3, this);
            spawnEnemy(enemy15, 3);
            enemies.add(enemy15);
        }
        if (spawnTicks == spawnRate * 13) {
            enemy16 = new Enemy(0, 0, 4, this);
            spawnEnemy(enemy16, 4);
            enemies.add(enemy16);
        }
        if (spawnTicks == spawnRate * 14) {
            enemy17 = new Enemy(0, 0, 4, this);
            spawnEnemy(enemy17, 4);
            enemies.add(enemy17);
        }
        if (spawnTicks == spawnRate * 15) {
            enemy18 = new Enemy(0, 0, 4, this);
            spawnEnemy(enemy18, 4);
            enemies.add(enemy18);
        }
        if (spawnTicks == spawnRate * 16) {
            enemy19 = new Enemy(0, 0, 4, this);
            spawnEnemy(enemy19, 4);
            enemies.add(enemy19);
        }
        if (spawnTicks == spawnRate * 17) {
            enemy20 = new Enemy(0, 0, 4, this);
            spawnEnemy(enemy20, 4);
            enemies.add(enemy20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g = getGraphics();

        if (stageStart == false) {

            spawnNewEnemies();
            spawnTicks++;

            checkEnemy(enemy1, g);
            checkEnemy(enemy2, g);
            checkEnemy(enemy3, g);
            checkEnemy(enemy4, g);
            checkEnemy(enemy5, g);
            checkEnemy(enemy6, g);
            checkEnemy(enemy7, g);
            checkEnemy(enemy8, g);
            checkEnemy(enemy9, g);
            checkEnemy(enemy10, g);
            checkEnemy(enemy11, g);
            checkEnemy(enemy12, g);
            checkEnemy(enemy13, g);
            checkEnemy(enemy14, g);
            checkEnemy(enemy15, g);
            checkEnemy(enemy16, g);
            checkEnemy(enemy17, g);
            checkEnemy(enemy18, g);
            checkEnemy(enemy19, g);
            checkEnemy(enemy20, g);

            enemies.stream().filter((en) -> (en.getVisible() == true)).map((en) -> {
                moveBullets(en);
                return en;
            }).map((en) -> {
                en.moveX();
                return en;
            }).forEach((en) -> {
                en.moveY();
            });
            checkCollisions(player1);
            moveBullets(player1);
            if (twoPlayerGame == true) {
                checkCollisions(player2);
                moveBullets(player2);
            }

            if (gameIsOver == false) {
                if (player1.getVisible() == true || !flagIsDestroyed) {
                    player1.moveX();
                    player1.moveY();
                }
                if (twoPlayerGame == true) {
                    if (player2.getVisible() == true || !flagIsDestroyed) {
                        player2.moveX();
                        player2.moveY();
                    }
                }
            }

            //map();
            repaint();

            if (flagIsDestroyed || (player1.getVisible() == false && player2.getVisible() == false)) {
                gameOver(g);
            }
        }
        if ((enemy1 != null
                && enemy2 != null
                && enemy3 != null
                && enemy4 != null
                && enemy5 != null
                && enemy6 != null
                && enemy7 != null
                && enemy8 != null
                && enemy9 != null
                && enemy10 != null
                && enemy11 != null
                && enemy12 != null
                && enemy13 != null
                && enemy14 != null
                && enemy15 != null
                && enemy16 != null
                && enemy17 != null
                && enemy18 != null
                && enemy19 != null
                && enemy20 != null) || powerUpNextStage == true) {
            if ((enemy1.getVisible() == false
                    && enemy2.getVisible() == false
                    && enemy3.getVisible() == false
                    && enemy4.getVisible() == false
                    && enemy5.getVisible() == false
                    && enemy6.getVisible() == false
                    && enemy7.getVisible() == false
                    && enemy8.getVisible() == false
                    && enemy9.getVisible() == false
                    && enemy10.getVisible() == false
                    && enemy11.getVisible() == false
                    && enemy12.getVisible() == false
                    && enemy13.getVisible() == false
                    && enemy14.getVisible() == false
                    && enemy15.getVisible() == false
                    && enemy16.getVisible() == false
                    && enemy17.getVisible() == false
                    && enemy18.getVisible() == false
                    && enemy19.getVisible() == false
                    && enemy20.getVisible() == false)) {
                //JOptionPane.showMessageDialog(null, "You've destroyed all enemies! You win!");
                //System.exit(0);
                stageStart = true;
                startStage(g);

            }
        }

    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paint(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(background, 0, 0, null);
            g2d.drawImage(boarder, 0, 0, null);
            map(g2d);

            //paintBricks(brick, g2d);
            refreshTank(player1, g2d);
            paintLives(player1, g2d);

            if (twoPlayerGame == true) {
                refreshTank(player2, g2d);
                paintLives(player2, g2d);

            }
            enemies.stream().forEach((en) -> {
                refreshTank(en, g2d);
            });
            movingWater();
            if(currentPowerUp != null){
                
                g2d.drawImage(currentPowerUp.getDomiantImage(), currentPowerUp.getX(), currentPowerUp.getY(), null);
            
            }
            paintLevelNumber(g2d, (768 - 72), (24 * 18 + 168), (768 - 48), input);

            for (int i = 0; i < enemiesSpawned; i++) {
                if (i % 2 == 0) {
                    g2d.drawImage(enemyIcon, 768 - 72, ((i * 24) + (3 * 24)) - (i * 12), null);
                } else {
                    g2d.drawImage(enemyIcon, 768 - 48, ((i * 24) + (3 * 24)) - ((i + 1) * 12), null);
                }
            }
            for (int i = 19; i >= enemiesSpawned; i--) {
                if (i % 2 == 0) {
                    g2d.drawImage(blankGrey, 768 - 72, ((i * 24) + (3 * 24)) - (i * 12), null);
                } else {
                    g2d.drawImage(blankGrey, 768 - 48, ((i * 24) + (3 * 24)) - ((i + 1) * 12), null);
                }
            }

//            if(miniEx != null){
//                g2d.drawImage(miniEx.getDomiantImage(), miniExX, miniExY, null);
//            }
            if (!time.isRunning()) {
                time.start();
//                go();
            }
            if(yourPlayerNumber == 1){
                //sendEnemyData();
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
        } else if (lives == 1) {
            g.drawImage(one, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 2) {
            g.drawImage(two, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 3) {
            g.drawImage(three, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 4) {
            g.drawImage(four, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 5) {
            g.drawImage(five, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 6) {
            g.drawImage(six, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 7) {
            g.drawImage(seven, 768 - 48, 24 * 18 + y, null);
        } else if (lives == 8) {
            g.drawImage(eight, 768 - 48, 24 * 18 + y, null);
        } else {
            g.drawImage(nine, 768 - 48, 24 * 18 + y, null);
        }

    }

    public void paintLevelNumber(Graphics g, int x, int y, int x2, int input) {
        if (input < 10) {
            g.drawImage(blankGrey, x, y, null);
        }
        if (input / 10 == 1) {
            g.drawImage(one, x, y, null);
        } else if (input / 10 == 2) {
            g.drawImage(two, x, y, null);
        } else if (input / 10 == 3) {
            g.drawImage(three, x, y, null);
        } else if (input / 10 == 4) {
            g.drawImage(four, x, y, null);
        } else if (input / 10 == 5) {
            g.drawImage(five, x, y, null);
        } else if (input / 10 == 6) {
            g.drawImage(six, x, y, null);
        } else if (input / 10 == 7) {
            g.drawImage(seven, x, y, null);
        } else if (input / 10 == 8) {
            g.drawImage(eight, x, y, null);
        } else if (input / 10 == 9) {
            g.drawImage(nine, x, y, null);
        } else {
            g.drawImage(zero, x, y, null);
        }
        if (input % 10 == 0) {
            g.drawImage(zero, x2, y, null);

        }
        if (input % 10 == 1) {
            g.drawImage(one, x2, y, null);
        }
        if (input % 10 == 2) {
            g.drawImage(two, x2, y, null);
        }
        if (input % 10 == 3) {
            g.drawImage(three, x2, y, null);
        }
        if (input % 10 == 4) {
            g.drawImage(four, x2, y, null);
        }
        if (input % 10 == 5) {
            g.drawImage(five, x2, y, null);
        }
        if (input % 10 == 6) {
            g.drawImage(six, x2, y, null);
        }
        if (input % 10 == 7) {
            g.drawImage(seven, x2, y, null);
        }
        if (input % 10 == 8) {
            g.drawImage(eight, x2, y, null);
        }
        if (input % 10 == 9) {
            g.drawImage(nine, x2, y, null);
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
        int gridX = ((x * 4) / 26);
        int gridY = (y / 26);

        int entity = newMap[gridY][gridX];

        return entity == BLANK_SPACE || entity == ENTITY_TREE || entity == SLIDING_TILE;

    }

    private class AL extends KeyAdapter {

        int p1Shoot;
        int p2Shoot;

        @Override
        public void keyReleased(KeyEvent e) {

            if(playingOnline == true){
               if (yourPlayerNumber == 1) {
                player1.keyReleased(e);
            } else if (yourPlayerNumber == 2 && player2 != null) {
                player2.keyReleased(e);
            }
            if (twoPlayerGame == true) {
                player2.keyReleased(e);
            }

            keyReleased = e.getKeyCode(); 
            }
            else {
                player1.keyReleased(e);
                if (twoPlayerGame == true) {
                    player2.keyReleased(e);
                }
            }
            
            
//                writer.println(yourPlayerNumber +"," + player1.getX() + "," + player1.getY());
//                writer.flush();

        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressed = e.getKeyCode();
            p1Shoot = 0;
            p2Shoot = 0;
            if (playingOnline == true) {
                if (yourPlayerNumber == 1) {
                    player1.keyPressed(e);
                    if (keyPressed == player1.shoot) {
                        p1Shoot = 1;
                    }
                    writer.println(yourPlayerNumber + "," + player1.getX() + "," + player1.getY() + "," + p1Shoot + "," + player1.getDirection());
                    writer.flush();
                } else if (yourPlayerNumber == 2 && player2 != null) {
                    player2.keyPressed(e);
                    if (keyPressed == player2.shoot) {
                        p2Shoot = 1;
                    }
                    System.out.println("my name is player 2 and I'm sending you my data");
                    writer.println(yourPlayerNumber + "," + player2.getX() + "," + player2.getY() + "," + p2Shoot + "," + player2.getDirection());
                    writer.flush();

                }
                writer.println(yourPlayerNumber + "," + player1.getX() + "," + player1.getY() + "," + p1Shoot + "," + player1.getDirection());
            writer.flush();
            }
            else {
                player1.keyPressed(e);
                if (twoPlayerGame == true) {
                    
                    player2.keyPressed(e);
                }
            }

            
            

        }

    }

    public void checkCollisions(Player p) {
        Graphics g = getGraphics();
        Rectangle player = p.getBounds();
        Rectangle p1 = player1.getBounds();
        if (twoPlayerGame == true) {
            Rectangle p2 = player2.getBounds();
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

        }
////        Rectangle starPower = starPowerUp.getBounds();
////        Rectangle gunPower = gunPowerUp.getBounds();
////        Rectangle lifePower = lifePowerUp.getBounds();
////        Rectangle grenadePower = grenadePowerUp.getBounds();
////        Rectangle shovelPower = shovelPowerUp.getBounds();
////        Rectangle timerPower = timerPowerUp.getBounds();
//        Rectangle hardHatPower = hardHatPowerUp.getBounds();
        Rectangle powerUp;
        if (currentPowerUp != null) {
            powerUp = currentPowerUp.getBounds();
            if (player.intersects(powerUp) && currentPowerUp.getVisible() == true) {
                doPowerUpThing(currentPowerUp, p, g);

            }
        }
        

        enemies.stream().forEach((en) -> {
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
        });//        if (player.intersects(starPower) && starPowerUp.getVisible() == true) {
//            doPowerUpThing(starPowerUp, p, g);
//        }
//        if (player.intersects(gunPower) && gunPowerUp.getVisible() == true) {
//            doPowerUpThing(gunPowerUp, p, g);
//        }
//        if (player.intersects(lifePower) && lifePowerUp.getVisible() == true) {
//            doPowerUpThing(lifePowerUp, p, g);
//        }
//        if (player.intersects(grenadePower) && grenadePowerUp.getVisible() == true) {
//            doPowerUpThing(grenadePowerUp, p, g);
//        }
//        if (player.intersects(shovelPower) && shovelPowerUp.getVisible() == true) {
//            doPowerUpThing(shovelPowerUp, p, g);
//        }
//        if (player.intersects(timerPower) && timerPowerUp.getVisible() == true) {
//            doPowerUpThing(timerPowerUp, p, g);
//        }
//        if (player.intersects(hardHatPower) && hardHatPowerUp.getVisible() == true) {
//            doPowerUpThing(hardHatPowerUp, p, g);
//        }

    }

    public void playSound(String s) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url + s + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error with playing sound.");
        }
    }

    public void doPowerUpMarioThing() {
        enemy1 = new Enemy(0, 0, 1, this);
        enemy2 = new Enemy(0, 0, 1, this);
        enemy3 = new Enemy(0, 0, 1, this);
        enemy4 = new Enemy(0, 0, 1, this);
        enemy5 = new Enemy(0, 0, 1, this);
        enemy6 = new Enemy(0, 0, 1, this);
        enemy7 = new Enemy(0, 0, 1, this);
        enemy8 = new Enemy(0, 0, 1, this);
        enemy9 = new Enemy(0, 0, 1, this);
        enemy10 = new Enemy(0, 0, 1, this);
        enemy11 = new Enemy(0, 0, 1, this);
        enemy12 = new Enemy(0, 0, 1, this);
        enemy13 = new Enemy(0, 0, 1, this);
        enemy14 = new Enemy(0, 0, 1, this);
        enemy15 = new Enemy(0, 0, 1, this);
        enemy16 = new Enemy(0, 0, 1, this);
        enemy17 = new Enemy(0, 0, 1, this);
        enemy18 = new Enemy(0, 0, 1, this);
        enemy19 = new Enemy(0, 0, 1, this);
        enemy20 = new Enemy(0, 0, 1, this);
        enemy1.setVisible(false);
        enemy2.setVisible(false);
        enemy3.setVisible(false);
        enemy4.setVisible(false);
        enemy5.setVisible(false);
        enemy6.setVisible(false);
        enemy7.setVisible(false);
        enemy8.setVisible(false);
        enemy9.setVisible(false);
        enemy10.setVisible(false);
        enemy11.setVisible(false);
        enemy12.setVisible(false);
        enemy13.setVisible(false);
        enemy14.setVisible(false);
        enemy15.setVisible(false);
        enemy16.setVisible(false);
        enemy17.setVisible(false);
        enemy18.setVisible(false);
        enemy19.setVisible(false);
        enemy20.setVisible(false);
        

    }

    public void doPowerUpThing(PowerUp pow, Player p, Graphics g) {
        pow.setVisible(false);
        if (pow == powerUps[4]) {
            pow.doPowerUpThing(p, enemy1, steel, newMap, g);
        } else if (pow == powerUps[7]) {
            doPowerUpMarioThing();

        }
        else if(pow == powerUps[5]){
            
            enemies.stream().forEach((e) -> {
                pow.doPowerUpThing(e);
            });
            
            
            
        }
        else {
            enemies.stream().forEach((en) -> {
                pow.doPowerUpThing(p, en);
            });

        }
        pow.setEmptyImage();

        pow.playPowerUpSound();
    }

}
