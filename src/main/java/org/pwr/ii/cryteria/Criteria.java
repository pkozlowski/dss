package org.pwr.ii.cryteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public interface Criteria {
    public static final int ACCURACY_LEVEL = 7;
    public double calculate(Alcohol alcohol);
}