package GUI;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import functions.DBFunctions;
import functions.FunctionsOne;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import GUI.PrepararGato;

public class gui {

    //método que prepara el GUI
    public static void prepareGUI() throws IOException {
        //creamos el campo de texto, etc

        JLabel passLabel = new JLabel("Contraseña generada: ");
        passLabel.setOpaque(false);
        JTextField passField = new JTextField();
        passField.setEditable(false);


        //Creamos el fondo
        final JLabel fondo = new JLabel(new PrepararGato().prepararGato());
        fondo.setSize(650, 450);


        //Creamos el frame
        JFrame frame = new JFrame("Generador de Contraseñas");
        frame.setSize(650,450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(fondo);

        //Creamos el botón que genera la contraseña
        JButton buttonCrearContra = new JButton("Generar contraseña");
        buttonCrearContra.setBounds(50, 50, 200, 20);
        buttonCrearContra.setOpaque(false);
        fondo.add(buttonCrearContra);


        //Creamos el botón que borra una contraseña
        JButton buttonEliminarContra = new JButton("Borrar una contraseña");
        buttonEliminarContra.setBounds(270, 50, 200, 20);
        buttonEliminarContra.setOpaque(false);
        fondo.add(buttonEliminarContra);

        //Creamos el botón que guarda la contraseña
        JButton buttonGuardarContra = new JButton("Guardar contraseña");
        buttonGuardarContra.setBounds(50, 90, 200, 20);
        buttonGuardarContra.setOpaque(false);
        fondo.add(buttonGuardarContra);

        //Creamos el botón que muestra todas las tablas
        JButton buttonMostrarTodo = new JButton("Mostrar tablas de contraseñas");
        buttonMostrarTodo.setFont(new Font("Arial", Font.BOLD, 10));
        buttonMostrarTodo.setBounds(50, 130, 200, 20);
        buttonMostrarTodo.setOpaque(false);
        fondo.add(buttonMostrarTodo);

        //Creamos el botón que crea una tabla
        JButton buttonCrearTabla = new JButton("Crear una nueva tabla");
        buttonCrearTabla.setBounds(50, 150, 200, 20);
        buttonCrearTabla.setOpaque(false);
        fondo.add(buttonCrearTabla);

        //Creamos el botón que borra una tabla
        JButton buttonBorrarTabla = new JButton("Borrar una tabla");
        buttonBorrarTabla.setBounds(270, 150, 200, 20);
        buttonBorrarTabla.setOpaque(false);
        fondo.add(buttonBorrarTabla);


        //Creamos el botón que muestra UNA tabla específica
        JButton buttonMostrarUnaTabla = new JButton("Mostrar una tabla");
        buttonMostrarUnaTabla.setBounds(50, 170, 200, 20);
        buttonMostrarUnaTabla.setOpaque(false);
        fondo.add(buttonMostrarUnaTabla);



        //creamos el botón que cambia de imagen
        JButton buttonGato = new JButton("Cambiar de gatito");
        buttonGato.setBounds(50, 230, 210, 20);
        buttonGato.setOpaque(false);
        fondo.add(buttonGato);

        //agregamos y ubicamos el passfield
        fondo.add(passField);
        passField.setBounds(50, 70, 200, 20);


      //indicamos la acción al presionar el botón
        buttonCrearContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passw = FunctionsOne.generacontrasena();
                passField.setText(passw);
                JOptionPane.showMessageDialog(null, "Creaste una nueva contraseña.", "Éxito!", 1);
            }
        });

        buttonCrearTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane();
                String tableName = pane.showInputDialog("Ingrese el nombre de su tabla: ");
                if (tableName != null && !tableName.isEmpty()) {
                    System.out.println(tableName);
                    DBFunctions.createDBTable(tableName);
                    pane.showMessageDialog(null, "Tabla creada con éxito!", "Ingresar tabla.", 1);
                }else if (tableName == null) {

                }else{
                    pane.showMessageDialog(null, "Ingrese un nombre válido.", "Ingresar tabla.", 1);
                }
            }
        });

        buttonBorrarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane();
                String tableName = pane.showInputDialog("Ingrese la tabla a borrar. Tablas disponibles:\n" + formatArray(DBFunctions.showAllTables()));
                if (tableName != null && !tableName.isEmpty() && FindTable(DBFunctions.showAllTables(), tableName)) {
                    DBFunctions.DeleteDBTable(tableName);
                    pane.showMessageDialog(null, "Tabla borrada!");
                }else if (tableName == null) {
                    //no hacer nada
                }else{
                    pane.showMessageDialog(null, "Ingrese una tabla válida.");
                }
            }
        });




        buttonGuardarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane pane = new JOptionPane("Guardar una contraseña");
                String tableName = pane.showInputDialog("Ingrese la tabla de la contraseña a guardar. Tablas disponibles:\n" + formatArray(DBFunctions.showAllTables()));
                if (tableName != null && !tableName.isEmpty() && FindTable(DBFunctions.showAllTables(), tableName) && (!passField.getText().isEmpty())) {
                    pane.showMessageDialog(null, "Su contraseña ha sido guardada.", "Éxito!", 1);
                    DBFunctions.enterPasswordtoDB(passField.getText(), tableName);
                    System.out.println(passField.getText());
                    }else if (tableName == null)
                    //no hacer nada
                    {
                    }else{
                    pane.showMessageDialog(null, "Ingrese una tabla válida.");
                }
            }
        });



        buttonEliminarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane pane = new JOptionPane("Borrar una contraseña");
                String tableName = pane.showInputDialog("Ingrese la tabla de la contraseña a borrar. Tablas disponibles:\n" + formatArray(DBFunctions.showAllTables()));
                if (tableName != null && !tableName.isEmpty() && FindTable(DBFunctions.showAllTables(), tableName)) {
                    int id = Integer.valueOf(pane.showInputDialog("Ingrese la ID de la contraseña a borrar. Contraseñas disponibles:\n" + formatArray((DBFunctions.showOneTable(tableName)))));
                    String idstr = String.valueOf(id);
                    if ((idstr != null) && (StringUtils.isNumeric(idstr)) && (DBFunctions.showOneTableIDS(tableName).contains(idstr))){
                        pane.showMessageDialog(null, "Su contraseña ha sido eliminada.", "Atención!", 1);
                        DBFunctions.deletePassword(tableName, id);
                    }else if (idstr == null) {
                        //no hacer nada
                    }else{
                        pane.showMessageDialog(null, "Ingrese un ID válido.");
                    }
                }else if (tableName == null) {
                    //no hacer nada
                }else{
                    pane.showMessageDialog(null, "Ingrese una tabla válida.");
                }
            }
        });

        buttonMostrarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane();
                String tables = formatArray(DBFunctions.showAllTables());
                pane.showMessageDialog(null, tables,"Tablas disponibles", 1);
            }
        });


        buttonMostrarUnaTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane pane = new JOptionPane("Contraseñas: ");
                String tableName = pane.showInputDialog("Ingrese la tabla a mostrar. Tablas disponibles:\n" + formatArray(DBFunctions.showAllTables()));
                if (tableName != null && !tableName.isEmpty() && FindTable(DBFunctions.showAllTables(), tableName)) {
                    System.out.println(FindTable(DBFunctions.showAllTables(), tableName));
                    pane.showMessageDialog(null, formatArray(DBFunctions.showOneTable(tableName)), "Tabla:" + tableName, 1);
                }else if (tableName == null) {

                }else{
                    pane.showMessageDialog(null, "Ingrese una tabla válida.");
                }
            }
        });

        buttonGato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fondo.setIcon(new PrepararGato().prepararGato());
                } catch (IOException exc) {
                    System.out.println(exc);;
                }
                JOptionPane.showMessageDialog(null, "Has cambiado el fondo.", "Cambiaste el fondo!", 1 );
            }
        });
    }






    /**
     * @author Juan Iglesias
     * @param Tables Array de tablas a formatear
     * @return ArrayList Devuelve el ArrayList en forma de lista sin guiones ni puntos
     */

    private static String formatArray(ArrayList tables) {
        //formateamos la lista de tablas
        String formattedString = tables.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .replace(" ", "")
                .trim();           //remove trailing spaces from partially initialized arrays
        return formattedString;
    }

    /**
     *
     * @param ArrayList Array de Tablas
     * @param TableName Nombre de Tabla a Buscar
     * @return Boolean Falso o Verdadero según si se encontró un match
     */

    private static Boolean FindTable(ArrayList <String> allTables, String tableName){
        Boolean find = null;
        //Si la lista de tablas es 0, devuelve falso.
        if (allTables.size()>0){
        for (int i=0; i<allTables.size(); i++) {
            if (allTables.get(i).trim().equals(tableName.trim())) {
                find = true;
                break;
            } else {
                find = false;
            }
        }
        }else find = false;
        return find;
    }

}
