package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
