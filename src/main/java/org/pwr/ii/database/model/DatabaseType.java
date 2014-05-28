package org.pwr.ii.database.model;

public class DatabaseType {

    private final int typeVoltage;
    private final String typeName;

    public DatabaseType(int typeVoltage, String typeName) {
        this.typeVoltage = typeVoltage;
        this.typeName = typeName;
    }

    public int getVoltage() {
        return typeVoltage;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "DatabaseType [typeVoltage=" + typeVoltage + ", typeName=" + typeName + "]";
    }
}
