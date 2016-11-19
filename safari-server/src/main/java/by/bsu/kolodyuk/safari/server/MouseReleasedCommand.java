
package by.bsu.kolodyuk.safari.server;

import java.io.Serializable;

public class MouseReleasedCommand implements Command, Serializable {

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;

    public MouseReleasedCommand(int startX, int startY, int stopX, int stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    public void evaluate(BowMan bowMan, GameVariables gameVariables) {
        double speed  = Math.sqrt((startX-stopX)*(startX-stopX) + (startY-stopY)*(startY-stopY))/5;
        if(speed < 7) speed = 7;
        double angle = getShotAngle(bowMan.getX(), bowMan.getY(), startX, startY);
        synchronized(gameVariables.getArrows()){
            gameVariables.getArrows().add(new Arrow(bowMan.getX(),bowMan.getY(), angle,speed));
        }
    }

    private double getShotAngle(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2-y1, x2-x1) + GameConstants.getBowStartAngle();
    }
}
