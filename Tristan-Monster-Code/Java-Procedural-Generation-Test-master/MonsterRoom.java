import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class MonsterRoom extends Room
{
    private int numMonsters;

    private ArrayList<Monster> monsters;
    private boolean cleared;

    public MonsterRoom(BSPLeaf leaf, int numMonsters)
    {
        super(leaf);

        this.numMonsters = numMonsters;
        this.monsters = new ArrayList<Monster>();

        for (int x = 0; x < numMonsters; x++)
        {
           monsters.add(new Monster(ExtraTools.randomRange((int)this.center.getX() / 100 - this.w * 32, (int)this.center.getX() / 100 + this.w * 32),
                   ExtraTools.randomRange((int)this.center.getY() / 100 - this.h * 32, (int)this.center.getY() / 100 + this.h * 32),
                   1, 100, 5, 2));
        }

    System.out.println(this.x + this.w);

        isCleared();
    }

    public boolean isCleared()
    {
        cleared = false;

        if (monsters.size() == 0)
            cleared = true;

        return cleared;
    }

    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }
}
