package fxBooks;

import books.Author;
import books.Book;
import books.BookCollection;
import books.Publisher;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.RadioButtonChooser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles events related to adding a new book
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class BookDialogController implements ModalControllerInterface<Book>, Initializable {

    @FXML private TextField textTitle;
    @FXML private TextField textOrigTitle;
    @FXML private TextField textPubYear;
    @FXML private TextField textLang;
    @FXML private Label labelError;
    @FXML private Button okButton;
    @FXML private Button cancelButton;
    @FXML private RadioButtonChooser<Boolean> chooserStatus;
    @FXML private RadioButtonChooser<Integer> chooserRating;
    @FXML private ComboBox<Author> dropdownAuthors;
    @FXML private ComboBox<Publisher> dropdownPublishers;

    @FXML
    void handleNewAuthor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/AuthorDialogView.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/PubDialogView.fxml"));
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
        /*String pubYear = textPubYear.getText();
        if (pubYear.matches("^\\d{4}$")) {
            showError("Invalid publication year");
            return;
        }

        ModalController.closeStage(labelError);*/

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //==============================================================================

    private BookCollection books;

    /*
    protected void setBookCollection(BookCollection books) {
        this.books = books;
    } */

    @Override
    public Book getResult() {
        return null;
    }

    @Override
    public void setDefault(Book book) {

    }

    @Override
    public void handleShown() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Shows error message at the bottom of the window
     * @param msg error message
     */
    private void showError(String msg) {
        if (msg.isEmpty()) {
            labelError.setText("");
            //textPubYear.getStyleClass().removeAll("error");
        } else {
            labelError.setText(msg);
            //textPubYear.getStyleClass().add("error");
        }
    }

    /**
     * Show the book's values in the window
     * @param book shown book
     */
    public void showBook(Book book) {
        if (book == null) return;

        int author = book.getAuthorId();
        int publisher = book.getPubId();

        dropdownAuthors.setValue(books.getAuthorById(author));
        dropdownPublishers.setValue(books.getPublisherById(publisher));

        textTitle.setText(book.getTitle());
        textOrigTitle.setText(book.getOrigTitle());
        textPubYear.setText("" + book.getPubYear());
        textLang.setText(book.getLanguage());

        int status = book.getStatus() == 1 ? 1 : 0;
        chooserStatus.setSelectedIndex(status, true);
        chooserRating.setSelectedIndex(book.getRating(), true);
    }

    /**
     * Creates a dialog for retrieving a book
     * @param stage modality stage, for the application the value is null
     * @param book default book shown
     * @return edited data or null if pressed cancel
     */
    public static Book getBook(Stage stage, Book book) {
        return ModalController.showModal(BookDialogController.class.getResource("BookDialogView.fxml"),"Book", stage, book, null);
    }

}
