package controller.managers;

import controller.EnemyPlaneController;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/14/2016.
 */
public class EnemyControllerManager extends ControllerManager {
    private int count;

    private void spawn() {
        count++;
        if (count == 50) {
            Random r = new Random();
            this.controllers.add(EnemyPlaneController.createEnemy(r.nextInt(750), 0));
            count =0;
        }

    }

    @Override
    public void run() {

        super.run();
        spawn();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
