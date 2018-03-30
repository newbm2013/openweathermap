package com.shumidub.openweathermvp.ui.view;

import android.os.Bundle;

import com.shumidub.openweathermvp.ui.presenter.IBasePresenter;

/**
 * Created by Артем on 21.03.2018.
 *
 */

public interface IBaseView {

    //todo can it ovveride without paramentre?
    void attachPresenter(IBasePresenter iBasePresenter);

    void detachPresenter();


}
