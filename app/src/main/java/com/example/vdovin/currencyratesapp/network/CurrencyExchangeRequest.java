package com.example.vdovin.currencyratesapp.network;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observable;

public class CurrencyExchangeRequest {

    private static final String BANKS_URL = "http://curs.md/en/ajax/block?block_name=bank_valute_table";
    private static final String BODY_PARAM = "CotDate";

    private static final String DAY = "2017-01-07";

    private OkHttpClient httpClient;

    public CurrencyExchangeRequest() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
    }

    public Observable<String> getCurrencyExchangeResponse() {

        RequestBody body = new FormBody.Builder()
                .add(BODY_PARAM, DAY)
                .build();

        Request request = new Request.Builder()
                .url(BANKS_URL)
                .post(body)
                .build();

        return Observable.create(subscriber -> {
            try {
                Response response = httpClient.newCall(request).execute();
                String responseBody = response.body().string();
                Observable.just(responseBody);
            } catch (IOException e) {
                Observable.error(e);
            }
        });
    }

}
