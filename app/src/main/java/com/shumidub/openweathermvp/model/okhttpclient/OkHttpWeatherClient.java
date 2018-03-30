package com.shumidub.openweathermvp.model.okhttpclient;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shumidub.openweathermvp.model.SimpleWeatherFieldResponce;
import com.shumidub.openweathermvp.model.SimpleWeatherResposeModel;
import com.shumidub.openweathermvp.model.exceptions.NoLoadingDataException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Артем on 22.03.2018.
 *
 */

public class OkHttpWeatherClient {

    double temp = -777;






    public OkHttpWeatherClient(){

    }

    public double getWeather (final String city) throws NoLoadingDataException, IOException {

//        String city = "Rostov-On-Don";


        AsyncTask<Void, Void, Double> asyncTask = new AsyncTask() {
            @Override
            protected Double doInBackground(Object[] objects) {

                OkHttpClient okHttpClient;
                String baseUrl = "https://api.openweathermap.org/";

                String cityParam = "data/2.5/weather?q=%s";


                String apiParam = "&APPID=%s";
                String apiKey = "f66f741b6032d0ac4b356b10c4fe1a04";

                String metric = "&units=metric";

                final Request request = new Request.Builder()
                        .url(baseUrl + String.format(cityParam, city) + String.format(apiParam, apiKey) + metric)
                        .get()
                        .build();

                Response response = null;
                SimpleWeatherResposeModel simpleWeatherResposeModel = null;
                SimpleWeatherFieldResponce simpleWeatherFieldResponce = null;
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                okHttpClient = new OkHttpClient();

                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Log.d("DTAG", "getWeather: response = " + json );
                        simpleWeatherFieldResponce = gson.fromJson(json, SimpleWeatherFieldResponce.class);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (simpleWeatherFieldResponce !=null){
                    return simpleWeatherFieldResponce.getSimpleWeatherResposeModel().getTemp();
                } else return temp;

            }

            @Override
            protected void onPostExecute(Object o) {
                if (o != null){
                    temp = (Double) o;
                }

                Log.d("DTAG", "onPostExecute: temp = " + temp);
                super.onPostExecute(o);
            }


        };


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    response = okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        Log.d("DTAG", "getWeather: response = " + response.body().string());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        asyncTask.execute();

        return temp;


    }

}
