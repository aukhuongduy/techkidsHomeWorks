import controller.*;
import controller.managers.BodyManager;
import controller.managers.BulletControllerManager;
import controller.managers.EnemyControllerManager;
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
    EnemyControllerManager enemyControllerManager;
    BulletControllerManager bulletControllerManager;
    Random r = new Random();


    public GameWindow() {
        setTitle("Game 1945");

        setSize(800, 600);
        setVisible(true);

        KeySetting keySetting1 = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        plane1 = PlaneController.createPlane(450, 450);//day anh
        plane1.setKeySetting(keySetting1);
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        bulletControllerManager = new BulletControllerManager();
        enemyControllerManager = new EnemyControllerManager();


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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bulletControllerManager.spawn(plane1);
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
        enemyControllerManager.draw(backBufferGraphics);
        bulletControllerManager.draw(backBufferGraphics);
        Font font = new Font("Bauhaus 93",Font.BOLD,50);
        backBufferGraphics.setFont(font);
        backBufferGraphics.drawString(String.valueOf(PlaneController.score),30,550);
        backBufferGraphics.drawString(String.valueOf(PlaneController.hp),700,550);
        if (PlaneController.hp<=0) {
            backBufferGraphics.drawString("Game Over",250,300);
        }


        g.drawImage(backBuffer, 0, 0, 800, 600, null);
    }


    @Override
    public void run() {
        while (true) {
            try {

                bulletControllerManager.run();
                BodyManager.instance.checkContact();
                enemyControllerManager.run();
                this.repaint();
                if(PlaneController.hp<=0){
                    return;
                }
                Thread.sleep(17);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    // Design pattern: Factory;

}
