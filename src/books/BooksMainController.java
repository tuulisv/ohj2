package books;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Tuuli Veini
 * @version 31.1.2020
 *
 * Pääohjelman tapahtumien käsittely
 */
public class BooksMainController {

    @FXML
    private TextField textSearch;

    @FXML
    void handleAdd() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/BooksAddBookView.fxml"));
            Parent addRoot = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(addRoot));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleEdit() {
        BooksMain.notWorking();
    }

    @FXML
    void handleSave() {
        BooksMain.notWorking();
    }

    @FXML
    void handleRemove() {
        BooksMain.notWorking();
    }

    @FXML
    void handleQuit() {
        BooksMain.notWorking();
    }

    @FXML
    void handleHelp() {
        BooksMain.notWorking();
    }

    @FXML
    void handleAbout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/BooksEnterView.fxml"));
            Parent aboutRoot = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(aboutRoot));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSearch() {

    }

    @FXML
    void handleSelectAuthor() {

    }

    @FXML
    void handleSelectBook() {

    }

    @FXML
    void handleSelectRating() {

    }

    @FXML
    void handleSelectStatus() {

    }



}
