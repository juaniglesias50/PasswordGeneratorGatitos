package GUI;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PrepararGato {
    //método que nos conecta a la API y nos devuelve la imagen del gato
    public static ImageIcon prepararGato() throws IOException {
        //preparamos la conexión con la API
        Images images = new Images();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .addHeader("x-rapidapi-key", images.getApiKey())
                .build();
        Response response = client.newCall(request).execute();

        //preparamos el objeto GSon cortando los corchetes de la respuesta
        String respuesta = response.body().string();
        respuesta = respuesta.substring(1, respuesta.length());
        respuesta = respuesta.substring(0, respuesta.length()-1);
        //la respuesta está en formato JSon
        System.out.println(respuesta);

        //convertimos el Json en un objeto de Java usando la clase Gson

        Gson gson = new Gson();
        GUI.Images fondoimagen = gson.fromJson(respuesta, GUI.Images.class);

        System.out.println(fondoimagen.getUrl());

        //trabajamos con la URL que nos dio la API
        URL url = new URL(fondoimagen.getUrl());

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "");
        //leemos la imagen
        BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
        //la guardamos
        ImageIcon image = new ImageIcon(bufferedImage);
        //resizeamos
        if (image.getIconWidth() > 1){
            Image image1 = image.getImage();
            Image imagenmodded = image1.getScaledInstance(500,500, Image.SCALE_SMOOTH);
            image = new ImageIcon(imagenmodded);
        }
        return image;
    }
}
