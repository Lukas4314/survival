import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int height = 600;
    int width = 600;
    int[][] board;
    float avg;
    int counter;
    int hav = 4;

    public void settings() {
        size(height, width);
    }

    public void setup() {
        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 128 + (int) random(-10, 10);
            }
        }
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i > hav && j > hav && i < width - hav && j < height - hav) {
                        // (i != 0 && j != 0 && i != 599 && j != 599) {
                        board[i][j] = (board[i - 1][j] +
                                board[i + 1][j] +
                                board[i][j - 1] +
                                board[i][j + 1] +
                                board[i - 1][j - 1] +
                                board[i + 1][j + 1] +
                                board[i - 1][j + 1] +
                                board[i + 1][j - 1]) / 8
                                + (int) random(-10, 10);
                    }
                }
            }
        }
        counter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i > hav && j > hav && i < width - hav && j < height - hav) {
                    avg = avg + board[i][j];
                    counter++;
                }
            }
        }
        avg = avg / counter;
    }

    public void draw() {
        background(0);
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (board[i][j] < avg) {
                    pixels[i * width + j] = color(255);
                } else {
                    pixels[i * width + j] = color(0);
                }

            }

        }
        updatePixels();
    }

}