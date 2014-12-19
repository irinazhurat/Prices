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
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReceiptItemsController extends AbstractController{

    private ObservableList<ReceiptItems> usersReceiptItems = FXCollections.observableArrayList();

    @Autowired
    private ReceiptItemsService receiptItemsService;

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
        goodNameColumn.setCellFactory(new Callback<TableColumn<ReceiptItems, Goods>, TableCell<ReceiptItems, Goods>>(){

            @Override
            public TableCell<ReceiptItems, Goods> call(TableColumn<ReceiptItems, Goods> param) {

                TableCell<ReceiptItems, Goods> goodsTableCell = new TableCell<ReceiptItems, Goods>(){

                    @Override
                    protected void updateItem(Goods item, boolean empty) {
                        if (item != null) {
                            Label goodName = new Label(item.getName());
                            setGraphic(goodName);
                        }
                    }
                };

                return goodsTableCell;
            }

        });


        tableReceiptItems.setItems(usersReceiptItems);
    }

    private void initData() {

        for (ReceiptItems receiptItems: receiptItemsService.getAll())
            receiptItems.getGoods();

        for (ReceiptItems receiptItems: receiptItemsService.getAll())
            usersReceiptItems.add(receiptItems);
    }
}
