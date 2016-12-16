package controller.managers;


import controller.BulletController;
import controller.PlaneController;

import java.awt.*;
import java.util.Random;

/**
 * Created by Khuong Duy on 12/16/2016.
 */
public class BulletControllerManager extends ControllerManager{

    public void spawn(PlaneController planeController) {
            this.controllers.add(BulletController.createBullet(planeController));
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
