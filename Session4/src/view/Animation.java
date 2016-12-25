package view;

import model.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/24/2016.
 */
public class Animation implements View {
    private Vector<BufferedImage> images;
    private int count = 0;
    private int imageCount =0;
    public Animation(Vector<BufferedImage> images) {
        this.images = images;
    }
    private boolean animationReachEnd = false;

    public boolean isAnimationReachEnd() {
        return animationReachEnd;
    }

    @Override
    public void draw(Graphics g, Model model) {
        BufferedImage image = images.get(imageCount);
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
        count++;
        if (count > 20) {
            count = 0;
            imageCount++;
            if(imageCount>images.size()-1){
                imageCount=0;
                animationReachEnd=true;
            }
        }
    }
}
