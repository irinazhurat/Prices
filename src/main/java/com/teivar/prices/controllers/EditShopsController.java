package com.teivar.prices.controllers;

import com.teivar.prices.entity.Shops;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 * Created by Teivar on 21.12.2014.
 */
public class EditShopsController extends AbstractController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descField;

    private Stage dialogStage;
    private Shops shops;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setShops(Shops shops) {
        this.shops = shops;

        nameField.setText(shops.getName());
        descField.setText(shops.getDesc());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            shops.setName(nameField.getText());
            shops.setDesc(descField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (descField.getText() == null || descField.getText().length() == 0) {
            errorMessage += "No valid desc!\n";
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




}
