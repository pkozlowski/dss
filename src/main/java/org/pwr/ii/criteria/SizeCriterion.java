package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-06-01.
 */
public class SizeCriterion extends Criterion {
    private final double size;
    private final double maxSize;

    public SizeCriterion(double factor, double size, double maxSize) {
        super(factor);
        this.size = size;
        this.maxSize = maxSize;
    }

    @Override
    public double calculatePartial(Alcohol alcohol) {
        return 1 - Math.pow(Math.abs(alcohol.getSize() - size) / maxSize, 2.0);
    }

    @Override
    public String getName() {
        return "Size Criterion";
    }
}
