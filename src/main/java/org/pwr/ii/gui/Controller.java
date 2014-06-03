package org.pwr.ii.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import org.pwr.ii.criteria.*;
import org.pwr.ii.database.DatabaseUtils;
import org.pwr.ii.gui.components.BottleTitledPane;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */
public class Controller implements EventHandler<ActionEvent> {
    public static final int NUMBER_OF_RESULTS = 10;
    private final MainScene scene;
    private DatabaseUtils database;
    private List<Alcohol> alcohols;

    public Controller(MainScene scene) {
        this.scene = scene;
        scene.getFindButton().setOnAction(this);
        loadDatabase();
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
        Accordion accordion = scene.getAccordion();
        accordion.getPanes().clear();
        List<Alcohol> alcoholList = calculator.getBestAlcohols().collect(Collectors.toList());
        for (int i = 0; i < alcoholList.size() && i < NUMBER_OF_RESULTS; i++) {
            Alcohol alcohol = alcoholList.get(i);
            accordion.getPanes().add(new BottleTitledPane(alcohol, calculator, accordion.getWidth(), accordion.getHeight()));
        }
    }

    private CriteriaCalculator setUpCriteriaCalculator() {
        CriteriaCalculator calculator = new CriteriaCalculator(alcohols);
        double sumOfAllSliders = scene.getPriceSlider().getValue() + scene.getVoltageSlider().getValue()
                + scene.getColorSlider().getValue() + scene.getSizeSlider().getValue();

        double priceFactor = scene.getPriceSlider().getValue() / sumOfAllSliders;
        calculator.addCriterion(createPriceCriterion(priceFactor));

        double voltageFactor = scene.getVoltageSlider().getValue() / sumOfAllSliders;
        calculator.addCriterion(createVoltageCriterion(voltageFactor));

        double colorFactor = scene.getColorSlider().getValue() / sumOfAllSliders;
        calculator.addCriterion(createColorCriterion(colorFactor));

        double sizeFactor = scene.getSizeSlider().getValue() / sumOfAllSliders;
        calculator.addCriterion(createSizeCriterion(sizeFactor));

        return calculator;
    }

    private Criterion createSizeCriterion(double sizeFactor) {
        double size = getNumericValueFromTextField(scene.getSizeField());
        return new SizeCriterion(sizeFactor, size, database.getMaxSize());
    }

    private Criterion createColorCriterion(double colorFactor) {
        return new ColorCriterion(colorFactor, scene.getColorPicker().getValue());
    }

    private Criterion createVoltageCriterion(double voltageFactor) {
        double minVol = getNumericValueFromTextField(scene.getVoltageMinimumField());
        double maxVol = getNumericValueFromTextField(scene.getVoltageMaximumField());
        return new VoltageCriterion(voltageFactor, minVol, maxVol, database.getMaxVoltage());
    }

    private double getNumericValueFromTextField(TextField textField) {
        String text = textField.getText();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }

    private Criterion createPriceCriterion(double priceFactor) {
        double maxPrice = getNumericValueFromTextField(scene.getPriceMaximumField());
        double minPrice = getNumericValueFromTextField(scene.getPriceMinimumField());
        return new PriceCriterion(priceFactor, minPrice, maxPrice, database.getMaxPrice());
    }
}
