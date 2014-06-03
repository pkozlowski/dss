package org.pwr.ii.database.model;

public class DatabaseType {

    private final double typeVoltage;
    private final String typeName;

    public DatabaseType(double typeVoltage, String typeName) {
        this.typeVoltage = typeVoltage;
        this.typeName = typeName;
    }

    public double getVoltage() {
        return typeVoltage;
    }

    public String getTypeName() {
        return typeName;
    }
}
