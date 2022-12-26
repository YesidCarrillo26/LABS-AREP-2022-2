package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.entities.Message;
import co.edu.escuelaing.sparkdockerdemolive.entities.User;
import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpResponse;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class SparkWebServer
{
    private static MongoDBConnection mongoConnection = new MongoDBConnection();
    public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        post("/user", (req,res) -> insertUser(req,res));
        post("/message", (req,res) -> insertMessage(req,res));
        get("/stream", (req,res) -> getStream(req,res));
    }

    private static String getStream(Request req, Response res) {
        return null;
    }

    private static String insertUser(Request req, Response res) {
        res.type("application/json");
        User user = new Gson().fromJson(req.body(), User.class);
        mongoConnection.insertUser(user);
        System.out.println("Usuario: "+ user.getUserName()+ "guardado exitosamente");
        res.status(201);
        return String.valueOf(res.status());
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    // LogService
    private static String insertMessage(Request request, Response response){
        response.type("application/json");
        Message message = new Gson().fromJson(request.body(), Message.class);
        System.out.println(verifyIfExists(message.getUser()));
        if(verifyIfExists(message.getUser())){
            mongoConnection.insertMessage(message);
            response.status(201);
            return String.valueOf(response.status());
        }
        else{
            return "Usuario no encontrado";
        }

    }

    private static boolean verifyIfExists(String userName){
        List<User> users = mongoConnection.getUsers();
        System.out.println(users);
        for (User user: users){
            if (user.getUserName().equals(userName)) return true;
        }
        return false;
    }

    private static String getMessages(spark.Request request, spark.Response response){
        String content = mongoConnection.getData();
        return content;
    }


    
}
