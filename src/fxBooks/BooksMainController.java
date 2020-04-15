package fxBooks;

import books.*;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.RadioButtonChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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
    void handleEdit() {
        editBook();
    }

    @FXML
    void handleSave() {
        save();
    }

    @FXML
    void handleRemove() {
        remove();
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

    private BookCollection books;

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
        textSearch.setOnKeyReleased((event) -> search(textSearch.getText()));
    }

    /**
     * Adds a new book with test values
     */
    private void newBook() {
        Author author = chooserAuthors.getSelectedObject();
        Book book = new Book();
        if (author != null) book.setAuthorId(author.getId());
        book = BookDialogController.getBook(null, book);
        if (book == null) return;
        book.register();
        books.add(book);
        addAuthorToList(book.getAuthorId());
        showAuthorsWorks();
    }

    /**
     * Edit the selected book
     */
    private void editBook() {
        Book selectedBook = chooserBooks.getSelectedObject();
        if (selectedBook == null) return;
        BookDialogController.getBook(null, selectedBook);
        books.replaceOrAdd(selectedBook);
        showAuthorsWorks();
    }

    /**
     * Adds the author to the author list chooser and sets it as selected
     * @param id author id
     */
    private void addAuthorToList(int id) {
        chooserAuthors.clear();
        int index = books.getNoOfAuthors() - 1;
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
        if (chooserBooks.getObjects().isEmpty()) return;
        Author selectedAuthor = chooserAuthors.getSelectedObject();
        Book selectedBook = chooserBooks.getSelectedObject();
        Publisher publisher = books.getPublisherById(selectedBook.getPubId());
        if (selectedBook == null) return;

        labelBookTitle.setText(selectedBook.getTitle());
        labelOrigTitle.setText(selectedBook.getOrigTitle());
        labelAuthor.setText("" + selectedAuthor.getName());
        labelPubYear.setText("" + selectedBook.getPubYear());
        labelPub.setText("" + publisher.getName());
        labelLang.setText(selectedBook.getLanguage());

        int status = selectedBook.getStatus() == 1 ? 1 : 0;
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
        chooserStatus.setSelectedIndex(chooserStatus.getSelectedIndex(), false);
        //chooserRating.setSelectedIndex(0, true);
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
     * Search and display authors based on the search term
     * @param str search term
     */
    public void search(String str) {
        List<Author> foundAuthors = books.search(str);
        chooserAuthors.clear();
        for (int i = 0; i < foundAuthors.size(); i++) {
            Author author = foundAuthors.get(i);
            chooserAuthors.add("" + author.getName(), author);
        }

        chooserAuthors.setSelectedIndex(foundAuthors.size() - 1);
    }

    private void remove() {
        Book book = chooserBooks.getSelectedObject();
        if (book == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove book");
        alert.setHeaderText(null);
        alert.setContentText("Remove selected book?");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(ok, cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ok) {
            books.remove(book);
            search(""); // updates author list
            chooserAuthors.setSelectedIndex(this.books.getAuthorIndex(book.getAuthorId()));
        }
    }

    /**
     * Check if changes are saved
     * @return true if saved
     */
    public boolean canClose() {
        save();
        return true;
    }

    /**
     * Save changes to all files
     */
    private void save() {
        try {
            books.save();
        } catch (StoreException e) {
            Dialogs.showMessageDialog("Error in saving files: " + e.getMessage());
        }
    }

    /**
     * Reads files
     */
    public void readFile() {
        try {
            books.readFile();
            search(""); //refreshes the author list
        } catch (StoreException e) {
            Dialogs.showMessageDialog("Error in reading file: " + e.getMessage());
        }
    }

    /**
     * Set BookCollection as one given by the controller
     * @param books referred BookCollection
     */
    public void setBookCollection(BookCollection books) {
        this.books = books;
        BookDialogController.setBookCollection(books);
    }
}
