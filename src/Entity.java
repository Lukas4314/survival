import processing.core.PApplet;
import processing.core.PVector;

public class Entity extends PApplet {
    PVector pos;
    PVector directions;
    float speed;
    boolean done;
    int goal;
    int tasks;
    int counter;
    int inttemp;
    int maxhp;
    int hp;

    public void move() {
        pos.add(directions.mult(speed));
    }

    public void update() {
    }

    public void newgoal() {
        goal = (int) random(tasks);
    }

    public float getxpos() {
        return pos.x;
    }

    public float getypos() {
        return pos.y;
    }

    public boolean ofb() {
        if (pos.x + directions.mult(speed).x >= App.getWidth()
                || pos.x + directions.mult(speed).x <= 0
                || pos.y + directions.mult(speed).y >= App.getHeight()
                || pos.y + directions.mult(speed).y <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean latprob(int a) {
        if (a != App.getterrain((int) pos.x + (int) directions.mult(speed).x,
                (int) pos.y + (int) directions.mult(speed).y)) {
            return true;
        } else {
            return false;
        }
    }
}
