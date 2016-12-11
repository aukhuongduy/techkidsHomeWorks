package view;

import model.BulletModel;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class BulletView {
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

    public void draw(Graphics g, BulletModel bulletModel) {
        g.drawImage(image, bulletModel.getX(), bulletModel.getY(), width, height, null);
    }
}
