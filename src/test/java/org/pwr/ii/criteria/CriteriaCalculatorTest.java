package org.pwr.ii.criteria;


import com.google.common.collect.Lists;
import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.data.Offset;
import org.junit.Test;
import org.pwr.ii.database.model.AlcoholDatabase;
import org.pwr.ii.database.model.BottleDatabase;
import org.pwr.ii.database.model.DatabaseType;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriteriaCalculatorTest {

    @Test
    public void should_return_values_from_documentations() {
        //given
        CriteriaCalculator criteriaCalculator = new CriteriaCalculator();
        criteriaCalculator.addCriterion(new PriceCriterion(0.2, 3, 15, 28));
        criteriaCalculator.addCriterion(new VoltageCriterion(0.8, 3, 10, 38));
        Alcohol piwoPiast = mock(Alcohol.class);
        when(piwoPiast.getPrice()).thenReturn(2.5);
        when(piwoPiast.getVoltage()).thenReturn(3.2);

        //when, then
        assertThat(criteriaCalculator.calculate(piwoPiast)).isEqualTo(0.984998, Offset.offset(0.000001));
    }

    @Test
    public void shouldCompareAlcohols() {
        //given
        List<Alcohol> alcohols = Lists.newArrayList();
        final Alcohol alcohol_normal = createAlcohol(16d, 11);
        final Alcohol alcohol_best = createAlcohol(7d, 7);
        final Alcohol alcohol_worst = createAlcohol(150d, 50);

        alcohols.add(alcohol_worst);
        alcohols.add(alcohol_normal);
        alcohols.add(alcohol_best);
        CriteriaCalculator criteriaCalculator = new CriteriaCalculator(alcohols);
        criteriaCalculator.addCriterion(new PriceCriterion(0.5, 3, 15, 28));
        criteriaCalculator.addCriterion(new VoltageCriterion(0.5, 3, 10, 38));

        Assertions.assertThat(alcohols.get(0)).isEqualTo(alcohol_worst);
        Assertions.assertThat(alcohols.get(1)).isEqualTo(alcohol_normal);
        Assertions.assertThat(alcohols.get(2)).isEqualTo(alcohol_best);

        //when
        Stream<Alcohol> bestAlcohols = criteriaCalculator.getBestAlcohols();

        //then
        bestAlcohols.forEachOrdered(new Consumer<Alcohol>() {
            int iteration = 0;

            @Override
            public void accept(Alcohol consumedAlcohol) {
                if (iteration == 0) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(alcohol_best);
                }
                if (iteration == 1) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(alcohol_normal);
                }
                if (iteration == 2) {
                    Assertions.assertThat(consumedAlcohol).isEqualTo(alcohol_worst);
                }
                iteration++;
            }
        });

    }

    private Alcohol createAlcohol(double priceSale, int typeVoltage) {
        String typeName = "Test type name";
        DatabaseType databaseType = new DatabaseType(typeVoltage, typeName);
        Color color = new Color(1d, 1d, 1d, 1d);
        String bottleName = "Test bottle name";
        int bottleSize = 600;
        BottleDatabase bottleDatabase = new BottleDatabase(databaseType, color, bottleName, bottleSize);
        double priceRetail = 20;
        int priceDateStart = 123;
        int priceDateEnd = 321;
        return new AlcoholDatabase(bottleDatabase, priceRetail, priceSale, priceDateStart, priceDateEnd);
    }

}