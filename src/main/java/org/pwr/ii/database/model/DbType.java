package org.pwr.ii.database.model;

public class DbType {

    private final int typeVoltage;
    private final String typeName;

    public DbType(int typeVoltage, String typeName) {
        this.typeVoltage = typeVoltage;
        this.typeName = typeName;
    }

    public int getTypeVoltage() {
        return typeVoltage;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "DbType [typeVoltage=" + typeVoltage + ", typeName=" + typeName + "]";
    }
}
