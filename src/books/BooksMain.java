package books;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * @author Tuuli Veini
 * @version 31.1.2020
 *
 * Pääohjelma kirjarekisteriohjelman käynnistämiseksi
 */
public class BooksMain extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxBooks/BooksMainView.fxml"));
        stage.setTitle("Books");
        stage.setScene(new Scene(root, 850, 550));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    protected static void notWorking() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ei toimi");
        alert.showAndWait();
    }
}
