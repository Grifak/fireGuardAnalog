package fire.guard.analog.fireguard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FireGuardApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FireGuardApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 900);
        stage.setTitle("Горючие газы - Отчет");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}