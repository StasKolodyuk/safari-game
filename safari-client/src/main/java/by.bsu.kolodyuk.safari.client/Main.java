
package by.bsu.kolodyuk.safari.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;

public class Main extends JFrame {

    private static Socket socket;
    private static ObjectInputStream socketInput;
    private static ObjectOutputStream socketOutput;
    private static CanvasPanel canvasPanel;
    public Main()
    {
        canvasPanel = new CanvasPanel(socketInput, socketOutput);
        add(canvasPanel);
        setResizable(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        try{
            socket = new Socket("localhost", 8888);
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            socketOutput.flush();
            socketInput = new ObjectInputStream(socket.getInputStream());
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                        while(true){
                            GameVariables gameVariables = (GameVariables)socketInput.readObject();
                            if(gameVariables != null)
                                canvasPanel.repaint();
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
            new Main();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}