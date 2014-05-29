package org.pwr.ii.gui.listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 * Created by Vortim on 2014-05-29.
 */
public class FindButtonListener implements EventHandler<ActionEvent> {

    private Scene scene;

    public FindButtonListener(Scene mainScene) {
        this.scene = mainScene;
    }

    @Override
    public void handle(ActionEvent event) {

    }

}
