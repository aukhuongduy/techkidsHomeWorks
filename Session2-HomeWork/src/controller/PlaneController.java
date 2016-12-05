package controller;

import model.PlaneModel;
import view.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class PlaneController {
    private PlaneModel planeModel = new PlaneModel();
    private PlaneView planeView = new PlaneView();
    private KeySetting keySetting ;

    public PlaneController(int x, int y, Image image, int width, int height, KeySetting keySetting) {
        this.planeModel.setX(x);
        this.planeModel.setY(y);
        this.planeView.setImage(image);
        this.planeView.setWidth(width);
        this.planeView.setHeight(height);
        this.keySetting = keySetting;
    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keySetting != null) {
            if (keyCode == keySetting.getKeyUp()) {
                planeModel.move(0, -5);
            } else if (keyCode == keySetting.getKeyDown()) {
                planeModel.move(0, +5);
            } else if (keyCode == keySetting.getKeyLeft()) {
                planeModel.move(-5, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                planeModel.move(+5, 0);
            }
        }
    }

    public void draw(Graphics g) {
        planeView.draw(g, this.planeModel);
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }
}
