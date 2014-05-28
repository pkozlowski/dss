package org.pwr.ii.criteria;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public interface Alcohol {
    default public String getName() {
        return "unknown";
    }

    default public double getPrice() {
        return 0;
    }

    default public double getVoltage() {
        return 0;
    }

    default public String getTypeName() {
        return "unknown";
    }

    public double getSize();

}
