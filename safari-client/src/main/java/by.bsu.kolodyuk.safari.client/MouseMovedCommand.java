
package by.bsu.kolodyuk.safari.client;

import java.io.Serializable;


public class MouseMovedCommand implements Command, Serializable {

    private int x;
    private int y;
    public MouseMovedCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void evaluate(BowMan bowMan, GameVariables gameVariables) {
        bowMan.getBow().setAngle(getShotAngle(bowMan.getX(), bowMan.getY(), x, y));
    }

    private double getShotAngle(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2-y1, x2-x1) + GameConstants.getBowStartAngle();
    }
}
