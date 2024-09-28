package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.ConfigLoader;

/**
 * DatabaseConnector class for managing database connections.
 */
public class DatabaseConnector {
    private static ConfigLoader cfgLoader = ConfigLoader.getInstance();
    private static DatabaseConnector instance = null;
    private Connection conn = null;

    // Constructor for production use
    private DatabaseConnector() {
        this(cfgLoader);
    }

    // Constructor for testing purposes
    DatabaseConnector(ConfigLoader configLoader) {
        try {
            String connectionProperty = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String host = configLoader.getProperty("database.host"),
                    port = configLoader.getProperty("database.port"),
                    user = configLoader.getProperty("database.username"),
                    password = configLoader.getProperty("database.password"),
                    name = configLoader.getProperty("database.name");
            Class.forName(configLoader.getProperty("database.driver_class"));
            String url = String.format("jdbc:%s://%s:%s/%s?%s", configLoader.getProperty("database.jdbc"), host, port, name, connectionProperty);
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connect to database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("You haven't installed driver mysql");
            System.out.println(e.toString());
            throw new RuntimeException("MySQL driver not found!", e);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
            System.out.println(e.toString());
            throw new RuntimeException("Failed to connect to the database!", e);
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

    // Method to reset the singleton instance for testing purposes
    static void resetInstance() {
        instance = null;
    }
}