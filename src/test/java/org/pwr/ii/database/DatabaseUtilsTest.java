package org.pwr.ii.database;

import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.pwr.ii.criteria.Alcohol;

public class DatabaseUtilsTest {
    private final String databasePath = "jdbc:sqlite:src/main/resources/alcohol.sqlite";
    private final String driver = "org.sqlite.JDBC";

    @Test(expected=ClassNotFoundException.class)
    public void shouldInitializeDatabase() throws ClassNotFoundException {
        //given
        DatabaseUtils util = new DatabaseUtils("some other driver", databasePath);

        //when, then - expected exception here
        util.init();
    }

    @Test
    public void shouldReadDatabaseToMemory() throws ClassNotFoundException {
        //given
        DatabaseUtils util = new DatabaseUtils(driver, databasePath);

        //when
        util.init();

        //then
        List<Alcohol> alcoholData = util.getDatabaseContent();
        Assertions.assertThat(alcoholData.size()).isEqualTo(7892);
    }
}
