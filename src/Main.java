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
        size(1000,545);
        img = loadImage("Mining_Mayhem_Field.png");
    }

    @Override
    public void setup() {
        frameRate(180);
        f = createFont("Arial",16,true);
    }

    @Override
    public void draw() {
        background(255,255,255);
        image(img, 0, 66);

        //Draw the point buttons
        fill(134, 31, 65, 200);
        stroke(0, 0, 0);
        rect(700,150,100,50,10);

        fill(229, 117, 31, 200);
        rect(700,250,100,50, 10);

        //Add the initial point buttons
        fill(134, 31, 65, 200);
        rect(700, 400, 100, 30,10);

        // Label the buttons
        fill(0,0,0);
        text("Add Point",715,180);
        text("Remove\n   Point",720,272);
        text("Initial Point", 712,422);

        if((int)(Math.random() + 1) == 1) {
            stroke(255,119,0);
            fill(255,119,0,100);
        } else {
            stroke(0,60,255);
            fill(0,60,255,100);
        }

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

        if(overButton(700, 400, 100, 30)) {
            if(points.size() >= 1) {
                points.set(0, new Point(74, 171));
            } else points.add(new Point(74,171));
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
