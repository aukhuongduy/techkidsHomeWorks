package utils;

import controller.PlaneController;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/7/2016.
 */
public class Utils {

    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if (repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage loadImage(String url) {
        try {
            BufferedImage image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }

    public static Vector<BufferedImage> loadSheet(String url, int width, int height, int number) {
        BufferedImage image = loadImage(url);
        Vector<BufferedImage> images = new Vector<>();
        for (int i = 0; i < number; i++) {
            BufferedImage imagex = image.getSubimage(1 + width * i + 1 * i, 1, width, height);
            images.add(imagex);
        }
        return images;
    }

    public static boolean isEndGame() {
        return PlaneController.instancePlane.lives <= 0 && PlaneController.instancePlane.hp <= 0;
    }

}
