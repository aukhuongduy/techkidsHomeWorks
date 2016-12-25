import controller.*;
import controller.managers.*;
import utils.Utils;
import view.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/5/2016.
 */
public class GameWindow extends Frame implements Runnable {
    BufferedImage backBuffer;
    Image background = Utils.loadImage("resources/background.png");
    GameSetting gameSetting;
    Vector<BaseController> controllers;
    Random r = new Random();


    public GameWindow() {

        configSetting();

        controllers =new Vector<>();

        controllers.add(ControllerManager.explosion);
        controllers.add(EnemyControllerManager.enemyControllerManager);
        controllers.add(ItemController.itemController);
        controllers.add(PlaneController.instancePlane);

        backBuffer = new BufferedImage(gameSetting.getWidth(), gameSetting.getHeight(), BufferedImage.TYPE_INT_ARGB);

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
                PlaneController.instancePlane.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    PlaneController.instancePlane.bulletControllers.add(BulletController.createBullet(PlaneController.instancePlane));

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void configSetting() {
        setTitle("Game 1945");
        gameSetting = GameSetting.instance;
        setSize(gameSetting.getWidth(), gameSetting.getHeight());
        setVisible(true);
        KeySetting keySetting1 = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        PlaneController.instancePlane.setKeySetting(keySetting1);

    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBuffer.getGraphics();
        backBufferGraphics.drawImage(background, 0, 0, 800, 600, null);
        for (BaseController controller : controllers) {
            controller.draw(backBufferGraphics);
        }
        Font font = new Font("Bauhaus 93",Font.BOLD,50);
        backBufferGraphics.setFont(font);
        backBufferGraphics.drawString(String.valueOf(PlaneController.instancePlane.lives),30,550);
        backBufferGraphics.drawString(String.valueOf(PlaneController.hp),700,550);
        if(Utils.isEndGame()){
            backBufferGraphics.drawString("Game Over",250,300);
        }
        g.drawImage(backBuffer, 0, 0, 800, 600, null);
    }


    @Override
    public void run() {
        while (true) {
            try {
                for (BaseController controller : controllers) {
                    controller.run();
                }
                BodyManager.instance.checkContact();
                this.repaint();
                if(Utils.isEndGame()){
                    this.repaint();
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
