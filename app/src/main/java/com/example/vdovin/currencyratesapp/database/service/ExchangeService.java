package com.example.vdovin.currencyratesapp.database.service;

import android.database.Cursor;

import com.example.vdovin.currencyratesapp.database.dao.DaoSession;
import com.example.vdovin.currencyratesapp.database.dao.ExchangeDao;
import com.example.vdovin.currencyratesapp.database.model.Exchange;

import java.util.ArrayList;
import java.util.List;

public class ExchangeService {

    private static final String SELECT_DISTINCT = "SELECT DISTINCT ";
    private static final String FROM = " FROM ";
    private static final int COLUMN_INDEX = 0;

    private DaoSession daoSession;

    public ExchangeService(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void insertAll(List<Exchange> exchangeList) {
        daoSession.getExchangeDao().insertInTx(exchangeList);
    }

    public List<Exchange> loadAll() {
        return daoSession.getExchangeDao().loadAll();
    }

    public List<String> loadAllCurrencies() {
        List<String> exchangeList = new ArrayList<>();
        String distinctQueryByCurrency = SELECT_DISTINCT + ExchangeDao.Properties.CurrencyName.columnName + FROM + ExchangeDao.TABLENAME;
        Cursor cursor = daoSession.getDatabase().rawQuery(distinctQueryByCurrency, null);
        while (cursor.moveToNext()) {
            exchangeList.add(cursor.getString(COLUMN_INDEX));
        }
        return exchangeList;
    }

}
