package org.pwr.ii.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

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
    public static ColorPicker getColorPicker(Scene scene){
        return (ColorPicker) scene.lookup("#colorPicker");
    }
    public static Slider getColorSlider(Scene scene){
        return (Slider) scene.lookup("#colorSlider");
    }
    public static Accordion getAccordion(MainScene scene) {
        return (Accordion) scene.lookup("#accordionList");
    }
    public static TextField getSizeField(MainScene scene) {  return (TextField) scene.lookup("#sizeField");  }
    public static Slider getSizeSlider(MainScene scene) {return (Slider) scene.lookup("#sizeSlider"); }
}

