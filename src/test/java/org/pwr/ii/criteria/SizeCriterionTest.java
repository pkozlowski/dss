package org.pwr.ii.criteria;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SizeCriterionTest {

    @Test
    public void should_return_1_when_equals_to_criterion() throws Exception {
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getSize()).thenReturn(700d);
        Criterion sizeCriterion = new SizeCriterion(1, 700, 700);

        assertThat(sizeCriterion.calculate(alcohol)).isEqualTo(1);
    }

    @Test
    public void should_return_0_when_far_from_criterion() throws Exception {
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getSize()).thenReturn(0d);
        Criterion sizeCriterion = new SizeCriterion(1, 700, 700);
        assertThat(sizeCriterion.calculate(alcohol)).isEqualTo(0);
    }
}