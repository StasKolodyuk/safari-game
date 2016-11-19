package by.bsu.kolodyuk.safari.client;


import java.awt.Image;
import javax.swing.ImageIcon;


public class Bird {

    private Image image;
    private int x;
    private int y;
    private double speed;
    private boolean isFalling;

    public Bird() {
        image = new ImageIcon(getClass().getClassLoader().getResource("bird.gif")).getImage();
        x = 0;
        y = (int)(Math.random()*GameConstants.getBirdMaxYPosition());
        speed = Math.random()*GameConstants.getBirdMaxSpeed() + 0.1;
        isFalling = false;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public void move() {
        if(!isFalling) {
            x += speed*GameConstants.getDeltaX();
        }
        else
            y += GameConstants.getDeltaX();
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
