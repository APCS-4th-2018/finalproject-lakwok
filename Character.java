import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Character extends Movable
{
    private BSPTree bspTree;
    private ArrayList<Bullet> bulletsFired;
    private int level, currHp, maxHp, damage;

    private Room currentRoom;

    public Character(BSPTree tree, double x, double y, int lv, int hp, int dmg)
    {
        super(x, y);

        bspTree = tree;
        bulletsFired = new ArrayList<Bullet>();

        level = lv;
        maxHp = hp;
        currHp = hp;
        damage = dmg;
    }

    public int getCurrHp()
    {
        return currHp;
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public ArrayList<Bullet> getBulletsFired()
    {
        return bulletsFired;
    }

    public boolean gameOver()
    {
        return (currHp <= 0);
    }

    public void levelUp()
    {
        level++;
    }

    public void addHealth(int amount)
    {
        currHp += amount;

        if (currHp > maxHp)
            currHp = maxHp;
    }

    public void addDamage(int amount)
    {
        damage += amount;
    }

    public void scanRooms(ArrayList<BSPLeaf> leaves)
    {
        for (BSPLeaf leaf : leaves)
        {
            Room temp = leaf.getRoom();

            if (temp.getX() < this.getX() && this.getX() < temp.getX() + temp.getW() &&
                temp.getY() < this.getY() && this.getY() < temp.getY() + temp.getH())
            {
                currentRoom = temp;
            }
        }
    }

    public void attack(MouseEvent event)
    {
        Bullet temp = new Bullet(bspTree, x, y, 15, 10);
        bulletsFired.add(temp);
        temp.launch(event);
    }

    @Override
    public void move(char direction, double delta)
    {
        orientation = direction;

        double newX = x;
        double newY = y;

        int[][] map = bspTree.getTileMap();

        switch (direction)
        {
            case 'N':
                if (map[(int)(y - delta) / GUI.TILE_WIDTH][(int)x / GUI.TILE_WIDTH] != -3)
                    y -= delta;
                break;
            case 'S':
                if (map[(int)(y + delta + GUI.TILE_WIDTH - 3) / GUI.TILE_WIDTH][(int)x / GUI.TILE_WIDTH] != -3)
                    y += delta;
                break;
            case 'W':
                if (map[(int)y / GUI.TILE_WIDTH][(int)(x - delta) / GUI.TILE_WIDTH] != -3)
                    x -= delta;
                break;
            case 'E':
                if (map[(int)y / GUI.TILE_WIDTH][(int)(x + delta + GUI.TILE_WIDTH - 3) / GUI.TILE_WIDTH] != -3)
                    x += delta;
                break;
            default:
                break;
        }
    }
}
