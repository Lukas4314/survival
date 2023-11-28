import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int height = 600;
    int width = 600;
    int[][] board;
    float avg;
    float avg2;
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
        for (int k = 0; k < 70; k++) {
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
                                + (int) random(-5, 5);
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
        // lav det mere pixled
        for (int k = 1; k < 10; k++) {
            for (int i = 0; i <= height; i = i + (int) Math.pow(2, k)) {
                for (int j = 0; j <= width; j = j + (int) Math.pow(2, k)) {
                    // System.out.println(j);
                    if (i + (int) Math.pow(2, k) >= height || j + (int) Math.pow(2, k) >= width) {
                        System.out.println("for stor");
                        System.out.print("i:  ");
                        System.out.println(i);
                        System.out.print("j:  ");

                        System.out.println(j);
                        System.out.print("tal:");

                        System.out.println((int) Math.pow(2, k));

                        break;
                    }
                    avg2 = (board[i][j] + board[i + (int) Math.pow(2, k)][j] + board[i][j + (int) Math.pow(2, k)]
                            + board[i + (int) Math.pow(2, k)][j + (int) Math.pow(2, k)]) / 4;
                    // System.out.println(avg2);
                    board[i][j] = (int) avg2;
                    board[i + 1][j] = (int) avg2;
                    board[i][j + 1] = (int) avg2;
                    board[i + 1][j + 1] = (int) avg2;
                }
            }
        }
        System.out.println("done");
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