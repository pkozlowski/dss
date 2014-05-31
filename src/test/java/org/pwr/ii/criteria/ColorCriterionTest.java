package org.pwr.ii.criteria;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ColorCriterionTest {

    @Test
    public void shouldCalculateDistanceBetweenColors() {
        //given
        double factor = 0.4;
        // Distances are pow 2, to avoid Math.sqrt usage
        double maxDistance = 510 * 510;
        ColorCriterion criterion = new ColorCriterion(factor, new Color(123, 123, 123, 123));
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getColor()).thenReturn(new Color(222, 222, 222, 222));

        //when
        double result = criterion.calculate(alcohol);

        //then
        Assertions.assertThat(result).isEqualTo(1 - 198 * 198 * factor / maxDistance);
    }

}