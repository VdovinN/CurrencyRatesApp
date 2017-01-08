package com.example.vdovin.currencyratesapp.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vdovin.currencyratesapp.application.CurrencyApp;
import com.example.vdovin.currencyratesapp.screens.main.dagger.CurrencyExchangeModule;
import com.example.vdovin.currencyratesapp.screens.main.dagger.DaggerCurrencyExchangeComponent;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangePresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeView;

import javax.inject.Inject;

public class CurrencyExchangeActivity extends AppCompatActivity {

    @Inject
    CurrencyExchangePresenter presenter;
    @Inject
    CurrencyExchangeView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCurrencyExchangeComponent.builder()
                .appComponent(CurrencyApp.getAppComponent())
                .currencyExchangeModule(new CurrencyExchangeModule(this))
                .build()
                .inject(this);


        setContentView(view.getView());

        presenter.doSync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribeSubscription();
    }
}
