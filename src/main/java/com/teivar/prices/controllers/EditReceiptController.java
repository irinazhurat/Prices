package com.teivar.prices.controllers;

import com.teivar.prices.entity.Receipts;
import com.teivar.prices.entity.Shops;
import com.teivar.prices.service.ShopsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
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
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {

            LocalDate ld = dtReceiptDate.getValue();
            Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date dateRes = Date.from(instant);
            receipts.setTimeStamp(dateRes);

            Shops shops = shopsComboBox.getSelectionModel().getSelectedItem();
            receipts.setShops(shops);

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

        if (shopsComboBox.getSelectionModel().getSelectedIndex() == 0) {
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
    }

    private void initData() {
        for (Shops shops : shopsService.getAll()){
            shopses.add(shops);
        }


    }

}
