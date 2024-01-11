import processing.core.PVector;

public class Cow extends Entity {

    public Cow() {
        done = true;
        tasks = 2;
        directions = new PVector(0, 0);
        speed = 1;
        counter = 0;
        inttemp = 0;
        maxhp = 600;
        hp = maxhp;

        while (true) {
            pos = new PVector(random(App.getWidth()), random(App.getHeight()));
            int temp = App.getterrain((int) pos.x, (int) pos.y);
            if (temp == 1) {
                break;
            }
        }
    }

    public void update() {
        hp--;
        if (done == true) {
            newgoal();
            done = false;
        } else {
            switch (goal) {
                case 0:
                    // stand
                    if (inttemp == 0) {
                        inttemp = (int) random(30, 120);
                    }
                    if (counter >= inttemp) {
                        done = true;
                        inttemp = 0;
                        counter = 0;
                    }
                    counter++;
                    break;
                case 1:
                    if (inttemp == 0) {
                        inttemp = (int) random(30, 120);
                        directions = new PVector(random(-5, 5), random(-5, 5));
                    }
                    if (counter >= inttemp) {
                        done = true;
                        inttemp = 0;
                        counter = 0;
                        directions = new PVector(0, 0);
                    }
                    counter++;
                    break;
                case 2:
                    for (int i = 0; i < App.getgrassssize(); i++) {
                        Grass gras = App.getgrass(i);

                    }
                    done = true;
                    break;

            }
        }
        while (ofb() || latprob(1)) {
            directions = new PVector(random(-5, 5), random(-5, 5));
        }
        move();
    }
}
