
import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int height = 600;
    int width = 600;
    int[] boardx;
    int[] boardy;
    int[][] board;
    int[] grads;
    float frequency = 1f / 100f;
    float amplitude = 1f / 5f;

    public void settings() {
        size(height, width);
    }

    public void setup() {
        grads = new int[width * 2];
        boardx = new int[width];
        boardy = new int[height];
        board = new int[height][width];
        for (int i = 0; i < grads.length; i++) {
            if (random(-1000, 1000) > 0) {
                grads[i] = 1;
            } else {
                grads[i] = -1;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // float n = noise2(j * frequency) * amplitude;

                float n = noise2(j * (1f / 300f)) * 1f +
                        noise2(j * (1f / 150f)) * 0.5f +
                        noise2(j * (1f / 75f)) * 0.25f +
                        noise2(j * (1f / 37.5f)) * 0.125f;

                float y = 2 * ((float) i / (float) height) - 1; /* map fragCoord.y into [-1; 1] range */
                if (n > y) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                    // System.out.println("sort");
                }
            }
        }
    }

    public void draw() {
        background(0);
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (board[i][j] == 1) {
                    pixels[i * width + j] = color(255);
                } else {
                    pixels[i * width + j] = color(0);
                    // System.out.println("sort");
                }

            }

        }
        updatePixels();
    }

    private float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private float noise2(float p) {
        float p0 = floor(p);
        float p1 = p0 + 1;

        float t = p - p0;
        float fade_t = fade(t);

        float g0 = grads[(int) p0];
        float g1 = grads[(int) p1];

        return (1 - fade_t) * g0 * (p - p0) + fade_t * g1 * (p - p1);
    }

}
