
package by.bsu.kolodyuk.safari.server;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.Timer;

public class ClientThread extends Thread {

    private  ObjectInputStream socketInput;
    private  ObjectOutputStream socketOutput;
    private  ClientThread[] threads;
    private  Socket clientSocket;
    private  BowMan bowMan;
    private  GameVariables gameVariables;
    private  Timer timer;

    public ClientThread(Socket clientSocket, ClientThread[] threads, GameVariables gameVariables) throws IOException {
        this.clientSocket = clientSocket;
        this.threads = threads;
        socketInput = new ObjectInputStream(clientSocket.getInputStream());
        socketOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        socketOutput.flush();
        bowMan = new BowMan();
        this.gameVariables = gameVariables;
        this.gameVariables.getBowMans().add(bowMan);
        setTimer();
    }

    @Override
    public void run() {
        try{
            while(true){

                Command command = (Command)socketInput.readObject();
                if(command instanceof ExitCommand)
                    break;
                synchronized(gameVariables) {
                    if(command != null)
                        command.evaluate(bowMan, gameVariables);
                }

            }

            /*synchronized(this) {
                for(int i = 0; i < threads.length; i++)
                    if(threads[i] == this)
                        threads[i] = null;
            }
            socketInput.close();
            socketOutput.close();
            clientSocket.close();*/
        }
        catch(IOException e){
            System.out.println("Failed to terminate the thread properly !!!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setTimer(){
        timer = new Timer(60, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try{
                    //updateStage();
                    synchronized(ClientThread.this) {
                        for(int i = 0; i < threads.length; i++)
                            if(threads[i] != null) {
                                socketOutput.writeObject(gameVariables);
                        }
                    }
                }
                catch(IOException e){

                }
            }
        });
        timer.start();
    }
    private void checkCollisions() {

        List<Bird> birds = gameVariables.getBirds();
        List<Arrow> arrows = gameVariables.getArrows();
        List<Elephant> elephants = gameVariables.getElephants();
        synchronized(gameVariables) {
            for(int i = 0; i < arrows.size(); i++) {
                for(int j = 0; j < birds.size(); j++) {
                    Rectangle arrow = new Rectangle(arrows.get(i).getX(), arrows.get(i).getY(), arrows.get(i).getImage().getWidth(null)/2, arrows.get(i).getImage().getHeight(null)/2);
                    Rectangle bird = new Rectangle(birds.get(j).getX()+15, birds.get(j).getY(), birds.get(j).getImage().getWidth(null)/2, birds.get(j).getImage().getHeight(null)/2);
                    if(arrow.intersects(bird)) {
                        arrows.get(i).setIsFalling(true);
                        birds.get(j).setIsFalling(true);
                    }
                }
            }
            for(int i = 0; i < arrows.size(); i++) {
                for(int j = 0; j < elephants.size(); j++) {
                    Rectangle arrow = new Rectangle(arrows.get(i).getX(), arrows.get(i).getY(), arrows.get(i).getImage().getWidth(null)/2, arrows.get(i).getImage().getHeight(null)/2);
                    Rectangle elephant = new Rectangle(elephants.get(j).getX()+15, elephants.get(j).getY(), elephants.get(j).getImage().getWidth(null)/2, elephants.get(j).getImage().getHeight(null)/2);
                    if(arrow.intersects(elephant)) {
                        arrows.get(i).setIsFalling(true);
                        elephants.get(j).setIsFalling(true);
                    }
                }
            }
        }
    }
    private void addSomeBirds() {
        if(gameVariables.getBirds().size() < Math.random()*GameConstants.getBirdsMaxCount()) {
            int count = Math.min(GameConstants.getBirdsMaxCount()/3,(int)(Math.random()*GameConstants.getBirdsMaxCount()));
            for(int i = 0; i < count; i++)
                gameVariables.getBirds().add(new Bird());
        }
    }
    private void addSomeElephants() {
        if((int)(Math.random()*1000) > 995 && gameVariables.getElephants().size() == 0) {
                gameVariables.getElephants().add(new Elephant());
        }
    }
    private void updateStage() {
        checkCollisions();
        addSomeBirds();
        addSomeElephants();
        List<Bird> birds = gameVariables.getBirds();
        for(int i = 0; i < birds.size(); i++) {
            if(birds.get(i).getX() < GameConstants.getCanvasWidth() && birds.get(i).getY() < GameConstants.getCanvasHeight() && birds.get(i).getX()*birds.get(i).getY() >= 0)
                birds.get(i).move();
            else
                birds.remove(i);
        }
        List<Arrow> arrows = gameVariables.getArrows();
        for(int i = 0; i < arrows.size(); i++) {
            if(arrows.get(i).getX() < GameConstants.getCanvasWidth() && arrows.get(i).getY() < GameConstants.getCanvasHeight() && arrows.get(i).getX()*arrows.get(i).getY() >= 0)
                arrows.get(i).move();
            else
                arrows.remove(i);
        }
        List<Elephant> elephants = gameVariables.getElephants();
        for(int i = 0; i < elephants.size(); i++) {
            if(elephants.get(i).getX() < GameConstants.getCanvasWidth() && elephants.get(i).getY() < GameConstants.getCanvasHeight() && elephants.get(i).getX() >= GameConstants.getElephantStartXPosition() && elephants.get(i).getY() >= 0)
                elephants.get(i).move();
            else
                elephants.remove(i);
        }
    }


}