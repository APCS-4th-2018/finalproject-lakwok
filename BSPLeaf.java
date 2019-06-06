import java.awt.*;
import java.util.ArrayList;

public class BSPLeaf
{
    // Constants
    private final int MIN_LEAF_SIZE = 20;
    private final int MAX_LEAF_SIZE = 30;
    private final double H_DISCARD_RATIO = 0.3;
    private final double V_DISCARD_RATIO = 0.3;

    // Instance Variables
    private BSPLeaf left, right;
    private Room room;
    private int x, y, w, h;
    private Point center;

    /**
    * BSPLeaf() - Constructor for BSPLeaf Class
    * @param x initial x coordinate
    * @param y initial y coordinate
    * @param w initial w value
    * @param h initial h value
    */
    public BSPLeaf(int x, int y, int w, int h)
    {
        this.x = x; // x coordinate (x)
        this.y = y; // y coordinate (y)
        this.w = w; // w value (widh)
        this.h = h; // h value (height)

        // Calculates the center point from the coordinates and values given
        this.center = new Point(x + w/2, y + h/2);
    }

    /**
    * getX() - Returns the x coordinate
    * @return x coordinate
    */
    public int getX()
    {
        return x;
    }

    /**
    * getY() - Returns the y coordinate
    * @return y coordinate
    */
    public int getY()
    {
        return y;
    }

    /**
    * getW() - Returns the w value
    * @return w value
    */
    public int getW()
    {
        return w;
    }

    /**
    * getH() - Returns the h value
    * @return h value
    */
    public int getH()
    {
        return h;
    }

    /**
    * getLeft() - Returns the left BSPLeaf
    * @return the left BSPLeaf
    */
    public BSPLeaf getLeft()
    {
        return left;
    }

    /**
    * getRight() - Returns the right BSPLeaf
    * @return the right BSPLeaf
    */
    public BSPLeaf getRight()
    {
        return right;
    }

    /**
    * getRoom() - Returns the room
    * @return the room
    */
    public Room getRoom()
    {
        return room;
    }

    /**
    * getCenter() - Returns the center point
    * @return the center point
    */
    public Point getCenter()
    {
        return center;
    }

    /**
    * setX() - Sets the x coordinate
    * @param x the x coordinate to be set
    */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
    * setY() - Sets the y coordinate
    * @param y the y coordinate to be set
    */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
    * setW() - Sets the w value
    * @param w the w value to be set
    */
    public void setW(int w)
    {
        this.w = w;
    }

    /**
    * setH() - Sets the h value
    * @param h the h value to be set
    */
    public void setH(int h)
    {
        this.h = h;
    }

    /**
    * setLeft() - Sets the left BSPLeaf
    * @param h the left BSPLeaf to be set
    */
    public void setLeft(BSPLeaf newLeft)
    {
        left = newLeft;
    }

    /**
    * setRight() - Sets the right BSPLeaf
    * @param h the right BSPLeaf to be set
    */
    public void setRight(BSPLeaf newRight)
    {
        right = newRight;
    }

    /**
    * setRoom() - Sets the room
    * @param h the room to be set
    */
    public void setRoom(Room r)
    {
        room = r;
    }

    /**
    * split() - Splits the BSPLeaf's recursively
    */
    public void split()
    {
        // Declare Variables and Object References
        BSPLeaf l = null, r = null;
        int splitOffset;
        boolean splitH;

        // If current BSPLeaf w or h greater than MAX_LEAF_SIZE
        if (this.w > MAX_LEAF_SIZE || this.h > MAX_LEAF_SIZE)
        {
            splitH = ExtraTools.randomBoolean(); // Random boolean value stored to splitH

            // splitH false if w / h >= 1.25
            if ((double)w / h >= 1.25)
                splitH = false;

            // splitH true if h / w >= 1.25
            if ((double)h / w >= 1.25)
                splitH = true;

            // If splitH true
            if (splitH)
            {
                // if height >= 2 * MIN_LEAF_SIZE
                if (h >= 2 * MIN_LEAF_SIZE) // gen splitOffset
                {
                    splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, h - MIN_LEAF_SIZE); // gets random range

                    l = new BSPLeaf(x, y, w, splitOffset);
                    r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);

                    while (splitOffset < MIN_LEAF_SIZE && h - splitOffset < MIN_LEAF_SIZE) // while not valid keep regenerating
                    {
                        splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, h - MIN_LEAF_SIZE);

                        l = new BSPLeaf(x, y, w, splitOffset);
                        r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);
                    }
                }
            }
            else // if splitH false
            {
                // if width >= 2 * MIN_LEAF_SIZE
                if (w >= 2 * MIN_LEAF_SIZE)
                {
                    splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, w - MIN_LEAF_SIZE); // get random range

                    l = new BSPLeaf(x, y, splitOffset, h);
                    r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);

                    while (splitOffset < MIN_LEAF_SIZE && w - splitOffset < MIN_LEAF_SIZE) // regenerate rand while invalid
                    {
                        splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, w - MIN_LEAF_SIZE);

                        l = new BSPLeaf(x, y, splitOffset, h);
                        r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);
                    }
                }
            }

            left = l;
            right = r;

            if (left != null) // split left (recursive call) if left not null
                left.split();

            if (right != null) // split right (recursivecall) if right not null
                right.split();
        }
    }

    /**
    * getLeaves() - Returns an ArrayList of BSPLeaf's
    * @return ArrayList of BSPLeaf's
    */
    public ArrayList<BSPLeaf> getLeaves()
    {
        // ArrayList
        ArrayList<BSPLeaf> leaves = new ArrayList<>();

        // Make sure non-null, get leaves to add to ArrayList
        if (left != null || right != null)
        {
            if (left != null)
                leaves.addAll(left.getLeaves());

            if (right != null)
                leaves.addAll(right.getLeaves());
        }
        // Adds current BSPLeaf if no non-null
        else
        {
            leaves.add(this); // Add current if all null
        }

        // Return the ArrayList
        return leaves;
    }
}
