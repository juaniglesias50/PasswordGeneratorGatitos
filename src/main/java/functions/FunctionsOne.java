package functions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunctionsOne {

    public static String generacontrasena() {
        // print(len(mayusculas))
        // generamos una contraseña de 8 caracteres con mayúsculas, minúsculas y números
        //tenemos 26 letras disponibles y 10 números
        ArrayList<String> mayusculas = new ArrayList();
        for (char c = 'A'; c <= 'Z'; ++c) {
            mayusculas.add(String.valueOf(c));
        }
        ArrayList<String> minusculas = new ArrayList();
        for (char c = 'a'; c <= 'z'; ++c) {
            mayusculas.add(String.valueOf(c));
        }
        ArrayList<Integer> numeros = new ArrayList();
        for (int i = 0; i <= 9; ++i) {
            numeros.add(i);
        }
        ArrayList<String> numeros_str = new ArrayList();
        for (int i = 0; i <9; i++) {
            numeros_str.add(String.valueOf(numeros.get(i)));
        }

        ArrayList caracteres = new ArrayList();
        caracteres.addAll(mayusculas);
        caracteres.addAll(minusculas);
        caracteres.addAll(numeros_str);

        //ya tengo todos los caracteres prontos en un ArrayList

        //System.out.println(caracteres);
        //System.out.println()
        //System.out.println(caracteres.length)

        ArrayList contrasenaLista = new ArrayList();

        for (int i = 0; i <= 7; i++) {
            Random rand = new Random();
            contrasenaLista.add(caracteres.get(rand.nextInt(61)));
        }


        String contrasena = contrasenaLista.toString().replace("[", "").replace("]", "").replace(",","").replace(" ","");

        return contrasena;
    }
}
