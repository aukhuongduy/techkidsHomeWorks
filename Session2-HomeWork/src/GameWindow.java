import controller.BulletController;
import controller.KeySetting;
import controller.PlaneController;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class GameWindow extends Frame implements Runnable {
    BufferedImage backBuffer;
    PlaneController plane1,plane2;

    Vector<BulletController> bulletControllers;

    public GameWindow() {
        setTitle("Game 1945");

        setSize(800, 600);
        setVisible(true);

        KeySetting keySetting1 = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        KeySetting keySetting2 = new KeySetting(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        KeySetting keySettingFire = new KeySetting(KeyEvent.VK_SPACE);
        plane1 = new PlaneController(450, 450, loadImage("resources/plane3.png"), 100, 100, keySetting1);
        plane2 = new PlaneController(300,450, loadImage("resources/plane2.png"),100,100,keySetting2);
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        bulletControllers = new Vector<>();

        setLocationRelativeTo(null);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                plane1.keyPressed(e);
                plane2.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    BulletController bulletController = new BulletController(plane1.getPlaneModel().getX() + plane1.getPlaneView().getWidth()/2 - 6,
                            plane1.getPlaneModel().getY() - 30,
                            loadImage("resources/bullet.png"),
                            12, 30, keySettingFire);
                    bulletControllers.add(bulletController);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBuffer.getGraphics();
        backBufferGraphics.drawImage(loadImage("resources/background.png"), 0, 0, 800, 600, null);
        plane1.draw(backBufferGraphics);
        plane2.draw(backBufferGraphics);
        if (bulletControllers != null) {
            for (BulletController bulletController : bulletControllers) {
                bulletController.draw(backBufferGraphics);
            }
        }
        g.drawImage(backBuffer, 0, 0, 800, 600, null);
    }

    public Image loadImage(String url) {
        try {
            Image image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                if (bulletControllers != null) {
                    for (BulletController bulletController : bulletControllers
                            ) {
                        bulletController.move();
                    }
                }
                this.repaint();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
