package com.teivar.prices.controllers;

import com.teivar.prices.entity.Goods;
import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import com.teivar.prices.javaFXApplication.MainApp;
import com.teivar.prices.service.ReceiptItemsService;
import com.teivar.prices.service.ReceiptsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Teivar on 21.12.2014.
 */
public class ReceiptsController extends AbstractController {

    @Autowired
    private ReceiptsService receiptsService;
    @Autowired
    private ReceiptItemsService receiptItemsService;

    private ObservableList<Receipts> receiptses = FXCollections.observableArrayList();
    private ObservableList<ReceiptItems> receiptItemses = FXCollections.observableArrayList();

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
    private TableView<ReceiptItems> receiptItemsTableView;

    @FXML
    private TableColumn<ReceiptItems, Integer> idItemsColumn;

    @FXML
    private TableColumn<ReceiptItems, Goods> goodsItemsColumn;

    @FXML
    private TableColumn<ReceiptItems, Double> priceItemsColumn;

    @FXML
    private TableColumn<ReceiptItems, Double> quanItemsColumn;


    @FXML
    private void initialize() {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Date>("timeStamp"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Double>("sum"));
        shopsColumn.setCellValueFactory(new PropertyValueFactory<Receipts, Shops>("shops"));
        shopsColumn.setCellFactory(param -> new TableCell<Receipts, Shops>() {
                    @Override
                    protected void updateItem(Shops item, boolean empty) {
                        if (item != null) {
                            Label shopName = new Label(item.getFullName());
                            setGraphic(shopName);
                        }
                    }
        });
        receiptsTableView.setItems(receiptses);
        showReceiptItems(null);
        receiptsTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReceiptItems(newValue));
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
    private void handleNewReceiptItems() {
        ReceiptItems receiptItems = new ReceiptItems();
        receiptItems.setReceipts(receiptsTableView.getSelectionModel().getSelectedItem());
        boolean okClicked = mainApp.showEditReceiptItems(receiptItems);
        if (okClicked) {
            receiptItemsService.addReceiptItems(receiptItems);
            showReceiptItems(receiptItems.getReceipts());
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
    private void handleEditReceiptItems() {
        ReceiptItems selectedReceiptItems = receiptItemsTableView.getSelectionModel().getSelectedItem();
        if (selectedReceiptItems != null) {
            boolean okClicked = mainApp.showEditReceiptItems(selectedReceiptItems);
            if (okClicked) {
                receiptItemsService.editReceiptItems(selectedReceiptItems);
                showReceiptItems(selectedReceiptItems.getReceipts());
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

    @FXML
    private void handleDeleteReceiptItems() {
        ReceiptItems selectedReceiptItems = receiptItemsTableView.getSelectionModel().getSelectedItem();
        if (selectedReceiptItems != null) {
            receiptItemsService.delete(selectedReceiptItems.getId());
            showReceiptItems(selectedReceiptItems.getReceipts());
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

    private void showReceiptItems(Receipts receipts){

        receiptItemses.clear();
        if (receipts != null && receiptItemsService.getByReceipts(receipts) != null) {
            receiptItemses.addAll(receiptItemsService.getByReceipts(receipts));

            idItemsColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Integer>("id"));
            priceItemsColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Double>("price"));
            quanItemsColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Double>("quan"));
            goodsItemsColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Goods>("goods"));
            goodsItemsColumn.setCellFactory(param -> new TableCell<ReceiptItems, Goods>() {
                @Override
                protected void updateItem(Goods item, boolean empty) {
                    if (item != null) {
                        Label goodName = new Label(item.getName());
                        setGraphic(goodName);
                    }
                }
            });
        }
        receiptItemsTableView.setItems(receiptItemses);

    }


}
