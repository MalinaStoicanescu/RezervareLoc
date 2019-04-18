package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSQL implements IDatabase {
    private static DatabaseSQL instance = new DatabaseSQL();

    private final static String DB_NAME = "database";
    private final String DB_URL = "jdbc:mysql://localhost:3306";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    private Connection _connection = null;

    private DatabaseSQL() {
        this.connect();
    }

    public static DatabaseSQL getInstance() {
        return instance;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    @Override
    public boolean connect() {
        try {
            _connection = DriverManager.getConnection(DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection get_connection() {
        return _connection;
    }
}
