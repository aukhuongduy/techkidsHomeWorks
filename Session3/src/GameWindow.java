import controller.*;
import model.Model;
import utils.Utils;
import view.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class GameWindow extends Frame implements Runnable {
    BufferedImage backBuffer;
    PlaneController plane1, plane2, enemy;
    Image background = Utils.loadImage("resources/background.png");
    Vector<BulletController> bulletControllers;
    Vector<EnemyBulletController> enemyBullets;
    Vector<EnemyPlaneController> enemyPlanes;
    Random r = new Random();
    int count = 0;

    public GameWindow() {
        setTitle("Game 1945");

        setSize(800, 600);
        setVisible(true);

        KeySetting keySetting1 = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        KeySetting keySetting2 = new KeySetting(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        KeySetting keySettingFire = new KeySetting(KeyEvent.VK_SPACE);
        plane1 = PlaneController.createPlane(450, 450);
        plane1.setKeySetting(keySetting1);
        plane2 = PlaneController.createPlane(300, 450);

        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        bulletControllers = new Vector<>();
        enemyBullets = new Vector<>();
        enemyPlanes = new Vector<>();


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
                    BulletController bulletController = new BulletController(new Model(plane1.getModel().getX() + plane1.getModel().getWidth() / 2 - 6,
                            plane1.getModel().getY() - 30, 12, 30), new View(Utils.loadImage("resources/bullet.png")));
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
        backBufferGraphics.drawImage(background, 0, 0, 800, 600, null);
        plane1.draw(backBufferGraphics);
        plane2.draw(backBufferGraphics);
        if (enemyPlanes != null) {
            for (EnemyPlaneController enemy : enemyPlanes
                    ) {
                enemy.draw(backBufferGraphics);
            }
        }
        if (bulletControllers != null) {
            for (BulletController bulletController : bulletControllers) {
                bulletController.draw(backBufferGraphics);
            }
        }
        if (enemyBullets != null) {
            for (EnemyBulletController bullet : enemyBullets) {
                bullet.draw(backBufferGraphics);
            }
        }
        g.drawImage(backBuffer, 0, 0, 800, 600, null);
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (bulletControllers != null) {
                    for (BulletController bulletController : bulletControllers
                            ) {
                        bulletController.run();
                    }
                }
                count++;
                if (count == 80) {
                    enemyPlanes.add(EnemyPlaneController.createEnemy());
                    for (EnemyPlaneController e : enemyPlanes
                         ) {
                        enemyBullets.add(EnemyBulletController.createBullet(e));
                    }

                    count = 0;
                }
                if (enemyBullets != null) {
                    for (EnemyBulletController bullet : enemyBullets
                            ) {
                        bullet.run();
                    }
                }
                if (enemyPlanes != null) {
                    for (EnemyPlaneController enemy : enemyPlanes
                            ) {
                        enemy.run();
                    }
                }
                this.repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    // Design pattern: Factory;


}
