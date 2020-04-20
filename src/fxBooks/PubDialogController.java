package fxBooks;

import books.Publisher;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles adding a new publisher
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 31.1.2020
 * @version 7.0 20.4.2020
 */
public class PubDialogController implements ModalControllerInterface<Publisher> {

    @FXML private TextField textPublisher;
    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private Label labelError;

    @FXML
    void handleAddPublisher() {
        if (textPublisher.getText().trim().isEmpty()) {
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

    //========================================================================

    private Publisher newPublisher;

    /**
     * Defines that new publisher is returned
     * @return new publisher
     */
    @Override
    public Publisher getResult() {
        return this.newPublisher;
    }

    /**
     * Default value for the publisher
     * @param publisher default publisher
     */
    @Override
    public void setDefault(Publisher publisher) {
        this.newPublisher = publisher;
    }

    /**
     * Focuses to text field when dialog is opened
     */
    @Override
    public void handleShown() {
        textPublisher.requestFocus();
    }

    /**
     * Updates values for the publisher
     */
    private void handleChanges() {
        this.newPublisher.setName(textPublisher.getText());
        this.newPublisher.register();
    }

    /**
     * Shows error message
     */
    private void showError() {
        labelError.setText("Publisher name cannot be empty");
        labelError.getStyleClass().add("error");
    }

    /**
     * Creates a dialog for adding a new publisher
     * @param stage modality stage
     * @param publisher default publisher shown
     * @return edited data or null if pressed cancel
     */
    public static Publisher getPublisher(Stage stage, Publisher publisher) {
        return ModalController.showModal(PubDialogController.class.getResource("PubDialogView.fxml"),
                                         "New publisher", stage, publisher, null);
    }
}