package com.shumidub.openweathermvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Артем on 28.03.2018.
 */

public class SimpleWeatherResponse {

    @SerializedName("main")
    MainWeatherResponsePart simpleWeatherResposeModel;

    public MainWeatherResponsePart getSimpleWeatherResposeModel() {
        return simpleWeatherResposeModel;
    }

}
