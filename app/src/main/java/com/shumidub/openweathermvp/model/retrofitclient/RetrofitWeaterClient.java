package com.shumidub.openweathermvp.model.retrofitclient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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


    public RetrofitWeaterClient(){
        client = new OkHttpClient();

        retrofitService = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .build();



    public static UmoriliApi getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UmoriliApi umoriliApi = retrofit.create(UmoriliApi.class);
        return umoriliApi;

    }


    public static double getWeater

    http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/

}
