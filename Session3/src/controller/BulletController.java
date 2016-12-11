package controller;

import model.Model;
import view.View;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class BulletController extends Controller {

    public BulletController(Model model, View view) {
        super(model, view);
    }

    public void run(){
        model.move(0,-5);
    }

}
