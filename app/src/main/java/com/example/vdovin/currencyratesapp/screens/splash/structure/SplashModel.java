package com.example.vdovin.currencyratesapp.screens.splash.structure;

import com.example.vdovin.currencyratesapp.database.model.Exchange;
import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.screens.splash.SplashActivity;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class SplashModel {

    private OkHttpClient httpClient;
    private Request request;
    private StringParser stringParser;
    private ExchangeService exchangeService;

    private SplashActivity splashActivity;

    public SplashModel(SplashActivity splashActivity,
                       OkHttpClient httpClient,
                       Request request,
                       StringParser stringParser,
                       ExchangeService exchangeService) {
        this.splashActivity = splashActivity;
        this.httpClient = httpClient;
        this.request = request;
        this.stringParser = stringParser;
        this.exchangeService = exchangeService;
    }

    public Observable<String> getCurrencyExchangeResponseObservable() {
        return Observable.defer(() -> {
            try {
                Response response = httpClient.newCall(request).execute();
                String responseBody = response.body().string();
                return Observable.just(responseBody);
            } catch (IOException e) {
                return Observable.error(e);
            }
        });
    }

    public Observable<List<Exchange>> getParsedResponseObservable(String response) {
        return Observable.just(stringParser.parse(response));
    }

    public void updateDatabase(List<Exchange> exchangeList) {
        exchangeService.insertAll(exchangeList);
    }

    public void finishSplashActivity() {
        splashActivity.finish();
    }

    public void startMainActivity() {//TODO review
        splashActivity.startMainActivity();
    }

}
