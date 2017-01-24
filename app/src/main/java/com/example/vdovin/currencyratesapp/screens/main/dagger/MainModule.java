package com.example.vdovin.currencyratesapp.screens.main.dagger;

import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.screens.main.MainActivity;
import com.example.vdovin.currencyratesapp.screens.main.structure.MainModel;
import com.example.vdovin.currencyratesapp.screens.main.structure.MainPresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.MainView;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainScope
    MainModel provideCurrencyExchangeModel(ExchangeService exchangeService) {
        return new MainModel(exchangeService);
    }

    @Provides
    @MainScope
    StringParser provideStringParser() {
        return new StringParser();
    }

    @Provides
    @MainScope
    MainPresenter provideCurrencyExchangePresenter(MainModel model,
                                                   RxSchedulers rxSchedulers) {
        return new MainPresenter(model, rxSchedulers);
    }

}
