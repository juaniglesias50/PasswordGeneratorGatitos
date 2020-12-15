package functions;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DBFunctions {

    public static void createDBTable(String tableName) {
        //conectamos a la DB
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "CREATE TABLE `passwords_db`." + tableName + "(\n" +
                    "  `pass_id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `pass` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`pass_id`));";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void DeleteDBTable(String tableName) {
        //conectamos a la DB
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();

            String query = "DROP TABLE " + tableName;
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ArrayList showAllTables() {

        ArrayList result = new ArrayList();
        //conectamos a la DB
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "SHOW TABLES FROM `passwords_db`";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);
            while (resultSet.next()) {
                String stringResult = resultSet.getString("Tables_in_passwords_db") + "\n";
                result.add(stringResult);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;

    }

    public static ArrayList showOneTable(String tableName) {
        ArrayList result = new ArrayList();
        ArrayList idList = new ArrayList();
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "SELECT * FROM " + tableName;
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);

            while (resultSet.next()) {
                String stringResult = "ID"+ resultSet.getString("pass_id") + " : " + resultSet.getString("pass") + "\n";
                String idResult = "ID" + resultSet.getString("pass_id");
                result.add(stringResult);
                idList.add(idResult);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }


    /**
     *
     * @param tableName Nombre de la tabla a buscar
     * @return Devuelve un ArrayList con los IDs de la columna de la primary key
     */
    public static ArrayList showOneTableIDS(String tableName){
        ArrayList idList = new ArrayList();
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "SELECT * FROM " + tableName;
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);

            while (resultSet.next()) {
                String idResult = resultSet.getString("pass_id");
                idList.add(idResult);
            }
        }catch (SQLException e) {
            System.out.println(e);
        }return idList;
    }


    public static void enterPasswordtoDB(String passw, String tableName) {
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "INSERT INTO `" + tableName + "`" + "(`pass`)" + " VALUES" + "(\' " + passw + "\')";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deletePassword(String tablename, int passID) {
        try {
            ConnectionToSQLDB conn = new ConnectionToSQLDB();
            String query = "DELETE FROM " + tablename + " WHERE `pass_id`= " + String.valueOf(passID);
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}