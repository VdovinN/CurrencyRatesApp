package com.example.vdovin.currencyratesapp.application.dagger;

import android.content.Context;

import com.example.vdovin.currencyratesapp.database.dao.DaoMaster;
import com.example.vdovin.currencyratesapp.database.dao.DaoSession;
import com.example.vdovin.currencyratesapp.database.service.ExchangeService;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DB_NAME = "CURRENCY_RATES_DB";

    @Provides
    @AppScope
    DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        return new DaoMaster(helper.getWritableDatabase()).newSession();
    }

    @Provides
    @AppScope
    ExchangeService provideExchangeService(DaoSession daoSession) {
        return new ExchangeService(daoSession);
    }



}
