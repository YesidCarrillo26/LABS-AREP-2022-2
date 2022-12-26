package co.edu.escuelaing.sparkdockerdemolive;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class MongoDBConnection {
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase database;

    public MongoDBConnection() {
        //uri = new MongoClientURI("mongodb://localhost:27017/db");
        uri = new MongoClientURI("mongodb://yesidcarrillo:03261Yesid@ac-uujm92h-shard-00-00.t58ohky.mongodb.net:27017,ac-uujm92h-shard-00-01.t58ohky.mongodb.net:27017,ac-uujm92h-shard-00-02.t58ohky.mongodb.net:27017/?ssl=true&replicaSet=atlas-yrezg0-shard-0&authSource=admin&retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("db");
    }

    public void insertMessage(String message){
        MongoCollection<Document> collection = database.getCollection("Messages");
        Document document = new Document();
        document.append("mensaje",message);
        document.append("fecha", new Date().toString());
        collection.insertOne(document);
    }

    public String getData(){
        MongoCollection<Document> collection = database.getCollection("Messages");
        String message = "";
        FindIterable<Document> iterable = collection.find();
        for (Document document: iterable) {
            message += "<tr><td>" + document.get("mensaje").toString() + "</td><td>" + document.get("fecha").toString() + "</td></tr>";
        }
        System.out.println(message);
        return message;
    }

}