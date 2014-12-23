package com.teivar.prices.controllers;

import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import com.teivar.prices.javaFXApplication.MainApp;
import com.teivar.prices.service.ReceiptsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Teivar on 21.12.2014.
 */
public class ReceiptsController extends AbstractController {

    @Autowired
    private ReceiptsService receiptsService;

    private ObservableList<Receipts> receiptses = FXCollections.observableArrayList();

    private MainApp mainApp;

    @FXML
    private TableView<Receipts> receiptsTableView;

    @FXML
    private TableColumn<Receipts, Integer> idColumn;

    @FXML
    private TableColumn<Receipts, Date> dateColumn;

    @FXML
    private TableColumn<Receipts, Double> sumColumn;

    @FXML
    private TableColumn<Receipts, Shops> shopsColumn;

    @FXML
    private void initialize() {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Date>("timeStamp"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Double>("sum"));
        shopsColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Shops>("shops"));
        shopsColumn.setCellFactory(new Callback<TableColumn<Receipts, Shops>, TableCell<Receipts, Shops>>(){

            @Override
            public TableCell<Receipts, Shops> call(TableColumn<Receipts, Shops> param) {

                TableCell<Receipts, Shops> shopsTableCell = new TableCell<Receipts, Shops>(){

                    @Override
                    protected void updateItem(Shops item, boolean empty) {
                        if (item != null) {
                            Label shopName = new Label(item.getFullName());
                            setGraphic(shopName);
                        }
                    }
                };

                return shopsTableCell;
            }

        });

        receiptsTableView.setItems(receiptses);
    }

    @FXML
    private void handleNewReceipts() {
        Receipts receipts = new Receipts();
        boolean okClicked = mainApp.showEditReceipts(receipts);
        if (okClicked) {
            receiptsService.addReceipts(receipts);
            initData();
        }
    }

    @FXML
    private void handleEditReceipts() {
        Receipts selectedReceipts = receiptsTableView.getSelectionModel().getSelectedItem();
        if (selectedReceipts != null) {
            boolean okClicked = mainApp.showEditReceipts(selectedReceipts);
            if (okClicked) {
                receiptsService.editReceipts(selectedReceipts);
                initData();
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Receipts Selected")
                    .message("Please select a receipts in the table.")
                    .showWarning();
        }
    }

    @FXML
    private void handleDeleteReceipts() {
        int selectedIndex = receiptsTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Receipts selectedReceipts = receiptsTableView.getSelectionModel().getSelectedItem();
            receiptsService.delete(selectedReceipts.getId());
            initData();
        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Receipts Selected")
                    .message("Please select a receipts in the table.")
                    .showWarning();
        }
    }

    public void setMainApp(MainApp mainApp){ this.mainApp = mainApp;}

    private void initData(){
        receiptses.clear();
        receiptses.addAll(receiptsService.getAll());

    }


}
