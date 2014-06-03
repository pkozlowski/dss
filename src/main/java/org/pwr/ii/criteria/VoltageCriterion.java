package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */

public class VoltageCriterion extends Criterion {
    private double intervalBegin;
    private double intervalEnd;
    private double maxValue;

    public VoltageCriterion(double factor, double intervalBegin, double intervalEnd, double maxValue) {
        super(factor);
        this.intervalBegin = intervalBegin;
        this.intervalEnd = intervalEnd;
        this.maxValue = maxValue;
    }

    public VoltageCriterion(double intervalBegin, double intervalEnd, double maxValue) {
        this(1.0, intervalBegin, intervalEnd, maxValue);
    }

    public double calculatePartial(Alcohol alcohol) {
        double subtrahend = Math.pow((CriteriaUtil.countMiddle(intervalBegin, intervalEnd) - alcohol.getVoltage()) / maxValue, 2);
        if (CriteriaUtil.isInRange(alcohol.getVoltage(), intervalBegin, intervalEnd))
            return 1 - subtrahend * 0.7;
        return 1 - subtrahend;
    }

    @Override
    public String getName() {
        return "Voltage Criterion";
    }

}
