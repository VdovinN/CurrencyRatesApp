package com.example.vdovin.currencyratesapp.screens.main.dagger;

import com.example.vdovin.currencyratesapp.screens.main.CurrencyExchangeActivity;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeModel;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangePresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeView;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class CurrencyExchangeModule {

    private CurrencyExchangeActivity currencyExchangeActivity;

    public CurrencyExchangeModule(CurrencyExchangeActivity currencyExchangeActivity) {
        this.currencyExchangeActivity = currencyExchangeActivity;
    }

    @Provides
    @CurrencyExchangeActivityScope
    CurrencyExchangeView provideCurrencyExchangeView() {
        return new CurrencyExchangeView(currencyExchangeActivity);
    }

    @Provides
    @CurrencyExchangeActivityScope
    CurrencyExchangeModel provideCurrencyExchangeModel(OkHttpClient httpClient, Request request) {
        return new CurrencyExchangeModel(httpClient, request);
    }

    @Provides
    @CurrencyExchangeActivityScope
    CurrencyExchangePresenter provideCurrencyExchangePresenter(CurrencyExchangeView view, CurrencyExchangeModel model, RxSchedulers rxSchedulers) {
        return new CurrencyExchangePresenter(view, model, rxSchedulers);
    }

}
