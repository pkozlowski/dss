package org.pwr.ii.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Decision support system");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);
        MainScene mainScene = new MainScene(root, 1000, 700);
        primaryStage.setScene(mainScene);
        new Controller(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
