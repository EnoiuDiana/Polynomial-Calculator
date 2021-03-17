package poly.calculator.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/poly/calculator/View/scene.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Polynomial Calculator");
        stage.getIcons().add(new Image("/poly/calculator/View/calculatorIcon.png"));
        stage.setScene(scene);
        stage.show();

    }
}
