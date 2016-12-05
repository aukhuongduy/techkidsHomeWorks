package view;

import model.PlaneModel;

import java.awt.*;


/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class PlaneView {
    private Image image;
    private int width, height;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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


    public void draw(Graphics g, PlaneModel planeModel){
        g.drawImage(image,planeModel.getX(),planeModel.getY(),width,height,null);
    }
}
