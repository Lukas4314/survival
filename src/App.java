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
    int blend = 3;

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
        for (int k = 0; k < 1; k++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i > hav && j > hav && i < width - hav && j < height - hav) {
                        avg = 0;
                        for (int l = -hav; l <= hav; l++) {
                            for (int f = -hav; f <= hav; f++) {
                                avg = avg + board[i - l][j - f];
                            }
                        }
                        avg = avg / (hav * hav);
                        board[i][j] = (int) avg + (int) random(-10, 10);
                        /*
                         * board[i][j] = (board[i - 1][j] +
                         * board[i + 1][j] +
                         * board[i][j - 1] +
                         * board[i][j + 1] +
                         * board[i - 1][j - 1] +
                         * board[i + 1][j + 1] +
                         * board[i - 1][j + 1] +
                         * board[i + 1][j - 1]) / 8
                         * + (int) random(-10, 10);
                         */
                    }
                }
            }
        }
        counter = 0;
        avg = 0;
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