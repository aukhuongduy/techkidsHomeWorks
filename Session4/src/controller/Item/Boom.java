package controller.Item;

import controller.Body;
import controller.Controller;
import controller.PlaneController;
import controller.managers.BodyManager;
import model.Model;
import utils.Utils;
import view.SingleView;
import view.View;

/**
 * Created by Khuong Duy on 12/23/2016.
 */
public class Boom extends Controller implements Body {

    private BoomBehavior boomBehavior;

    public Boom(Model model, View view, BoomBehavior boomBehavior) {
        super(model, view);
        this.boomBehavior = boomBehavior;
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        this.getModel().move(0, 3);
    }

    public static Boom createBoom(int x, int y, BoomType type) {
        switch (type) {
            case KILLALL:
                Boom boom = new Boom(new Model(x, y, 50, 50), new SingleView(Utils.loadImage("resources/bomb.png")), new BoomKillAll());
                System.out.println("create");
                return boom;
            case KILLNEAR:
                Boom booms = new Boom(new Model(x, y, 50, 50), new SingleView(Utils.loadImage("resources/bomb.png")), new BoomKillNearPlane());
                System.out.println("create");
                return booms;
        }
        return null;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            this.getModel().setAlive(false);
            System.out.println("Cham may bay minh");
            BodyManager.instance.remove();
            this.boomBehavior.explosion();
        }
    }
}
