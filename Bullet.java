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

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }


    public Rectangle2D getBounds()
    {
        return new Rectangle2D(x, y, 16, 16);
    }

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
