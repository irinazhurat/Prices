package com.teivar.prices.controllers;

import com.teivar.prices.entity.Shops;
import com.teivar.prices.javaFXApplication.MainApp;
import com.teivar.prices.service.ShopsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Teivar on 20.12.2014.
 */
public class ShopsController extends AbstractController {

    @Autowired
    private ShopsService shopsService;

    private ObservableList<Shops> shopses = FXCollections.observableArrayList();

    private MainApp mainApp;

    private Stage shopsStage;

    @FXML
    private Button btnClose;

    @FXML
    private TableView<Shops> shopsTableView;

    @FXML
    private TableColumn<Shops, Integer> idColumn;

    @FXML
    private TableColumn<Shops, String> nameColumn;

    @FXML
    private TableColumn<Shops, String> descColumn;

    @FXML
    private void initialize() {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Shops, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Shops, String>("name"));
        descColumn.setCellValueFactory(new PropertyValueFactory<Shops, String>("desc"));

        shopsTableView.setItems(shopses);
    }

    @FXML
    private void handleNewShops() {
        Shops tmpShops = new Shops();
        boolean okClicked = mainApp.showEditShops(tmpShops);
        if (okClicked) {
           shopsService.addShops(tmpShops);
            initData();
        }
    }

    @FXML
    private void handleEditShops() {
        Shops selectedShops = shopsTableView.getSelectionModel().getSelectedItem();
        if (selectedShops != null) {
            boolean okClicked = mainApp.showEditShops(selectedShops);
            if (okClicked) {
                shopsService.editShops(selectedShops);
                initData();
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Shops Selected")
                    .message("Please select a shops in the table.")
                    .showWarning();
        }
    }

    @FXML
    private void handleDeleteShops() {
        int selectedIndex = shopsTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Shops selectedShops = shopsTableView.getSelectionModel().getSelectedItem();
            shopsService.delete(selectedShops.getId());
            initData();
        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Shops Selected")
                    .message("Please select a shops in the table.")
                    .showWarning();
        }
    }

    @FXML
    private void handleClose(){
        // get a handle to the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void initData(){
        shopses.clear();
        shopses.addAll(shopsService.getAll());
    }

    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp;}

    public void setShopsStage(Stage stage){ this.shopsStage = stage;}

}
