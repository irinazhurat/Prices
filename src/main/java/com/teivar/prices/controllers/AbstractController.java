package com.teivar.prices.controllers;

/**
 * Created by Zalesskiy_K on 18.12.2014.
 */
import com.teivar.prices.controllers.Controller;
import javafx.scene.Node;

public abstract class AbstractController implements Controller {
    private Node view;

    public Node getView() {
        return view;
    }

    public void setView (Node view){
        this.view = view;
    }
}