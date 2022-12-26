package co.edu.escuelaing.arem.designprimer;

import co.edu.escuelaing.arem.designprimer.CacheMemory;
import co.edu.escuelaing.arem.designprimer.HttpStockService;
import co.edu.escuelaing.arem.designprimer.IEXCloudHttpStockService;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SparkWebApp {
    private static final CacheMemory<String, String> cacheMemory = new CacheMemory<String,String>(5000,5000,100);
    public static void main(String[] args) {
        staticFileLocation("/public");
        staticFiles.location("/public");
        port(getPort());
        /*get("/stock", (req, res) -> {
             res.type("application/json");
             return ApiAdvantageReader.getStock();
             });*/
        // get("/inputDataStockService", SparkWebApp::inputDataPageStockService);
        get("/stockService", SparkWebApp::stockService);
        get("/iexService", SparkWebApp::iexService);
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }



    private static String stockService(Request req, Response res){
        String response = "None";
        try {
            response = HttpStockService.getAlphaService().getStockInfoAsJSON(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cacheMemory.get(req.url()) == null){
            cacheMemory.put(req.url(),response);
            return response;
        }else {
            return cacheMemory.get(req.url());
        }
    }

    private static String iexService(Request req, Response res){
        String response = "None";
        try{
            response = IEXCloudHttpStockService.getIEXService().getStockInfoAsJSON(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cacheMemory.get(req.url()) == null){
            cacheMemory.put(req.url(),response);
            return response;
        }else {
            return cacheMemory.get(req.url());
        }
    }
}