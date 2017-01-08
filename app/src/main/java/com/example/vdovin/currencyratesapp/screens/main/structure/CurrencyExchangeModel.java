package com.example.vdovin.currencyratesapp.screens.main.structure;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class CurrencyExchangeModel {

    private OkHttpClient httpClient;
    private Request request;

    public CurrencyExchangeModel(OkHttpClient httpClient, Request request) {
        this.httpClient = httpClient;
        this.request = request;
    }

    public Observable<String> getCurrencyExchangeResponse() {
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

}
