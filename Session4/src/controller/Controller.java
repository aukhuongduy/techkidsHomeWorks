package controller;

import model.Model;
import view.View;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class Controller {
    protected Model model;
    protected View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    public void run() {

    }

    public void draw(Graphics g) {
        view.draw(g, model);
    }
}
