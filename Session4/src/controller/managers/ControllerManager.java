package controller.managers;

import controller.Controller;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/14/2016.
 */
public class ControllerManager implements BaseController{
    protected Vector<Controller> controllers;

    public static final ControllerManager explosion = new ControllerManager();

    public ControllerManager(){
        controllers = new Vector<>();

    }
    public void draw(Graphics g){
        for(Controller controller: controllers){
            controller.draw(g);
        }
    }
    public void run(){
        for(Controller controller: controllers){
            controller.run();
        }
        Iterator<Controller> iterator = this.controllers.iterator();
        while (iterator.hasNext()){
            Controller controller = iterator.next();
            if(controller.getModel().isAlive()==false ){
                iterator.remove();
            }

        }
    }
    public void add(Controller controller){
        controllers.add(controller);
    }
    public void remove(Controller controller){
        controllers.remove(controller);
    }
}
