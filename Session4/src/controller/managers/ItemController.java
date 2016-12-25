package controller.managers;

import controller.Item.Boom;
import controller.Item.BoomType;

import java.awt.*;
import java.util.Random;

/**
 * Created by Khuong Duy on 12/23/2016.
 */
public class ItemController extends ControllerManager implements BaseController{

    public static final ItemController itemController = new ItemController();

    private int count = 0;

    private void spawn() {
        count++;
        Random r = new Random();
        if (count ==200){
            Boom boom = Boom.createBoom(r.nextInt(700)+50,0, BoomType.KILLALL);
            controllers.add(boom);
        }
        if(count ==400){
            Boom boom = Boom.createBoom(r.nextInt(700)+50,0,BoomType.KILLNEAR);
            controllers.add(boom);
            count=0;
        }
    }

    @Override
    public void run() {
        super.run();
        spawn();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
