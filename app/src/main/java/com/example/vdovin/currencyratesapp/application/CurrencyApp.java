package com.example.vdovin.currencyratesapp.application;

import android.app.Application;
import android.content.Context;

import com.example.vdovin.currencyratesapp.application.dagger.AppComponent;
import com.example.vdovin.currencyratesapp.application.dagger.AppModule;
import com.example.vdovin.currencyratesapp.application.dagger.DaggerAppComponent;

public class CurrencyApp extends Application {

    private static AppComponent appComponent;

    public static CurrencyApp get(Context context) {
        return (CurrencyApp) context.getApplicationContext();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}
