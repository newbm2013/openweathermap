package com.shumidub.openweathermvp.model.repository;

import com.shumidub.openweathermvp.model.exceptions.NoLoadingDataException;
import com.shumidub.openweathermvp.model.okhttpclient.OkHttpWeatherClient;

import java.io.IOException;

/**
 * Created by Артем on 22.03.2018.
 */

public class RepositoryWeather {

    /* todo репозиторий содержит такие методы?
     (т.е. только методы которые дергаются) или в себе и логику должен заключать,
     какие методы дергать - кроме этих методов?
      какая зона ответственности презентера и какая репозитория?
      */

    static OkHttpWeatherClient okHttpWeatherClient;



    public static int getWeatherFromHttpOKClient (String city) throws NoLoadingDataException {

        if (okHttpWeatherClient==null){
            okHttpWeatherClient = new OkHttpWeatherClient();
        }

        int temp = -777;

        try {
            temp = (int)  okHttpWeatherClient.getWeather(city) ;
        } catch (NoLoadingDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int getWeatherFromRetrofitClient () throws NoLoadingDataException  {

        return 0;
    }

    public int getWeatherFromLocalDB () throws NoLoadingDataException {

        return 0;
    }

    public int getWeatherFromLurua () throws NoLoadingDataException {

        return 0;
    }
}
