package org.pwr.ii.database.model;

public class BottleDatabase {

    private final DatabaseType type;
    private final String bottleName;
    private final int bottleSize;

    public BottleDatabase(DatabaseType type, String bottleName, int bottleSize) {
        this.type = type;
        this.bottleName = bottleName;
        this.bottleSize = bottleSize;
    }

    public String getBottleName() {
        return bottleName;
    }

    public int getBottleSize() {
        return bottleSize;
    }

    public DatabaseType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "BottleDatabase [type=" + type + ", bottleName=" + bottleName + ", bottleSize="
                + bottleSize + "]";
    }

    public double getVoltage() {
        return type.getVoltage();
    }

    public String getTypeName() {
        return type.getTypeName();
    }
}
