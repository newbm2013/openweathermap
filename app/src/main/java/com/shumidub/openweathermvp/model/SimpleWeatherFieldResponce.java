package com.shumidub.openweathermvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Артем on 28.03.2018.
 */

public class SimpleWeatherFieldResponce {

    @SerializedName("main")
    SimpleWeatherResposeModel simpleWeatherResposeModel;

    public SimpleWeatherResposeModel getSimpleWeatherResposeModel() {
        return simpleWeatherResposeModel;
    }

}
