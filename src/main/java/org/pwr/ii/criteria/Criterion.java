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
    public final double calculate(Alcohol alcohol){
        return calculatePartial(alcohol) * factor;
    }
    public abstract double calculatePartial(Alcohol alcohol);

    public abstract String getName();
}