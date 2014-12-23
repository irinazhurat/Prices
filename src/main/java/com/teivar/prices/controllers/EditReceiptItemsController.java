package com.teivar.prices.controllers;

import com.teivar.prices.entity.Goods;
import com.teivar.prices.entity.ReceiptItems;
import com.teivar.prices.service.GoodsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Zalesskiy_K on 23.12.2014.
 */
public class EditReceiptItemsController extends AbstractController {


    private boolean okClick = false;

    private ObservableList<Goods>  goodses = FXCollections.observableArrayList();

    private ReceiptItems receiptItems;

    private Stage dialogStage;

    @Autowired
    private GoodsService goodsService;

    @FXML
    private ComboBox<Goods> goodsComboBox;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField quanTextField;

    public boolean isOkClick(){ return this.okClick;}

    public void setDialogStage(Stage stage){ this.dialogStage = stage;}

    public void setReceiptItems(ReceiptItems receiptItems){
        this.receiptItems = receiptItems;
        if (this.receiptItems.getId() != 0) {
            goodsComboBox.setValue(receiptItems.getGoods());
            priceTextField.setText(Double.toString(receiptItems.getPrice()));
            quanTextField.setText(Double.toString(receiptItems.getQuan()));
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {

            receiptItems.setGoods(goodsComboBox.getValue());
            receiptItems.setPrice(Double.parseDouble(priceTextField.getText()));
            receiptItems.setQuan(Double.parseDouble(quanTextField.getText()));

            okClick = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (goodsComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid goods!\n";
        }
        if (priceTextField.getText() == null || priceTextField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }
        if (quanTextField.getText() == null || quanTextField.getText().length() == 0) {
            errorMessage += "No valid quan!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }

    @FXML
    private void initialize() {
        initData();
        goodsComboBox.getItems().addAll(goodses);
        goodsComboBox.setCellFactory(p -> new ListCell<Goods>(){
            @Override protected void updateItem(Goods item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Label goodsName = new Label(item.getName());
                    setGraphic(goodsName);}
            }
        });

        goodsComboBox.setConverter(new StringConverter<Goods>() {
            @Override
            public String toString(Goods goods) {
                if (goods == null){
                    return null;
                } else {
                    return goods.getName();
                }
            }

            @Override
            public Goods fromString(String goodsName) {
                return null;
            }
        });
    }

    private void initData() {
        goodses.clear();
        goodses.addAll(goodsService.getAll());

    }


}
