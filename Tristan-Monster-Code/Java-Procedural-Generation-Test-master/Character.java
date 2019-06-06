import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class Character extends Movable
{
    private BSPTree bspTree;

    private int level, health, damage;

    private Room currentRoom;

    public Character(BSPTree tree, double x, double y, int lv, int hp, int dmg)
    {
        super(x, y);

        bspTree = tree;

        level = lv;
        health = hp;
        damage = dmg;
    }

    public void addHealth(int amount)
    {
        health += amount;
    }

    public void levelUp()
    {
        level++;
    }

    public void scanRooms(ArrayList<BSPLeaf> leaves)
    {
        for (BSPLeaf leaf : leaves)
        {
            Room temp = leaf.getRoom();

            if (temp.getX() * 32 < this.x && this.x < temp.getX() * 32 + temp.getW() * 32 &&
                temp.getY() * 32 < this.y && this.y < temp.getY() * 32 + temp.getH() * 32)
            {
                currentRoom = temp;
            }
        }
    }

    public void attack()
    {
        if (currentRoom instanceof MonsterRoom)
        {
            for (Monster m : ((MonsterRoom) currentRoom).getMonsters())
            {
                switch (this.getOrientation())
                {
                    case 'N':
                         // START HERE TOMORROW FINISH IMPLEMENTING ATTACK
                }
            }
        }
    }

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

        int[][] map = bspTree.getTileMap();

        System.out.println("X: " + (int)(x / 32) + " Y: " + (int)(y / 32));

        if (map[(int)(newY / 32)][(int)(newX / 32)] == -3)
        {
            System.out.println("u in wall");
        }

        x = newX;
        y = newY;

    }
}
