package org.pwr.ii.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Created by Vortim on 2014-05-29.
 */
public class ComponentProvider {

    private ComponentProvider() {
    }

    public static Button getFindButton(Scene scene) {
        return (Button) scene.lookup("#findButton");
    }

    public static Slider getVoltageSlider(Scene scene) {
        return (Slider) scene.lookup("#voltageSlider");
    }

    public static Slider getPriceSlider(Scene scene) {
        return (Slider) scene.lookup("#priceSlider");
    }

    public static TextField getPriceMinimumField(Scene scene) {
        return (TextField) scene.lookup("#priceMinimum");
    }

    public static TextField getPriceMaximumField(Scene scene) {
        return (TextField) scene.lookup("#priceMaximum");
    }

    public static TextField getVoltageMinimumField(Scene scene) {
        return (TextField) scene.lookup("#voltageMinimum");
    }

    public static TextField getVoltageMaximumField(Scene scene) {
        return (TextField) scene.lookup("#voltageMaximum");
    }
}

