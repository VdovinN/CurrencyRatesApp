package com.example.vdovin.currencyratesapp.application.dagger;

import android.content.Context;

import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import dagger.Component;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@AppScope
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        ParserModule.class
})
public interface AppComponent {

    Context context();

    OkHttpClient httpClient();

    Request request();

    RxSchedulers rxSchedulers();

    ExchangeService exchangeService();

    StringParser stringParser();

}
