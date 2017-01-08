package com.example.vdovin.currencyratesapp.application.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vdovin.currencyratesapp.utils.rx.AppRxSchedulers;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    @AppScope
    Context provideContext() {
        return context;
    }

    @Provides
    @AppScope
    public RxSchedulers provideSchedulers() {
        return new AppRxSchedulers();
    }

}
