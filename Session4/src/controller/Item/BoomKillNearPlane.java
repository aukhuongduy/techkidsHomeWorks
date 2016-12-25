package controller.Item;

import controller.managers.EnemyControllerManager;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class BoomKillNearPlane implements BoomBehavior{
    @Override
    public void explosion() {
        EnemyControllerManager.enemyControllerManager.removeEnemyNearPlane();
    }
}
