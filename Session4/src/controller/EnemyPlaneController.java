package controller;

import controller.managers.BodyManager;
import model.Model;
import utils.Utils;
import view.View;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/12/2016.
 */
public class EnemyPlaneController extends Controller implements Body {
    private static final int SPEED = 2;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private int timeCounter;
    private int hp = 3;

    private Vector<EnemyBulletController> enemyBulletControllers;


    public EnemyPlaneController(Model model, View view) {
        super(model, view);
        enemyBulletControllers = new Vector<>();
        timeCounter = 0;
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        model.move(0, SPEED);
        if (timeCounter > 50) {
            adddBullet();
            timeCounter = 0;
        }
        timeCounter++;
        for (EnemyBulletController e : enemyBulletControllers
                ) {
            e.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (EnemyBulletController e : enemyBulletControllers
                ) {
            e.draw(g);
        }
    }

    private void adddBullet() {
        EnemyBulletController enemyBulletController = EnemyBulletController.createBullet(this.getModel().getMidX() - EnemyBulletController.WIDTH / 2, this.getModel().getBottom());
        enemyBulletControllers.add(enemyBulletController);
    }

    public static EnemyPlaneController createEnemy(int x, int y) {
        Random r = new Random();
        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(new Model(x, y, WIDTH, HEIGHT), new View(Utils.loadImage("resources/enemy-green-3.png")));
        return enemyPlaneController;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletController) {
            hp--;
        }
        if (hp <= 0) {
            this.getModel().setAlive(false);
            BodyManager.instance.remove();
            PlaneController.score++;
        }

    }
}
