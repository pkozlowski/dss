package org.pwr.ii.database.model;

public class DbPrice {

    private final DbBottle bottle;
    private final double priceRetail;
    private final double priceSale;
    private final int priceDateStart;
    private final int priceDateEnd;

    public DbPrice(DbBottle bottle, double priceRetail, double priceSale, int priceDateStart,
            int priceDateEnd) {
        this.bottle = bottle;
        this.priceRetail = priceRetail;
        this.priceSale = priceSale;
        this.priceDateStart = priceDateStart;
        this.priceDateEnd = priceDateEnd;
    }

    public double getPriceRetail() {
        return priceRetail;
    }

    public DbBottle getBottle() {
        return bottle;
    }

    public int getPriceDateStart() {
        return priceDateStart;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public int getPriceDateEnd() {
        return priceDateEnd;
    }

    @Override
    public String toString() {
        return "DbPrice [bottle=" + bottle + ", priceRetail=" + priceRetail + ", priceSale="
                + priceSale + ", priceDateStart=" + priceDateStart + ", priceDateEnd="
                + priceDateEnd + "]";
    }
}
