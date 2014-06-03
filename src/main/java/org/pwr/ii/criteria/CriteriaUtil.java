package org.pwr.ii.criteria;

import com.google.common.annotations.VisibleForTesting;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
class CriteriaUtil {
    @VisibleForTesting
    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static double countMiddle(double intervalBegin, double intervalEnd) {
        return (intervalEnd + intervalBegin) / 2;
    }

    public static boolean isInRange(double value, double intervalBegin, double intervalEnd) {
        return value >= intervalBegin && value <= intervalEnd;
    }
}
