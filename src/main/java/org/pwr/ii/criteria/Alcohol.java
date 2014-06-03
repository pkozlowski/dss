package org.pwr.ii.criteria;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-28.
 */
public interface Alcohol {
    public String getName();

    public double getPrice();

    public double getVoltage();

    public Color getColor();

    public String getTypeName();

    public Image getImage();

    public double getSize();

}
