package com.shumidub.openweathermvp.ui.presenter;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.UiThread;

import com.shumidub.openweathermvp.model.exceptions.NoLoadingDataException;
import com.shumidub.openweathermvp.model.okhttpclient.OkHttpWeatherClient;
import com.shumidub.openweathermvp.model.repository.RepositoryWeather;
import com.shumidub.openweathermvp.ui.view.IMainActivityView;

import java.io.IOException;

/**
 * Created by Артем on 21.03.2018.
 *
 */

public class MainActivityPresenter implements IBasePresenter {

    String lastCity = "Rostov-on-Don";

    //todo - нужны методы onCreate and other lifecycle methods?

    IMainActivityView view;

    OkHttpWeatherClient okHttpWeatherClient;

    public MainActivityPresenter(IMainActivityView view) {
        this.view = view;
        okHttpWeatherClient = new OkHttpWeatherClient();
    }

    public void showWeather(String city){

        try {
            lastCity = city;
            int c = RepositoryWeather.getWeatherFromHttpOKClient(city);
            view.showWeather(c);
        } catch (NoLoadingDataException e) {
            view.showError();
            e.printStackTrace();
        }



    }

    @Override
    public void showLoading() {
        view.showLoading();

//        final Handler h = new Handler();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//               h.post(new Runnable() {
//                   @Override
//                   public void run() {
//                       showWeather(lastCity);
//                   }
//               });
//
//
//            }
//        }).start();
    }

    @Override
    public void showError() {
        view.showError();
    }

    @Override
    public void showEmptyState() {
        view.showEmptyState();
    }

    @Override
    public void onCreate() {
        showLoading();
        showWeather(lastCity);
    }

}
