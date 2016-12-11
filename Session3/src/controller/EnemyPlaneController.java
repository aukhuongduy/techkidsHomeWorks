package controller;

import model.Model;
import utils.Utils;
import view.View;

import java.util.Random;

/**
 * Created by Khuong Duy on 12/12/2016.
 */
public class EnemyPlaneController extends Controller {
    private static final int SPEED = 5;

    public EnemyPlaneController(Model model, View view) {
        super(model, view);
    }

    public void run() {
        model.move(0, +2);
    }

    public static EnemyPlaneController createEnemy() {
        Random r = new Random();
        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(new Model(r.nextInt(750)+50, 0,50,50), new View(Utils.loadImage("resources/enemy-green-3.png")));
        return enemyPlaneController;
    }
    public Model getModel(){
        return model;
    }
}
