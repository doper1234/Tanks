/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Chris
 */

public class TitleScreen extends JPanel implements ActionListener{
    boolean twoPlayerGame = false;
    String url = "src/tanks/";
    ImageIcon titlecon = new ImageIcon(url + "Title.png");
    ImageIcon selecticon = new ImageIcon(url +"Select.png");
    ImageIcon emptycon = new ImageIcon(url + "emptySelect.png");
    
    Image title = titlecon.getImage();
    Image select = selecticon.getImage();
    Image empty = emptycon.getImage();
    
    int selectX = 200;
    int selectY = 385;
    public TitleScreen() {
        
        addKeyListener(new AL());
        setFocusable(true);
        
    }


//    public void startStage(Graphics g) {
//        g.drawImage(stageScreen, 0, 0, null);
//        paintLevelNumber(g, 768 / 2 + 50, 720 / 2 - 23, 768 / 2 + 74, input + 1);
//        stageStart = true;
//        if (stageTime == 500) {
//            initializeMap = true;
//            initializeEnemies();
//            input++;
//            stageTime = 0;
//            stageStart = false;
//            playSound("themes");
//            player1.setSpawning();
//            player2.setSpawning();
//            spawnTicks = 0;
//            powerUpNextStage = false;
//        }
//
//        stageTime++;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g = getGraphics();
    }

    @Override
    public void paint(Graphics g) {
            super.paint(g);

               
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(title, 0, 0, null);
            g2d.drawImage(select, selectX, selectY, null);
    }


    private class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            
            
            
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int k = e.getKeyCode();
            if(k == KeyEvent.VK_ESCAPE){
               System.exit(0);
            }
            if(k == KeyEvent.VK_UP){
                selectY = 385;
                getGraphics().drawImage(empty, selectX, selectY + 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);
                
                
            }
            if(k == KeyEvent.VK_DOWN){
                selectY = 433;
                getGraphics().drawImage(empty, selectX, selectY - 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);
            }
            if(k == KeyEvent.VK_ENTER && selectY == 433){
                twoPlayerGame = true;
            }
        }
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
}