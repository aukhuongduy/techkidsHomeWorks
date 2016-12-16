package controller;

import model.Model;

/**
 * Created by Khuong Duy on 12/14/2016.
 */
public interface Body { //Pure Abstract
    Model getModel();
    void onContact(Body other);

}
