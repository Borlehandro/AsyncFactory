package com.borlehandro.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 650, 300));
        primaryStage.show();
        Controller controller = loader.getController();
        primaryStage.setOnCloseRequest(windowEvent -> {
            controller.shutdown();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}