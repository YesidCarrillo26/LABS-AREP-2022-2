package co.edu.escuelaing.arem.designprimer;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiAdvantageReader {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
    // private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";
    /*private static final ApiAdvantageReader alphaInstance = createAlphaService();

    private static final ApiAdvantageReader _IEXinstance = createIEXService();
    
    
    public static ApiAdvantageReader getAlphaService(){
        return alphaInstance;
    }

    public static ApiAdvantageReader getIEXService(){
        return _IEXinstance;
    }
     
    public static ApiAdvantageReader createAlphaService(){
        return new AlphaAdvantageHttpStockService();
    }
    
    public static ApiAdvantageReader createIEXService(){
        return new IEXCloudHttpStockService();
    }*/

        public static String getStock() throws IOException {

        URL obj = new URL(GET_URL);
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

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "Failed";
    }

}