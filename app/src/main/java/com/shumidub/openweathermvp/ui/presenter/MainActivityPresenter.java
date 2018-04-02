package com.shumidub.openweathermvp.ui.presenter;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.util.Log;

import com.shumidub.openweathermvp.model.SimpleWeatherResponse;
import com.shumidub.openweathermvp.model.exceptions.NoLoadingDataException;
import com.shumidub.openweathermvp.model.okhttpclient.OkHttpWeatherClient;
import com.shumidub.openweathermvp.model.repository.RepositoryWeather;
import com.shumidub.openweathermvp.model.retrofitclient.RetrofitWeaterClient;
import com.shumidub.openweathermvp.ui.view.IMainActivityView;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        try {
//            lastCity = city;
//            int c = RepositoryWeather.getWeatherFromHttpOKClient(city);
//            view.showWeather(c);
//        } catch (NoLoadingDataException e) {
//            view.showError();
//            e.printStackTrace();
//        }


        String appid = "f66f741b6032d0ac4b356b10c4fe1a04";

        new RetrofitWeaterClient().getApi().getWeather(city, appid, "metric" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SimpleWeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("DTAG", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(SimpleWeatherResponse simpleWeatherResponse) {
                        Log.d("DTAG", "onNext: ");
                       double t = simpleWeatherResponse.getSimpleWeatherResposeModel().getTemp();
                       view.showWeather((int) t );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DTAG", "onError: ");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("DTAG", "onComplete: ");
                    }
                });






//
//
//
//                .enqueue(new Callback<SimpleWeatherResponse>() {
//            @Override
//            public void onResponse(Call<SimpleWeatherResponse> call, Response<SimpleWeatherResponse> response) {
//
//                Log.d("DTAG", "onResponse: 5555");
//
//                if (response.isSuccessful()){
//                    SimpleWeatherResponse simpleWeatherResponse =
//                            (SimpleWeatherResponse) response.body();
//
//                    double temp = response.body().getSimpleWeatherResposeModel().getTemp();
//                    Log.d("DTAG", "onResponse: RETROFIIIITTTT!!!!!");
//                    view.showWeather((int) temp);
//
//                } else{
//
//                    view.showError();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SimpleWeatherResponse> call, Throwable t) {
//                Log.d("DTAG", "onResponse: erere RETROFIIIITTTT!!!!!");
//                view.showError();
//
//
//                t.printStackTrace();
//            }
//        });



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
