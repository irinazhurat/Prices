package com.teivar.prices.javaFXApplication;

/**
 * Created by Teivar on 18.12.2014.
 */

import com.teivar.prices.config.SpringFXMLLoader;
import com.teivar.prices.controllers.*;
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application
{

    private Stage primaryStage;

    private RootController rootController;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Prices");

        initRootLayout();
        showReceipts();

    }

    public void initRootLayout(){
        rootController = (RootController) SpringFXMLLoader.load("/fxml/RootLayout.fxml");
        rootController.setMainApp(this);
        Scene scene = new Scene((Parent)rootController.getView());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void showReceiptItems()
    {
        ReceiptItemsController controller = (ReceiptItemsController) SpringFXMLLoader.load("/fxml/ReceiptItems.fxml");
        BorderPane pane = (BorderPane) rootController.getView();
        pane.setCenter(controller.getView());
    }

    public void showReceipts()
    {
        ReceiptsController controller = (ReceiptsController) SpringFXMLLoader.load("/fxml/Receipts.fxml");
        BorderPane pane = (BorderPane) rootController.getView();
        pane.setCenter(controller.getView());
        controller.setMainApp(this);
    }

    public void showShops()
    {
        ShopsController controller = (ShopsController) SpringFXMLLoader.load("/fxml/Shops.fxml");
        controller.setMainApp(this);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Shops");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene((Parent) controller.getView());
        dialogStage.setScene(scene);
        controller.setShopsStage(dialogStage);
        dialogStage.showAndWait();


        /*BorderPane pane = (BorderPane) rootController.getView();
        pane.setCenter(controller.getView());*/
    }

    public boolean showEditShops(Shops shops){
        EditShopsController controller = (EditShopsController) SpringFXMLLoader.load("/fxml/EditShops.fxml");

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Shops");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene((Parent) controller.getView());
        dialogStage.setScene(scene);

        // Set the shops into the controller.
        controller.setDialogStage(dialogStage);
        controller.setShops(shops);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    public boolean showEditReceipts(Receipts receipts){
        EditReceiptController controller = (EditReceiptController) SpringFXMLLoader.load("/fxml/EditReceipts.fxml");

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Receipts");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene((Parent) controller.getView());
        dialogStage.setScene(scene);

        // Set the shops into the controller.
        controller.setDialogStage(dialogStage);
        controller.setReceipts(receipts);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClick();
    }


}