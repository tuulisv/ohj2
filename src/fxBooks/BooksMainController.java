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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Event handling in the main view
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 31.1.2020
 * @version 7.0 20.4.2020
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
    @FXML private Label labelUnsaved;

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("View program manual at https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/tumavein");
        alert.getDialogPane().setMinWidth(500);
        alert.show();
    }

    @FXML
    void handleAbout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxBooks/BooksAboutView.fxml"));
            Parent aboutRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(aboutRoot));
            stage.show();
        } catch (IOException e) {
            Dialogs.showMessageDialog("Error in opening about view: " + e.getMessage());
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
        selectRating();
    }

    @FXML
    void handleSelectStatus() {
        selectStatus();
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
     * Opens a dialog for adding a new book, sets the
     * currently selected author as default author
     */
    private void newBook() {
        Author author = chooserAuthors.getSelectedObject();
        Book book = new Book();
        if (author != null) book.setAuthorId(author.getId());
        book = BookDialogController.getBook(null, "New book", book, this.books);
        if (book == null || book.getTitle().trim().isEmpty()) return;
        book.register();
        this.books.add(book);
        addAuthorToList(book.getAuthorId());
        showAuthorsWorks();
        chooserBooks.setSelectedIndex(this.books.getBookIndex(book));
    }

    /**
     * Opens a dialog for editing the selected book
     */
    private void editBook() {
        Book selectedBook = chooserBooks.getSelectedObject();
        if (selectedBook == null) return;
        BookDialogController.getBook(null, "Edit book", selectedBook, this.books);
        this.books.replaceOrAdd(selectedBook);
        showAuthorsWorks();
        chooserBooks.setSelectedIndex(this.books.getBookIndex(selectedBook));
    }

    /**
     * Adds the author to the author list chooser and sets it as selected
     * @param id author id
     */
    private void addAuthorToList(int id) {
        chooserAuthors.clear();
        int index = this.books.getNoOfAuthors() - 1;
        for (int i = 0; i < this.books.getNoOfAuthors(); i++) {
            Author author = this.books.getAuthor(i);
            if (this.books.getAuthor(i).getId() == id) {
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
        Publisher publisher = this.books.getPublisherById(selectedBook.getPubId());

        labelBookTitle.setText(selectedBook.getTitle());
        labelOrigTitle.setText(selectedBook.getOrigTitle());
        labelAuthor.setText(selectedAuthor.getName());
        labelPubYear.setText("" + selectedBook.getPubYear());
        labelPub.setText(publisher.getName());
        labelLang.setText(selectedBook.getLanguage());

        int status = selectedBook.getStatus() == 1 ? 1 : 0;
        chooserStatus.setSelectedIndex(status, true);
        int rating = selectedBook.getRating();
        chooserRating.setSelectedIndex(rating, true);
    }

    /**
     * Shows null values for book
     */
    private void showEmptyBook() {
        labelBookTitle.setText("");
        labelOrigTitle.setText("");
        labelAuthor.setText("");
        labelPubYear.setText("");
        labelPub.setText("");
        labelLang.setText("");
        chooserStatus.setSelectedIndex(chooserStatus.getSelectedIndex(), false);
        chooserRating.setSelectedIndex(chooserRating.getSelectedIndex(), false);
    }

    /**
     * Shows the books by the selected author
     */
    private void showAuthorsWorks() {
        Author selectedAuthor = chooserAuthors.getSelectedObject();
        if (selectedAuthor == null) return;

        List<Book> works = this.books.getAuthorsWorks(selectedAuthor.getId());
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
    private void search(String str) {
        List<Author> foundAuthors = this.books.search(str);
        chooserAuthors.clear();

        if (foundAuthors.isEmpty()) {
            chooserBooks.clear();
            showEmptyBook();
            return;
        }

        for (Author author: foundAuthors) {
            chooserAuthors.add("" + author.getName(), author);
        }

        chooserAuthors.setSelectedIndex(foundAuthors.size() - 1);
    }

    /**
     * Shows a confirmation alert for deleting the
     * selected book and deletes it if pressed ok
     */
    private void remove() {
        Book book = chooserBooks.getSelectedObject();
        if (book == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove");
        alert.setHeaderText(null);
        alert.setContentText("Remove selected book?");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(ok, cancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ok) {
            int index = this.books.getAuthorIndex(book.getAuthorId());
            this.books.remove(book);
            search(""); // refreshes author list
            chooserAuthors.setSelectedIndex(index);
        }
    }

    /**
     * Change the rating of the book
     */
    private void selectRating() {
        Book book = chooserBooks.getSelectedObject();
        int rating = chooserRating.getSelectedIndex();
        if (book.getRating() != rating) {
            book.setRating(rating);
            unsavedChanges(true);
        }
    }

    /**
     * Change the status of the book
     */
    private void selectStatus() {
        Book book = chooserBooks.getSelectedObject();
        int status = chooserStatus.getSelectedIndex();
        if (book.getStatus() != status) {
            book.setStatus(status);
            unsavedChanges(true);
        }
    }

    /**
     * Shows a warning about unsaved changes
     */
    private void unsavedChanges(boolean changes) {
        if (changes) {
            labelUnsaved.setText("Unsaved changes");
            labelUnsaved.getStyleClass().add("error");
        } else {
            labelUnsaved.setText("");
            labelUnsaved.getStyleClass().removeAll("error");
        }
    }

    /**
     * Saves changes and returns confirmation
     * @return true if saved
     */
    protected boolean canClose() {
        save();
        return true;
    }

    /**
     * Save changes to all files
     */
    private void save() {
        try {
            this.books.save();
            unsavedChanges(false);
        } catch (StoreException e) {
            Dialogs.showMessageDialog("Error in saving files: " + e.getMessage());
        }
    }

    /**
     * Reads files
     */
    protected void readFile() {
        try {
            this.books.readFile();
            search(""); //refreshes the author list
        } catch (StoreException e) {
            Dialogs.showMessageDialog("Error in reading file: " + e.getMessage());
        }
    }

    /**
     * Set BookCollection as one given by the controller
     * @param books referred BookCollection
     */
    protected void setBookCollection(BookCollection books) {
        this.books = books;
    }
}
