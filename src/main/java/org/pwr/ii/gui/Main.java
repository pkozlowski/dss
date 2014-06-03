package org.pwr.ii.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        initializePrimaryStage(primaryStage);
        MainScene mainScene = new MainScene(root);
        primaryStage.setScene(mainScene);
        new Controller(mainScene);
        primaryStage.show();
    }

    public void initializePrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Decision support system - K&K Programming");
        primaryStage.setMinWidth(690);
        primaryStage.setMinHeight(450);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
