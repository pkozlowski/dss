package org.pwr.ii.database.model;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class DatabaseTypeTest {

    @Test
    public void shouldCreateDatabaseType() {
        int voltage = 30;
        String alcoholName = "Test alcohol";
        DatabaseType databaseType = new DatabaseType(voltage, alcoholName);

        Assertions.assertThat(databaseType.getVoltage()).isEqualTo(voltage);
        Assertions.assertThat(databaseType.getTypeName()).isEqualTo(alcoholName);
    }

}