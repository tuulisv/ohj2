package fxBooks;

import books.Author;
import books.Book;
import books.BookCollection;
import books.StoreException;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.RadioButtonChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Event handling in the main view
 *
 * @author Tuuli Veini
 * @version 31.1.2020
 */
public class BooksMainController implements Initializable {

    @FXML private TextField textSearch;
    @FXML private ListChooser<Book> chooserBooks;
    @FXML private ListChooser<Author> chooserAuthors;
    @FXML private Label labelBookTitle;
    @FXML private Label labelOrigTitle;
    @FXML private Label labelAuthor;
    @FXML private Label labelPubYear;
    @FXML private Label labelPub;
    @FXML private Label labelLang;
    @FXML private RadioButtonChooser<Boolean> chooserStatus;
    @FXML private RadioButtonChooser<Integer> chooserRating;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        initializeBooks();
    }

    @FXML
    void handleAddBook() {
        newBook();
    }

    @FXML
    void handleAddAuthor() {
        newAuthor();
    }

    @FXML
    void handleEdit() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleSave() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleRemove() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleQuit() {
        Platform.exit();
    }

    @FXML
    void handleHelp() {
        BooksMain.errorGeneral();
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
        BooksMain.errorGeneral();
    }

    @FXML
    void handleSelectAuthor() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleSelectBook() {
        showBook();
    }

    @FXML
    void handleSelectRating() {

    }

    @FXML
    void handleSelectStatus() {

    }

    //==============================================================================

    BookCollection books;

    /**
     * Clears the book list
     */
    private void initializeBooks() {
        chooserBooks.clear();
        chooserBooks.addSelectionListener((event) -> showBook());
    }

    /**
     * Adds a new book with test values
     */
    private void newBook() {
        Book book = new Book();
        book.register();
        book.exampleBook();

        try {
            books.addBook(book);
        } catch (StoreException e) {
            Dialogs.showMessageDialog("Error in adding book\n" + e.getMessage());
            return;
        }

        chooseBookById(book.getId());
    }

    /**
     * Adds a new author with test values
     */
    private void newAuthor() {
        Author author = new Author();
        author.register();
        author.exampleAuthor();
        books.addAuthor(author);
        chooseAuthorById(author.getId());
    }

    /**
     * Chooses the book corresponding to the id
     * @param id id of the book
     */
    private void chooseBookById(int id) {
        chooserBooks.clear();
        int index = 0;
        for (int i = 0; i < books.getNoOfBooks(); i++) {
            Book book = books.getBook(i);
            if (book.getId() == id) {
                index = i;
            }

            chooserBooks.add("" + book.getTitle(), book);
        }

        chooserBooks.setSelectedIndex(index);
    }

    /**
     * Chooses the author corresponding to the id
     * @param id author id
     */
    private void chooseAuthorById(int id) {
        chooserAuthors.clear();
        int index = 0;
        for (int i = 0; i < books.getNoOfAuthors(); i++) {
            Author author = books.getAuthor(i);
            if (books.getAuthor(i).getId() == id) {
                index = i;
            }

            chooserAuthors.add("" + author.getName(), author);
        }

        chooserAuthors.setSelectedIndex(index);
    }

    /**
     * Shows the attribute values of the book
     */
    private void showBook() {
        Book selectedBook = chooserBooks.getSelectedObject();
        if (selectedBook == null) return;

        labelBookTitle.setText(selectedBook.getTitle());
        labelOrigTitle.setText(selectedBook.getOrigTitle());
        labelAuthor.setText("" + selectedBook.getAuthorIndex());
        labelPubYear.setText("" + selectedBook.getPubYear());
        labelPub.setText("" + selectedBook.getPubIndex());
        labelLang.setText(selectedBook.getLanguage());
        int status = 0;
        if (!selectedBook.getStatus()) status = 1;
        chooserStatus.setSelectedIndex(status, true);
        int rating = selectedBook.getRating();
        chooserRating.setSelectedIndex(rating, true);
    }

    /**
     * Set BookCollection as one given by the controller
     * @param books referred BookCollection
     */
    public void setBookCollection(BookCollection books) {
        this.books = books;
    }
}
