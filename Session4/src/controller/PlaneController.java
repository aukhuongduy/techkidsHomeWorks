package controller;

import controller.Enemies.EnemyBulletController;
import controller.Enemies.ExplosionController;
import controller.managers.BaseController;
import controller.managers.BodyManager;
import controller.managers.ControllerManager;
import model.Model;
import utils.Utils;
import view.Animation;
import view.SingleView;
import view.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class PlaneController extends Controller implements Body, BaseController {
    public KeySetting keySetting;
    private static final int SPEED = 5;
    public static int score = 0;
    public static int hp = 10;
    public static int lives = 3;
    int count = 0;

    public static final PlaneController instancePlane = createPlane(350, 500);

    public Vector<BulletController> bulletControllers = new Vector<>();

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public PlaneController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    @Override
    public void draw(Graphics g) {
        if (instancePlane.hp == 0) {

        } else {
            super.draw(g);
            for (BulletController bulletController : bulletControllers) {
                bulletController.draw(g);
            }
        }
    }

    @Override
    public void run() {

        for (BulletController bulletController : bulletControllers) {
            bulletController.run();
        }
        Iterator<BulletController> iterator = this.bulletControllers.iterator();
        while (iterator.hasNext()){
            Controller controller = iterator.next();
            if(controller.getModel().isAlive()==false ){
                iterator.remove();
            }

        }
        if (instancePlane.hp <= 0) {
            model.setAlive(false);
            if(count==0){
                distroy();
            }
            count++;
            if (count == 150) {
                if (lives > 0) {
                    hp = 10;
                    model.setX(350);
                    model.setY(500);
                    model.setAlive(true);
                    BodyManager.instance.register(PlaneController.instancePlane);
                }
                lives--;
                count = 0;
            }
        }
    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keySetting != null && model.isAlive()) {
            if (keyCode == keySetting.getKeyUp()) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.getKeyDown()) {
                model.move(0, +SPEED);
            } else if (keyCode == keySetting.getKeyLeft()) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                model.move(+SPEED, 0);
            }
        }
    }

    public static PlaneController createPlane(int x, int y) { // goi dong nay o dau
        PlaneController planeController = new PlaneController(
                new Model(x, y, 80, 80),
                new SingleView(Utils.loadImage("resources/plane3.png")
                ));
        return planeController;

    }

    public Model getModel() {
        return model;
    }

    @Override
    public void onContact(Body other) {
        if (hp > 0 && other instanceof EnemyBulletController) {
            hp--;
        }
    }
    public void distroy(){
        ExplosionController explosionController =new ExplosionController(new Model(getModel().getMidX()-16,getModel().getMidY()-16,32,32),new Animation(Utils.loadSheet("resources/explosion.png",32,32,6)));
        Utils.playSound("resources/plane-explosion-sound.wav", false);
        ControllerManager.explosion.add(explosionController);
    }

}
