/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.io.*;
import java.net.*;
import java.util.*;

public class TanksServer {

    ArrayList connectedPlayerStream;
    Board b;
    ObjectOutputStream objOutStream;
    OutputStream outStream;
    ArrayList players;
    int keySend;
    int pressedOrReleasedSend;
    int p1;
    int p1X;
    int p1Y;
    int p1Shoot;
    int p1Direction;

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);

            } catch (Exception ex) {
            }

        }

        @Override
        public void run() {
            String message;
            int keyPressed = 0;
            int keyReleased = 0;
            int pressedOrReleased;
            boolean pressed = false;
            boolean released = false;
            int playerNumber = 0;
            p1 = 0;
            p1X = 0;
            p1Y = 0;
            p1Shoot = 10;
            p1Direction = 0;
            try {
                while ((message = reader.readLine()) != null) {
                    String result[] = message.split(",");
                    System.out.println(message);
                        p1 = Integer.parseInt(result[0]);
                        p1X = Integer.parseInt(result[1]);
                        p1Y = Integer.parseInt(result[2]);
                        p1Shoot = Integer.parseInt(result[3]);
                        p1Direction = Integer.parseInt(result[4]);
                    sendOutPlayerLocations();

                }
            } catch (IOException | NumberFormatException ex) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new TanksServer().go();

    }

    public void go() {
        connectedPlayerStream = new ArrayList();
        players = new ArrayList();
        int[] player = new int[3];
        int frameSkipConnection = 0;
        Object obj;
        try {
            ServerSocket serverSock = new ServerSocket(3074);

            while (true) {
                Socket clientSocket = serverSock.accept();

                if (frameSkipConnection % 2 != 0) {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                    connectedPlayerStream.add(writer);

                    if (players.isEmpty()) {
                        player[0] = 1;
                        player[1] = 100;
                        player[2] = 100;
                        players.add(player);

                    } else if (players.size() == 1) {

                        player[0] = 2;
                        player[1] = 100;
                        player[2] = 100;
                        players.add(player);

                    } 
                    
                    else if (players.size() == 2) {

                        player[0] = 3;
                        player[1] = 100;
                        player[2] = 100;
                        players.add(player);

                    }
                    
                    else if (players.size() == 3) {

                        player[0] = 4;
                        player[1] = 100;
                        player[2] = 100;
                        players.add(player);

                    }
                    

                    System.out.println("got a connection from " + clientSocket.getInetAddress());
                    System.out.println("Game created. Player " + players.size() + " entered");
                    writer.println("you are player " + connectedPlayerStream.size());
                    writer.flush();
                }
                frameSkipConnection++;
                Thread t = new Thread(new ClientHandler((clientSocket)));
                t.start();

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
        }
    }

    public Board getBoard() {
        b.createPlayer();
        return b;
    }
    
    public void sendOutPlayerLocations() {

        Iterator it = connectedPlayerStream.iterator();

        for (Object connectedPlayerStream1 : connectedPlayerStream) {
            try {
                PrintWriter writer = (PrintWriter) connectedPlayerStream1;
                writer.println(p1 + "," + p1X + "," + p1Y + "," + p1Shoot + "," + p1Direction);
                writer.flush();
//                for (int i = 0; i < players.size(); i++) {
//                    Player p = (Player) players.get(i);
//
//                    writer.println((i + 1) + "," + p.getX() + "," + p.getY());
//                    writer.flush();
//                    System.out.println((i + 1) + "," + p.getX() + "," + p.getY());
//
//                }
//                writer.println(p1.getX() + "," + p1.getY());
//                writer.flush();
            }catch (Exception ex) {
            }
        }
    }
}
