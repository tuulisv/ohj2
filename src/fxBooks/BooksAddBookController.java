package fxBooks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles events related to adding a new book
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class BooksAddBookController {

    @FXML private TextField textTitle;
    @FXML private TextField textOrigTitle;
    @FXML private TextField textPubYear;
    @FXML private TextField textLang;
    @FXML private javafx.scene.control.Button cancelButton;

    @FXML
    void handleNewAuthor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/BooksAddAuthView.fxml"));
            Parent authRoot = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(authRoot));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleNewPublisher() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/BooksAddPubView.fxml"));
            Parent pubRoot = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(pubRoot));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleSave() {
        BooksMain.errorGeneral();
    }
}
