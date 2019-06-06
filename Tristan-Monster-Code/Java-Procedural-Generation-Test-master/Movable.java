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

    public void move(char direction, double delta)
    {

        orientation = direction;

        double newX = x;
        double newY = y;

        switch (direction)
        {
            case 'N':
                newY -= delta;

                //if (newY < bound.getY()) newY = y;

                break;
            case 'S':
                newY += delta;

                //if (newY > bound.getY() + bound.getH() - 1) newY = y;

                break;
            case 'W':
                newX -= delta;

                //if (newX < bound.getX()) newX = x;

                break;
            case 'E':
                newX += delta;

                //if (newX > bound.getX() + bound.getW() - 1) newX = x;

            default:
                break;
        }

        x = newX;
        y = newY;
    }
}
