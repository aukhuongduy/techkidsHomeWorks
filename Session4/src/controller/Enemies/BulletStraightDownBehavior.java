package controller.Enemies;

import controller.BulletController;
import controller.PlaneController;

/**
 * Created by Khuong Duy on 12/21/2016.
 */
public class BulletStraightDownBehavior implements ShootBehavior{


    @Override
    public void doShoot(EnemyBulletController enemyBulletController) {
        enemyBulletController.getModel().move(0,5);
    }
}
