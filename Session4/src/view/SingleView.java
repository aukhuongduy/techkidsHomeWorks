package view;

import model.Model;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class SingleView implements View {

    public Image image;

    public SingleView(Image image) {
        this.image = image;
    }

    public Image getImage() {

        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, Model model){
        g.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
