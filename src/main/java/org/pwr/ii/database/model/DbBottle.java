package org.pwr.ii.database.model;

public class DbBottle {

    private final DbType type;
    private final String bottleName;
    private final int bottleSize;

    public DbBottle(DbType type, String bottle_name, int bottle_size) {
        this.type = type;
        this.bottleName = bottle_name;
        this.bottleSize = bottle_size;
    }

    public String getBottleName() {
        return bottleName;
    }

    public int getBottleSize() {
        return bottleSize;
    }

    public DbType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DbBottle [type=" + type + ", bottleName=" + bottleName + ", bottleSize="
                + bottleSize + "]";
    }
}
