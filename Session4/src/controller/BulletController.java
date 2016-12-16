package controller;

import controller.managers.BodyManager;
import model.Model;
import utils.Utils;
import view.View;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class BulletController extends Controller implements Body {
    private static final int SPEED = 5;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int timeCounter;

    private Vector<BulletController> bulletControllers;

    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }
    public void addBullet(PlaneController planeController){

        BulletController bulletController = BulletController.createBullet(planeController);
        bulletControllers.add(bulletController);

    }
    public static BulletController createBullet(PlaneController planeController){
        return new BulletController(new Model(planeController.getModel().getMidX() -WIDTH/2,
                planeController.getModel().getTop(), WIDTH, HEIGHT), new View(Utils.loadImage("resources/bullet.png")));
    }
    @Override
    public void run() {
        model.move(0, -SPEED);
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyPlaneController) {
            System.out.println("Đạn mình chạm gì đó ");
            this.getModel().setAlive(false);
            BodyManager.instance.remove();
        }
    }
}
