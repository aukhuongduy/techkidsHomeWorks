package controller.Enemies;

import controller.Controller;
import model.Model;
import utils.Utils;
import view.Animation;
import view.View;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class ExplosionController extends Controller {
    public ExplosionController(Model model, View view) {
        super(model, view);
    }

    @Override
    public void draw(Graphics g) {

        super.draw(g);
        Animation animation = (Animation) this.view;
        if (animation.isAnimationReachEnd()) {
            model.setAlive(false);

        }
    }
}
