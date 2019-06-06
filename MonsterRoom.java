import java.util.ArrayList;

public class MonsterRoom extends Room
{
    private int numMonsters;
    private ArrayList<Monster> monsters;

    /**
    * MonsterRoom() - Constructor for MonsterRoom class
    * @param leaf BSPLeaf to get coordinate positions and values from
    * @param numMonsters Number of monsters
    */
    public MonsterRoom(BSPLeaf leaf, int numMonsters)
    {
        super(leaf);

        this.numMonsters = numMonsters;
        this.monsters = new ArrayList<Monster>();
    }

    /**
    * getMonsters() - Returns ArrayList of monsters
    * @return ArrayList of monsters
    */
    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }
}
