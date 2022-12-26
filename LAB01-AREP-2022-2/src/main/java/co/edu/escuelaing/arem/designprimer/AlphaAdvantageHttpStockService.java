package co.edu.escuelaing.arem.designprimer;

import co.edu.escuelaing.arem.designprimer.HttpStockService;

public class AlphaAdvantageHttpStockService extends HttpStockService {

    @Override
    public String getURL(String timeSeries, String stock) {
        // TODO Auto-generated method stub
        //return "https://www.alphavantage.co/query?function=TIME_SERIES_"+timeSeries+"&symbol="+stock+"&interval=5min&apikey=4NC2BOCH03RV7XZ0";
        return "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
    }

    
}