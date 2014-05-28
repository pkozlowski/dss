package org.pwr.ii.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.pwr.ii.database.model.AlcoholDatabase;
import org.pwr.ii.database.model.BottleDatabase;
import org.pwr.ii.database.model.DatabaseType;
import org.pwr.ii.database.names.BottleColumns;
import org.pwr.ii.database.names.PriceColumns;
import org.pwr.ii.database.names.TableNames;
import org.pwr.ii.database.names.TypeColumns;

import com.google.common.collect.Lists;

public class DatabaseUtils {

    private final String databasePath;

    public DatabaseUtils(String driver, String databasePath) {
        this.databasePath = databasePath;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("No driver for JDBC was found");
            e.printStackTrace();
        }
    }

    public List<AlcoholDatabase> readDatabaseToMemory() {
        List<AlcoholDatabase> result = Lists.newArrayList();
        try (Connection conn = DriverManager.getConnection(databasePath)) {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            String statement = String
                    .format("SELECT * FROM %s INNER JOIN %s ON(%s.%s=%s.%s) INNER JOIN %s ON(%s.%s=%s.%s);",
                            TableNames.PRICES, TableNames.BOTTLES, TableNames.PRICES,
                            PriceColumns.BOTTLE_ID, TableNames.BOTTLES, BottleColumns.BOTTLE_ID,
                            TableNames.TYPES, TableNames.BOTTLES, BottleColumns.TYPE_ID,
                            TableNames.TYPES, TypeColumns.TYPE_ID);
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                DatabaseType type = createType(rs);
                BottleDatabase bottle = createBottle(rs, type);
                AlcoholDatabase price = createPrice(rs, bottle);
                result.add(price);
            }
        } catch (SQLException e) {
            System.err.println("Problem with opening connection");
            e.printStackTrace();
        }
        return result;
    }

    private AlcoholDatabase createPrice(ResultSet rs, BottleDatabase bottle) throws SQLException {
        double priceRetail = rs.getDouble(PriceColumns.PRICE_RETAIL);
        double priceSale = rs.getDouble(PriceColumns.PRICE_SALE);
        int priceDateStart = rs.getInt(PriceColumns.PRICE_DATE_START);
        int priceDateEnd = rs.getInt(PriceColumns.PRICE_DATE_END);
        return new AlcoholDatabase(bottle, priceRetail, priceSale, priceDateStart, priceDateEnd);
    }

    private BottleDatabase createBottle(ResultSet rs, DatabaseType type) throws SQLException {
        String bottle_name = rs.getString(BottleColumns.BOTTLE_NAME);
        int bottle_size = rs.getInt(BottleColumns.BOTTLE_SIZE);
        BottleDatabase bottle = new BottleDatabase(type, bottle_name, bottle_size);
        return bottle;
    }

    private DatabaseType createType(ResultSet rs) throws SQLException {
        int typeVoltage = rs.getInt(TypeColumns.TYPE_VOLTAGE);
        String typeName = rs.getString(TypeColumns.TYPE_NAME);
        DatabaseType type = new DatabaseType(typeVoltage, typeName);
        return type;
    }
}
