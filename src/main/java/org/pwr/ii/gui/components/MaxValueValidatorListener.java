package org.pwr.ii.gui.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-06-05.
 */
public class MaxValueValidatorListener implements ChangeListener<Boolean> {
    private final TextField textField;
    double maxValue;

    public MaxValueValidatorListener(double maxValue, TextField textField) {
        this.maxValue = maxValue;
        this.textField = textField;
    }


    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            if (Integer.parseInt(textField.getText()) > maxValue)
                textField.setStyle("-fx-text-fill: red;");
            else
                textField.setStyle("-fx-text-fill: black;");
        }
    }
}
