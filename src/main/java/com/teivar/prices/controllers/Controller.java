package com.teivar.prices.controllers;

/**
 * Created by Zalesskiy_K on 18.12.2014.
 */
import javafx.scene.Node;

public interface Controller {
    Node getView();
    void setView (Node view);
}