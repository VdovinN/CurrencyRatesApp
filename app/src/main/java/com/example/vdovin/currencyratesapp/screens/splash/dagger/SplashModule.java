package com.example.vdovin.currencyratesapp.screens.splash.dagger;

import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.screens.splash.SplashActivity;
import com.example.vdovin.currencyratesapp.screens.splash.structure.SplashModel;
import com.example.vdovin.currencyratesapp.screens.splash.structure.SplashPresenter;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class SplashModule {

    private SplashActivity splashActivity;

    public SplashModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    @SplashScope
    SplashModel provideSplashModel(OkHttpClient httpClient,
                                   Request request,
                                   StringParser parser,
                                   ExchangeService exchangeService) {
        return new SplashModel(splashActivity, httpClient, request, parser, exchangeService);
    }

    @Provides
    @SplashScope
    SplashPresenter provideSplashPresenter(SplashModel model,
                                           RxSchedulers rxSchedulers) {
        return new SplashPresenter(model, rxSchedulers);
    }

}
