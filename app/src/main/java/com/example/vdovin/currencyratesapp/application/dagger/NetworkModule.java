package com.example.vdovin.currencyratesapp.application.dagger;

import dagger.Module;
import dagger.Provides;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    private static final String BANKS_URL = "http://curs.md/en/ajax/block?block_name=bank_valute_table";
    private static final String BODY_PARAM = "CotDate";

    private static final String DAY = "2017-01-07";

    @Provides
    @AppScope
    public OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
    }

    @Provides
    @AppScope
    public Request provideHttpRequest() {
        RequestBody body = new FormBody.Builder()
                .add(BODY_PARAM, DAY)
                .build();

        return new Request.Builder()
                .url(BANKS_URL)
                .post(body)
                .build();
    }

}
