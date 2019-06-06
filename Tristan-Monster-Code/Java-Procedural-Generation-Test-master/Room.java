import java.awt.*;
import java.util.ArrayList;

public abstract class Room
{
    protected final int PADDING = 2;

    protected int x, y, w, h;
    protected Point center;

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

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public Point getCenter()
    {
        return center;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setW(int w)
    {
        this.w = w;
    }

    public void setH(int h)
    {
        this.h = h;
    }
}
