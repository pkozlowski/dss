package org.pwr.ii.criteria;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class VoltageCriteriaTest {
    @Test
    public void should_return_1_when_volume_is_in_the_middle(){
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getVoltage()).thenReturn(1.0);
        Criteria criteria = new VoltageCriteria(0, 2, 3);

        //when, then
        assertThat(criteria.calculate(alcohol)).isEqualTo(1.0);
    }

    @Test
    public void should_promote_volume_factor_when_in_interval(){
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getVoltage()).thenReturn(1.5);
        Criteria criteria = new VoltageCriteria(0, 2, 3);

        //when, then
        assertThat(criteria.calculate(alcohol)).isEqualTo(0.9805556);
    }

    @Test
    public void should_not_promote_volume_factor_when_out_of_interval(){
        //given
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getVoltage()).thenReturn(3.0);
        Criteria criteria = new VoltageCriteria(0, 2, 3);

        //when, then
        assertThat(criteria.calculate(alcohol)).isEqualTo(0.5555556);
    }
}