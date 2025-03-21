import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Path {

    double[] b;
    int x = 0;
    int y = 0;

    ArrayList<Point> points;

    public Path(ArrayList<Point> points) {
        this.points = points;
    }

    public void drawPath(PApplet pApplet) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).drawPoint(pApplet);
        }

        double delta = 0.02;

        if (points.size() == 2) {
            pApplet.noFill();
            pApplet.stroke(0,0,0);
            pApplet.line(points.get(0).getX(), points.get(0).getY(), points.get(1).getX(), points.get(1).getY());
        } else if (points.size() == 3) {
            pApplet.noFill();
            pApplet.stroke(0,0,0);
            pApplet.beginShape();
            for (double t = 0.0; t <= 1.000001; t += delta) {
                Point v = quadratic(points.get(0), points.get(1), points.get(2), t, pApplet);
                pApplet.vertex(v.getX(), v.getY());
            }
            pApplet.endShape();
        } else if (points.size() == 4) {
            pApplet.noFill();
            pApplet.stroke(0,0,0);
            pApplet.beginShape();
            for (double t = 0.0; t <= 1.000001; t += delta) {
                Point v = cubic(points.get(0), points.get(1), points.get(2), points.get(3), t, pApplet);
                pApplet.vertex(v.getX(), v.getY());
            }
            pApplet.endShape();
        } else if (points.size() > 4) {
            pApplet.noFill();
            pApplet.stroke(0,0,0);
            pApplet.beginShape();
            for (double t = 0.0; t <= 1.000001; t += delta) {
                Point v = anyBezier(points, t, pApplet);
                pApplet.vertex(v.getX(), v.getY());
            }
            pApplet.endShape();
        }
    }

    Point quadratic(Point p0, Point p1, Point p2, double t, PApplet pApplet) {
        double delta = 0.02;
        float x1 = pApplet.lerp(p0.getX(), p1.getX(), (float)t);
        float y1 = pApplet.lerp(p0.getY(), p1.getY(), (float)t);
        float x2 = pApplet.lerp(p1.getX(), p2.getX(), (float)t);
        float y2 = pApplet.lerp(p1.getY(), p2.getY(), (float)t);
        float x = pApplet.lerp(x1,x2,(float)t);
        float y = pApplet.lerp(y1,y2,(float)t);
        return new Point((int)x,(int)y);

    }

    Point cubic(Point p0, Point p1, Point p2, Point p3, double t, PApplet pApplet) {
        Point v1 = quadratic(p0,p1,p2,t, pApplet);
        Point v2 = quadratic(p1,p2,p3,t, pApplet);
        float x = pApplet.lerp(v1.getX(),v2.getX(),(float)t);
        float y = pApplet.lerp(v1.getY(),v2.getY(),(float)t);
        return new Point((int)x,(int)y);
    }

    Point anyBezier(ArrayList<Point> p, double t, PApplet pApplet) {
        int n = p.size() - 1;
        b = binomialCoefficients(n);

        x = 0;
        y = 0;
        for(int i = 0; i <= n; i++) {
            x += b[i] * Math.pow(1-t,n-i) * Math.pow(t, i) * p.get(i).getX();
            y += b[i] * Math.pow(1-t,n-i) * Math.pow(t, i) * p.get(i).getY();
        }

        return new Point(x,y);
    }

    public static double[] binomialCoefficients(int n) {
        double[] b = new double[n + 1];
        b[0] = 1;
        b[1] = 1;

        if(n == 1){
            return(b);
        } else {
            for(int i = 1; i < n; i ++) {
                b[i + 1] = 1;
                for(int k = i; k > 0; k --) {
                    b[k] = b[k] + b[k - 1];
                }
            }
            return(b);
        }
    }

    void displayPoints (PApplet pApplet, ArrayList<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            pApplet.fill(0,0,0);
//            text("P" + (i + 1) + points.get(i).getX() + ", " + points.get(i).getY(), 800, (i*20) + 35);

            pApplet.text("P" + (i + 1) + getFieldCoords(points.get(i)), 850, (i*20) + 35);
        }
    }

    String getFieldCoords(Point p) {
        double pixelsToCM = 0.39456;
        if (p.getY() >= 130 && p.getY() <= 419 && p.getX() >= 13 && p.getX() <= 610) {
            int x = (int)((p.getX() - 13) * pixelsToCM);
            int y = (int)-((p.getY() - 419) * pixelsToCM);

            return ("(" + x + ", " + y + ")");
        } else return "Invalid Location";
    }
}
