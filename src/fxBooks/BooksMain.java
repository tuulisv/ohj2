package fxBooks;

import books.BookCollection;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Tuuli Veini
 * @version 31.1.2020
 *
 * Main class for launching the book catalogue program
 */
public class BooksMain extends Application {

    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxBooks/BooksMainView.fxml"));
            final Pane root = ldr.load();
            final BooksMainController booksCtrl = ldr.getController();
            final Scene scene = new Scene(root, 850, 550);

            final FXMLLoader enterLdr = new FXMLLoader(getClass().getResource("/fxBooks/BooksEnterView.fxml"));
            final Pane enterPane = enterLdr.load();
            final Scene enterScene = new Scene(enterPane);

            stage.setTitle("Book Collection");
            stage.setScene(enterScene);
            stage.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished((event) -> {
                stage.close();
                stage.setScene(scene);
                stage.show();
            });
            delay.play();

            scene.getStylesheets().add(getClass().getResource("books.css").toExternalForm());
            BookCollection books = new BookCollection();
            booksCtrl.setBookCollection(books);

            stage.setOnCloseRequest((event) -> {
                if (!booksCtrl.canClose()) event.consume();
            });

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
