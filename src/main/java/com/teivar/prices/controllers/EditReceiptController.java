package com.teivar.prices.controllers;

import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import com.teivar.prices.service.ShopsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Teivar on 21.12.2014.
 */
public class EditReceiptController extends AbstractController {

    private boolean okClick = false;

    private ObservableList<Shops> shopses = FXCollections.observableArrayList();

    private Receipts receipts;

    private Stage dialogStage;

    @Autowired
    private ShopsService shopsService;

    @FXML
    private DatePicker dtReceiptDate;

    @FXML
    private ComboBox<Shops> shopsComboBox;

    public boolean isOkClick(){ return this.okClick;}

    public void setDialogStage(Stage stage){ this.dialogStage = stage;}

    public void setReceipts(Receipts receipts){
        this.receipts = receipts;
        if (this.receipts.getId() != 0) {
            dtReceiptDate.setValue(receipts.getTimeStamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            shopsComboBox.setValue(receipts.getShops());
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {

            receipts.setTimeStamp(Date.from(dtReceiptDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            receipts.setShops(shopsComboBox.getSelectionModel().getSelectedItem());

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

        if (shopsComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid shops!\n";
        }
        if (dtReceiptDate.getValue() == null) {
            errorMessage += "No valid date!\n";
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
        shopsComboBox.getItems().addAll(shopses);
        shopsComboBox.setCellFactory(new Callback<ListView<Shops>, ListCell<Shops>>() {
            @Override public ListCell<Shops> call(ListView<Shops> p) {
                return new ListCell<Shops>() {
                    @Override protected void updateItem(Shops item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Label shopName = new Label(item.getFullName());
                            setGraphic(shopName);
                        }
                    }
                };
            }
        });
        shopsComboBox.setConverter(new StringConverter<Shops>() {
            @Override
            public String toString(Shops shops) {
                if (shops == null){
                    return null;
                } else {
                    return shops.getFullName();
                }
            }

            @Override
            public Shops fromString(String shopName) {
                return null;
            }
        });
    }

    private void initData() {
        shopses.clear();
        shopses.addAll(shopsService.getAll());

    }

}
