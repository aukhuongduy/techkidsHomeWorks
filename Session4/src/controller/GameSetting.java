package controller;

import model.Model;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class GameSetting {
    private int width,height;

    public static final GameSetting instance = new GameSetting(800,600);

    public GameSetting(int width, int height) {
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
    public boolean isInScreen(Model model){
        return model.getTop()<this.getHeight();
    }
}
