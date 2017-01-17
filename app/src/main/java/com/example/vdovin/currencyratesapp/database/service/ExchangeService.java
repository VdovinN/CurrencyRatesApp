package com.example.vdovin.currencyratesapp.database.service;

import com.example.vdovin.currencyratesapp.database.dao.DaoSession;
import com.example.vdovin.currencyratesapp.database.model.Exchange;

import java.util.List;

public class ExchangeService {

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

}
