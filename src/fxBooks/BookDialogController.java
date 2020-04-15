package fxBooks;

import books.Author;
import books.Book;
import books.BookCollection;
import books.Publisher;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.RadioButtonChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Author author = new Author();
        author = AuthorDialogController.getAuthor(null, author);
        books.add(author);
        updateAuthors();
        dropdownAuthors.setValue(author);
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
        selectedBook = null;
        ModalController.closeStage(labelError);
    }

    @FXML
    void handleSave() {
        String bookTitle = textTitle.getText();
        String pubYear = textPubYear.getText();
        if (bookTitle.isEmpty()) {
            showError("Title cannot be empty", textTitle);
            return;
        } else if (dropdownAuthors.getValue().getName().isEmpty()) {
            showError("Author cannot be empty", null);
            return;
        } else if (!pubYear.matches("^\\d{4}$")) {
            showError("Invalid publication year", textPubYear);
            return;
        }

        handleChanges();
        ModalController.closeStage(labelError);
    }

    //==============================================================================

    private static BookCollection books;
    private Book selectedBook;

    /**
     * Sets the BookCollection
     * @param bc BookCollection
     */
    protected static void setBookCollection(BookCollection bc) {
        books = bc;
    }

    @Override
    public Book getResult() {
        return selectedBook;
    }

    @Override
    public void setDefault(Book book) {
        selectedBook = book;
        showBook(selectedBook);
    }

    @Override
    public void handleShown() {
        textTitle.requestFocus();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        assert dropdownAuthors != null : "dropdownAuthors was not injected";
        assert dropdownPublishers != null : "dropdownPublishers was not injected";
        assert textTitle != null : "textTitle was not initialized";

        updateAuthors();
        ObservableList<Publisher> pubList = FXCollections.observableArrayList(books.getPublishers());
        dropdownPublishers.setItems(pubList);
    }

    /**
     * Shows error message at the bottom of the window
     * @param msg error message
     */
    private void showError(String msg, TextField field) {
        if (msg.isEmpty()) {
            labelError.setText("");
            textPubYear.getStyleClass().removeAll("error");
        } else {
            labelError.setText(msg);
            if (field == textPubYear) {
                field.getStyleClass().add("error");
            }
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
     * Updates authors in the dropdown menu
     */
    private void updateAuthors() {
        ObservableList<Author> authorList = FXCollections.observableArrayList(books.getAuthors());
        dropdownAuthors.setItems(authorList);
    }

    /**
     * Updates all values for the book
     */
    private void handleChanges() {
        selectedBook.setTitle(textTitle.getText());
        selectedBook.setOriginalTitle((textOrigTitle.getText()));
        selectedBook.setAuthorId(dropdownAuthors.getValue().getId());
        selectedBook.setPubYear(Integer.parseInt(textPubYear.getText()));
        selectedBook.setPubId(dropdownPublishers.getValue().getId());
        selectedBook.setLanguage((textLang.getText()));
        selectedBook.setStatus(chooserStatus.getSelectedIndex());
        selectedBook.setRating(chooserRating.getSelectedIndex());
    }

    /**
     * Creates a dialog for retrieving a book
     * @param stage modality stage, for the application the value is null
     * @param book default book shown
     * @return edited data or null if pressed cancel
     */
    public static Book getBook(Stage stage, Book book) {
        return ModalController.showModal(BookDialogController.class.getResource("BookDialogView.fxml"),
                                         "Book", stage, book, null);
    }
}
