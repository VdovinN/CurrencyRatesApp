package com.example.vdovin.currencyratesapp.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.vdovin.currencyratesapp.R;
import com.example.vdovin.currencyratesapp.network.CurrencyExchangeRequest;
import com.example.vdovin.currencyratesapp.utils.rx.AppRxSchedulers;

import rx.Observable;
import rx.Subscription;

public class CurrencyExchangeActivity extends AppCompatActivity {

    private CurrencyExchangeRequest request;

    private Subscription subscription;
    private AppRxSchedulers rxSchedulers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxSchedulers = new AppRxSchedulers();

        request = new CurrencyExchangeRequest();

        doSync();
    }

    private void doSync() {
        Observable<String> observable = request.getCurrencyExchangeResponse();
        subscription = observable
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(
                        response -> Log.i("Response ", response),
                        error -> Log.e("Error ", error.getMessage())
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
