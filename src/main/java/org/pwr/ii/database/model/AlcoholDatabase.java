package org.pwr.ii.database.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.pwr.ii.criteria.Alcohol;

import java.io.IOException;

public class AlcoholDatabase implements Alcohol {

    private final BottleDatabase bottle;
    private final double priceRetail;
    private final double priceSale;
    private final int priceDateStart;
    private final int priceDateEnd;

    public AlcoholDatabase(BottleDatabase bottle, double priceRetail, double priceSale, int priceDateStart,
                           int priceDateEnd) {
        this.bottle = bottle;
        this.priceRetail = priceRetail;
        this.priceSale = priceSale;
        this.priceDateStart = priceDateStart;
        this.priceDateEnd = priceDateEnd;
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
    public Image getImage(){
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
