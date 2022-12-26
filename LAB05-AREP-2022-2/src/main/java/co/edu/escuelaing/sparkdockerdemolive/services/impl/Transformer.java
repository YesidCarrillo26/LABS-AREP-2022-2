package co.edu.escuelaing.sparkdockerdemolive.services.impl;

import co.edu.escuelaing.sparkdockerdemolive.services.ITransformer;
import com.google.gson.JsonObject;

public class Transformer implements ITransformer {

    @Override
    public JsonObject toJson(int code, String message, String serverMessage) {
        JsonObject json = new JsonObject();
        json.addProperty("status", code);
        json.addProperty("result", message);
        json.addProperty("serverResponse", serverMessage);

        return json;
    }
}
