package org.pwr.ii.database.model;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class AlcoholDatabaseTest {

    @Test
    public void shouldTestAlcoholDatabaseTest() {
        int typeVoltage = 38;
        String typeName = "Test whiskey";
        DatabaseType type = new DatabaseType(typeVoltage, typeName);
        String bottleName = "Mlodziutka i fajniutka";
        int bottleSize = 700;
        BottleDatabase bottle = new BottleDatabase(type, bottleName, bottleSize);
        double priceRetail = 20.5;
        double priceSale = 22.6;
        int priceDateStart = 123123;
        int priceDateEnd = 3535245;
        AlcoholDatabase alcoholDatabase = new AlcoholDatabase(bottle, priceRetail, priceSale, priceDateStart, priceDateEnd);

        Assertions.assertThat(alcoholDatabase.getTypeName()).isEqualTo(typeName);
        Assertions.assertThat(alcoholDatabase.getName()).isEqualTo(bottleName);
        Assertions.assertThat(alcoholDatabase.getPrice()).isEqualTo(priceSale);
        Assertions.assertThat(alcoholDatabase.getSize()).isEqualTo(bottleSize);
        Assertions.assertThat(alcoholDatabase.getVoltage()).isEqualTo(typeVoltage);
    }

}