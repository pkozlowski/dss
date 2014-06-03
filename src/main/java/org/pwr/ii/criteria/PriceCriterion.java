package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public class PriceCriterion extends Criterion {
    private final double maxValue;
    private double intervalEnd;
    private double intervalBegin;

    public PriceCriterion(double factor, double intervalBegin, double intervalEnd, double maxValue) {
        super(factor);
        this.intervalBegin = intervalBegin;
        this.intervalEnd = intervalEnd;
        this.maxValue = maxValue;
    }

    public PriceCriterion(double intervalBegin, double intervalEnd, double maxValue) {
        this(1.0, intervalBegin, intervalEnd, maxValue);
    }

    @Override
    public double calculatePartial(Alcohol alcohol) {
        if (CriteriaUtil.isInRange(alcohol.getPrice(), intervalBegin, intervalEnd)) return 1;
        return 1 - Math.pow(((CriteriaUtil.countMiddle(intervalBegin, intervalEnd) - alcohol.getPrice()) / maxValue), 2);
    }

    @Override
    public String getName() {
        return "Price Criterion";
    }
}
