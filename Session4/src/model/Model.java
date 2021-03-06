package model;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class Model {
    private int x, y;
    private int width, height;
    private boolean isAlive =true;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    public int getMidX() {
        return x + width / 2;
    }

    public int getMidY() {
        return y + height / 2;
    }

    public int getBottom() {
        return y + height;
    }

    public int getTop(){
        return y;
    }
    public void move(int dx, int dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean intersects(Model other) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();

        return rect1.intersects(rect2);
    }
}
