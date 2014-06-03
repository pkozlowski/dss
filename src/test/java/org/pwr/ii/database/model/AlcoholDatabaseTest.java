package org.pwr.ii.database.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.io.IOException;

public class AlcoholDatabaseTest {

    @Test
    public void shouldTestAlcoholDatabaseTest() throws IOException {
        int typeVoltage = 38;
        String typeName = "Canadian Whisky";
        DatabaseType type = new DatabaseType(typeVoltage, typeName);
        String bottleName = "Apple_Brandy";
        int bottleSize = 700;
        Color color = new Color(1 / 255d, 2 / 255d, 3 / 255d, 5 / 255d);
        BottleDatabase bottle = new BottleDatabase(type, color, bottleName, bottleSize);
        double priceRetail = 20.5;
        double priceSale = 22.6;
        int priceDateStart = 123123;
        int priceDateEnd = 3535245;
        AlcoholDatabase alcoholDatabase = new AlcoholDatabase(bottle, priceRetail,
                priceSale, priceDateStart, priceDateEnd);

        Assertions.assertThat(alcoholDatabase.getTypeName()).isEqualTo(typeName);
        Assertions.assertThat(alcoholDatabase.getName()).isEqualTo(bottleName);
        Assertions.assertThat(alcoholDatabase.getPrice()).isEqualTo(priceSale);
        Assertions.assertThat(alcoholDatabase.getSize()).isEqualTo(bottleSize);
        Assertions.assertThat(alcoholDatabase.getVoltage()).isEqualTo(typeVoltage);
        Assertions.assertThat(alcoholDatabase.getColor()).isEqualTo(color);
        Assertions.assertThat(alcoholDatabase.getImage()).isInstanceOf(Image.class);
    }

}