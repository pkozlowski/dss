package org.pwr.ii.criteria;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;

import java.util.Comparator;
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

    public CriteriaCalculator() {
        this(Lists.newArrayList());
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
    }

    public java.util.stream.Stream<Alcohol> getBestAlcohols() {
        return alcohols.stream().sorted(new Comparator<Alcohol>() {
            @Override
            public int compare(Alcohol o1, Alcohol o2) {
                int modulator = 100000000;
                return (int) ((calculate(o1) - calculate(o2))* modulator);
            }
        });
    }

    @VisibleForTesting
    double calculate(Alcohol alcohol) {
        double result = 0.0;
        for (Criterion criterion : criteria) {
            result += criterion.calculate(alcohol);
        }
        return result;
    }


}
