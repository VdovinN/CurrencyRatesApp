package com.example.vdovin.currencyratesapp.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeModel;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangePresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeView;
import com.example.vdovin.currencyratesapp.utils.rx.AppRxSchedulers;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class CurrencyExchangeActivity extends AppCompatActivity {

    private static final String BANKS_URL = "http://curs.md/en/ajax/block?block_name=bank_valute_table";
    private static final String BODY_PARAM = "CotDate";

    private static final String DAY = "2017-01-07";

    private CurrencyExchangePresenter presenter;
    private CurrencyExchangeModel model;
    private CurrencyExchangeView view;

    private AppRxSchedulers rxSchedulers;

    private OkHttpClient httpClient;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        httpClientInit();
        httpRequestInit();

        rxSchedulers = new AppRxSchedulers();
        view = new CurrencyExchangeView(this);
        model = new CurrencyExchangeModel(httpClient, request);
        presenter = new CurrencyExchangePresenter(view, model, rxSchedulers);

        setContentView(view.getView());

        presenter.doSync();
    }

    private void httpClientInit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
    }

    private void httpRequestInit() {
        RequestBody body = new FormBody.Builder()
                .add(BODY_PARAM, DAY)
                .build();

        request = new Request.Builder()
                .url(BANKS_URL)
                .post(body)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribeSubscription();
    }
}
