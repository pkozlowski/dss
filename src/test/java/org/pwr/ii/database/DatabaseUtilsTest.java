package org.pwr.ii.database;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.pwr.ii.criteria.Alcohol;

import java.io.IOException;
import java.util.List;

public class DatabaseUtilsTest {
    private final String databasePath = "jdbc:sqlite:src/test/resources/alcohol.sqlite";
    private final String driver = "org.sqlite.JDBC";

    @Test(expected = ClassNotFoundException.class)
    public void shouldNotInitializeDatabase() throws ClassNotFoundException {
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
        Assertions.assertThat(alcoholData.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetMaxValues() throws ClassNotFoundException {
        //given
        DatabaseUtils utils = new DatabaseUtils(driver, databasePath);
        utils.init();

        //when
        double maxPrice = utils.getMaxPrice();
        double maxVoltage = utils.getMaxVoltage();

        //then
        Assertions.assertThat(maxPrice).isEqualTo(24.5);
        Assertions.assertThat(maxVoltage).isEqualTo(45);
    }

    @Test
    public void shouldCheckDatabaseRowContent() throws ClassNotFoundException, IOException {
        //given
        DatabaseUtils util = new DatabaseUtils(driver, databasePath);
        util.init();

        //when
        List<Alcohol> alcoholData = util.getDatabaseContent();
        Alcohol alcohol = alcoholData.get(0);

        //then
        Assertions.assertThat(alcohol.getColor()).isEqualTo(new Color(1d,1d,1d,0d));
        Assertions.assertThat(alcohol.getVoltage()).isEqualTo(45);
        Assertions.assertThat(alcohol.getImage()).isInstanceOf(Image.class);
        Assertions.assertThat(alcohol.getName()).isEqualTo("Test bottle name");
        Assertions.assertThat(alcohol.getPrice()).isEqualTo(24.5);
        Assertions.assertThat(alcohol.getSize()).isEqualTo(500);
        Assertions.assertThat(alcohol.getTypeName()).isEqualTo("Canadian Whisky");
    }
}
