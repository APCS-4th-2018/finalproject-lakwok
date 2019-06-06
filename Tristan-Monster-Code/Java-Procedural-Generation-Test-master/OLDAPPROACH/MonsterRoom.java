package OLDAPPROACH;

import OLDAPPROACH.DungeonContainer;
import OLDAPPROACH.Room;

import java.util.ArrayList;

public class MonsterRoom extends Room
{
    /**
    private int numMonst;
    private ArrayList<Monster> monsters;

    public MonsterRoom(DungeonContainer container, int level)
    {
        super(container, level);
        monsters = new ArrayList<Monster>();
        placeMonsters();
    }

    private void placeMonsters()
    {
        numMonst = w * h / 80 * roomLevel;

        for (int i = 0; i < numMonst; i++)
        {
            int type = (int)(Math.random() * 3);
            Monster temp;

            switch(type)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        }
    }

    public boolean isCleared()
    {
        return monsters.isEmpty();
    }
     */
}
