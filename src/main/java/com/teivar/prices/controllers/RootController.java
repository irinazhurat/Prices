package com.teivar.prices.controllers;

import com.teivar.prices.javaFXApplication.MainApp;
import javafx.fxml.FXML;

/**
 * Created by Teivar on 21.12.2014.
 */
public class RootController extends AbstractController {

    @FXML
    private void handleShowShops(){
        mainApp.showShops();
    }

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){this.mainApp = mainApp;}
}
