package books;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Tuuli Veini
 * @version 31.1.2020
 *
 * Kirjailijan lisäämiseen liittyvien tapahtumien käsittely
 */
public class BooksAddAuthController {

    @FXML
    private TextField textAuthor;

    @FXML
    void handleAddAuthor() {
        BooksMain.notWorking();
    }

    @FXML
    void handleCancel() {
        BooksMain.notWorking();
    }

}
