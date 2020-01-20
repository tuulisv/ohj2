package books;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BooksMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxBooks/BooksView.fxml"));
        primaryStage.setTitle("Books");
        primaryStage.setScene(new Scene(root, 850, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
