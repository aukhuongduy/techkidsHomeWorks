package controller.Enemies;

import controller.BulletController;
import controller.PlaneController;

/**
 * Created by Khuong Duy on 12/21/2016.
 */
public class BulletFollowBehavior implements ShootBehavior {

    @Override
    public void doShoot(EnemyBulletController enemyBulletController) {
        if (enemyBulletController.isFocus == false) {
            double distance = Math.sqrt(Math.pow(Math.abs(enemyBulletController.getModel().getMidX()-PlaneController.instancePlane.getModel().getMidX()),2)+
                    Math.pow(Math.abs(enemyBulletController.getModel().getMidY()-PlaneController.instancePlane.getModel().getMidY()),2));
            double part = distance/3;
            int tan =(PlaneController.instancePlane.getModel().getMidX() - enemyBulletController.getModel().getMidX())/(PlaneController.instancePlane.getModel().getMidY() - enemyBulletController.getModel().getMidY());
            enemyBulletController.dx = (int)((PlaneController.instancePlane.getModel().getMidX() - enemyBulletController.getModel().getMidX())/part);
            enemyBulletController.dy = (int)((PlaneController.instancePlane.getModel().getMidY() - enemyBulletController.getModel().getMidY())/part);
            enemyBulletController.isFocus =true;
        }
        enemyBulletController.getModel().move(enemyBulletController.dx, enemyBulletController.dy);
    }
}
