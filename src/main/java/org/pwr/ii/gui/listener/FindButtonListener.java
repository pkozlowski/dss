package org.pwr.ii.gui.listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import org.pwr.ii.criteria.*;
import org.pwr.ii.database.DatabaseUtils;
import org.pwr.ii.gui.ComponentProvider;

import java.util.List;

/**
 * Created by Vortim on 2014-05-29.
 */
public class FindButtonListener implements EventHandler<ActionEvent> {

    private final Scene scene;
    private DatabaseUtils database;
    private List<Alcohol> alcohols;

    public FindButtonListener(Scene mainScene) {
        this.scene = mainScene;
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

        for (Alcohol alcohol : alcohols)
            calculator.calculate(alcohol);
        //TODO finding best alcohol in alcohols
        System.out.print("No implementation for best alcohols!!!"+alcohols.get(0));
    }

    private CriteriaCalculator setUpCriteriaCalculator() {
        CriteriaCalculator calculator = new CriteriaCalculator();
        Slider priceSlider = ComponentProvider.getPriceSlider(scene);
        Slider voltageSlider = ComponentProvider.getVoltageSlider(scene);
        double maxValueOfSliders = voltageSlider.getMax() + priceSlider.getMax();

        double priceFactor = priceSlider.getValue() / maxValueOfSliders;
        double priceIntervalBegin = Integer.valueOf(ComponentProvider.getPriceMinimumField(scene).getText());
        double priceIntervalEnd = Integer.valueOf(ComponentProvider.getPriceMaximumField(scene).getText());
        Criterion priceCriterion = new PriceCriterion(priceFactor, priceIntervalBegin, priceIntervalEnd, database.getMaxPrice());

        double voltageFactor = voltageSlider.getValue() / maxValueOfSliders;
        double voltageIntervalBegin = Integer.valueOf(ComponentProvider.getVoltageMinimumField(scene).getText());
        double voltageIntervalEnd = Integer.valueOf(ComponentProvider.getVoltageMaximumField(scene).getText());
        Criterion voltageCriterion = new VoltageCriterion(voltageFactor, voltageIntervalBegin, voltageIntervalEnd, database.getMaxVoltage());
        calculator.addCriterion(priceCriterion);
        calculator.addCriterion(voltageCriterion);
        return calculator;
    }

}
