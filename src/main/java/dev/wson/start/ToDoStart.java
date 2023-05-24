package dev.wson.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class ToDoStart extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent janela = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layout/todostart.fxml")));
        Scene cena = new Scene(janela);
        primaryStage.setTitle("ToDoStart com JavaFX");
        primaryStage.setScene(cena);
        primaryStage.show();
    }
}