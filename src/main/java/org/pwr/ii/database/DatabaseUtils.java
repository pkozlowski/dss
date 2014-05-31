package org.pwr.ii.database;

import com.google.common.collect.Lists;
import javafx.scene.paint.Color;
import org.pwr.ii.criteria.Alcohol;
import org.pwr.ii.database.model.AlcoholDatabase;
import org.pwr.ii.database.model.BottleDatabase;
import org.pwr.ii.database.model.DatabaseType;
import org.pwr.ii.database.names.BottleColumns;
import org.pwr.ii.database.names.PriceColumns;
import org.pwr.ii.database.names.TableNames;
import org.pwr.ii.database.names.TypeColumns;

import java.sql.*;
import java.util.List;

public class DatabaseUtils {

    private double maxPrice;
    private String driver;
    private final String databasePath;
    private double maxVoltage;

    public DatabaseUtils(String driver, String databasePath) {
        this.driver = driver;
        this.databasePath = databasePath;
    }

    public void init() throws ClassNotFoundException {
        Class.forName(driver);
        maxVoltage = countMaxVoltage();
        maxPrice = countMaxPrice();
    }

    public double getMaxVoltage() {
        return maxVoltage;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    private double countMaxVoltage() {
        String query = String.format("SELECT MAX(%s) AS %s FROM %s", TypeColumns.TYPE_VOLTAGE, TypeColumns.TYPE_VOLTAGE, TableNames.TYPES);
        return getMaxValueForQuery(query, TypeColumns.TYPE_VOLTAGE);
    }

    public double countMaxPrice() {
        String query = String.format("SELECT MAX(%s)AS %s FROM %s", PriceColumns.PRICE_SALE, PriceColumns.PRICE_SALE, TableNames.PRICES);
        return getMaxValueForQuery(query, PriceColumns.PRICE_SALE);
    }

    private double getMaxValueForQuery(String query, String valueName) {
        double result = 0d;
        try (Connection conn = DriverManager.getConnection(databasePath)) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = rs.getDouble(valueName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Alcohol> getDatabaseContent() {
        List<Alcohol> result = Lists.newArrayList();
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

        String bottleColorAsString = rs.getString(BottleColumns.BOTTLE_COLOR);
        String[] splitOfBottleColor = bottleColorAsString.split(",");
        Color color = new Color(Integer.parseInt(splitOfBottleColor[0])/255d, Integer.parseInt(splitOfBottleColor[1])/255d,
                Integer.parseInt(splitOfBottleColor[2])/255d, Integer.parseInt(splitOfBottleColor[3])/255d);

        BottleDatabase bottle = new BottleDatabase(type, color, bottle_name, bottle_size);
        return bottle;
    }

    private DatabaseType createType(ResultSet rs) throws SQLException {
        int typeVoltage = rs.getInt(TypeColumns.TYPE_VOLTAGE);
        String typeName = rs.getString(TypeColumns.TYPE_NAME);
        DatabaseType type = new DatabaseType(typeVoltage, typeName);
        return type;
    }
}
