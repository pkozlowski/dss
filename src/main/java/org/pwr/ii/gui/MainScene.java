package org.pwr.ii.gui;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.pwr.ii.gui.components.NumericEventFilter;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */
public class MainScene extends Scene {
    private ColorPicker colorPicker;
    private Slider colorSlider;
    private TextField voltageMinimumField;
    private TextField voltageMaximumField;
    private Slider voltageSlider;
    private TextField priceMinimumField;
    private TextField priceMaximumField;
    private Slider priceSlider;
    private Button findButton;
    private Accordion accordion;

    public MainScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {
        super(root, width, height);
        initComponents();
    }

    private void initComponents() {
        NumericEventFilter eventFilter = new NumericEventFilter();

        initPriceComponents(eventFilter);
        initVoltageComponents(eventFilter);
        initColorComponents();
        initListComponent();
        findButton = ComponentProvider.getFindButton(this);
    }

    private void initListComponent() {
        accordion = ComponentProvider.getAccordion(this);
    }

    private void initColorComponents() {
        colorPicker = ComponentProvider.getColorPicker(this);
        colorSlider = ComponentProvider.getColorSlider(this);
    }

    private void initVoltageComponents(NumericEventFilter eventFilter) {
        voltageMinimumField = ComponentProvider.getVoltageMinimumField(this);
        voltageMinimumField.addEventFilter(KeyEvent.KEY_TYPED, eventFilter);

        voltageMaximumField = ComponentProvider.getVoltageMaximumField(this);
        voltageMaximumField.addEventFilter(KeyEvent.KEY_TYPED, eventFilter);

        voltageSlider = ComponentProvider.getVoltageSlider(this);
    }

    private void initPriceComponents(NumericEventFilter eventFilter) {
        priceMinimumField = ComponentProvider.getPriceMinimumField(this);
        priceMinimumField.addEventFilter(KeyEvent.KEY_TYPED, eventFilter);

        priceMaximumField = ComponentProvider.getPriceMaximumField(this);
        priceMaximumField.addEventFilter(KeyEvent.KEY_TYPED, eventFilter);

        priceSlider = ComponentProvider.getPriceSlider(this);
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public Slider getColorSlider() {
        return colorSlider;
    }

    public TextField getVoltageMinimumField() {
        return voltageMinimumField;
    }

    public TextField getVoltageMaximumField() {
        return voltageMaximumField;
    }

    public Slider getVoltageSlider() {
        return voltageSlider;
    }

    public TextField getPriceMinimumField() {
        return priceMinimumField;
    }

    public TextField getPriceMaximumField() {
        return priceMaximumField;
    }

    public Slider getPriceSlider() {
        return priceSlider;
    }

    public Button getFindButton() {
        return findButton;
    }

    public Accordion getAccordion() {
        return accordion;
    }
}

