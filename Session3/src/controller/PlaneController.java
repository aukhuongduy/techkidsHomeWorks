package controller;

import model.Model;
import utils.Utils;
import view.View;

import java.awt.event.KeyEvent;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class PlaneController extends Controller {
    public KeySetting keySetting;
    private static final int SPEED =5;

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public PlaneController(Model model, View view) {
        super(model, view);
    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keySetting != null) {
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
    public static PlaneController createPlane(int x, int y) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, 80, 80),
                new View(Utils.loadImage("resources/plane3.png")
                ));
        return planeController;

    }
    public Model getModel(){
        return model;
    }
    public void run(){
        model.move(0,+2);
    }
}
