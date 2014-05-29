package org.pwr.ii.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.pwr.ii.gui.listener.FindButtonListener;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Decision support system");
        Scene mainScene = new Scene(root, 300, 275);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        setUpFindButtonListener(mainScene);
    }

    private void setUpFindButtonListener(Scene mainScene) {
        Button findButton = ComponentProvider.getFindButton(mainScene);
        findButton.setOnAction(new FindButtonListener(mainScene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
