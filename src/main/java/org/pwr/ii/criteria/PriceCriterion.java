package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public class PriceCriterion extends Criterion {
    private final double maxValue;
    private double intervalEnd;
    private double intervalBegin;

    public PriceCriterion(double intervalBegin, double intervalEnd, double maxValue, double factor) {
        super(factor);
        this.intervalBegin = intervalBegin;
        this.intervalEnd = intervalEnd;
        this.maxValue = maxValue;
    }

    @Override
    public double calculate(Alcohol alcohol) {
        if (CriteriaUtil.isInRange(alcohol.getPrice(), intervalBegin, intervalEnd)) return 1;
        double result = 1 - Math.pow(((CriteriaUtil.getMiddle(intervalBegin, intervalEnd) - alcohol.getPrice()) / maxValue), 2);
        return CriteriaUtil.round(result, ACCURACY_LEVEL);
    }
}
