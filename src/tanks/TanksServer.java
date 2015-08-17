/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import com.sun.glass.events.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class TanksServer {

    ArrayList connectedPlayer;
    Board b;
    ObjectOutputStream objOutStream;
    OutputStream outStream;
    
    
    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        @Override
        public void run() {
            String message;
            int keyPressed;
            try {
                while ((message = reader.readLine()) != null) {
                    keyPressed = Integer.parseInt(message);
                    if (keyPressed == KeyEvent.VK_ENTER) {
                        JOptionPane.showMessageDialog(null, "Enter was pressed");
                    }
                    System.out.println("read " + keyPressed);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new TanksServer().go();

    }

    public void go() {
        connectedPlayer = new ArrayList();
        Object obj;
        try {
            ServerSocket serverSock = new ServerSocket(3074);

            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                connectedPlayer.add(writer);

                Thread t = new Thread(new ClientHandler((clientSocket)));
                t.start();
                System.out.println("got a connection from " + clientSocket.getInetAddress());
                System.out.println("Game created. Player 1 entered");
                writer.println("you are player " + connectedPlayer.size());
                
//                try {
//                    outStream = clientSocket.getOutputStream();
//                    objOutStream = new ObjectOutputStream(outStream);
//                    objOutStream.writeObject(b);
//                    System.out.println("giving a board to");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    System.out.println("Couldn't get board");
//                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Board getBoard() {
        b.createPlayer();
        return b;
    }
}
