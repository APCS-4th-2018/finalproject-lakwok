package SUPEROLDAPPROACH;

import OLDAPPROACH.Room;

import java.util.ArrayList;

public class DungeonLeaf
{
    /**
    private final int MIN_LEAF_SIZE = 5;
    private int x, y, width, height;

    private Room room;
    private ArrayList<Hallway> hallways = new ArrayList<Hallway>();

    private DungeonLeaf left, right;

    public DungeonLeaf(int xPos, int yPos, int w, int h)
    {
        x = xPos;
        y = yPos;
        width = w;
        height = h;
    }

    public DungeonLeaf getLeft()
    {
        return left;
    }

    public DungeonLeaf getRight()
    {
        return right;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public ArrayList<DungeonLeaf> getLeaves()
    {
        ArrayList<DungeonLeaf> leaves = new ArrayList<DungeonLeaf>();

        if (left != null && right != null)
        {
            for (DungeonLeaf leaf : left.getLeaves())
                leaves.add(leaf);
            for (DungeonLeaf leaf : right.getLeaves())
                leaves.add(leaf);
        }

        return leaves;
    }

    public boolean split()
    {
        boolean flag = true;
        
        if (left != null || right != null)
        {
            flag = false;
        }
        else
        {
            boolean splitH = (Math.random() < 0.5);
            
            if (width > height && (double)width / height >= 1.25)
                splitH = false;
            
            if (height > width && (double)height / width >= 1.25)
                splitH = true;
            
            int maxDimension = (splitH ? height : width) - MIN_LEAF_SIZE;
            
            if (maxDimension <= MIN_LEAF_SIZE)
            {
                flag = false;
            }
            else
            {
                int split = ExtraTools.randomRange(MIN_LEAF_SIZE, maxDimension);
                
                if (splitH)
                {
                    left = new DungeonLeaf(x, y, width, split);
                    right = new DungeonLeaf(x, y + split, width, height - split);
                }
                else
                {
                    left = new DungeonLeaf(x, y, split, height);
                    right = new DungeonLeaf(x + split, y, width - split, height);
                }
            }
        }
        
        return flag;
    }


    public void update()
    {

    }
     */
}
