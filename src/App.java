
import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int[][] board;
    final static int height = 600;
    final static int width = 1000;
    Worldgen world;

    public void settings() {
        size(width, height);
    }

    public void setup() {
        board = new int[height][width];
        world = new Worldgen();
        board = world.getarr();
        System.out.println(board[1][0]);
    }

    public void draw() {
        background(0);
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = color(world.board[i][j]);
            }
        }
        updatePixels();
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
}
