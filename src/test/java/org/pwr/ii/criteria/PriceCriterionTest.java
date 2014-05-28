package org.pwr.ii.criteria;

import org.fest.assertions.data.Offset;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceCriterionTest {
    @Test
    public void should_return_one_when_price_is_in_interval() {
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getPrice()).thenReturn(0.0);
        Criterion criterion = new PriceCriterion(0, 1, 0);

        assertThat(criterion.calculate(alcohol)).isEqualTo(1.0);
    }

    @Test
    public void should_calculate_price_factor_when_price_is_out_of_interval() {
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getPrice()).thenReturn(3.0);
        Criterion criterion = new PriceCriterion(0, 2, 3);

        //when, then
        assertThat(criterion.calculate(alcohol)).isEqualTo(0.5555556);
    }

    @Test
    public void should_return_data_presented_in_documentation() {
        Alcohol piwoPiast = mock(Alcohol.class);
        when(piwoPiast.getPrice()).thenReturn(2.5);

        Criterion criterion = new PriceCriterion(3, 15, 28);

        assertThat(criterion.calculate(piwoPiast)).isEqualTo(0.984375, Offset.offset(0.000001));
    }
}