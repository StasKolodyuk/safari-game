
package by.bsu.kolodyuk.safari.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static ClientThread[] clientThreads;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static int portNumber;
    private static final int maxUserCount = 10;
    private static GameVariables gameVariables;
    
    public static void main(String[] args) {

        try{
            if(args.length < 1)
                portNumber = 8888;
            else
                portNumber = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(portNumber);
            clientThreads = new ClientThread[maxUserCount];
            gameVariables = new GameVariables();
            System.out.println("Server is waiting for clients...");
            while(true)
            {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                int i;
                for(i = 0; i < clientThreads.length; i++)
                    if(clientThreads[i] == null) {
                        (clientThreads[i] = new ClientThread(clientSocket, clientThreads, gameVariables)).start();
                        break;
                    }
                if(i == maxUserCount){
                    clientSocket.close();
                }
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("Cannot start server on this port !!!");
        }
        catch(IOException e){
            System.out.println("Something wrong with IO !!!");
            e.printStackTrace();
        }
    }
}