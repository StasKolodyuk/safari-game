
package by.bsu.kolodyuk.safari.server;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Elephant {

    private final Image image;
    private int x;
    private int y;
    private double speed;
    private boolean isFalling;

    public Elephant() {
        this.image = new ImageIcon(getClass().getClassLoader().getResource("elephant.gif")).getImage();
        this.x = GameConstants.getElephantStartXPosition();
        this.y = GameConstants.getElephantYPosition();
        this.speed = GameConstants.getElephantMaxSpeed();
        this.isFalling = false;
    }
    public void move() {
        if(!isFalling) {
            x += GameConstants.getDeltaX()/5;
        }
        else
            y += GameConstants.getDeltaX()/5;
    }
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

}
