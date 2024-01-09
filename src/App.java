
import java.util.ArrayList;

import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int[][] board;
    final static int height = 600;
    final static int width = 1000;
    Worldgen world;
    ArrayList<Cow> cows = new ArrayList<Cow>();

    public void settings() {
        size(width, height);
    }

    public void setup() {
        board = new int[height][width];
        world = new Worldgen();
        board = world.getarr();
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

        if ((int) random(cows.size() * cows.size()) == 0) {
            cows.add(new Cow());
            System.out.println("tilføjet en");
        }
        fill(255, 0, 0);
        for (int i = 0; i < cows.size(); i++) {
            circle(cows.get(i).getxpos(), cows.get(i).getypos(), 15);
        }
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
}
