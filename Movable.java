public abstract class Movable
{
    protected double x, y;
    protected char orientation;

    public Movable(double xPos, double yPos)
    {
        x = xPos;
        y = yPos;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public char getOrientation()
    {
        return orientation;
    }

    public abstract void move(char direction, double delta);
}
