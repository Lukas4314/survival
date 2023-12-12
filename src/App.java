import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    int height = 600;
    int width = 600;
    int[] boardx;
    int[] boardy;
    int[][] board;
    PVector[] grads;
    float frequency = 1f / 100f;
    float amplitude = 1f / 5f;
    int vand = 30;
    int land = 60;
    int bjerge = 180;

    public void settings() {
        size(height, width);
    }

    public void setup() {
        grads = new PVector[width * 2 * height];
        boardx = new int[width];
        boardy = new int[height];
        board = new int[height][width];
        for (int i = 0; i < grads.length; i++) {
            grads[i] = new PVector((random(100) - 50f) / 50f, (random(100) - 50f) / 50f);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                float n = noise2(new PVector(j * frequency, i * frequency));
                // System.out.println(n);
                int temp = (int) (n * 127 + 127);
                // System.out.println(temp);
                board[i][j] = temp;

            }
        }
    }

    public void draw() {
        background(0);
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = color(board[i][j]);
            }
        }
        updatePixels();
    }

    private float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    PVector grad(PVector p) {
        float texture_width = width;
        PVector v = new PVector(p.x / texture_width, p.y / texture_width);
        return (v.mult(2).sub(1, 1)).normalize(); /* remap sampled value to [-1; 1] and normalize */
    }

    /* 2D noise */
    float noise2(PVector p) {
        /* Calculate lattice points. */
        PVector p0 = new PVector(floor(p.x), floor(p.y));
        PVector p1 = p0.copy().add(1, 0);
        PVector p2 = p0.copy().add(0, 1);
        PVector p3 = p0.copy().add(1, 1);

        /* Look up gradients at lattice points. */
        PVector g0 = grads[(int) p0.y * width + (int) p0.x];
        PVector g1 = grads[(int) p1.y * width + (int) p1.x];
        PVector g2 = grads[(int) p2.y * width + (int) p2.x];
        PVector g3 = grads[(int) p3.y * width + (int) p3.x];

        float t0 = p.x - p0.x;
        float fade_t0 = fade(t0); /* Used for interpolation in horizontal direction */

        float t1 = p.y - p0.y;
        float fade_t1 = fade(t1); /* Used for interpolation in vertical direction. */

        /* Calculate dot products and interpolate. */
        float p0p1 = (1 - fade_t0) * g0.dot(p.copy().sub(p0)) + fade_t0 * g1.dot(p.copy().sub(p1));
        /*
         * between upper two lattice
         * points
         */
        float p2p3 = (1 - fade_t0) * g2.dot(p.copy().sub(p2)) + fade_t0 * g3.dot(p.copy().sub(p3));
        /*
         * between lower two lattice
         * points
         */

        /* Calculate final result */
        // float temp = (1 - fade_t1) * p0p1 + fade_t1 * p2p3;
        return (1 - fade_t1) * p0p1 + fade_t1 * p2p3;
    }
}
