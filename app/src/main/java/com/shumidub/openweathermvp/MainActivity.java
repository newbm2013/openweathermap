package com.shumidub.openweathermvp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shumidub.openweathermvp.ui.presenter.IBasePresenter;
import com.shumidub.openweathermvp.ui.presenter.MainActivityPresenter;
import com.shumidub.openweathermvp.ui.view.IMainActivityView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    MainActivityPresenter mainActivityPresenter;
    IMainActivityView iMainActivityView;

    @BindView(R.id.city)
    EditText etCity;

    @BindView(R.id.temperature)
    TextView tvTemperature;

    @BindView(R.id.error)
    TextView tvError;

    @BindView(R.id.empty_state)
    TextView tvEmptyState;

    @BindView(R.id.refresh_button )
    Button btnRefresh;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindViews({R.id.city, R.id.temperature, R.id.error,
            R.id.empty_state, R.id.refresh_button, R.id.progress })
    List<View> allViews;

    @BindString(R.string.temperature)
    String temperatureTemplateText;

    ButterKnife.Action<View> setViewsInvisibleAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iMainActivityView = this;
        if (mainActivityPresenter == null){
            attachPresenter(new MainActivityPresenter(iMainActivityView));
        }
        setViewsInvisibleAction = new ButterKnife.Action<View>() {
            @Override
            public void apply(@NonNull View view, int index) {
                view.setVisibility(View.INVISIBLE);
            }
        };
        setInvisibleAllView();
        mainActivityPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
        detachView();
    }

    @Override
    public void showWeather(int temperatureDegrees) {
        setInvisibleAllView();
        tvTemperature.setVisibility(View.VISIBLE);
        etCity.setVisibility(View.VISIBLE);
        tvTemperature.setText(String.format(temperatureTemplateText, temperatureDegrees));
        btnRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        setInvisibleAllView();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        setInvisibleAllView();
        tvError.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        setInvisibleAllView();
        tvEmptyState.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
    }

    //todo: how we do it?
    private void setInvisibleAllView(){
        ButterKnife.apply(allViews, setViewsInvisibleAction);
    }

    @Override
    public void attachPresenter(IBasePresenter iBasePresenter) {
        mainActivityPresenter = (MainActivityPresenter) iBasePresenter;
    }

    @Override
    public void detachPresenter() {
        mainActivityPresenter= null;
    }


    //todo: is it need?
    public void detachView(){
        iMainActivityView = null;
    }


    public void onRefreshButtonClick(View view) {
        mainActivityPresenter.showLoading();
        mainActivityPresenter.showWeather(etCity.getText().toString());
    }
}
