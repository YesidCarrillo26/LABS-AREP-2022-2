package edu.escuelaing.arep;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class URLReader {

    public static void main(String[] args) throws Exception {
        // Crea el objeto que representa una URL
        //URL siteURL = new URL(site);
        URL google = new URL("http://www.google.com/");
        // Crea el objeto que URLConnection
        URLConnection urlConnection = google.openConnection();
        // Obtiene los campos del encabezado y los almacena en un estructura Map
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        // Obtiene una vista del mapa como conjunto de pares <K,V>
        // para poder navegarlo
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        // Recorre la lista de campos e imprime los valores
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
        //Si el nombre es nulo, significa que es la linea de estado
            if(headerName !=null){System.out.print(headerName + ":");}
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value);
            }
            System.out.println("");
        }
    }
}
