package edu.escuelaing.arep;
import java.io.*;
import java.net.*;

public class Networking {

    public static void main(String[] args) throws Exception {
        try {
            URL personalSite = new URL("https://github.com/YesidCarrillo26/AREP-Taller2.git");
            System.out.println("Protocol:" + personalSite.getProtocol());
            System.out.println("Authority:" + personalSite.getAuthority());
            System.out.println("Host:" + personalSite.getHost());
            System.out.println("Path:" + personalSite.getPath());
            System.out.println("Query:" + personalSite.getQuery());
            System.out.println("File:" + personalSite.getFile());
            System.out.println("Ref:" + personalSite.getRef());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
