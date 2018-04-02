package com.shumidub.openweathermvp.model.retrofitclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Артем on 22.03.2018.
 */

public class RetrofitWeaterClient {

OkHttpClient client;
Retrofit retrofitService;

public static final String BASE_URL = "https://api.openweathermap.org/";

String cityParam = "data/2.5/weather?q=%s";

String apiParam = "&APPID=%s";
String apiKey = "f66f741b6032d0ac4b356b10c4fe1a04";

String metric = "&units=metric";


    public OpenWeatherMapApi getApi(){

        Gson gson = new GsonBuilder().setLenient().create();

        client = new OkHttpClient.Builder()
                .addInterceptor(new LoginIntercepter() ).build();

        retrofitService = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        OpenWeatherMapApi openWeatherMapApi = retrofitService.create(OpenWeatherMapApi.class);

        return  openWeatherMapApi;

    }


}
