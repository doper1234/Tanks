/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Frame extends JPanel implements ActionListener {

    //static final String iP = "192.168.1.102";
    static final String iP = "127.0.0.1";
    private Container c;
    private JFrame menuScreen;
    private final Timer timer;
    boolean twoPlayerGame = false;
    boolean playingOnline = false;
    Socket connectToServer;
    int ticks = 0;
    String url = "src/tanks/";
    ImageIcon titlecon = new ImageIcon(getClass().getResource("Title.png"));
    ImageIcon selectIcon = new ImageIcon(getClass().getResource("Select.png"));
    ImageIcon selectIcon2 = new ImageIcon(getClass().getResource("Select2.png"));
    ImageIcon emptycon = new ImageIcon(getClass().getResource("emptySelect.png"));

    Image title = titlecon.getImage();
    Image select = selectIcon.getImage();
    Image empty = emptycon.getImage();
    int selectX = 200;
    int selectY = 385;
    Board b;
    JFrame frame;
    
    Thread readerThread;
    BufferedReader reader;
    PrintWriter writer;
    
    public Frame() {
        JOptionPane.showMessageDialog(null, 
                "Controls: " + "\n" + 
                "Player 1 uses the arrow keys to move and enter to shoot" + "\n" +
                "unless player 1 is playing online, then space is shoot" + "\n" + 
                "\n" +
                "Player 2 uses awds to move and space bar to shoot." + "\n" +
                "Press escape to quit game at any time." + "\n" +
                "Press ok when ready to go to the main menu");
        timer = new Timer(5, this);
    }
    
    public void go(){
        
        this.setLayout(null);
        timer.start();
        this.setPreferredSize(new Dimension(768, 720));
        addKeyListener(new Frame.AL());
        setFocusable(true);
        setMenuScreen();
        //setUpNetWorking();
        readerThread = new Thread(new IncomingReader());
        readerThread.start();
        
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(title, 0, 0, null);
        g2d.drawImage(select, selectX, selectY, null);
    }

    public static void main(String[] args) {
        new Frame().go();
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ticks++;
        //JOptionPane.showMessageDialog(null, "c");
        if (ticks % 2 == 0) {
            //select = selectIcon.getImage();
        } else if (ticks % 2 == 1) {
            select = selectIcon2.getImage();
        }

        if (b != null) {
            if (b.gameIsOver == true) {
                b.gameIsOver = false;
                setMenuScreen();

            }
        }

    }

    private class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            int k = e.getKeyCode();
            if(writer != null){
            
                writer.println(k);
            }
            
            if (k == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
            if (k == KeyEvent.VK_UP) {
                if (selectY > 385) {
                    selectY = selectY - 48;
                }

                getGraphics().drawImage(empty, selectX, selectY + 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);

            }
            if (k == KeyEvent.VK_DOWN) {
                if (selectY < 529) {
                    selectY = selectY + 48;
                }

                getGraphics().drawImage(empty, selectX, selectY - 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);
            }
            if (k == KeyEvent.VK_ENTER && selectY == 433) {
                setBoard(2);
            } else if (k == KeyEvent.VK_ENTER && selectY == 385) {
                setBoard(1);
            } else if (k == KeyEvent.VK_ENTER && selectY == 481) {
                JOptionPane.showMessageDialog(null, "Construction is not yet supported!");
            } else if (k == KeyEvent.VK_ENTER && selectY == 529) {
               setUpNetWorking();
                
            }
            
        }
    }

    public void setMenuScreen() {
        if (frame != null) {
            frame.dispose();
        }
        menuScreen = new JFrame();
        menuScreen.setUndecorated(true);
        //menuScreen.setUndecorated(true);
        menuScreen.setLocation(300, 0);
        c = menuScreen.getContentPane();
        c.add(this);
        menuScreen.pack();
        menuScreen.setVisible(true);

    }

    public void setBoard(int players) {
        menuScreen.dispose();
        frame = new JFrame();
        //frame.setUndecorated(true);
        b = new Board(players, playingOnline);
        frame.add(b);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768, 720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    
    public void startThread(boolean start) {

        setUpNetWorking();
        

    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String message;
            int player1;
            int keyPressed1;
            int keyReleased1;
            int pressedOrReleased;
            int player2 = 0;
            int x2 = -100;
            int y2 = -100;
            int p1 = 1;
            int p2 = 2;
            int playerNumber = 0;
            try {

                if (reader != null) {
                    while ((message = reader.readLine()) != null) {

                        
                        System.out.println(readerThread.isAlive() + " after reader");
                        
                        String[] result = reader.readLine().split(",");
                        player1 = Integer.parseInt(result[0]);
                        
                        if (result.length > 3) {

                            player2 = Integer.parseInt(result[3]);
                            x2 = Integer.parseInt(result[4]);
                            y2 = Integer.parseInt(result[5]);

                        }

                        playerNumber = Integer.parseInt(result[0]);
                        pressedOrReleased = Integer.parseInt(result[2]);
                    
                    if (pressedOrReleased == Board.KEYPRESSED) {
                        
                        keyPressed1 = Integer.parseInt(result[1]);
                        
                        System.out.println("Player " + playerNumber + " pressed " + keyPressed1);
                    } 
                    if (pressedOrReleased == Board.KEYRELEASED) {
                        
                        keyReleased1 = Integer.parseInt(result[1]);
                        System.out.println("Player " + playerNumber + " released " + keyReleased1);
                    }
                        System.out.println(message);
                        if (player1 == 1) {

//                        if (p1 == null) {
//                            p1 = new Player(100, 100, 1);
//                        }
//                        p1.setX(x1);
//                        p1.setY(y1);
                        }
                        if (message.equalsIgnoreCase("I am player 1")) {
                            playerNumber = 1;
                            //p1 = new Player(100, 100, playerNumber);
                        }

                        if (player2 == 2) {
//                        if (p2 == null) {
//                            p2 = new Player(100, 100, 2);
//                        }
//                        p2.setX(x2);
//                        p2.setY(y2);

                        }
                        if (message.equalsIgnoreCase("I am player 2")) {
                            playerNumber = 2;
                            //p2 = new Player(100, 100, playerNumber);

                        }

                    }
                }
                
            } catch (IOException | NumberFormatException ex) {
                System.out.println(readerThread.isAlive() + "failed");
            }
        }
    }

    public void setUpNetWorking() {

        
        try {
            connectToServer = new Socket(iP, 3074);
            InputStreamReader streamReader = new InputStreamReader(connectToServer.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(connectToServer.getOutputStream());
            System.out.println("Networking established");
            playingOnline = true;
            setBoard(1);
            System.out.println("New Game Created");
            
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: Could not connect to server");
            
        }
    }
}