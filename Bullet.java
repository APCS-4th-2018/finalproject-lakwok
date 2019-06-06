import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Bullet
{
    private BSPTree bspTree;
    private ArrayList<Monster> allMonsters;

    private boolean visible;
    private double direction;
    private double speed;
    private double x;
    private double y;
    private int damage;

    /**
    * Bullet() - Constructor for the Bullet class
    * @param tree BSPTree for the bullet
    * @param xPos initial x coordinate
    * @param yPos initial y coordinate
    * @param spd initial speed
    * @param dmg initial damage done
    */
    public Bullet(BSPTree tree, double xPos, double yPos, int spd, int dmg)
    {
        bspTree = tree;
        allMonsters = new ArrayList<Monster>();

        x = xPos;
        y = yPos;
        speed = spd;
        damage = dmg;

        visible = false;
    }

    /**
    * getX() - Returns the x coordinate
    * @return x coordinate
    */
    public double getX()
    {
        return x;
    }

    /**
    * getY() - Returns the y coordinate
    * @return y coordinate
    */
    public double getY()
    {
        return y;
    }


    /**
    * getBounds() - Returns a rectangle with the current bounds
    * @return rectangle with the current bounds
    */
    public Rectangle2D getBounds()
    {
        return new Rectangle2D(x, y, 16, 16);
    }

    /**
    * update() - Update the speed
    */
    public void update()
    {
        x += Math.cos(direction) * speed;
        y += Math.sin(direction) * speed;

        for (Monster m : allMonsters)
        {
            if (this.getBounds().intersects(m.getHitBox()))
            {
                visible = false;
                m.addHealth(-damage);
            }
        }

        if ((x < -5000 || x > 5000) || (y < -5000 || y > 5000))
            visible = false;
    }

    /**
    * launch() - Launches a bullet on click
    * @param event mouse click
    */
    public void launch(MouseEvent event)
    {
        visible = true;

        double diffx = event.getX() - this.x;
        double diffy = event.getY() - this.y;

        direction =  Math.atan2(diffy, diffx);

        for (BSPLeaf leaf : bspTree.getRoot().getLeaves())
        {
            if (leaf.getRoom() instanceof MonsterRoom)
            {
                MonsterRoom monsterRoom = (MonsterRoom)leaf.getRoom();
                allMonsters.addAll(monsterRoom.getMonsters());
            }
        }
    }
}
