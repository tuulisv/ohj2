package fxBooks;

import books.BookCollection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Tuuli Veini
 * @version 31.1.2020
 * <p>
 * Main class for launching the book register program
 */
public class BooksMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxBooks/BooksMainView.fxml"));
            final Pane root = (Pane) ldr.load();
            final BooksMainController booksCtrl = (BooksMainController) ldr.getController();
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

    /**
     * Error popup
     */
    protected static void errorGeneral() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ei toimi");
        alert.showAndWait();
    }
}
