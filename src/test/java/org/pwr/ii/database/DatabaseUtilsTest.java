package org.pwr.ii.database;

import java.util.List;

import org.junit.Test;
import org.pwr.ii.database.model.AlcoholDatabase;

public class DatabaseUtilsTest {


    @Test
    // TODO create tests !!!!
    public void printSomeDatabaseContent() {
        String databasePath = "jdbc:sqlite:src/main/resources/alcohol.sqlite";
        String driver = "org.sqlite.JDBC";

        DatabaseUtils util = new DatabaseUtils(driver, databasePath);
        List<AlcoholDatabase> content = util.readDatabaseToMemory();

        System.out.println(content.get(0));
    }

}
