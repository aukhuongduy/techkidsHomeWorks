package controller.Enemies;

import controller.Body;
import controller.BulletController;
import controller.Controller;
import controller.PlaneController;
import controller.managers.BodyManager;
import controller.managers.ControllerManager;
import model.Model;
import utils.Utils;
import view.Animation;
import view.SingleView;
import view.View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/12/2016.
 */

public class EnemyPlaneController extends Controller implements Body {
    private static final int SPEED = 2;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    public int timeCounter;
    private int hp = 3;

    private Vector<EnemyBulletController> enemyBulletControllers;
    private MoveBehavior moveBehavior;
    private BulletMoveType type;


    public EnemyPlaneController(Model model, View view, MoveBehavior moveBehavior, BulletMoveType type) {
        super(model, view);
        enemyBulletControllers = new Vector<>();
        timeCounter = 0;
        this.moveBehavior = moveBehavior;
        this.type = type;
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        if (moveBehavior != null) {
            moveBehavior.doMove(this);
        }
        if (type == BulletMoveType.DOWN) {
            if (timeCounter > 100) {
                adddBullet(BulletMoveType.DOWN);
                timeCounter = 0;
            }
        } else if (type == BulletMoveType.FOLLOW) {
            if (timeCounter > 150) {
                adddBullet(BulletMoveType.FOLLOW);
                timeCounter = 0;
            }
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

    private void adddBullet(BulletMoveType type) {
        switch (type) {
            case DOWN:
                EnemyBulletController enemyBulletController = EnemyBulletController.createBullet(this.getModel().getMidX() - EnemyBulletController.WIDTH / 2, this.getModel().getBottom(), BulletMoveType.DOWN);
                enemyBulletControllers.add(enemyBulletController);
                break;
            case FOLLOW:
                EnemyBulletController enemyBulletController2 = EnemyBulletController.createBullet(this.getModel().getMidX() - EnemyBulletController.WIDTH / 2, this.getModel().getBottom(), BulletMoveType.FOLLOW);
                enemyBulletControllers.add(enemyBulletController2);
                break;
        }


    }

    public static EnemyPlaneController createEnemy(int x, int y, EnemyType type, BulletMoveType typeBullet) {
        Random r = new Random();
        switch (type) {
            case GREEN:
                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(Utils.loadImage("resources/enemy-green-3.png")),
                        new MoveStraightDownBehavior(), typeBullet);
                return enemyPlaneController;
            case BROWN:
                EnemyPlaneController enemyPlaneController1 = new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(Utils.loadImage("resources/enemy-green-1.png")),
                        new MoveLeftToRightBehavior(), typeBullet);
                return enemyPlaneController1;
            case ZICZAC:
                EnemyPlaneController enemyPlaneController2 = new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(Utils.loadImage("resources/enemy-green-1.png")),
                        new MoveZicZacBehavior(), typeBullet);
                return enemyPlaneController2;
            case WHITE:
//                Vector<BufferedImage> images = Utils.loadSheet("resources/explosion.png",32,32,6);
                Vector<BufferedImage> images = new Vector<>();
                images.add( Utils.loadImage("resources/enemy_plane_white_1.png"));
                images.add( Utils.loadImage("resources/enemy_plane_white_2.png"));
                images.add( Utils.loadImage("resources/enemy_plane_white_3.png"));
                EnemyPlaneController enemyPlaneController3 = new EnemyPlaneController(new Model(x, y, WIDTH, HEIGHT),
                        new Animation(images),
                        new MoveStraightDownBehavior(), typeBullet);
                return enemyPlaneController3;
        }
        return null;
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
            distroy();
            BodyManager.instance.remove();
            PlaneController.score++;
        }

    }
    public void distroy(){
        this.getModel().setAlive(false);
        ExplosionController  explosionController =new ExplosionController(new Model(getModel().getX(),getModel().getY(),32,32),new Animation(Utils.loadSheet("resources/explosion.png",32,32,6)));
        Utils.playSound("resources/enemy-explosion-sound.wav", false);
        ControllerManager.explosion.add(explosionController);
    }
}
