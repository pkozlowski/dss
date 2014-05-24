package org.pwr.ii.cryteria;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-24.
 */
public class CriteriaHandlerTest {

    @Test
    public void should_return_one_when_price_is_in_interval() {
        assertThat(CriteriaHandler.calculatePriceFactor(0, 0, 1, 0), is(1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_round_places_is_lower_than_0() throws Exception {
        CriteriaHandler.round(0, -1);
    }

    @Test
    public void should_return_0_when_round_double_min_value_with_places_equals_to_0() throws Exception {
        double roundedNumber = CriteriaHandler.round(Double.MIN_VALUE, 0);
        assertThat(roundedNumber, is(0.0));
    }

    @Test
    public void should_not_round_when_this_is_not_necessary() throws Exception {
        double roundedNumber = CriteriaHandler.round(Double.MIN_VALUE, 324);
        assertThat(roundedNumber, is(Double.MIN_VALUE));
    }

    @Test
    public void should_calculate_price_factor_when_price_is_out_of_interval() {
        assertThat(CriteriaHandler.calculatePriceFactor(3, 0, 2, 3), is(0.5555556));
    }

    @Test
    public void should_return_1_when_volume_is_in_the_middle(){
        assertThat(CriteriaHandler.calculateVolumeFactor(1, 0, 2, 3), is(1.0));
    }

    @Test
    public void should_promote_volume_factor_when_in_interval(){
        assertThat(CriteriaHandler.calculateVolumeFactor(1.5, 0, 2, 3), is(0.9805556));
    }

    @Test
    public void should_not_promote_volume_factor_when_out_of_interval(){
        assertThat(CriteriaHandler.calculateVolumeFactor(3, 0, 2, 3), is(0.5555556));
    }
}