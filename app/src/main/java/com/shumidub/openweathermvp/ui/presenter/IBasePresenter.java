package com.shumidub.openweathermvp.ui.presenter;

import com.shumidub.openweathermvp.ui.view.IBaseView;

/**
 * Created by Артем on 21.03.2018.
 *
 */

public interface IBasePresenter {

    void showLoading();

    void showError();

    void showEmptyState();

    void onCreate();

    void onDestroy();

    void onStart();

    void onResume();

}
