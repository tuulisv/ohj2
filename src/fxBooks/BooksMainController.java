package fxBooks;

import books.Author;
import books.Book;
import books.BookCollection;
import books.Publisher;
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
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Event handling in the main view
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
        initializeAuthors();
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
    void handleAddPublisher() {
        newPublisher();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSearch() {
        BooksMain.errorGeneral();
    }

    @FXML
    void handleSelectAuthor() {
        showAuthorsWorks();
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
     * Clears the author list
     */
    private void initializeAuthors() {
        chooserAuthors.clear();
        chooserAuthors.addSelectionListener((event) -> showAuthorsWorks());
    }

    /**
     * Adds a new book with test values
     */
    private void newBook() {
        Author selectedAuthor = chooserAuthors.getSelectedObject();
        if (selectedAuthor == null) {
            BooksMain.errorGeneral();
            return;
        }

        Book book = new Book(selectedAuthor.getId());
        book.register();
        book.exampleBook();
        books.addBook(book);
        showAuthorsWorks();
    }

    /**
     * Adds a new author with example values
     */
    private void newAuthor() {
        Author author = new Author();
        author.register();
        author.exampleAuthor();
        books.addAuthor(author);
        addAuthorToList(author.getId());
    }

    /**
     * Adds a new publisher with example values
     */
    private void newPublisher() {
        Publisher publisher = new Publisher();
        publisher.register();
        publisher.examplePublisher();
        books.addPublisher(publisher);
    }

    /**
     * Adds the author to the author list chooser and sets it as selected
     * @param id author id
     */
    private void addAuthorToList(int id) {
        //chooserAuthors.clear();
        int index = books.getNoOfAuthors() - 1;
        /*for (int i = 0; i < books.getNoOfAuthors(); i++) {
            Author author = books.getAuthor(i);
            if (books.getAuthor(i).getId() == id) {
                index = i;
            }

            chooserAuthors.add("" + author.getName(), author);
        }*/
        Author author = books.getAuthorById(id);
        chooserAuthors.add("" + author.getName(), author);
        chooserAuthors.setSelectedIndex(index);
    }

    /**
     * Shows the attribute values of the book
     */
    private void showBook() {
        Author selectedAuthor = chooserAuthors.getSelectedObject();
        Book selectedBook = chooserBooks.getSelectedObject();
        if (selectedBook == null) return;

        labelBookTitle.setText(selectedBook.getTitle());
        labelOrigTitle.setText(selectedBook.getOrigTitle());
        labelAuthor.setText("" + selectedAuthor.getName());
        labelPubYear.setText("" + selectedBook.getPubYear());

        if (books.getNoOfPublishers() == 0) {
            labelPub.setText("");
        } else {
            Publisher pub = books.getRandomPublisher();
            labelPub.setText("" + pub.getPublisher());
        }

        labelLang.setText(selectedBook.getLanguage());
        int status = 0;
        if (!selectedBook.getStatus()) status = 1;
        chooserStatus.setSelectedIndex(status, true);
        int rating = selectedBook.getRating();
        chooserRating.setSelectedIndex(rating, true);
    }

    /**
     * Shows empty attribute values of a book
     */
    private void showEmptyBook() {
        labelBookTitle.setText("");
        labelOrigTitle.setText("");
        labelAuthor.setText("");
        labelPubYear.setText("");
        labelPub.setText("");
        labelLang.setText("");
        chooserStatus.setSelectedIndex(0, false);
        chooserRating.setSelectedIndex(0, false);
    }

    /**
     * Shows the books by the selected author
     */
    private void showAuthorsWorks() {
        Author selectedAuthor = chooserAuthors.getSelectedObject();
        if (selectedAuthor == null) return;

        List<Book> works = books.getAuthorsWorks(selectedAuthor);
        if (works.isEmpty()) showEmptyBook();

        chooserBooks.clear();
        for (int i = 0; i < works.size(); i++) {
            chooserBooks.add("" + works.get(i).getTitle(), works.get(i));
            chooserBooks.setSelectedIndex(i);
        }
    }

    /**
     * Set BookCollection as one given by the controller
     * @param books referred BookCollection
     */
    public void setBookCollection(BookCollection books) {
        this.books = books;
    }
}
