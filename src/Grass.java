import processing.core.PVector;

public class Grass extends Entity {
    public Grass() {
        while (true) {
            pos = new PVector(random(App.getWidth()), random(App.getHeight()));
            int temp = App.getterrain((int) pos.x, (int) pos.y);
            if (temp == 1) {
                break;
            }
        }
    }
}
