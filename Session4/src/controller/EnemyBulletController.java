package controller;

import controller.managers.BodyManager;
import model.Model;
import utils.Utils;
import view.View;

import java.util.Vector;

/**
 * Created by Khuong Duy on 12/11/2016.
 */
public class EnemyBulletController extends Controller implements Body {
    public static final int SPEED = 7;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;



    public EnemyBulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);

    }

    public void run() {
        model.move(0, SPEED);

    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void onContact(Body other) {
        if(other instanceof PlaneController){
            this.getModel().setAlive(false);
            BodyManager.instance.remove();
        }
    }

    public static EnemyBulletController createBullet(int x, int y) {
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new Model(x, y, WIDTH, HEIGHT)
                , new View(Utils.loadImage("resources/enemy_bullet.png")));
        return enemyBulletController;
    }
}
