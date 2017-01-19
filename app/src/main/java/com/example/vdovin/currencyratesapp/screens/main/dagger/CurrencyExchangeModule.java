package com.example.vdovin.currencyratesapp.screens.main.dagger;

import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.screens.main.CurrencyExchangeActivity;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeModel;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangePresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.CurrencyExchangeView;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;
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
    CurrencyExchangeModel provideCurrencyExchangeModel(ExchangeService exchangeService) {
        return new CurrencyExchangeModel(exchangeService);
    }

    @Provides
    @CurrencyExchangeActivityScope
    StringParser provideStringParser() {
        return new StringParser();
    }

    @Provides
    @CurrencyExchangeActivityScope
    CurrencyExchangePresenter provideCurrencyExchangePresenter(CurrencyExchangeView view,
                                                               CurrencyExchangeModel model,
                                                               RxSchedulers rxSchedulers) {
        return new CurrencyExchangePresenter(view, model, rxSchedulers);
    }

}
