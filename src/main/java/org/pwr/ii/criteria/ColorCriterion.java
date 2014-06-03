package org.pwr.ii.criteria;


import javafx.scene.paint.Color;

/**
 * Created by Vortim on 2014-05-31.
 */
public class ColorCriterion extends Criterion {

    private Color selectedColor;
    private final double maxDistance;

    public ColorCriterion(double factor, Color selectedColor) {
        super(factor);
        this.selectedColor = selectedColor;
        maxDistance = calculateDistancePow2(new Color(0, 0, 0, 0), new Color(1, 1, 1, 1));
    }

    public ColorCriterion(Color selectedColor) {
        this(1.0, selectedColor);
    }

    private double calculateDistancePow2(Color from, Color to) {
        return Math.pow(from.getRed() - to.getRed(), 2) + Math.pow(from.getGreen() - to.getGreen(), 2) +
                Math.pow(from.getBlue() - to.getBlue(), 2) + Math.pow(from.getOpacity() - to.getOpacity(), 2);
    }

    @Override
    public double calculatePartial(Alcohol alcohol) {
        return 1 - calculateDistancePow2(selectedColor, alcohol.getColor()) / maxDistance;
    }

    @Override
    public String getName() {
        return "Color Criterion";
    }
}
