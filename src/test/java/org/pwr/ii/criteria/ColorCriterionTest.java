package org.pwr.ii.criteria;

import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.data.Offset;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ColorCriterionTest {

    @Test
    public void shouldCalculateDistanceBetweenColors() {
        //given
        double factor = 0.4;
        // Distances are pow 2, to avoid Math.sqrt usage
        double maxDistance = 510 * 510/255d;
        ColorCriterion criterion = new ColorCriterion(factor, new Color(123/255d, 123/255d, 123/255d, 123/255d));
        Alcohol alcohol = mock(Alcohol.class);
        when(alcohol.getColor()).thenReturn(new Color(222/255d, 222/255d, 222/255d, 222/255d));

        //when
        double result = criterion.calculate(alcohol);

        //then
        Assertions.assertThat(result).isEqualTo(1 - 198 * 198 / 255d * factor / maxDistance, Offset.offset(0.0000001));
    }

}