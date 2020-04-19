package fxBooks;

import books.Author;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles adding a new author
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 31.1.2020
 * @version 7.0 19.4.2020
 */
public class AuthorDialogController implements ModalControllerInterface<Author> {

    @FXML private TextField textAuthor;
    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private Label labelError;

    @FXML
    void handleOk() {
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

    /**
     * Defines what is returned
     * @return new author
     */
    @Override
    public Author getResult() {
        return newAuthor;
    }

    /**
     * Default value for the author
     * @param author author
     */
    @Override
    public void setDefault(Author author) {
        newAuthor = author;
    }

    /**
     * Focuses to text field when dialog is opened
     */
    @Override
    public void handleShown() {
        textAuthor.requestFocus();
    }

    /**
     * Updates values for the author
     */
    private void handleChanges() {
        newAuthor.setName(textAuthor.getText());
        newAuthor.register();
    }

    /**
     * Shows error message in the window
     */
    private void showError() {
        labelError.setText("Author name cannot be empty");
        labelError.getStyleClass().add("error");
    }

    /**
     * Creates a dialog for adding a new author
     * @param stage modality stage
     * @param author default author shown
     * @return edited data or null if pressed cancel
     */
    public static Author getAuthor(Stage stage, Author author) {
        return ModalController.showModal(AuthorDialogController.class.getResource("AuthorDialogView.fxml"),
                                         "New author", stage, author, null);
    }
}
