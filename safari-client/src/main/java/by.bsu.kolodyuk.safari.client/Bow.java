/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsu.kolodyuk.safari.client;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Bow {

    private final Image image;
    private double angle;

    public Bow() {
        this.image = new ImageIcon(getClass().getClassLoader().getResource("bow.png")).getImage();
        angle = 0;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Image getImage() {
        return image;
    }

}
