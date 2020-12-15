package functions;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUser {

    public static Boolean authUser() {

        Boolean userExists = null;
        Boolean userEntersCorrectInfo = false;
        JOptionPane pane = new JOptionPane("Autenticación");
        String userName = new String();

        while (!userEntersCorrectInfo && userName != null) {
            userName = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
            if (userName != null && !userName.isEmpty()) {
                //conectamos a la DB y buscamos el user
                try {
                    ConnectionToSQLDB conn = new ConnectionToSQLDB();
                    String query = "SELECT user_name FROM app_users.users";
                    PreparedStatement ps = conn.getAuthConnection().prepareStatement(query);
                    ResultSet resultSet = ps.executeQuery(query);
                    while (resultSet.next()) {
                        if (resultSet.getString("user_name").trim().equals(userName)) {
                            userExists = true;
                            userEntersCorrectInfo = true;
                            System.out.println(userExists);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "El usuario no existe.");
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } else if (userName != null) {
                JOptionPane.showMessageDialog(null, "Ingrese datos.");
            } else {
                JOptionPane.showMessageDialog(null, "Saliendo!");
            }
        }

        if (userName == null) {
        }

        //autenticamos la contraseña
        Boolean userIsAuthenticated = false;
        String password = new String();
        Boolean userEnteredPassword = false;
        Boolean passwordIsCorrect = false;

        while (!userEnteredPassword && password != null && !passwordIsCorrect && userName != null) {
            password = JOptionPane.showInputDialog("Ingrese su contraseña");
            if (password != null && !password.isEmpty()) {
                //conectamos a la DB y checkeamos la contra
                userEnteredPassword = true;
                try {
                    ConnectionToSQLDB conn = new ConnectionToSQLDB();
                    String query = "SELECT password FROM app_users.users;";
                    PreparedStatement ps = conn.getAuthConnection().prepareStatement(query);
                    ResultSet resultSet = ps.executeQuery(query);
                    while (resultSet.next()) {
                        if (resultSet.getString("password").trim().equals(password)) {
                            passwordIsCorrect = true;
                            System.out.println(passwordIsCorrect);
                            break;
                        }
                    }
                } catch (SQLException e){
                    System.out.println(e);
                }
                if (userExists && passwordIsCorrect) {
                    JOptionPane.showMessageDialog(null, "Bienvenido!");
                    userIsAuthenticated = true;
                    System.out.println(userIsAuthenticated);
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña/Usuario incorrectos");
                    userIsAuthenticated = false;
                }

            } else if (password == null) {
                //volver al menú de usuario
                AuthUser.authUser();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la contraseña.");
            }
        }return userIsAuthenticated;
     }
}
