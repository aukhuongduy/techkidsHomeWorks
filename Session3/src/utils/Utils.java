package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class Utils {
    public static Image loadImage(String url) {
        try {
            Image image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }
}
