package org.pwr.ii.database.model;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

public class BottleDatabaseTest {

    @Test
    public void shouldCreateBottleDatabaseObject() {
        DatabaseType databaseType = Mockito.mock(DatabaseType.class);
        String bottleName = "Test bottle";
        int bottleCapacity = 500;
        Color color = new Color(1, 2, 3, 5);
        BottleDatabase bottleDatabase = new BottleDatabase(databaseType, color, bottleName, bottleCapacity);

        Assertions.assertThat(bottleDatabase.getBottleName()).isEqualTo(bottleName);
        Assertions.assertThat(bottleDatabase.getBottleSize()).isEqualTo(bottleCapacity);
        Assertions.assertThat(bottleDatabase.getType()).isEqualTo(databaseType);
        Assertions.assertThat(bottleDatabase.getColor()).isEqualTo(color);
    }

}