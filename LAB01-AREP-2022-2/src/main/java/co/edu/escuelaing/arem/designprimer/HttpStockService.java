package co.edu.escuelaing.arem.designprimer;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpStockService {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final HttpStockService _alphaInstance = createAlphaService();

    public static HttpStockService getAlphaService(){
        return _alphaInstance;
    }

    public static HttpStockService createAlphaService(){
        return new AlphaAdvantageHttpStockService();
    }

    private static final HttpStockService _IEXinstance = createIEXService();

    public static HttpStockService getIEXService(){
        return _IEXinstance;
    }

    public static HttpStockService createIEXService(){
        return new IEXCloudHttpStockService();
    }

    public String getStockInfoAsJSON(Request req) throws IOException {
        String responseStr = "None";
        String timeSeries = req.queryParams("time_series");
        String stock = req.queryParams("stock");
        URL obj = new URL(getURL(timeSeries,stock));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseStr = response.toString();
            // print result
            System.out.println(responseStr);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return responseStr;
    }

    public abstract String getURL(String timeSeries,String stock);

} 