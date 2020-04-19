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
import javafx.fxml.Initializable;
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
    @FXML private RadioButtonChooser<Boolean> chooserStatus;
    @FXML private RadioButtonChooser<Integer> chooserRating;
    @FXML private ComboBox<Author> dropdownAuthors;
    @FXML private ComboBox<Publisher> dropdownPublishers;

    @FXML
    void handleNewAuthor() {
        Author author = new Author();
        author = AuthorDialogController.getAuthor(null, author);
        if (author.getName().trim().isEmpty()) return;
        this.books.add(author);
        updateAuthors();
        dropdownAuthors.setValue(author);
    }

    @FXML
    void handleNewPublisher() {
        Publisher publisher = new Publisher();
        publisher = PubDialogController.getPublisher(null, publisher);
        if (publisher.getName().trim().isEmpty()) return;
        this.books.add(publisher);
        updatePublishers();
        dropdownPublishers.setValue(publisher);
    }

    @FXML
    void handleCancel() {
        this.selectedBook = null;
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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

    //==============================================================================

    private BookCollection books;
    private Book selectedBook;

    /**
     * Sets the BookCollection
     * @param books BookCollection
     */
    private void setBookCollection(BookCollection books) {
        this.books = books;
    }

    /**
     * Defines what is returned
     * @return book
     */
    @Override
    public Book getResult() {
        return this.selectedBook;
    }

    /**
     * Default value for the book
     * @param book book
     */
    @Override
    public void setDefault(Book book) {
        this.selectedBook = book;
    }

    /**
     * What is shown when the dialog is opened
     */
    @Override
    public void handleShown() {
        showBook(this.selectedBook);
        updateAuthors();
        updatePublishers();
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
            labelError.getStyleClass().add("error");
            if (field == textPubYear) {
                field.getStyleClass().add("error");
            }
        }
    }

    /**
     * Show the book's values in the fields
     * @param book shown book
     */
    private void showBook(Book book) {
        if (book == null) return;

        int author = book.getAuthorId();
        int publisher = book.getPubId();
        dropdownAuthors.setValue(this.books.getAuthorById(author));
        dropdownPublishers.setValue(this.books.getPublisherById(publisher));
        textTitle.setText(book.getTitle());
        textOrigTitle.setText(book.getOrigTitle());

        int pubYear = book.getPubYear();
        if (pubYear != 0) {
            textPubYear.setText("" + book.getPubYear());
        }

        textLang.setText(book.getLanguage());

        int status = book.getStatus() == 1 ? 1 : 0;
        chooserStatus.setSelectedIndex(status, true);
        chooserRating.setSelectedIndex(book.getRating(), true);
    }

    /**
     * Updates authors in the dropdown menu
     */
    private void updateAuthors() {
        ObservableList<Author> authorList = FXCollections.observableArrayList(this.books.getAuthors());
        dropdownAuthors.setItems(authorList);
    }

    /**
     * Updates publishers in the dropdown menu
     */
    private void updatePublishers() {
        ObservableList<Publisher> pubList = FXCollections.observableArrayList(this.books.getPublishers());
        dropdownPublishers.setItems(pubList.sorted());
    }

    /**
     * Updates all values for the book
     */
    private void handleChanges() {
        this.selectedBook.setTitle(textTitle.getText());
        this.selectedBook.setOriginalTitle((textOrigTitle.getText()));
        this.selectedBook.setAuthorId(dropdownAuthors.getValue().getId());
        this.selectedBook.setPubYear(Integer.parseInt(textPubYear.getText()));
        this.selectedBook.setPubId(dropdownPublishers.getValue().getId());
        this.selectedBook.setLanguage((textLang.getText()));
        this.selectedBook.setStatus(chooserStatus.getSelectedIndex());
        this.selectedBook.setRating(chooserRating.getSelectedIndex());
    }

    /**
     * Creates a dialog for retrieving a book
     * @param stage modality stage, for the application the value is null
     * @param book default book shown
     * @param bc book collection
     * @return edited data or null if pressed cancel
     */
    public static Book getBook(Stage stage, Book book, BookCollection bc) {
        return ModalController.<Book, BookDialogController>showModal(
                BookDialogController.class.getResource("/fxBooks/BookDialogView.fxml"),
                "Book",
                stage, book,
                ctrl -> ctrl.setBookCollection(bc)
        );
    }
}
