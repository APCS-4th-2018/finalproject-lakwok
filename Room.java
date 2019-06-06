import java.awt.*;

/**
 * Room - A class to create dungeon rooms
 *
 * @author Andrew Wang
 * @version June 6, 2019
 */
public class Room
{
    private final int PADDING = 2;

    private int x, y, w, h;
    private Point center;

    /**
    * Room() - Constructor for the Room class
    * @param leaf the BSPLeaf to get position coodernates and values from
    */
    public Room(BSPLeaf leaf)
    {
        int leafX = leaf.getX();
        int leafY = leaf.getY();
        int leafW = leaf.getW();
        int leafH = leaf.getH();

        x = leafX + ExtraTools.randomRange(PADDING, leafW / 4);
        y = leafY + ExtraTools.randomRange(PADDING, leafH / 4);
        w = leafW - (this.x - leafX) - PADDING;
        h = leafH - (this.y - leafY) - PADDING;

        w -= ExtraTools.randomRange(PADDING, this.w / 4);
        h -= ExtraTools.randomRange(PADDING, this.h / 4);

        center = new Point(x + w/2, y + h/2);
    }

    /**
    * getX() - Returns x coordinate
    * @return x coordinate
    */
    public int getX()
    {
        return x;
    }

    /**
    * getY() - Returns y coordinate
    * @return y coordinate
    */
    public int getY()
    {
        return y;
    }

    /**
    * getW() - Returns width value
    * @return width value
    */
    public int getW()
    {
        return w;
    }

    /**
    * getH() - Returns height value
    * @return height value
    */
    public int getH()
    {
        return h;
    }

    /**
    * getH() - Returns center point
    * @return center point
    */
    public Point getCenter()
    {
        return center;
    }

    /**
    * setX() - Sets x coordinate
    * @param x x coordinate to set
    */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
    * setY() - Sets y coordinate
    * @param y y coordinate to set
    */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
    * setW() - Sets width value
    * @param w width value set
    */
    public void setW(int w)
    {
        this.w = w;
    }

    /**
    * setH() - Sets height value
    * @param h height value set
    */
    public void setH(int h)
    {
        this.h = h;
    }
}
