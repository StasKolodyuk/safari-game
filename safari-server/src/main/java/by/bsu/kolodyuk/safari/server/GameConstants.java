
package by.bsu.kolodyuk.safari.server;

import javax.swing.JPanel;

public final class GameConstants {

    private static final int canvasWidth = 1024;
    private static final int canvasHeight = 576;
    private static final int bowmanXPosition = 455;
    private static final int bowmanYPosition = 455;
    private static final int elephantYPosition = 430;
    private static final int bowRotateXCenter = 9;
    private static final int bowRotateYCenter = 32;
    private static final double bowStartAngle = Math.toRadians(22);
    private static final int birdMaxYPosition = 200;
    private static final double birdMaxSpeed = 1;
    private static final int elephantStartXPosition = -50;

    public static int getElephantStartXPosition() {
        return elephantStartXPosition;
    }
    private static final double elephantMaxSpeed = 1;
    private static final int birdsMaxCount = 5;
    private static final int elephantsMaxCount = 5;

    public static int getElephantsMaxCount() {
        return elephantsMaxCount;
    }
    private static final int deltaX = 10;

    public static double getElephantMaxSpeed() {
        return elephantMaxSpeed;
    }

    public static int getElephantYPosition() {
        return elephantYPosition;
    }

    public static int getBirdsMaxCount() {
        return birdsMaxCount;
    }

    public static int getDeltaX() {
        return deltaX;
    }

    public static double getBirdMaxSpeed() {
        return birdMaxSpeed;
    }

    public static int getBirdMaxYPosition() {
        return birdMaxYPosition;
    }


    public static double getBowStartAngle() {
        return bowStartAngle;
    }

    public static int getBowmanXPosition() {
        return bowmanXPosition;
    }

    public static int getBowmanYPosition() {
        return bowmanYPosition;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }

    public static int getBowRotateXCenter() {
        return bowRotateXCenter;
    }

    public static int getBowRotateYCenter() {
        return bowRotateYCenter;
    }
}
