import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends PApplet{


    private PImage img;
    public ArrayList<Point> points = new ArrayList();

    Path path = new Path(points);

    int x = 0;
    int y = 0;

    PFont f;


    public static void main(String[] args) {
        PApplet.main(new String [] {Main.class.getName()});
    }

    @Override
    public void settings() {
        size(1000,644);
        img = loadImage("Power_Play_Field_Only.jpg");
    }

    @Override
    public void setup() {
        frameRate(180);
        f = createFont("Arial",16,true);
    }

    @Override
    public void draw() {
        background(255,255,255);
        image(img, 0, 0);

        //Draw the point buttons
        fill(255, 255, 255);
        stroke(0, 0, 0);
        rect(700,150,100,50);
        rect(700,250,100,50);

        //Add the initial point buttons
        stroke(0, 0, 240);
        fill(0, 0, 200,100);
        rect(700, 590, 100, 30,10);

        stroke(240, 0, 0);
        fill(200, 0, 0,100);
        rect(810, 590, 100, 30,10);

        stroke(0, 0, 240);
        fill(0, 0, 200,100);
        rect(700, 550, 100, 30,10);

        stroke(240, 0, 0);
        fill(200, 0, 0,100);
        rect(810, 550, 100, 30,10);

        // Label the buttons
        fill(0,0,0);
        text("Add Point",700,150);
        text("Blue Top", 718,570);
        text("Blue Bottom", 706,610);
        text("Red Top", 830,570);
        text("Red Bottom", 817,610);

        if((int)(Math.random() + 1) == 1) {
            stroke(255,119,0);
            fill(255,119,0,100);
        } else {
            stroke(0,60,255);
            fill(0,60,255,100);
        }

//        for (int i = 0; i < points.size(); i++) {
//            points.get(i).drawPoint(this);
//        }

        path.drawPath(this);
        path.displayPoints(this, points);

        stroke(0, 0, 0);
        textFont(f);

    }

    @Override
    public void mouseDragged() {
        for(int i = 0; i < points.size(); i++) {
            points.get(i).mouseDragged(this);
        }
    }

    @Override
    public void mouseClicked() {
        if(overButton(700,150,100,50)) {
            points.add(new Point(300,300));
        } else if (overButton(700,250,100,50)) {
            if(points.size() > 1) {
                points.remove(points.size()-1);
            }
        }

        if(overButton(700, 550, 100, 30)) {
            if(points.size() >= 1) {
                points.set(0, new Point(74, 171));
            } else points.add(new Point(74,171));
        } else if(overButton(700, 590, 100, 30)) {
            if (points.size() >= 1) {
                points.set(0, new Point(74, 461));
            } else points.add(new Point(74, 461));
        } else if(overButton(810, 550, 100, 30)) {
            if (points.size() >= 1) {
                points.set(0, new Point(572, 171));
            } else points.add(new Point(572, 171));
        } else if(overButton(810, 590, 100, 30)) {
            if (points.size() >= 1) {
                points.set(0, new Point(572, 461));
            } else points.add(new Point(572, 461));
        }
    }

    boolean overButton(int x, int y, int width, int height) {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }

}
