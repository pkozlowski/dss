package org.pwr.ii.criteria;


import com.google.common.collect.Lists;
import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.pwr.ii.database.model.AlcoholDatabase;
import org.pwr.ii.database.model.BottleDatabase;
import org.pwr.ii.database.model.DatabaseType;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;

public class CriteriaCalculatorTest {

    Alcohol piwoPiast;
    Alcohol cytrynowka;
    Alcohol winoFresco;
    Alcohol piwoPiastMocny;
    List<Alcohol> alcohols;

    @Before
    public void setUp() throws Exception {
        piwoPiast = createAlcohol(2.5, 3.2, "piwoPiast");
        cytrynowka = createAlcohol(28, 38, "cytrynowka");
        winoFresco = createAlcohol(22, 15, "cytrynowka");
        piwoPiastMocny = createAlcohol(4, 6, "piwoPiastMocny");
        alcohols = Lists.newArrayList(piwoPiast, cytrynowka, winoFresco, piwoPiastMocny);
    }


    @Test
    public void should_return_values_from_documentations() {
        //given
        CriteriaCalculator criteriaCalculator = new CriteriaCalculator(alcohols);
        criteriaCalculator.addCriterion(new PriceCriterion(0.2, 3, 15, 28));
        criteriaCalculator.addCriterion(new VoltageCriterion(0.8, 3, 10, 38));

        //when, then
        assertThat(criteriaCalculator.calculate(piwoPiast)).isEqualTo(0.984998, Offset.offset(0.000001));
        assertThat(criteriaCalculator.calculate(cytrynowka)).isEqualTo(0.3581852, Offset.offset(0.000001));
        assertThat(criteriaCalculator.calculate(winoFresco)).isEqualTo(0.9168600, Offset.offset(0.000001));
        assertThat(criteriaCalculator.calculate(piwoPiastMocny)).isEqualTo(0.9999030, Offset.offset(0.000001));
    }

    @Test
    public void shouldCompareAlcohols() {
        //given
        CriteriaCalculator criteriaCalculator = new CriteriaCalculator(alcohols);
        criteriaCalculator.addCriterion(new PriceCriterion(0.2, 3, 15, 28));
        criteriaCalculator.addCriterion(new VoltageCriterion(0.8, 3, 10, 38));

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        bestAlcohols.forEachOrdered(new Consumer<Alcohol>() {
            int iteration = 0;

            @Override
            public void accept(Alcohol consumedAlcohol) {
                if (iteration == 0) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(piwoPiastMocny);
                }
                if (iteration == 1) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(piwoPiast);
                }
                if (iteration == 2) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(winoFresco);
                }
                if (iteration == 3) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(cytrynowka);
                }
                iteration++;
            }
        });
    }

    private Alcohol createAlcohol(double priceSale, double typeVoltage, String name) {
        String typeName = "";
        DatabaseType databaseType = new DatabaseType(typeVoltage, typeName);
        Color color = new Color(1d, 1d, 1d, 1d);
        int bottleSize = 600;
        BottleDatabase bottleDatabase = new BottleDatabase(databaseType, color, name, bottleSize);
        return new AlcoholDatabase(bottleDatabase, priceSale);
    }

}