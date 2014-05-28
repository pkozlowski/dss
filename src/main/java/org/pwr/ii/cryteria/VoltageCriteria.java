package org.pwr.ii.cryteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */

public class VoltageCriteria implements Criteria{
    private double intervalBegin;
    private double intervalEnd;
    private double maxValue;

    public VoltageCriteria(double intervalBegin, double intervalEnd, double maxValue) {
        this.intervalBegin = intervalBegin;
        this.intervalEnd = intervalEnd;
        this.maxValue = maxValue;
    }

    @Override
    public double calculate(Alcohol alcohol) {
        double subtrahend = Math.pow((CriteriaUtil.getMiddle(intervalBegin, intervalEnd) - alcohol.getVoltage()) / maxValue, 2);
        if (CriteriaUtil.isInRange(alcohol.getVoltage(), intervalBegin, intervalEnd))
            return CriteriaUtil.round(1 - subtrahend * 0.7, ACCURACY_LEVEL);
        return CriteriaUtil.round(1 - subtrahend, ACCURACY_LEVEL);
    }
}
