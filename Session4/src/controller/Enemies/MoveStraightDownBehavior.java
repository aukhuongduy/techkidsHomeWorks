package controller.Enemies;

/**
 * Created by Khuong Duy on 12/21/2016.
 */
public class MoveStraightDownBehavior implements MoveBehavior {
    private int speed;
    @Override
    public void doMove(EnemyPlaneController enemyPlaneController) {
        enemyPlaneController.getModel().move(0,2);
    }
}
