package org.pwr.ii.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import org.pwr.ii.criteria.*;
import org.pwr.ii.database.DatabaseUtils;
import org.pwr.ii.gui.components.BottleTitledPane;
import org.pwr.ii.gui.components.MaxValueValidatorListener;

import java.util.List;
import java.util.stream.Collectors;

import static org.pwr.ii.gui.ComponentProvider.*;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */
public class Controller implements EventHandler<ActionEvent> {
    public static final int NUMBER_OF_RESULTS = 10;
    private Scene scene;
    private DatabaseUtils database;
    private List<Alcohol> alcohols;

    public Controller(Scene scene) {
        initialize(scene);
    }

    public void initialize(Scene scene) {
        this.scene = scene;
        getFindButton(this.scene).setOnAction(this);
        loadDatabase();
        initValidators();
    }

    private void initValidators() {
        TextField priceMaximumField = getPriceMaximumField(scene);
        priceMaximumField.focusedProperty().addListener(new MaxValueValidatorListener(database.getMaxPrice(), priceMaximumField));
        TextField voltageMaximumField = getVoltageMaximumField(scene);
        voltageMaximumField.focusedProperty().addListener(new MaxValueValidatorListener(database.getMaxVoltage(), voltageMaximumField));
    }

    private void loadDatabase() {
        String databasePath = "jdbc:sqlite:src/main/resources/alcohol.sqlite";
        String driver = "org.sqlite.JDBC";
        database = new DatabaseUtils(driver, databasePath);
        try {
            database.init();
        } catch (ClassNotFoundException e) {
            System.err.println("Could not initialize database !");
            e.printStackTrace();
        }
        alcohols = database.getDatabaseContent();
    }

    @Override
    public void handle(ActionEvent event) {
        CriteriaCalculator calculator = setUpCriteriaCalculator();
        Accordion accordion = ComponentProvider.getAccordion(scene);
        accordion.getPanes().clear();
        List<Alcohol> alcoholList = calculator.getBestAlcohols().collect(Collectors.toList());
        for (int i = 0; i < alcoholList.size() && i < NUMBER_OF_RESULTS; i++) {
            Alcohol alcohol = alcoholList.get(i);
            accordion.getPanes().add(new BottleTitledPane(alcohol, calculator, accordion.getHeight()));
        }
    }

    private CriteriaCalculator setUpCriteriaCalculator() {
        CriteriaCalculator calculator = new CriteriaCalculator(alcohols);
        double sumOfAllSliders = getPriceSlider(scene).getValue() + getVoltageSlider(scene).getValue()
                + getColorSlider(scene).getValue() + getSizeSlider(scene).getValue();

        double priceFactor = getPriceSlider(scene).getValue() / sumOfAllSliders;
        calculator.addCriterion(createPriceCriterion(priceFactor));

        double voltageFactor = getVoltageSlider(scene).getValue() / sumOfAllSliders;
        calculator.addCriterion(createVoltageCriterion(voltageFactor));

        double colorFactor = getColorSlider(scene).getValue() / sumOfAllSliders;
        calculator.addCriterion(createColorCriterion(colorFactor));

        double sizeFactor = getSizeSlider(scene).getValue() / sumOfAllSliders;
        calculator.addCriterion(createSizeCriterion(sizeFactor));

        return calculator;
    }

    private Criterion createSizeCriterion(double sizeFactor) {
        double size = getNumericValueFromTextField(getSizeField(scene));
        return new SizeCriterion(sizeFactor, size, database.getMaxSize());
    }

    private Criterion createColorCriterion(double colorFactor) {
        return new ColorCriterion(colorFactor, getColorPicker(scene).getValue());
    }

    private Criterion createVoltageCriterion(double voltageFactor) {
        double minVol = getNumericValueFromTextField(getVoltageMinimumField(scene));
        double maxVol = getNumericValueFromTextField(getVoltageMaximumField(scene));
        return new VoltageCriterion(voltageFactor, minVol, maxVol, database.getMaxVoltage());
    }

    private double getNumericValueFromTextField(TextField textField) {
        String text = textField.getText();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }

    private Criterion createPriceCriterion(double priceFactor) {
        double maxPrice = getNumericValueFromTextField(getPriceMaximumField(scene));
        double minPrice = getNumericValueFromTextField(getPriceMinimumField(scene));
        return new PriceCriterion(priceFactor, minPrice, maxPrice, database.getMaxPrice());
    }
}
