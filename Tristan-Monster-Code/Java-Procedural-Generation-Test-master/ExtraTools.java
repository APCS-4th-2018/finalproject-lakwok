import java.util.HashMap;

public class ExtraTools
{
    public static int randomRange(int lower, int higher)
    {
        return (int)(Math.random() * (higher - lower + 1)) + lower;
    }

    public static boolean randomBoolean()
    {
        return (Math.random() < 0.5);
    }

    public static void fillSector(int[][] map, int fill, int x1, int x2, int y1, int y2)
    {
        for (int x = x1; x < x2; x++)
            for (int y = y1; y < y2; y++)
                map[y][x] = fill;
    }

    private static Integer maxConsecTile(int[][] map, Room rm, char direction)
    {
        int roomX = rm.getX(), roomY = rm.getY(), roomW= rm.getW(), roomH = rm.getH();

        int low = 0, high = 0, axis = 0;

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

        for (int i = low; i <= high; i++)
        {
            if (direction == 'N' || direction == 'S')
            {
                if (map[axis][i] == -2)
                {
                    int consec = 1;

                    while (i < high - 1 && map[axis][i + 1] == -2)
                    {
                        consec++;
                        i++;
                    }

                    if (consec > maxConsec)
                        maxConsec = consec;
                }
            }
            else
            {
                if (map[i][axis] == -2)
                {
                    int consec = 1;

                    while (i < high - 1 && map[i + 1][axis] == -2)
                    {
                        consec++;
                        i++;
                    }

                    if (consec > maxConsec)
                        maxConsec = consec;
                }
            }
        }

        return maxConsec;
    }

    public static int[] getRoomSurroundings(int[][] map, Room rm)
    {
        int[] counts = {maxConsecTile(map, rm, 'N'),
                        maxConsecTile(map, rm, 'E'),
                        maxConsecTile(map, rm, 'S'),
                        maxConsecTile(map, rm, 'W')};

        return counts;
    }
}
