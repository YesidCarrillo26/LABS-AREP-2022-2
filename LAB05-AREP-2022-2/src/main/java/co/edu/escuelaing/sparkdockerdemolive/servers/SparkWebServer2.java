package co.edu.escuelaing.sparkdockerdemolive.servers;

import co.edu.escuelaing.sparkdockerdemolive.SecureURLReader;
import co.edu.escuelaing.sparkdockerdemolive.servers.SparkWebServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static spark.Spark.*;

public class SparkWebServer2 {

    public static void main(String... args) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);
        //secure("keystores/ecikeystore.p12", "123456", null, null);
        secure(getKeyStore(), getKeyStorePass(), null, null);
        SecureURLReader.trustStore(SparkWebServer2.getKeyStore(), SparkWebServer2.getKeyStorePass());
        port(getPort());
        get("/hello", (req,res)->"Hello World Server 2");
        get("/remote", (req, res) -> SecureURLReader.readURL(SparkWebServer.getUrl()));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystores/ecikeystore2.p12"; //returns default keystore if keystore isn't set (i.e. on localhost)
    }

    public static String getKeyStorePass(){
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "123456";
    }

    public static String getUrl(){
        return "https://ec2-3-88-90-17.compute-1.amazonaws.com:5001/hello";
    }


}

