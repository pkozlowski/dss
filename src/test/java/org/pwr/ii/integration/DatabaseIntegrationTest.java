package org.pwr.ii.integration;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.pwr.ii.criteria.*;
import org.pwr.ii.database.DatabaseUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Vortim on 2014-06-05.
 */
public class DatabaseIntegrationTest {

    private final String databasePath = "jdbc:sqlite:src/test/resources/alcohol.sqlite";
    private final String driver = "org.sqlite.JDBC";
    private CriteriaCalculator criteriaCalculator;
    private double maxPrice;
    private double maxSize;
    private double maxVoltage;

    @Before
    public void setUp() throws ClassNotFoundException {
        DatabaseUtils util = new DatabaseUtils(driver, databasePath);
        util.init();

        List<Alcohol> databaseContent = util.getDatabaseContent();
        maxPrice = util.getMaxPrice();
        maxSize = util.getMaxSize();
        maxVoltage = util.getMaxVoltage();
        criteriaCalculator = new CriteriaCalculator(databaseContent);
    }

    @Test
    public void shouldTestColorCriterionWithDatabaseContent() {
        //given
        Color expectedColor = getColor(150, 128, 19, 51);
        ColorCriterion colorCriterion = new ColorCriterion(expectedColor);
        criteriaCalculator.addCriterion(colorCriterion);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        Optional<Alcohol> alcoholOptional = bestAlcohols.findFirst();
        Alcohol alcohol = alcoholOptional.get();

        Assertions.assertThat(alcohol.getColor()).isEqualTo(expectedColor);
        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(5);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name 4");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(106.78);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(1500);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Test type name 3");
    }

    @Test
    public void shouldTestPriceCriterionWithDatabaseContent() {
        //given
        double intervalBegin = 16d;
        double intervalEnd = 19d;
        PriceCriterion priceCriterion = new PriceCriterion(intervalBegin, intervalEnd, maxPrice);
        criteriaCalculator.addCriterion(priceCriterion);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        Optional<Alcohol> alcoholOptional = bestAlcohols.findFirst();
        Alcohol alcohol = alcoholOptional.get();

        Assertions.assertThat(alcohol.getColor()).isEqualTo(getColor(55, 145, 22, 102));
        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(45);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name 5");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(18.75);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(50);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Test type name 1");
    }

    @Test
    public void shouldTestSizeCriterionWithDatabaseContent() {
        //given
        double size = 220;
        SizeCriterion sizeCriterion = new SizeCriterion(size, maxSize);
        criteriaCalculator.addCriterion(sizeCriterion);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        Optional<Alcohol> alcoholOptional = bestAlcohols.findFirst();
        Alcohol alcohol = alcoholOptional.get();
        Assertions.assertThat(alcohol.getColor()).isEqualTo(getColor(192, 76, 3, 77));
        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(15);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name 2");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(22.07);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(225);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Test type name 2");
    }

    @Test
    public void shouldTestVoltageCriterionWithDatabaseContent() {
        //given
        double intervalBegin = 30;
        double intervalEnd = 50;
        VoltageCriterion voltageCriterion = new VoltageCriterion(intervalBegin, intervalEnd, maxVoltage);
        criteriaCalculator.addCriterion(voltageCriterion);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        Optional<Alcohol> alcoholOptional = bestAlcohols.findFirst();
        Alcohol alcohol = alcoholOptional.get();
        Assertions.assertThat(alcohol.getColor()).isEqualTo(getColor(255, 255, 255, 0));
        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(45);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name 1");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(24.5);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(500);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Test type name 1");
    }

    @Test
    public void shouldTestAllCriteriaWithDatabaseContent() {
        //given
        double voltageFactor = 0.4;
        double sizeFactor = 0.1;
        double priceFactor = 0.2;
        double colorFactor = 0.3;
        VoltageCriterion voltageCriterion = new VoltageCriterion(voltageFactor, 30, 50, maxVoltage);
        SizeCriterion sizeCriterion = new SizeCriterion(sizeFactor, 220, maxSize);
        PriceCriterion priceCriterion = new PriceCriterion(priceFactor, 16d, 19d, maxPrice);
        ColorCriterion colorCriterion = new ColorCriterion(colorFactor, getColor(150, 128, 19, 51));

        criteriaCalculator.addCriterion(voltageCriterion);
        criteriaCalculator.addCriterion(sizeCriterion);
        criteriaCalculator.addCriterion(priceCriterion);
        criteriaCalculator.addCriterion(colorCriterion);

        Assertions.assertThat(voltageFactor + sizeFactor + priceFactor + colorFactor).isEqualTo(1d);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        Optional<Alcohol> alcoholOptional = bestAlcohols.findFirst();
        Alcohol alcohol = alcoholOptional.get();

        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(45);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name 5");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(18.75);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(50);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Test type name 1");
        Assertions.assertThat(alcohol.getColor()).isEqualTo(getColor(55, 145, 22, 102));
    }

    private Color getColor(int red, int green, int blue, int opacity) {
        return new Color(red / 255d, green / 255d, blue / 255d, opacity / 255d);
    }
}
