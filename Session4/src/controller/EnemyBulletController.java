package controller;

import model.Model;
import utils.Utils;
import view.View;

import java.util.Vector;

/**
 * Created by Khuong Duy on 12/11/2016.
 */
public class EnemyBulletController extends Controller {
    public static final int SPEED = 7;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;



    public EnemyBulletController(Model model, View view) {
        super(model, view);

    }

    public void run() {
        model.move(0, SPEED);

    }


    public Model getModel() {
        return model;
    }

    public static EnemyBulletController createBullet(int x, int y) {
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new Model(x, y, WIDTH, HEIGHT)
                , new View(Utils.loadImage("resources/enemy_bullet.png")));
        return enemyBulletController;
    }
}
