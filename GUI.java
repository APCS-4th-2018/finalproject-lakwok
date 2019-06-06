import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application
{
    private boolean camUp, camDown, camLeft, camRight;
    private double camX = 0, camY = 0, camW = 800, camH = 800;


    @Override
    public void start(Stage stage)
    {
        // Dungeon Scene

        BSPTree tree = new BSPTree(100, 100);
        tree.loadMap();

        Group dungeonGroup = new Group();

        Canvas mapLayer = drawDungeon(tree, 0);
        Canvas charLayer = new Canvas(mapLayer.getWidth(), mapLayer.getHeight());

        dungeonGroup.getChildren().addAll(mapLayer, charLayer);

        Scene dungeonScene = new Scene(dungeonGroup, 1000, 800);

        // Main Menu
        Group mainGroup = new Group();
        VBox mainVBox = new VBox();
        mainVBox.setLayoutY(200);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(100);

        Canvas menuCanvas = new Canvas(1000, 800);
        Button playButton = new Button("Play");
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setScene(dungeonScene);
            }
        });


        mainVBox.getChildren().addAll(playButton, menuCanvas);
        mainGroup.getChildren().add(mainVBox);

        Scene menuScene = new Scene(mainGroup, 1000, 800);

        // Character Handling
        Point spawn = tree.getRoot().getLeaves().get(0).getRoom().getCenter();
        Character mc = new Character(tree, spawn.getX() * Room.TILE_WIDTH, spawn.getY() * Room.TILE_WIDTH, 5, 100, 10);

        GraphicsContext gc = charLayer.getGraphicsContext2D();

        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(mc.getX(), mc.getY(), 16, 16);

        dungeonScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case W:
                        System.out.println("W pressed");
                        camUp = true;
                        break;
                    case A:
                        System.out.println("A pressed");
                        camLeft = true;
                        break;
                    case S:
                        System.out.println("S pressed");
                        camDown = true;
                        break;
                    case D:
                        System.out.println("D pressed");
                        camRight = true;
                        break;
                    default:
                        break;
                }

                System.out.println("Up: " + camUp + " Down: " + camDown + " Left: " + camLeft + " Right: " + camRight);
            }
        });

        dungeonScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case W:
                        System.out.println("W released");
                        camUp = false;
                        break;
                    case A:
                        System.out.println("A released");
                        camLeft = false;
                        break;
                    case S:
                        System.out.println("S released");
                        camDown = false;
                        break;
                    case D:
                        System.out.println("D released");
                        camRight = false;
                        break;
                    default:
                        break;
                }

                System.out.println("Up: " + camUp + " Down: " + camDown + " Left: " + camLeft + " Right: " + camRight);
            }
        });


        mapLayer.setTranslateX(mc.getX());
        mapLayer.setTranslateY(mc.getY());

        AnimationTimer animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {

                double delta = Room.TILE_WIDTH / 4;

                if (camUp)
                {
                    camY += delta;
                    mc.move('N', delta);
                }

                if (camDown)
                {
                    camY -= delta;
                    mc.move('S', delta);
                }

                if (camLeft)
                {
                    camX += delta;
                    mc.move('W', delta);
                }

                if (camRight)
                {
                    camX -= delta;
                    mc.move('E', delta);
                }

                charLayer.setTranslateX(mc.getX());
                charLayer.setTranslateY(mc.getY());
            }
        };

        animationTimer.start();

        stage.setScene(menuScene);

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    // Helper

    public Canvas drawDungeon(BSPTree btree, int initX)
    {
        int x = initX;
        int y = 0;

        Canvas cnv = new Canvas(btree.getDungeonWidth() * Room.TILE_WIDTH, btree.getDungeonHeight() * Room.TILE_WIDTH);
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
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -2:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -3:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -4:
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -98:
                        gc.setFill(Color.ROSYBROWN);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -99:
                        gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;
                    case -10:
                        gc.setFill(Color.GRAY);
                        gc.fillRect(x, y, Room.TILE_WIDTH, Room.TILE_WIDTH);
                        break;

                }
                x += Room.TILE_WIDTH;
            }
            x = initX;
            y += Room.TILE_WIDTH;
        }

        return cnv;
    }
}
