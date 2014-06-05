package org.pwr.ii.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private MainScene mainScene;

    public Main() throws IOException {
        this(FXMLLoader.load(Main.class.getClassLoader().getResource("main.fxml")));
    }

    public Main(Parent root) {
        mainScene = new MainScene(root);
        new Controller(root.getScene());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializePrimaryStage(primaryStage);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void initializePrimaryStage(Stage primaryStage) {
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
