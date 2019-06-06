import apcslib.Format;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * BSPTree - A class implementing
 */
public class BSPTree
{
    // Variable and Object Ref Declarations
    private int dungeonWidth, dungeonHeight;

    private BSPLeaf root;

    private int[][] tileMap;

    /**
    * BSPTree() - Constructor for the BSPTree class
    * @param width width of dungeon
    * @param height height of dungeon
    */
    public BSPTree(int width, int height)
    {
        dungeonWidth = width;
        dungeonHeight = height;
        root = new BSPLeaf(0, 0, width, height); // Instantiate a BSPLeaf object
        root.split(); // Call split() method in BSPLeaf

        tileMap = new int[height][width]; // Matrix tile map of [h][w] instance
    }

    /**
    * getDungeonWidth() - Returns the width of the dungeon
    * @return dungeon width
    */
    public int getDungeonWidth()
    {
        return dungeonWidth;
    }

    /**
    * getDungeonHeight() - Returns the height of the dungeon
    * @return dungeon height
    */
    public int getDungeonHeight()
    {
        return dungeonHeight;
    }

    /**
    * getTileMap() - Returns tile map matric
    * @return tile map matrix
    */
    public int[][] getTileMap()
    {
        return tileMap;
    }

    /**
    * getRoot() - Returns roof BSPLeaf
    * @return root BSPLeaf
    */
    public BSPLeaf getRoot()
    {
        return root;
    }

    /**
    * loadMap() - Loads Map
    */
    public void loadMap()
    {
        placeHallways(root); // Generate hallways from root BSP
        placeRooms(); // Generate rooms

        adjustMap(3); // adjust map according to hallway width
        placeWalls();

        placeSpecial();

    }

    private void placeHallways(BSPLeaf leaf)
    {
        if (leaf.getLeft() != null && leaf.getRight() != null)
        {
            Point leftCenter = leaf.getLeft().getCenter(), rightCenter = leaf.getRight().getCenter();

            int lCenterX = (int)leftCenter.getX(), lCenterY = (int)leftCenter.getY(), rCenterX = (int)rightCenter.getX(), rCenterY = (int)rightCenter.getY();

            // fills sectors appropriately
            if (lCenterX == rCenterX)
                ExtraTools.fillSector(tileMap, -2, lCenterX - 1, lCenterX + 2, lCenterY, rCenterY);
            else
                ExtraTools.fillSector(tileMap, -2, lCenterX, rCenterX, lCenterY - 1, lCenterY + 2);

            // recursive calls
            placeHallways(leaf.getLeft());
            placeHallways(leaf.getRight());
        }
    }

    private void placeRooms()
    {
        // Place floor tiles
        for (BSPLeaf leaf : root.getLeaves())
        {
            Room temp = new MonsterRoom(leaf, 5); // Instantiate MonsterRoom
            leaf.setRoom(temp); // Set new MonsterRoom (temp) as room in leaves

            // fill sectors
            ExtraTools.fillSector(tileMap, -1, temp.getX(), temp.getX() + temp.getW(), temp.getY(), temp.getY() + temp.getH());
        }
    }

    /**
    * adjustMap() - adjusts map according to hallway width
    * @param hallwayWidth width of the hallway
    */
    public void adjustMap(int hallwayWidth)
    {
        int repeat = 25;

        while (repeat > 0) // repeat until reaches 0 (minus 1 every run)
        {
            for (BSPLeaf leaf : root.getLeaves()) {
                Room leafRoom = leaf.getRoom(); // set leafRoom to room in leaf

                // array for wall
                int[] wallCounts = ExtraTools.getRoomSurroundings(tileMap, leafRoom);

                // if position is greater than 2 times hallwayWidth
                if (wallCounts[0] > 2 * hallwayWidth)
                {
                    ExtraTools.fillSector(tileMap, -1, leafRoom.getX(), leafRoom.getX() + leafRoom.getW(), leafRoom.getY() - 1, leafRoom.getY());
                    leafRoom.setY(leafRoom.getY() - 1);
                    leafRoom.setH(leafRoom.getH() + 1);
                }

                // if position is greater than 2 times hallwayWidth
                if (wallCounts[1] > 2 * hallwayWidth)
                {
                    ExtraTools.fillSector(tileMap, -1, leafRoom.getX() + leafRoom.getW(), leafRoom.getX() + leafRoom.getW() + 1, leafRoom.getY(), leafRoom.getY() + leafRoom.getH());
                    leafRoom.setW(leafRoom.getW() + 1);
                }

                // if position is greater than 2 times hallwayWidth
                if (wallCounts[2] > 2 * hallwayWidth)
                {
                    ExtraTools.fillSector(tileMap, -1, leafRoom.getX(), leafRoom.getX() + leafRoom.getW(), leafRoom.getY() + leafRoom.getH(), leafRoom.getY() + leafRoom.getH() + 1);
                    leafRoom.setH(leafRoom.getH() + 1);
                }

                // if position is greater than 2 times hallwayWidth
                if (wallCounts[3] > 2 * hallwayWidth)
                {
                    ExtraTools.fillSector(tileMap, -1, leafRoom.getX() - 1, leafRoom.getX(), leafRoom.getY(), leafRoom.getY() + leafRoom.getH());
                    leafRoom.setX(leafRoom.getX() - 1);
                    leafRoom.setW(leafRoom.getW() + 1);
                }
            }

            repeat -= 1; // decrement repeat
        }
    }

