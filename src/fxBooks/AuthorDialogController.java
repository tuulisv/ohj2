package fxBooks;

import books.Author;
import books.Book;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles adding a new author
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class AuthorDialogController implements ModalControllerInterface<Author> {

    @FXML private TextField textAuthor;
    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private Label labelError;


    @FXML
    void handleAddAuthor() {
        if (textAuthor.getText().trim().isEmpty()) {
            showError();
            return;
        }

        handleChanges();
        ModalController.closeStage(labelError);
    }

    @FXML
    void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //==================================================================

    private Author newAuthor;

    @Override
    public Author getResult() {
        return newAuthor;
    }

    @Override
    public void setDefault(Author author) {
        newAuthor = author;
    }

    @Override
    public void handleShown() {
        textAuthor.requestFocus();
    }

    private void handleChanges() {
        newAuthor.setName(textAuthor.getText());
        newAuthor.register();
    }

    private void showError() {
        labelError.setText("Author name cannot be empty");
    }

    public static Author getAuthor(Stage stage, Author author) {
        return ModalController.showModal(AuthorDialogController.class.getResource("AuthorDialogView.fxml"),
                                         "New author", stage, author, null);
    }
}
