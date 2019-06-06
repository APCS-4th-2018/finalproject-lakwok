/**
 * Movable - An abstract class objects that move can extend
 *
 * @author Andrew Wang
 * @version June 6, 2019
 */
public abstract class Movable
{
    // Variables-instance
    protected double x, y;
    protected char orientation;

    /**
    * Movable() - Constructor implemented for the Movable abstract class
    * @param xPos the initial x coordinate position
    * @param yPos the initial y coordinate position
    */
    public Movable(double xPos, double yPos)
    {
        // x and y coordinate position
        x = xPos;
        y = yPos;
    }

    /**
    * getX() - Returns the x coordinate
    * @return the x coordinate
    */
    public double getX()
    {
        return x;
    }

    /**
    * getY() - Returns the y coordinate
    * @return the y coordinate
    */
    public double getY()
    {
        return y;
    }

    /**
    * getOrientation() - Returns the orientation
    * @return orientation
    */
    public char getOrientation()
    {
        return orientation;
    }

    /**
    * move() - Moves the specified distance in the specified directio
    * @param direction the direction to move in
    * @param delta the amount to move
    */
    public abstract void move(char direction, double delta); // Not implemented
}
