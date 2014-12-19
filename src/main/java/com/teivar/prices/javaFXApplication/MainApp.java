package com.teivar.prices.javaFXApplication;

/**
 * Created by Teivar on 18.12.2014.
 */

import com.teivar.prices.config.SpringFXMLLoader;
import com.teivar.prices.controllers.ReceiptItemsController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        ReceiptItemsController controller = (ReceiptItemsController) SpringFXMLLoader.load("/fxml/main.fxml");
        Scene scene = new Scene((Parent) controller.getView(), 300, 275);
        stage.setTitle("Prices");
        stage.setScene(scene);
        stage.show();
    }
}