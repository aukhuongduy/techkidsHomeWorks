package controller;

import model.Model;
import utils.Utils;
import view.View;

/**
 * Created by Khuong Duy on 12/11/2016.
 */
public class EnemyBulletController extends Controller {
    public EnemyBulletController(Model model, View view) {
        super(model, view);
    }

    public void run() {
        model.move(0, +7);
    }
    public Model getModel(){
        return model;
    }
    public static EnemyBulletController createBullet(PlaneController plane){
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new Model(plane.getModel().getX() + plane.getModel().getWidth() / 2 - 6, plane.getModel().getY() + 30, 12, 30)
                , new View(Utils.loadImage("resources/enemy_bullet.png")));
            return enemyBulletController;
    }
}
