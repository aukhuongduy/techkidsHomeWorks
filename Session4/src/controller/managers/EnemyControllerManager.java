package controller.managers;

import controller.Controller;
import controller.Enemies.BulletMoveType;
import controller.Enemies.EnemyPlaneController;
import controller.Enemies.EnemyType;
import controller.PlaneController;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Khuong Duy on 12/14/2016.
 */
public class EnemyControllerManager extends ControllerManager implements BaseController{
    private int count=0;

    public static final EnemyControllerManager enemyControllerManager = new EnemyControllerManager();

    private void spawn() {
        count++;
        if (count == 100) {
            Random r = new Random();
            this.controllers.add(EnemyPlaneController.createEnemy(r.nextInt(750), 0, EnemyType.GREEN,BulletMoveType.DOWN));
            count =0;
        }
        if(count ==70){
            Random r= new Random();
            this.controllers.add(EnemyPlaneController.createEnemy(r.nextInt(400),0,EnemyType.BROWN,BulletMoveType.FOLLOW) );
        }
        if(count ==50){
            Random r= new Random();
            this.controllers.add(EnemyPlaneController.createEnemy(r.nextInt(500)+250,0,EnemyType.WHITE,BulletMoveType.FOLLOW) );
        }


    }

    public void removeEnemyNearPlane(){
        for (Controller controller : controllers) {
            double distance = Math.sqrt(Math.pow(Math.abs(controller.getModel().getMidX()- PlaneController.instancePlane.getModel().getMidX()),2)+
                    Math.pow(Math.abs(controller.getModel().getMidY()-PlaneController.instancePlane.getModel().getMidY()),2));
            if(distance<300){
                controller.getModel().setAlive(false);
                BodyManager.instance.remove();
            }
        }
    }
    public void removeAll(){
        for (Controller controller : controllers) {
            controller.getModel().setAlive(false);
        }
    }

    @Override
    public void run() {
        super.run();
        spawn();
        Iterator<Controller> iterator = this.controllers.iterator();
        while (iterator.hasNext()){
            Controller controller = iterator.next();
            if(controller.getModel().getTop()>=600 ){
                iterator.remove();
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
