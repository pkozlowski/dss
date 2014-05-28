package org.pwr.ii.criteria;


import org.fest.assertions.data.Offset;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriteriaCalculatorTest {

    @Test
    public void should_return_values_from_documentations() {
        CriteriaCalculator criteriaCalculator = new CriteriaCalculator();
        criteriaCalculator.addCriterion(new PriceCriterion(0.2, 3, 15, 28));
        criteriaCalculator.addCriterion(new VoltageCriterion(0.8, 3, 10,38));

        Alcohol piwoPiast = mock(Alcohol.class);
        when(piwoPiast.getPrice()).thenReturn(2.5);
        when(piwoPiast.getVoltage()).thenReturn(3.2);

        assertThat(criteriaCalculator.calculate(piwoPiast)).isEqualTo(0.996840, Offset.offset(0.000001));
    }

}