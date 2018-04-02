package com.shumidub.openweathermvp.model.retrofitclient;

import com.shumidub.openweathermvp.model.SimpleWeatherResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Артем on 31.03.2018.
 */

public interface OpenWeatherMapApi {


    @GET("data/2.5/weather")
    Observable <SimpleWeatherResponse> getWeather(@Query("q") String city,
                                               @Query("APPID") String appid, @Query("units") String inits);




}
