package org.pwr.ii.criteria;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public class CriteriaCalculator {
    private final List<Alcohol> alcohols;
    List<Criterion> criteria;

    public CriteriaCalculator(List<Alcohol> alcohols) {
        criteria = Lists.newArrayList();
        this.alcohols = alcohols;
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
    }

    public java.util.stream.Stream<Alcohol> getBestAlcohols() {
        return alcohols.stream().sorted((o1, o2) -> {
            int modulator = 100000000;
            return (int) ((calculate(o2) - calculate(o1)) * modulator);
        });
    }

    public double calculate(Alcohol alcohol) {
        double result = 0.0;
        for (Criterion criterion : criteria) {
            double value = criterion.calculate(alcohol);
            result += value;
        }
        return result;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }
}
