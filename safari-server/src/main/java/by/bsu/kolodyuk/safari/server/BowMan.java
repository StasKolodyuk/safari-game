
package by.bsu.kolodyuk.safari.server;

import java.awt.Image;
import javax.swing.ImageIcon;

public class BowMan {
    private final Image image;
    private Bow bow;
    private int x;
    private int y;
    public BowMan() {
        image = new ImageIcon(getClass().getClassLoader().getResource("bowman.png")).getImage();
        bow = new Bow();
        this.x = GameConstants.getBowmanXPosition();
        this.y = GameConstants.getBowmanYPosition();
    }

    public Bow getBow() {
        return bow;
    }
    public void moveRight() {
        this.x += GameConstants.getDeltaX();
    }

    public void moveLeft() {
        this.x -= GameConstants.getDeltaX();
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
}