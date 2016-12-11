package controller;

import model.BulletModel;
import view.BulletView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class BulletController {
    private BulletModel bulletModel = new BulletModel();
    private BulletView bulletView = new BulletView();
    private KeySetting keySetting;

    public BulletController(int x, int y, Image image, int width, int height, KeySetting keySetting) {
        this.bulletModel.setX(x);
        this.bulletModel.setY(y);
        this.bulletView.setImage(image);
        this.bulletView.setWidth(width);
        this.bulletView.setHeight(height);
        this.keySetting = keySetting;
    }

    public void move() {
        bulletModel.move(0, -5);
    }

    public void draw(Graphics g) {
        bulletView.draw(g, bulletModel);
    }

    public void run(){

    }

}
