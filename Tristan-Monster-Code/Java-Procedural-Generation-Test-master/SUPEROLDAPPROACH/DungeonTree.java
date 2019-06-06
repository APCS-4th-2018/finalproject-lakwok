package SUPEROLDAPPROACH;

import java.util.ArrayList;

/**
 * https://gamedevelopment.tutsplus.com/tutorials/how-to-use-bsp-trees-to-generate-game-maps--gamedev-12268
 */


public class DungeonTree
{
    /**
    private final int MAX_LEAF_SIZE = 10;
    private final int DUNGEON_WIDTH = 30;
    private final int DUNGEON_HEIGHT = 30;
    
    private ArrayList<DungeonLeaf> leaves;

    int[][] map = new int[DUNGEON_WIDTH][DUNGEON_HEIGHT];

    public DungeonTree()
    {
        leaves = new ArrayList<DungeonLeaf>();
        leaves.add(new DungeonLeaf(0, 0, DUNGEON_WIDTH, DUNGEON_HEIGHT));

        boolean hasSplit;

        do
        {
            hasSplit = false;

            for (int i = 0; i < leaves.size(); i++)
            {
                DungeonLeaf leaf = leaves.get(i);

                if (leaf.getLeft() == null && leaf.getRight() == null)
                {
                    if ((leaf.getWidth() > MAX_LEAF_SIZE || 
                        leaf.getHeight() > MAX_LEAF_SIZE) &&
                        leaf.split())
                    {
                        leaves.add(leaf.getLeft());
                        leaves.add(leaf.getRight());
                        hasSplit = true;
                    }
                }
            }
        }
        while (hasSplit);
    }

    public void updateMap() throws InterruptedException
    {
        int num = 1;

        for (DungeonLeaf leaf : leaves)
        {
            for (int r = 0; r < leaf.getHeight(); r++)
            {
                for (int c = 0; c < leaf.getWidth(); c++)
                {
                    map[r][c] = num;
                }
            }

            num++;

            drawMap();
            try
            {
                Thread.sleep(3000);
                System.out.print("\n\n\n\n\n");
            }
            catch (Exception e)
            {
                continue;
            }
        }
    }

    public void drawMap()
    {
        for (int r = 0; r < map.length; r++)
        {
            for (int c = 0; c < map[r].length; c++)
            {
                System.out.print(Format.left(map[r][c], 4));
            }
            System.out.println();
        }
    }
     */
}