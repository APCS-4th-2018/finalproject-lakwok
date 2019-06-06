import java.util.ArrayList;

public class MonsterRoom extends Room
{
    private int numMonsters;
    private ArrayList<Monster> monsters;

    public MonsterRoom(BSPLeaf leaf, int numMonsters)
    {
        super(leaf);

        this.numMonsters = numMonsters;
        this.monsters = new ArrayList<Monster>();
    }

    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }
}