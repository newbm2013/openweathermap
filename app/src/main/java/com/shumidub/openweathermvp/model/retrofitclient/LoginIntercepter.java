package com.shumidub.openweathermvp.model.retrofitclient;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Артем on 31.03.2018.
 */

public class LoginIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Log.d("HTTP_OK_REQUEST", chain.request().body().toString());
        Log.d("HTTP_OK_RESPONSE",chain.proceed(chain.request()).toString());


       return null;
    }
}
