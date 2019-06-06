import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * Character - A class for the main character of the game
 *
 * @author Andrew Wang
 * @version June 6, 2019
 */
public class Character extends Movable
{
    private BSPTree bspTree;
    private ArrayList<Bullet> bulletsFired;
    private int level, currHp, maxHp, damage;

    private Room currentRoom;

    /**
    * Character() - Constructor for Character class
    * @param tree BSPTree for the character
    * @param x initial x pos coordinate
    * @param y initial y pos coordinate
    * @param lv initial level
    * @param hp initial health
    * @param dmg initial damage done
    */
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

    /**
    * getCurrHp() - Returns current HP value
    * @return current HP
    */
    public int getCurrHp()
    {
        return currHp;
    }

    /**
    * getMaxHp() - Returns highest HP
    * @return highest HP
    */
    public int getMaxHp()
    {
        return maxHp;
    }

    /**
    * getBulletsFired() - Retuns ArrayList of bullets fired
    * @return ArrayList of bullets fired
    */
    public ArrayList<Bullet> getBulletsFired()
    {
        return bulletsFired;
    }

    /**
    * gameOver() - Ends the game if current HP < 0
    */
    public boolean gameOver()
    {
        return (currHp <= 0);
    }

    /**
    * levelUp() - Incerements the level
    */
    public void levelUp()
    {
        level++;
    }

    /**
    * addHealth() - Adds specified amount health to the character
    * @param amount the amount of health to add to the character
    */
    public void addHealth(int amount)
    {
        // Add health, if health above cap, cap at max hp
        currHp += amount;

        if (currHp > maxHp)
            currHp = maxHp;
    }

    /**
    * scanRooms() - Scans rooms in specified leaves on ArrayList
    * @param leaves ArrayList of leaves to scan
    */
    public void scanRooms(ArrayList<BSPLeaf> leaves)
    {
        for (BSPLeaf leaf : leaves)
        {
            // For every room
            Room temp = leaf.getRoom();

            // If character intersects the room's bounds
            if (temp.getX() < this.getX() && this.getX() < temp.getX() + temp.getW() &&
                temp.getY() < this.getY() && this.getY() < temp.getY() + temp.getH())
            {
                // Set currentRoom to this room
                currentRoom = temp;
            }
        }
    }

    /**
    * attack() - Attacks on mouse click
    * @param event mouse click
    */
    public void attack(MouseEvent event)
    {
        // Create a new bullet and add to fired bullets
        Bullet temp = new Bullet(bspTree, x, y, 15, 10);
        bulletsFired.add(temp);

        // Launch the bullet
        temp.launch(x, y, event);
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

        int[][] map = bspTree.getTileMap();

        switch (direction)
        {
            case 'N':
                // If no wall to north, move up
                if (map[(int)(y - delta) / GUI.TILE_WIDTH][(int)x / GUI.TILE_WIDTH] != -3)
                    y -= delta;
                break;
            case 'S':
                // If no wall to south, move down
                if (map[(int)(y + delta + GUI.TILE_WIDTH - 3) / GUI.TILE_WIDTH][(int)x / GUI.TILE_WIDTH] != -3)
                    y += delta;
                break;
            case 'W':
                // If no wall to west, move left
                if (map[(int)y / GUI.TILE_WIDTH][(int)(x - delta) / GUI.TILE_WIDTH] != -3)
                    x -= delta;
                break;
            case 'E':
                // If no wall to east, move right
                if (map[(int)y / GUI.TILE_WIDTH][(int)(x + delta + GUI.TILE_WIDTH - 3) / GUI.TILE_WIDTH] != -3)
                    x += delta;
                break;
            default:
                break;
        }
    }
}
