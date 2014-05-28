package org.pwr.ii.cryteria;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceCriteriaTest {
    @Test
    public void should_return_one_when_price_is_in_interval() {
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getPrice()).thenReturn(0.0);
        Criteria criteria = new PriceCriteria(0, 1, 0);

        assertThat(criteria.calculate(alcohol)).isEqualTo(1.0);
    }

    @Test
    public void should_calculate_price_factor_when_price_is_out_of_interval() {
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getPrice()).thenReturn(3.0);
        Criteria criteria = new PriceCriteria(0, 2, 3);

        //when, then
        assertThat(criteria.calculate(alcohol)).isEqualTo(0.5555556);
    }
}