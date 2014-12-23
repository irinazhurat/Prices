package com.teivar.prices.controllers;

/**
 * Created by Zalesskiy_K on 18.12.2014.
 */

import com.teivar.prices.entity.Goods;
import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.service.ReceiptItemsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReceiptItemsController extends AbstractController{

    @Autowired
    private ReceiptItemsService receiptItemsService;

    private ObservableList<ReceiptItems> receiptItemses = FXCollections.observableArrayList();

    @FXML
    private TableView<ReceiptItems> tableReceiptItems;

    @FXML
    private TableColumn<ReceiptItems, Integer> idColumn;

    @FXML
    private TableColumn<ReceiptItems, Double> priceColumn;

    @FXML
    private TableColumn<ReceiptItems, Double> quanColumn;

    @FXML
    private TableColumn<ReceiptItems, Goods> goodNameColumn;

    @FXML
    private void initialize() {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Integer>("id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Double>("price"));
        quanColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Double>("quan"));
        goodNameColumn.setCellValueFactory(new PropertyValueFactory<ReceiptItems, Goods>("goods"));
        goodNameColumn.setCellFactory(param -> new TableCell<ReceiptItems, Goods>(){
                @Override
                protected void updateItem(Goods item, boolean empty) {
                    if (item != null) {
                        Label goodName = new Label(item.getName());
                        setGraphic(goodName);
                    }
                }
        });
        tableReceiptItems.setItems(receiptItemses);
    }

    private void initData() {
        receiptItemses.clear();
        receiptItemses.addAll(receiptItemsService.getAll());
    }
}
