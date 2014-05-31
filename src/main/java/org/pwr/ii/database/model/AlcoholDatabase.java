package org.pwr.ii.database.model;

import org.pwr.ii.criteria.Alcohol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    @Override
    public String toString() {
        return "AlcoholDatabase [bottle=" + bottle + ", priceRetail=" + priceRetail + ", priceSale="
                + priceSale + ", priceDateStart=" + priceDateStart + ", priceDateEnd="
                + priceDateEnd + "]";
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
    public BufferedImage getImage() throws IOException {
        String imageFileName = bottle.getBottleName().replaceAll(" ", "_");
        return ImageIO.read(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("bottles\\" + imageFileName));
    }

    public double getSize() {
        return bottle.getBottleSize();
    }

    public Color getColor() {
        return bottle.getColor();
    }
}
