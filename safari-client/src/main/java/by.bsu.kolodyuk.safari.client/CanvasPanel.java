
package by.bsu.kolodyuk.safari.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class CanvasPanel extends JPanel {

    private Timer timer;
    private Image image;
    private BowMan bowMan;
    private List<Arrow> arrows;
    private List<Bird> birds;
    private List<Elephant> elephants;
    private ObjectInputStream socketInput;
    private ObjectOutputStream socketOutput;
    public CanvasPanel(ObjectInputStream socketInput, ObjectOutputStream socketOutput) {
        this.socketInput = socketInput;
        this.socketOutput = socketOutput;
        initStage();
        setBackgroundImage();
        setKeyBindings();
        addMouseListener();
        
    }
    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            private int startX, startY;
            private int stopX, stopY;

            @Override
            public void mousePressed(MouseEvent me) {
                super.mousePressed(me);
                startX = me.getX();
                startY = me.getY();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                try{
                    super.mouseReleased(me);
                    stopX = me.getX();
                    stopY = me.getY();
                    socketOutput.writeObject(new MouseReleasedCommand(startX, startY, stopX, stopY));
                }
                catch(IOException e){

                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent me) {
                super.mouseMoved(me);
                try{
                    socketOutput.writeObject(new MouseMovedCommand(me.getX(), me.getY()));
                    socketOutput.flush();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void setBackgroundImage() {
        image = new ImageIcon(getClass().getClassLoader().getResource("background.png")).getImage();
        System.out.println(image.getWidth(null) +" "+ image.getHeight(null));
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setDoubleBuffered(true);
    }
    private void initStage() {
        bowMan = new BowMan();
        arrows = Collections.synchronizedList(new ArrayList<Arrow>());
        birds = Collections.synchronizedList(new ArrayList<Bird>());
        elephants = Collections.synchronizedList(new ArrayList<Elephant>());
        //addSomeBirds();
        //addSomeElephants();
    }
 
    private void setKeyBindings() {
        InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "RightArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "LeftArrow");
        am.put("RightArrow", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                try{
                    socketOutput.writeObject(new KeyPressedCommand("RightArrow"));
                    socketOutput.writeObject(new MouseMovedCommand(CanvasPanel.this.getMousePosition().x,CanvasPanel.this.getMousePosition().y));
                }
                catch(IOException e){

                }
            }
        });
        am.put("LeftArrow", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                try{
                    socketOutput.writeObject(new KeyPressedCommand("LeftArrow"));
                    socketOutput.writeObject(new MouseMovedCommand(CanvasPanel.this.getMousePosition().x,CanvasPanel.this.getMousePosition().y));
                }
                catch(IOException e){
                }
            }
        });
    }
    private AffineTransform getBowTransform(double angle, double bowManX, double bowManY) {
        AffineTransform translation = AffineTransform.getTranslateInstance(bowManX, bowManY);
        AffineTransform rotation = AffineTransform.getRotateInstance(angle, GameConstants.getBowRotateXCenter(), GameConstants.getBowRotateYCenter());
        translation.concatenate(rotation);
        return translation;
    }
    private AffineTransform getArrowTransform(Arrow arrow) {
        AffineTransform rotation = AffineTransform.getRotateInstance(arrow.getAngle(), GameConstants.getBowRotateXCenter(), GameConstants.getBowRotateYCenter());
        AffineTransform translation = AffineTransform.getTranslateInstance(arrow.getX(), arrow.getY());
        translation.concatenate(rotation);
        return translation;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D)grphcs;
        grphcs.drawImage(image, 0, 0, this);
        grphcs.drawImage(bowMan.getImage(), bowMan.getX(), bowMan.getY(), this);
        g2d.drawImage(bowMan.getBow().getImage(), getBowTransform(bowMan.getBow().getAngle(),bowMan.getX(),bowMan.getY()), this);
        for(int i = 0; i < arrows.size(); i++) {
            g2d.drawImage(arrows.get(i).getImage(), getArrowTransform(arrows.get(i)), this);
        }
        for(int i = 0; i < birds.size(); i++) {
            g2d.drawImage(birds.get(i).getImage(), birds.get(i).getX(),birds.get(i).getY(), this);
        }
        for(int i = 0; i < elephants.size(); i++) {
            g2d.drawImage(elephants.get(i).getImage(), elephants.get(i).getX(), elephants.get(i).getY(), this);
        }
    }

    public GameVariables getGameVariables() {
        List<BowMan> bowMans = Collections.synchronizedList(new ArrayList<BowMan>());
        bowMans.add(bowMan);
        return new GameVariables(bowMans, arrows, birds, elephants);
    }
}