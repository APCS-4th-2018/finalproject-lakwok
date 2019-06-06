import java.sql.Statement;
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

    public void levelUp()
    {
        level++;
    }

    public void addHealth(int amount)
    {
        health += amount;
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

        int[][] map = bspTree.getTileMap();

        switch (direction)
        {
            case 'N':
                if (map[(int)(y - delta) / Room.TILE_WIDTH][(int)x / Room.TILE_WIDTH] != -3)
                    y -= delta;
                break;
            case 'S':
                if (map[(int)(y + delta + Room.TILE_WIDTH - 3) / Room.TILE_WIDTH][(int)x / Room.TILE_WIDTH] != -3)
                    y += delta;
                break;
            case 'W':
                if (map[(int)y / Room.TILE_WIDTH][(int)(x - delta) / Room.TILE_WIDTH] != -3)
                    x -= delta;
                break;
            case 'E':
                if (map[(int)y / Room.TILE_WIDTH][(int)(x + delta + Room.TILE_WIDTH - 3) / Room.TILE_WIDTH] != -3)
                    x += delta;
                break;
            default:
                break;
        }
    }
}
