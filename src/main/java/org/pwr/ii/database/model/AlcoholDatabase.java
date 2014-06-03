package org.pwr.ii.database.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.pwr.ii.criteria.Alcohol;

import java.io.IOException;

public class AlcoholDatabase implements Alcohol {

    private final BottleDatabase bottle;
    private final double priceSale;

    public AlcoholDatabase(BottleDatabase bottle, double priceSale) {
        this.bottle = bottle;
        this.priceSale = priceSale;
    }

    public String getName() {
        return bottle.getBottleName();
    }

    public double getPrice() {
        return priceSale;
    }

    public double getVoltage() {
        return bottle.getVoltage();
    }

    public String getTypeName() {
        return bottle.getTypeName();
    }

    @Override
    public Image getImage() {
        String imageFileName = bottle.getTypeName().replaceAll(" ", "_");
        String path = "bottles\\" + imageFileName + ".png";
        try {
            return new Image(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(path));
        } catch (NullPointerException e) {
            System.out.println("Could not find image: " + path);
        }
        return null;
    }

    public double getSize() {
        return bottle.getBottleSize();
    }

    public Color getColor() {
        return bottle.getColor();
    }
}
