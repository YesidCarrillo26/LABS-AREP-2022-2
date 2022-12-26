package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpServer {
    private static HttpServer _instance = new HttpServer();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean flag = true;
        String path = "";
        while(flag) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            //String inputLine, outputLine;
            String inputLine;
            String outputLine = "";
            //String path = "";
            int count = 0;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Receive: " + inputLine);
                if (!in.ready()) break;
                count++;
                if (count <= 1) path = inputLine;
            }
            System.out.println(path);
            try {
                if (!path.isEmpty()) outputLine += acceptPath(path.split(" ")[1]);
            }catch(Exception e){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>\n" + "</head>"
                        + "<body>"
                        + "My Web "
                        + "<img src=\"images/image.jpg\">"
                        + "</body>"
                        + "</html>" + inputLine;

                out.println(outputLine);
            }

            while ((inputLine = in.readLine()) != null) {

            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
        //System.out.println(path);

        //System.out.println(path);
        //serverSocket.close();
    }

    public static String acceptPath(String path) throws IOException {
        String outputLine ="";
        if (path.contains("css")) {
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/css\r\n"
                    + "\r\n";
            String info = "";
            info = new String(Files.readAllBytes(Paths.get("resources/" + path)), StandardCharsets.UTF_8);
            outputLine = info;
        }
        else if (path.contains("html")){
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n";
            String info = "";
            info = new String(Files.readAllBytes(Paths.get("resources/" + path)), StandardCharsets.UTF_8);
            outputLine = info;
        }
        return outputLine;

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}