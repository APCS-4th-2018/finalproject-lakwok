import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application
{
    private final static int TILE_WIDTH = 16;

    /**
     *  Format and import the scene and stage
     * @param Scene Import
     */
    @Override
    public void start(Stage stage)
    {
        BSPTree tree = new BSPTree(100, 100);
        tree.loadMap();

        Group g = new Group();
        Canvas canvas = drawDungeon(tree, 0);
        g.getChildren().addAll(canvas);

        Scene scene = new Scene(g, 800, 800);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    // Helper
    /**
     * Creating the Dungeon by creating tiles via width/height case staements. Each title is filled by a specific color, based on the specific role/location of it
     * @param Dungeon
     */
    public Canvas drawDungeon(BSPTree btree, int initX)
    {
        int x = initX;
        int y = 0;

        Canvas cnv = new Canvas(btree.getDungeonWidth() * TILE_WIDTH, btree.getDungeonHeight() * TILE_WIDTH);
        GraphicsContext gc = cnv.getGraphicsContext2D();

        int[][] map = btree.getTileMap();

        // Draw tiles
        for (int r = 0; r < map.length; r++)
        {
            for (int c = 0; c < map[r].length; c++)
            {
                switch (map[r][c])
                {
                    case -1:
                        gc.setFill(Color.BLUE);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -2:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -3:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -4:
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -98:
                        gc.setFill(Color.ROSYBROWN);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -99:
                        gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;
                    case -10:
                        gc.setFill(Color.GRAY);
                        gc.fillRect(x, y, TILE_WIDTH, TILE_WIDTH);
                        break;

                }
                x += TILE_WIDTH;
            }
            x = initX;
            y += TILE_WIDTH;
        }

        return cnv;
    }
}
