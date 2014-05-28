package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public abstract class Criterion {
    public static final int ACCURACY_LEVEL = 7;
    private final double factor;

    public Criterion(double factor) {
        this.factor = factor;
    }

    public abstract double calculate(Alcohol alcohol);

    public double getFactor() {
        return factor;
    }
}