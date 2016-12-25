package controller.Enemies;

import controller.Body;
import controller.Controller;
import controller.PlaneController;
import controller.managers.BodyManager;
import model.Model;
import utils.Utils;
import view.SingleView;
import view.View;

/**
 * Created by Khuong Duy on 12/11/2016.
 */
public class EnemyBulletController extends Controller implements Body {
    public static final int SPEED = 7;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public int dx,dy;
    public boolean isFocus=false;
    public ShootBehavior shootBehavior;

    public EnemyBulletController(Model model, View view, ShootBehavior shootBehavior) {
        super(model, view);
        this.shootBehavior = shootBehavior;
        BodyManager.instance.register(this);

    }

    public void run() {
        if(shootBehavior!=null){
            shootBehavior.doShoot(this);
        }
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            this.getModel().setAlive(false);
            BodyManager.instance.remove();
        }
    }

    public static EnemyBulletController createBullet(int x, int y, BulletMoveType type) {
        switch (type) {
            case DOWN:
                EnemyBulletController enemyBulletController = new EnemyBulletController(
                        new Model(x, y, WIDTH, HEIGHT)
                        , new SingleView(Utils.loadImage("resources/enemy_bullet.png")), new BulletStraightDownBehavior());
                return enemyBulletController;
            case FOLLOW:
                EnemyBulletController enemyBulletControllers = new EnemyBulletController(
                        new Model(x, y, WIDTH, HEIGHT)
                        , new SingleView(Utils.loadImage("resources/enemy_bullet.png")), new BulletFollowBehavior());
                return enemyBulletControllers;
        }
        return null;
    }
}
