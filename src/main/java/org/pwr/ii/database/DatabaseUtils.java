package org.pwr.ii.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.pwr.ii.database.model.DbBottle;
import org.pwr.ii.database.model.DbPrice;
import org.pwr.ii.database.model.DbType;
import org.pwr.ii.database.names.BottleColumns;
import org.pwr.ii.database.names.PriceColumns;
import org.pwr.ii.database.names.TableNames;
import org.pwr.ii.database.names.TypeColumns;

import com.google.common.collect.Lists;

public class DatabaseUtils {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:src/main/resources/alcohol.sqlite";

    public DatabaseUtils() {
        try {
            Class.forName(DatabaseUtils.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("No driver for JDBC was found");
            e.printStackTrace();
        }
    }

    public List<DbPrice> readDatabaseToMemory() {
        List<DbPrice> result = Lists.newArrayList();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
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
                DbType type = createType(rs);
                DbBottle bottle = createBottle(rs, type);
                DbPrice price = createPrice(rs, bottle);
                result.add(price);
            }
        } catch (SQLException e) {
            System.err.println("Problem with opening connection");
            e.printStackTrace();
        }
        return result;
    }

    private DbPrice createPrice(ResultSet rs, DbBottle bottle) throws SQLException {
        double priceRetail = rs.getDouble(PriceColumns.PRICE_RETAIL);
        double priceSale = rs.getDouble(PriceColumns.PRICE_SALE);
        int priceDateStart = rs.getInt(PriceColumns.PRICE_DATE_START);
        int priceDateEnd = rs.getInt(PriceColumns.PRICE_DATE_END);
        return new DbPrice(bottle, priceRetail, priceSale, priceDateStart, priceDateEnd);
    }

    private DbBottle createBottle(ResultSet rs, DbType type) throws SQLException {
        String bottle_name = rs.getString(BottleColumns.BOTTLE_NAME);
        int bottle_size = rs.getInt(BottleColumns.BOTTLE_SIZE);
        DbBottle bottle = new DbBottle(type, bottle_name, bottle_size);
        return bottle;
    }

    private DbType createType(ResultSet rs) throws SQLException {
        int typeVoltage = rs.getInt(TypeColumns.TYPE_VOLTAGE);
        String typeName = rs.getString(TypeColumns.TYPE_NAME);
        DbType type = new DbType(typeVoltage, typeName);
        return type;
    }
}
