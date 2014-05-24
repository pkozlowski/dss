package org.pwr.ii.cryteria;

import com.google.common.annotations.VisibleForTesting;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-24.
 */
public class CriteriaHandler {
    public static final int ACCURACY_LEVEL = 7;

    public static double calculatePriceFactor(double price, double intervalBegin, double intervalEnd, double maxValue) {
        if (isInRange(price, intervalBegin, intervalEnd)) return 1;
        double result = 1 - Math.pow(((getMiddle(intervalBegin, intervalEnd) - price) / maxValue), 2);
        return round(result, ACCURACY_LEVEL);
    }

    public static double calculateVolumeFactor(double price, double intervalBegin, double intervalEnd, double maxValue) {
        double subtrahend = Math.pow((getMiddle(intervalBegin, intervalEnd) - price) / maxValue, 2);
        if (isInRange(price, intervalBegin, intervalEnd))
            return round(1 - subtrahend * 0.7, ACCURACY_LEVEL);
        return round(1 - subtrahend, ACCURACY_LEVEL);
    }

    private static boolean isInRange(double value, double intervalBegin, double intervalEnd) {
        return value >= intervalBegin && value <= intervalEnd;
    }

    private static double getMiddle(double intervalBegin, double intervalEnd) {
        return (intervalEnd - intervalBegin) / 2;
    }

    @VisibleForTesting
    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
