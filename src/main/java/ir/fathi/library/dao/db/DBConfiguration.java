package ir.fathi.library.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
    private static DBConfiguration dbConfiguration = null;
    private static Connection connection = null;

    private DBConfiguration() {
    }

    public static DBConfiguration getDbConfigurationInstance() {
        if (dbConfiguration == null) {
            dbConfiguration = new DBConfiguration();
        }
        return dbConfiguration;
    }

    private Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1273136055");
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Connection getConnectionInstance() {
        if (connection == null) {
            connection = DBConfiguration.getDbConfigurationInstance().getConnection();
        }
        return connection;
    }
}
