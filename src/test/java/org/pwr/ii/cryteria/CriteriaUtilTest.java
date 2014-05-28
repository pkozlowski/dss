package org.pwr.ii.cryteria;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class CriteriaUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_round_places_is_lower_than_0() throws Exception {
        CriteriaUtil.round(0, -1);
    }

    @Test
    public void should_return_0_when_round_double_min_value_with_places_equals_to_0() throws Exception {
        double roundedNumber = CriteriaUtil.round(Double.MIN_VALUE, 0);
        assertThat(roundedNumber).isEqualTo(0.0);
    }

    @Test
    public void should_not_round_when_this_is_not_necessary() throws Exception {
        double roundedNumber = CriteriaUtil.round(Double.MIN_VALUE, 324);
        assertThat(roundedNumber).isEqualTo(Double.MIN_VALUE);
    }
}