package co.edu.escuelaing.sparkdockerdemolive.servers;

import co.edu.escuelaing.sparkdockerdemolive.SecureURLReader;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class SparkWebServer
{
    public static void main(String... args){
        // API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);
        secure(getKeyStore(), getKeyStorePass(), null, null);
        port(getPort());
        get("/hello", (req,res)->"Hello World Server 1");
        get("/remote", (req,res)-> SecureURLReader.readURL(SparkWebServer2.getUrl()));

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

     public static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystores/ecikeystore.p12"; //returns default keystore if keystore isn't set (i.e. on localhost)
    }

    public static String getKeyStorePass(){
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "123456";
    }
    public static String getUrl(){
        return "https://ec2-18-209-67-252.compute-1.amazonaws.com:5000/hello";
    }




    
}
