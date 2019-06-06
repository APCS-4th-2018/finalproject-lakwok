public class ExtraTools
{
    /**
    * randomRange() - Returns a random number from a specified range
    * @param lower lower bound of range
    * @param higher upper bound of rang
    * @return random number from the range given by the lower and upper bounds
    */
    public static int randomRange(int lower, int higher)
    {
        return (int)(Math.random() * (higher - lower + 1)) + lower;
    }

    /**
    * randomBoolean() - Returns a random boolean
    * @return random boolean
    */
    public static boolean randomBoolean()
    {
        return (Math.random() < 0.5);
    }

    /**
    * fillSector() - Fills a specified sector on the map matrix
    */
    public static void fillSector(int[][] map, int fill, int x1, int x2, int y1, int y2)
    {
        for (int x = x1; x < x2; x++)
            for (int y = y1; y < y2; y++)
                map[y][x] = fill;
    }

    private static Integer maxConsecTile(int[][] map, Room rm, char direction)
    {
        // Room dimensions
        int roomX = rm.getX(), roomY = rm.getY(), roomW= rm.getW(), roomH = rm.getH();

        int low = 0, high = 0, axis = 0;

        // Switch case to establish bounds for checking based on orientation
        switch (direction)
        {
            case 'N':
                low = roomX - 1;
                high = roomX + roomW;
                axis = roomY - 1;
                break;
            case 'S':
                low = roomX - 1;
                high = roomX + roomW;
                axis = roomY + roomH;
                break;
            case 'W':
                low = roomY;
                high = roomY + roomH - 1;
                axis = roomX - 1;
                break;
            case 'E':
                low = roomY;
                high = roomY + roomH - 1;
                axis = roomX + roomW;
                break;
            default:
                break;
        }

        int maxConsec = 0;

        // Loop from low to high, counting maximum consecutive length of hallways
        for (int i = low; i <= high; i++)
        {
            if (direction == 'N' || direction == 'S')
            {
                // If hallway
                if (map[axis][i] == -2)
                {
                    int consec = 1;

                    // Find consecutive hallways horizontally
                    while (i < high - 1 && map[axis][i + 1] == -2)
                    {
                        consec++;
                        i++;
                    }

                    // If new maximum, reset
                    if (consec > maxConsec)
                        maxConsec = consec;
                }
            }
            else
            {
                // hallway
                if (map[i][axis] == -2)
                {
                    int consec = 1;

                    // Find consecutive hallways vertically
                    while (i < high - 1 && map[i + 1][axis] == -2)
                    {
                        consec++;
                        i++;
                    }

                    // If new maximum, reset
                    if (consec > maxConsec)
                        maxConsec = consec;
                }
            }
        }

        return maxConsec;
    }

    /**
    * getRoomSurroundings() - Returns a room surrounding matrix
    * @return room surrounding matrix
    */
    public static int[] getRoomSurroundings(int[][] map, Room rm)
    {
        int[] counts = {maxConsecTile(map, rm, 'N'),
                maxConsecTile(map, rm, 'E'),
                maxConsecTile(map, rm, 'S'),
                maxConsecTile(map, rm, 'W')};

        return counts;
    }
}
