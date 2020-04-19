package fxBooks;

import books.BookCollection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main class for launching the book catalogue program
 *
 * @author Tuuli Veini (tuuli.m.veini at student.jyu.fi)
 * @version 1.0 31.1.2020
 * @version 7.0 19.4.2020
 */
public class BooksMain extends Application {

    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxBooks/BooksMainView.fxml"));
            final Pane root = ldr.load();
            final BooksMainController booksCtrl = ldr.getController();
            final Scene scene = new Scene(root, 850, 550);

            scene.getStylesheets().add(getClass().getResource("books.css").toExternalForm());
            stage.setTitle("Book Collection");
            stage.setScene(scene);

            BookCollection books = new BookCollection();
            booksCtrl.setBookCollection(books);

            stage.setOnCloseRequest((event) -> {
                if (!booksCtrl.canClose()) event.consume();
            });

            stage.show();
            booksCtrl.readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the UI
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
