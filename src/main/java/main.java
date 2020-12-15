import GUI.gui;
import functions.AuthUser;
import functions.ConnectionToSQLDB;
import functions.DBFunctions;


import javax.swing.*;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        Boolean authenticated = AuthUser.authUser();
        if (authenticated) {
            gui.prepareGUI();
        } else {
            JOptionPane.showMessageDialog(null, "Hasta pronto!");
        }
    }

}