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


public class Frame extends JPanel implements ActionListener{
    private Container c;
    private JFrame menuScreen;
    private Timer timer;
    boolean twoPlayerGame = false;
    int ticks = 0;
        String url = "src/tanks/";
        ImageIcon titlecon = new ImageIcon(url + "Title.png");
        ImageIcon selectIcon = new ImageIcon(url +"Select.png");
        ImageIcon selectIcon2 = new ImageIcon(url +"Select2.png");
        ImageIcon emptycon = new ImageIcon(url + "emptySelect.png");
    
        Image title = titlecon.getImage();
        Image select = selectIcon.getImage();
        Image empty = emptycon.getImage();
        int selectX = 200;
        int selectY = 385;
        Board b;
        JFrame frame;
        
    
    public Frame(){
        this.setLayout(null);
        timer = new Timer(5,this);
        timer.start();
        this.setPreferredSize(new Dimension(768,720));
        addKeyListener(new Frame.AL());
        setFocusable(true);
        setMenuScreen();
        
    }
    
    public void paintComponent(Graphics g){
        
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(title, 0, 0, null);
        g2d.drawImage(select, selectX, selectY, null);
    }
    
    public static void main(String[] args) {
        new Frame();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ticks++;
        //JOptionPane.showMessageDialog(null, "c");
        if(ticks % 2 == 0){
            //select = selectIcon.getImage();
        }
        else if(ticks % 2 == 1){
            select = selectIcon2.getImage();
        }
        
        if(b != null){
            if(b.gameIsOver == true){
                b.gameIsOver = false;
                setMenuScreen();
                
            }
        }
        
        
    }

    private class AL extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            
            
            
        }

        public void keyPressed(KeyEvent e) {

            int k = e.getKeyCode();
            if(k == KeyEvent.VK_ESCAPE){
               System.exit(0);
            }
            if(k == KeyEvent.VK_UP){
                if(selectY > 385){
                   selectY = selectY - 48; 
                }
                
                getGraphics().drawImage(empty, selectX, selectY + 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);
                
                
            }
            if(k == KeyEvent.VK_DOWN){
                if(selectY < 481){
                    selectY = selectY + 48;
                }
                
                getGraphics().drawImage(empty, selectX, selectY - 48, null);
                getGraphics().drawImage(select, selectX, selectY, null);
            }
            if(k == KeyEvent.VK_ENTER && selectY == 433){
                //JOptionPane.showMessageDialog(null, "Starting 2P game");
                setBoard(2);
            }
            else if(k == KeyEvent.VK_ENTER && selectY == 385){
                //JOptionPane.showMessageDialog(null, "Starting 1P game");
                setBoard(1);
            }
            else if(k == KeyEvent.VK_ENTER && selectY == 481) {
                JOptionPane.showMessageDialog(null, "Construction is not yet supported!");
            }
        }
    }
    public void setMenuScreen(){
        if(frame != null){
            frame.dispose();
        }
        menuScreen = new JFrame();
        menuScreen.setUndecorated(true);
        menuScreen.setLocation(300,0);
        c = menuScreen.getContentPane();
        c.add(this);
        menuScreen.pack();
        menuScreen.setVisible(true);
        
        
    }
    public void setBoard(int players){
        menuScreen.dispose();
            frame = new JFrame();
            frame.setUndecorated(true);
            b = new Board(players);
            frame.add(b);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(768,720);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); 
    }

}

//public class Frame{
//
//    
//    
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setUndecorated(true);
//        frame.add(new Board());
//        
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(768,720);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        
//        
//        
//    }
//    public void createGUI(){
//        
//        
//    }
//    
//}
