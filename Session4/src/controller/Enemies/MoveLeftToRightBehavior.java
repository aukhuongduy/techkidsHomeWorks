package controller.Enemies;

/**
 * Created by Khuong Duy on 12/21/2016.
 */
public class MoveLeftToRightBehavior implements MoveBehavior {
    @Override
    public void doMove(EnemyPlaneController enemyPlaneController) {
        enemyPlaneController.getModel().move(1,1);
    }
}
