package org.pwr.ii.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * Created by Vortim on 2014-05-29.
 */
public class ComponentProvider {

    public static final String FIND_BUTTON = "#findButton";
    public static final String VOLTAGE_SLIDER = "#voltageSlider";
    public static final String PRICE_SLIDER = "#priceSlider";
    public static final String PRICE_MINIMUM = "#priceMinimum";
    public static final String PRICE_MAXIMUM = "#priceMaximum";
    public static final String VOLTAGE_MINIMUM = "#voltageMinimum";
    public static final String VOLTAGE_MAXIMUM = "#voltageMaximum";
    public static final String COLOR_PICKER = "#colorPicker";
    public static final String COLOR_SLIDER = "#colorSlider";
    public static final String ACCORDION_LIST = "#accordionList";
    public static final String SIZE_FIELD = "#sizeField";
    public static final String SIZE_SLIDER = "#sizeSlider";

    private ComponentProvider() {
    }

    public static Button getFindButton(Scene scene) {
        return (Button) scene.lookup(FIND_BUTTON);
    }

    public static Slider getVoltageSlider(Scene scene) {
        return (Slider) scene.lookup(VOLTAGE_SLIDER);
    }

    public static Slider getPriceSlider(Scene scene) {
        return (Slider) scene.lookup(PRICE_SLIDER);
    }

    public static TextField getPriceMinimumField(Scene scene) {
        return (TextField) scene.lookup(PRICE_MINIMUM);
    }

    public static TextField getPriceMaximumField(Scene scene) {
        return (TextField) scene.lookup(PRICE_MAXIMUM);
    }

    public static TextField getVoltageMinimumField(Scene scene) {
        return (TextField) scene.lookup(VOLTAGE_MINIMUM);
    }

    public static TextField getVoltageMaximumField(Scene scene) {
        return (TextField) scene.lookup(VOLTAGE_MAXIMUM);
    }

    public static ColorPicker getColorPicker(Scene scene) {
        return (ColorPicker) scene.lookup(COLOR_PICKER);
    }

    public static Slider getColorSlider(Scene scene) {
        return (Slider) scene.lookup(COLOR_SLIDER);
    }

    public static Accordion getAccordion(Scene scene) {
        return (Accordion) scene.lookup(ACCORDION_LIST);
    }

    public static TextField getSizeField(Scene scene) {
        return (TextField) scene.lookup(SIZE_FIELD);
    }

    public static Slider getSizeSlider(Scene scene) {
        return (Slider) scene.lookup(SIZE_SLIDER);
    }
}

