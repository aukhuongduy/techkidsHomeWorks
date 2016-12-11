package model;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class BulletModel {
    private int x,y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int dx, int dy){
        this.setX(this.getX()+dx);
        this.setY(this.getY()+dy);
    }
}
