import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application
{
    public final static int TILE_WIDTH = 32;

    private boolean camUp, camDown, camLeft, camRight;
    private double camX = 0, camY = 0, camW = 800, camH = 800;


    /**
     * start() - Method containing the GUI's code
     * @param stage Main stage the JavaFX application uses
     */
    @Override
    public void start(Stage stage)
    {
        Scene menuScene, dungeonScene, helpScene;

        // Main Menu
        Group mainGroup = new Group();
        menuScene = new Scene(mainGroup, 1000, 800);
        menuScene.getStylesheets().add("GUI.css");

        VBox mainVBox = new VBox();
        mainVBox.setLayoutY(300);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(75);

        Canvas menuCanvas = new Canvas(1000, 800);

        // Dungeon Scene
        BSPTree tree = new BSPTree(100, 100);
        tree.loadMap();

        Group dungeonGroup = new Group();

        Canvas mapLayer = drawDungeon(tree, 0);
        Canvas charLayer = new Canvas(mapLayer.getWidth(), mapLayer.getHeight());
        Canvas projectiles = new Canvas(mapLayer.getWidth(), mapLayer.getHeight());

        dungeonGroup.getChildren().addAll(mapLayer, charLayer, projectiles);

        dungeonScene = new Scene(dungeonGroup, 1000, 800);

        // Main Scene cont.
        Button playButton = new Button("Play");
        playButton.setId("sand-brown");
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setScene(dungeonScene);
            }
        });


        // Help Screen
        Group helpGroup = new Group();
        helpScene = new Scene(helpGroup, 1000, 800);
        helpScene.getStylesheets().add("GUI.css");

        Label label = new Label("WASD to move\n\nArrow keys to move camera\n\nClick to shoot\n\n");
        label.setLayoutX(400);

        Button back = new Button("Back");
        back.setId("sand-brown");
        back.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setScene(menuScene);
            }
        });

        VBox helpVBox = new VBox(label, back);
        helpVBox.setAlignment(Pos.CENTER);
        helpVBox.setSpacing(100);

        helpGroup.getChildren().addAll(helpVBox);

        // Main scene cont.
        Button helpButton = new Button("Help");
        helpButton.setId("sand-brown");
        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setScene(helpScene);
            }
        });

        Button quitButton = new Button("Quit");
        quitButton.setId("sand-brown");
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.exit(0);
            }
        });


        mainVBox.getChildren().addAll(playButton, helpButton, quitButton, menuCanvas);
        mainGroup.getChildren().add(mainVBox);


        // Character Handling
        Point spawn = tree.getRoot().getLeaves().get(0).getRoom().getCenter();
        Character mc = new Character(tree, spawn.getX() * TILE_WIDTH, spawn.getY() * TILE_WIDTH, 5, 100, 10);

        ProgressBar hpBar = new ProgressBar();
        hpBar.setLayoutX(15);
        hpBar.setLayoutY(15);
        hpBar.setPrefHeight(50);
        hpBar.setPrefWidth(250);
        hpBar.setProgress(1);

        dungeonGroup.getChildren().add(hpBar);

        GraphicsContext gc = charLayer.getGraphicsContext2D();
        GraphicsContext projgc = projectiles.getGraphicsContext2D();

        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(mc.getX(), mc.getY(), 32, 32);

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


        dungeonScene.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                mc.attack(event);
            }
        });

        mapLayer.setTranslateX(mc.getX());
        mapLayer.setTranslateY(mc.getY());

        projectiles.setTranslateX(mc.getX());
        projectiles.setTranslateY(mc.getY());


        AnimationTimer animationTimer = new AnimationTimer()
        {

            @Override
            public void handle(long now)
            {
                if (!mc.gameOver())
                {
                    hpBar.setProgress((double) mc.getCurrHp() / mc.getMaxHp());

                    for (Bullet b : mc.getBulletsFired())
                    {
                        projgc.clearRect(b.getX(), b.getY(), 16, 16);
                        b.update();
                        projgc.setFill(Color.GOLD);
                        projgc.fillRect(b.getX(), b.getY(), 16, 16);
                    }

                    double delta = TILE_WIDTH / 4;

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

                    mapLayer.setTranslateX(camX);
                    mapLayer.setTranslateY(camY);
                    charLayer.setTranslateX(camX);
                    charLayer.setTranslateY(camY);
                    projectiles.setTranslateX(camX);
                    projectiles.setTranslateY(camY);


                }
                else
                {
                    this.stop();

                }
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

    /**
     * drawDungeon() - Draws the dungeon's tiles
     * @param btree BSPTree containing a loaded map to draw
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
