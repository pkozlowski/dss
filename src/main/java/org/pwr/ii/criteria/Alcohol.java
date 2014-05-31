package org.pwr.ii.criteria;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    default public Color getColor() {
        return new Color(255, 255, 255, 0);
    }

    default public String getTypeName() {
        return "unknown";
    }

    public BufferedImage getImage() throws IOException;

    public double getSize();

}
