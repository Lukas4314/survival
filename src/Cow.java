import processing.core.PApplet;
import processing.core.PVector;

public class Cow extends PApplet {
    PVector pos;

    public Cow() {
        pos = new PVector(random(App.getWidth()), random(App.getHeight()));
    }

    public float getxpos() {
        return pos.x;
    }

    public float getypos() {
        return pos.y;
    }
}
