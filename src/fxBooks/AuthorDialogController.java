package fxBooks;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles adding a new author
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class AuthorDialogController {

    @FXML private TextField textAuthor;
    @FXML private javafx.scene.control.Button cancelButton;


    @FXML
    void handleAddAuthor() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
