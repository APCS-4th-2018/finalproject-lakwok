import java.awt.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;
public class Monster extends Pane
{
    private int level, health, damage, movespeed;
    private boolean hasLoot;
    private double x, y;


    //New stuff
    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;
    private static Random random = new Random();
    Circle circle;
    double width, height = 30;
    double centerX, centerY, radius = 15;

    public Monster(double x1, double y1, int lv, int hp, int dmg, int spd)
    {
        x = x1;
        y = y1;
        level = lv;
        health = hp;
        damage = dmg;
        movespeed = spd;

        hasLoot = ((int)(Math.random() * 10) < 3);

        //New stuff
        location = new PVector(x, y, 0);
        velocity = new PVector(0, 0, 0);
        topspeed = 2;
        circle = new Circle(radius);

        circle.setCenterX(radius);
        circle.setCenterY(radius);

        circle.setStroke(Color.RED);
        circle.setFill(Color.RED.deriveColor(1, 1, 1, 0.3));

        getChildren().add(circle);
    }


    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }


    public void attack(Character c)
    {
        double xsq = Math.pow(this.getX() - c.getX(), 2);
        double ysq = Math.pow(this.getY() - c.getY(), 2);

        while (Math.sqrt(xsq + ysq) <= 8)
        {
            c.addHealth(-5);
        }
    }

    /*
    public void trackPlayer(Character c)
    {
        double xsq = Math.pow(this.getX() - c.getX(), 2);
        double ysq = Math.pow(this.getY() - c.getY(), 2);

        while (Math.sqrt(xsq + ysq) <= 15)
        {
            while (this.getX() != c.getX())
            {
                if (this.getX() < c.getX())
                    this.move('E', movespeed);
                else
                    this.move('W', movespeed);
            }

            while (this.getY() != c.getY())
            {
                if (this.getY() < c.getY())
                    this.move('S', movespeed);
                else
                    this.move('N', movespeed);
            }
        }
    }
*/
    public void checkBoundaries() {

        if (location.x > 800) {
            location.x = 0;
        } else if (location.x < 0) {
            location.x = 800;
        }

        if (location.y > 800) {
            location.y = 0;
        } else if (location.y < 0) {
            location.y = 800;
        }
    }


    public void step(PVector follow) {

        PVector dir = PVector.sub(follow, location);
        dir.normalize();
        dir.mult(0.5);
        acceleration = dir;
        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);

    }

    public void display()
    {
        relocate(location.x - centerX, location.y - centerY);
    }
}
