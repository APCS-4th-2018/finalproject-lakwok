import javafx.scene.image.Image;

public enum Tile
{
    TOPWALL(new Image("sprites/wall_corner_top_left.png")),
    BOTTOMWALL(new Image("sprites/")),
    LEFTWALL (new Image("sprites/wall_side_mid_left.png")),
    RIGHTWALL (new Image("sprites/wall_side_front_right.png"));

    public final static int TILE_WIDTH = 64;

    private int x, y;
    private Image texture;

    Tile(Image img)
    {
        //x = xPos;
        //y = yPos;
        texture = img;
    }
}
