package org.pwr.ii.database;

import java.util.List;

import org.junit.Test;
import org.pwr.ii.database.model.DbPrice;

public class DatabaseUtilsTest {

    @Test
    // TODO create tests !!!!
    public void printSomeDatabaseContent() {
        DatabaseUtils util = new DatabaseUtils();
        List<DbPrice> content = util.readDatabaseToMemory();

        System.out.println(content.get(0));
    }

}
