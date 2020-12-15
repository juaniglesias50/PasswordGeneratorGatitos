package functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToSQLDB {

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwords_db", "root", "");
            if (connection != null) {
                System.out.println("Conexión exitosa");
            } else {
                System.out.println("Conexión fallida.");
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

        public Connection getAuthConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_users", "root", "");
                if (connection != null) {
                    System.out.println("ConexiónAuth exitosa");
                } else {
                    System.out.println("ConexiónAuth fallida.");
                }
            }catch (SQLException e) {
                System.out.println(e);
            }
            return connection;
        }


    }