package OLDAPPROACH;

import java.awt.*;
import java.util.ArrayList;



public class DungeonMap //extends DungeonMap
{
    /**
    private final double W_DISCARD_RATIO = 0.3;
    private final double H_DISCARD_RATIO = 0.3;
    private final int TILE_WIDTH = 32;

    private int mapWidth, mapHeight, mapLevel;
    private int[][] tileMap;
    private Canvas mapCanvas;

    private ArrayList<OLDAPPROACH.Room> rooms;
    private DungeonTreeNode dungeonTree;

    public DungeonMap(int width, int height, int level)
    {
        mapWidth = width;
        mapHeight = height;
        mapLevel = level;

        tileMap = new int[mapWidth][mapHeight];
        mapCanvas = new Canvas(mapWidth * TILE_WIDTH, mapHeight * TILE_WIDTH);
        
        rooms = new ArrayList<OLDAPPROACH.Room>();
        dungeonTree = null;
    }

    public int[][] getTileMap()
    {
        return tileMap;
    }

    public Canvas getMapCanvas()
    {
        return mapCanvas;
    }

    private void binarySpacePartition()
    {
        DungeonContainer mainContainer = new DungeonContainer(0, 0, mapWidth - 1, mapHeight - 1, null);
        dungeonTree = splitContainer(mainContainer, 2);
    }

    private void placeRooms()
    {
        int minLevel = 1 + mapLevel / 2;
        int maxLevel = mapLevel + 1;

        for (DungeonContainer dc : dungeonTree.getLeaves())
        {
            OLDAPPROACH.Room temp = new OLDAPPROACH.MonsterRoom(dc, ExtraTools.randomRange(minLevel, maxLevel));
            dc.setRoom(temp);
            rooms.add(dc.getRoom());

            int xBound = temp.getX() + temp.getW();
            int yBound = temp.getY() + temp.getH();

            for (int x = temp.getX(); x < temp.getX() + temp.getW(); x++)
            {
                for (int y = temp.getY(); y < temp.getY() + temp.getH(); y++)
                {
                    tileMap[x][y] = 1;
                }
            }
        }
    }

    private void placeHallways(DungeonTreeNode node)
    {
        DungeonTreeNode left = node.getLeft();
        DungeonTreeNode right = node.getRight();
        
        while (left != null && right != null)
        {
            Point lcenter = left.getLeaf().getCenter();
            Point rcenter = right.getLeaf().getCenter();
            
            int lx = (int)lcenter.getX();
            int ly = (int)lcenter.getY();
            int rx = (int)rcenter.getX();
            int ry = (int)rcenter.getY();
            
            if (lx == rx)
            {
                for (int y = ly; y < ry; y++)
                    tileMap[lx][y] = 1;
            }
            else
            {
                for (int x = lx; x < rx; x++)
                    tileMap[x][ly] = 1;
            }
            
            placeHallways(left);
            placeHallways(right);
        }
    }
    
    private void initializeTileMap()
    {
        // INITIALIZE FLOOR TILES
        placeRooms();
        
        // IMPLEMENT HALLWAYS
        placeHallways(dungeonTree);
        
        // IMPLEMENT WALL PLACEMENT
        
        // IMPLEMENT SPAWN AND TREASURE ROOM PLACEMENT
    }
    
    public void initialize()
    {
        binarySpacePartition();
        System.out.println("ACTUALLY FINISHED BSP LOL");
        initializeTileMap();
    }

    private ArrayList<DungeonContainer> randomSplit(DungeonContainer c)
    {
        ArrayList<DungeonContainer> split = new ArrayList<DungeonContainer>();
        DungeonContainer cont1, cont2;

        if ((int)(Math.random() * 10) < 5)
        {
            cont1 = new DungeonContainer(c.getX(), c.getY(), ExtraTools.randomRange(1, c.getW()), c.getH(), null);
            cont2 = new DungeonContainer(c.getX() + cont1.getW(), c.getY(), c.getW() - cont1.getW(), c.getH(), null);

            
            double cont1_w_ratio = (double)cont1.getW() / cont1.getH();
            double cont2_w_ratio = (double)cont2.getW() / cont2.getH();

            while (cont1_w_ratio < W_DISCARD_RATIO || cont2_w_ratio < W_DISCARD_RATIO)
            {
                cont1 = new OLDAPPROACH.DungeonContainer(c.getX(), c.getY(), ExtraTools.randomRange(1, c.getW()), c.getH(), null);
                cont2 = new OLDAPPROACH.DungeonContainer(c.getX() + cont1.getW(), c.getY(), c.getW() - cont1.getW(), c.getH(), null);

                cont1_w_ratio = (double) cont1.getW() / cont1.getH();
                cont2_w_ratio = (double) cont2.getW() / cont2.getH();
            }
             
        }
        else
        {
            cont1 = new DungeonContainer(c.getX(), c.getY(), c.getW(), ExtraTools.randomRange(1, c.getH()), null);
            cont2 = new DungeonContainer(c.getX(), c.getY() + cont1.getH(), c.getW(), c.getH() - cont1.getH(), null);

            
            double cont1_h_ratio = cont1.getH() / cont1.getW();
            double cont2_h_ratio = cont2.getH() / cont2.getW();

            while (cont1_h_ratio < H_DISCARD_RATIO|| cont2_h_ratio < H_DISCARD_RATIO)
            {
                cont1 = new OLDAPPROACH.DungeonContainer(c.getX(), c.getY(), c.getW(), ExtraTools.randomRange(1, c.getH()), null);
                cont2 = new OLDAPPROACH.DungeonContainer(c.getX(), c.getY() + cont1.getH(), c.getW(), c.getH() - cont1.getH(), null);

                cont1_h_ratio = cont1.getH() / cont1.getW();
                cont2_h_ratio = cont2.getH() / cont2.getW();
            }
             
        }

        split.add(cont1);
        split.add(cont2);

        return split;
    }

    public DungeonTreeNode splitContainer(DungeonContainer c, int recurLevel)
    {
        DungeonTreeNode root = new DungeonTreeNode(c);

        if (recurLevel != 0)
        {
            ArrayList<DungeonContainer> split = randomSplit(c);
            root.setLeft(splitContainer(split.get(0), recurLevel - 1));
            root.setRight(splitContainer(split.get(1), recurLevel - 1));
        }

        return root;
    }
    */
}
