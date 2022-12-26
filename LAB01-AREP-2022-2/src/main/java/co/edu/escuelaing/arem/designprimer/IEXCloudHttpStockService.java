package co.edu.escuelaing.arem.designprimer;

import co.edu.escuelaing.arem.designprimer.HttpStockService;

public class IEXCloudHttpStockService extends HttpStockService {

    @Override
    public String getURL(String timeSeries, String stock) {
        // TODO Auto-generated method stub
        return "https://cloud.iexapis.com/stable/stock/market/batch/time_series?symbols="+stock+"&types=quote,chart&range="+timeSeries+"&token=pk_d4e76ebeb6b1480e9c3892e4ac904f24";
    }
    
}