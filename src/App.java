
import java.util.ArrayList;

import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    static int[][] board;
    final static int height = 600;
    final static int width = 1000;
    Worldgen world;
    ArrayList<Cow> cows = new ArrayList<Cow>();
    static ArrayList<Grass> grasss = new ArrayList<Grass>();

    public void settings() {
        size(width, height);
    }

    public void setup() {
        frameRate(30);
        board = new int[height][width];
        world = new Worldgen();
        board = world.getarr();
        noStroke();
    }

    public void draw() {
        background(0);
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    pixels[i * width + j] = color(0, 0, 255);
                }
                if (board[i][j] == 1) {
                    pixels[i * width + j] = color(0, 255, 0);
                }
                if (board[i][j] == 2) {
                    pixels[i * width + j] = color(70, 70, 70);
                }
                if (board[i][j] == 3) {
                    pixels[i * width + j] = color(255);
                }
            }
        }

        updatePixels();

        if ((int) random(cows.size() * cows.size()) == 0) {
            cows.add(new Cow());
        }
        if ((int) random(grasss.size() * grasss.size() * 10) == 0) {
            grasss.add(new Grass());
        }

        for (int i = cows.size() - 1; i >= 0; i--) {
            cows.get(i).update();
            if (cows.get(i).hp <= 0) {
                cows.remove(i);
            }
        }

        for (int i = 0; i < cows.size(); i++) {
            fill(0);
            circle(cows.get(i).getxpos(), cows.get(i).getypos(), 15);
            fill(212, 211, 203, cows.get(i).hp * 150 / cows.get(i).maxhp + 105);
            circle(cows.get(i).getxpos(), cows.get(i).getypos(), 15);
        }
        for (int i = 0; i < grasss.size(); i++) {
            fill(214, 214, 79);
            rect(grasss.get(i).getxpos() - 4, grasss.get(i).getypos() - 25, 8, 25);
        }
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getterrain(int a, int b) {
        return board[b][a];
    }

    public static int getgrassssize() {
        return grasss.size();
    }

    public static Grass getgrass(int a) {
        return grasss.get(a);
    }
}
