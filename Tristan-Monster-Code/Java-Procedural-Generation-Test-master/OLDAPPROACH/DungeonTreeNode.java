package OLDAPPROACH;

import OLDAPPROACH.DungeonContainer;

import java.util.ArrayList;

public class DungeonTreeNode
{
    /**
    private DungeonContainer leaf;
    private DungeonTreeNode left, right;

    public DungeonTreeNode(DungeonContainer newLeaf)
    {
        leaf = newLeaf;
        left = null;
        right = null;
    }

    public DungeonContainer getLeaf()
    {
        return leaf;
    }
    
    public DungeonTreeNode getLeft()
    {
        return left;
    }
    
    public DungeonTreeNode getRight()
    {
        return right;
    }
    
    public void setLeaf(DungeonContainer newLeaf)
    {
        leaf = newLeaf;
    }
    
    public void setLeft(DungeonTreeNode newLeft)
    {
        left = newLeft;
    }

    public void setRight(DungeonTreeNode newRight)
    {
        right = newRight;
    }

    public ArrayList<DungeonContainer> getLeaves()
    {
        ArrayList<DungeonContainer> leaves = new ArrayList<DungeonContainer>();

        if (left == null && right == null)
        {
            leaves.add(leaf);
        }
        else
        {
            leaves.addAll(left.getLeaves());
            leaves.addAll(right.getLeaves());
        }

        return leaves;
    }

    public ArrayList<DungeonTreeNode> getLevel(int level, ArrayList<DungeonTreeNode> queue)
    {
        if (queue == null)
            queue = new ArrayList<>();

        if (level == 1)
        {
            queue.add(this);
        }
        else
        {
            if (left == null)
                left.getLevel(level - 1, queue);
            if (right == null)
                right.getLevel(level - 1, queue);
        }

        return queue;
    }
     */
}