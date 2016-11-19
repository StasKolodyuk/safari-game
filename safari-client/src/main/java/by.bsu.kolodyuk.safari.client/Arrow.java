
package by.bsu.kolodyuk.safari.client;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Arrow {

    private final Image image;
    private int x;
    private int y;
    private double angle;
    private double speed;
    private boolean isFalling;

    public Arrow(int x, int y, double angle, double speed) {
        this.image = new ImageIcon(getClass().getClassLoader().getResource("arrow.png")).getImage();
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.isFalling = false;
    }
    
    public void move() {
        if(!isFalling) {
            x += Math.cos(angle - GameConstants.getBowStartAngle())*speed;
            y += Math.sin(angle - GameConstants.getBowStartAngle())*speed;
        }
        else
            y += GameConstants.getDeltaX();
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }
    public double getAngle() {
        return angle;
    }

    public Image getImage() {
        return image;
    }

    public double getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
