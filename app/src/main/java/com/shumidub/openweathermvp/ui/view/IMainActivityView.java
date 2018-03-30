package com.shumidub.openweathermvp.ui.view;

/**
 * Created by Артем on 21.03.2018.
 */

public interface IMainActivityView extends IBaseView {

    public void showWeather(int temperatureDegrees);

    public void showLoading();

    public void showError() ;

    public void showEmptyState();

}
