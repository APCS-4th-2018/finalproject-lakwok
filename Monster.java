import javafx.geometry.Rectangle2D;

/**
 * Monster - A class meant to simulate enemies in the dungeon
 *
 * @author Andrew Wang and Tristan Pham
 * @version June 6, 2019
 */
public class Monster extends Movable
{
    private final int WIDTH = 32, HEIGHT = 32;

    private int level, health, damage, movespeed;
    private boolean hasLoot;

    /**
    * Monster() - Constructor for Monster class
    * @param x initial x coordinate
    * @param y initial y coordinate
    * @param lv initial level
    * @param hp initial health
    * @param dmg initial damage done
    * @param spd initial speed
    */
    public Monster(double x, double y, int lv, int hp, int dmg, int spd)
    {
        super(x, y);
        level = lv;
        health = hp;
        damage = dmg;
        movespeed = spd;

        hasLoot = ((int)(Math.random() * 10) < 3);
    }

    /**
    * addHealth() - Adds health to the current monster object
    * @param amount amount of health to add
    */
    public void addHealth(int amount)
    {
        health += amount;
    }

    /**
    * getHitBox() - Returns the hit box
    * @return hit box
    */
    public Rectangle2D getHitBox()
    {
        return new Rectangle2D(x, y, WIDTH, HEIGHT);
    }

    /**
    * move() - Moves in the specified direction and distance
    *
    * @param direction direction toward which to move
    * @param delta distance to move
    */
    @Override
    public void move(char direction, double delta)
    {
        orientation = direction;

        double newX = x;
        double newY = y;

        switch (direction)
        {
            case 'N':
                newY -= delta;

                //if (newY < bound.getY()) newY = y;

                break;
            case 'S':
                newY += delta;

                //if (newY > bound.getY() + bound.getH() - 1) newY = y;

                break;
            case 'W':
                newX -= delta;

                //if (newX < bound.getX()) newX = x;

                break;
            case 'E':
                newX += delta;

                //if (newX > bound.getX() + bound.getW() - 1) newX = x;

            default:
                break;
        }

        x = newX;
        y = newY;
    }
}
