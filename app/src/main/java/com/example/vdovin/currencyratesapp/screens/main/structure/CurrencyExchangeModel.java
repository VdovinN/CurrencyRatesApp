package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.database.model.Exchange;
import com.example.vdovin.currencyratesapp.database.service.ExchangeService;
import com.example.vdovin.currencyratesapp.utils.parser.ParsedResponse;
import com.example.vdovin.currencyratesapp.utils.parser.StringParser;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class CurrencyExchangeModel {

    private ExchangeService exchangeService;

    public CurrencyExchangeModel(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public Observable<List<Exchange>> loadAllExchanges() {
        return Observable.just(exchangeService.loadAll());
    }
}
