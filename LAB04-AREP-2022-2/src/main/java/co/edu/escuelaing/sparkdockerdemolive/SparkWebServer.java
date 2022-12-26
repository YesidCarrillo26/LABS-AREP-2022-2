package co.edu.escuelaing.sparkdockerdemolive;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class SparkWebServer
{
    private static MongoDBConnection mongoConnection = new MongoDBConnection();
    public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        post("/message", (req,res) -> insertMessage(req,res));
        get("yesid", (req,res) -> "Yesid");
        get("/messages", (req,res)-> getMessages(req, res));

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    // LogService
    private static String insertMessage(spark.Request request, spark.Response response){
        mongoConnection.insertMessage(request.body());
        //mongoConnection.insertMessage(request.queryParams("message"));
        String content = getMessages(request,response);
        return content;
    }

    private static String getMessages(spark.Request request, spark.Response response){
        String content = mongoConnection.getData();
        return content;
    }


    
}
