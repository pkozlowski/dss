package org.pwr.ii.criteria;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public class CriteriaCalculator {
    List<Criterion> criteria;

    public CriteriaCalculator() {
        criteria = Lists.newArrayList();
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
    }

    public double calculate(Alcohol alcohol) {
        double result = 0.0;
        for (Criterion criterion : criteria) {
            result += criterion.calculate(alcohol);
        }
        return result;
    }
}
