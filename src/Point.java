import processing.core.PApplet;

import java.util.ArrayList;

public class Point {

    private int x;
    private int y;

    double[] b;

    private final int SIZE = 15;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawPoint(PApplet pApplet) {
        pApplet.circle(x, y, SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void mouseDragged(PApplet pApplet) {

        if (overCircle(x, y, SIZE, pApplet)) {
            x = pApplet.mouseX;
            y = pApplet.mouseY;
        }
    }

    boolean overCircle(int x, int y, int diameter, PApplet pApplet) {
        float disX = x - pApplet.mouseX;
        float disY = y - pApplet.mouseY;
        if (pApplet.sqrt(pApplet.sq(disX) + pApplet.sq(disY)) < diameter/2 ) {
            return true;
        } else {
            return false;
        }
    }

}