    private boolean makeSurroundCondition(int r, int c, int tileType)
    {
        return tileMap[r + 1][c] == tileType || tileMap[r - 1][c] == tileType || tileMap[r][c + 1] == tileType || tileMap[r][c - 1] == tileType || tileMap[r + 1][c + 1] == tileType || tileMap[r + 1][c - 1] == tileType || tileMap[r - 1][c + 1] == tileType || tileMap[r - 1][c - 1] == tileType;
    }

    /**
    * placeWalls() - generate walls
    */
    public void placeWalls()
    {
        // Padding for room generation
        int pad = 1;

        // Initial wall placement
        for (int r = pad; r < tileMap.length - pad; r++)
        {
            for (int c = pad; c < tileMap[r].length - pad; c++)
            {

                if (makeSurroundCondition(r, c, -1))
                {
                    if (tileMap[r][c] == -2)
                        tileMap[r][c] = -4;

                    if (tileMap[r][c] == 0)
                        tileMap[r][c] = -3;
                }
            }
        }

        // Place walls around hallways
        for (int r = pad; r < tileMap.length - pad; r++)
            for (int c = pad; c < tileMap[r].length - pad; c++)
                if (tileMap[r][c] == 0 && makeSurroundCondition(r, c, -2))
                    tileMap[r][c] = -3;
    }

    /**
    * placeSpecial() - generates special objects
    */
    public void placeSpecial()
    {
        ArrayList<BSPLeaf> leaves = root.getLeaves(); // ArrayList of BSPLeaf's

        Room spawn = leaves.get(0).getRoom(); // spawn

        int spawnX = (int)spawn.getCenter().getX();
        int spawnY = (int)spawn.getCenter().getY();

        tileMap[spawnY][spawnX] = -99; // set spawn position on tile map to -99

        Room end = leaves.get(leaves.size() - 1).getRoom(); // end

        int endX = (int)end.getCenter().getX(); // center
        int endY = (int)end.getCenter().getY();

        ExtraTools.fillSector(tileMap, -98, endX - 1, endX + 2, endY - 1, endY + 2); // fill sector

        Room test = root.getLeaves().get(0).getRoom();

        int centerX = (int)spawn.getCenter().getX();
        int centerY = (int)spawn.getCenter().getY();

        tileMap[centerY][centerX] = -99; // set ctr position on tile map to -99
    }


    // DEBUG METHODS

    /**
    * clearMap() - clears the map and fills with 0, for debugging purposes
    */
    public void clearMap()
    {
        // traverse
        for (int r = 0; r < tileMap.length; r++)
        {
            for (int c = 0; c < tileMap[0].length; c++)
                tileMap[r][c] = 0; // set each element to 0
        }
    }

    private void printMap()
    {
        // prints the map, for debugging and testing
        // use for loop, traverse prnt
        for (int r = 0; r < tileMap.length; r++)
        {
            for (int c = 0; c < tileMap[r].length; c++)
            {
                System.out.print(Format.left(tileMap[r][c], 4));
            }
            System.out.println();
        }
    }

    private void checkTooSmall()
    {
        // check if the height or width too small (less than 15), for debugging
        for (BSPLeaf leaf : root.getLeaves())
        {
            if (leaf.getH() < 15 || leaf.getW() < 15)
            {
                System.out.println("Gen failed.");
            }
        }
    }

    private void loadLeafBorders()
    {
        // load leaf borders, for debugging and testing
        // use for loops to traverse, set each in matric
        for (BSPLeaf leaf : root.getLeaves())
        {
            for (int r = leaf.getY(); r < leaf.getY() + leaf.getH(); r++)
            {
                if (r == leaf.getY() || r == leaf.getY() + leaf.getH() - 1)
                {
                    for (int c = leaf.getX(); c < leaf.getX() + leaf.getW(); c++)
                        tileMap[r][c] = -10;
                }
                else
                {
                    tileMap[r][0] = -10;
                    tileMap[r][leaf.getX() + leaf.getW() - 1] = -10;
                }
            }
        }
    }
}
