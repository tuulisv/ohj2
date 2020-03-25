package fxBooks;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles adding a new publisher
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class PubDialogController {

    @FXML private TextField textPublisher;
    @FXML private javafx.scene.control.Button cancelButton;

    @FXML
    void handleAddPublisher() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
