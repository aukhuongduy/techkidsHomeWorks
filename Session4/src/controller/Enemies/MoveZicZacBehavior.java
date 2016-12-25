package controller.Enemies;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class MoveZicZacBehavior implements MoveBehavior{
    int count =0;
    @Override
    public void doMove(EnemyPlaneController enemyPlaneController) {
        System.out.println(count);
        count ++;
        if(count<40){
            enemyPlaneController.getModel().move(-1,1);
        }else if(count>40){
            enemyPlaneController.getModel().move(1,1);
        }
        if(count>80){
            count=0;
        }

    }
}
