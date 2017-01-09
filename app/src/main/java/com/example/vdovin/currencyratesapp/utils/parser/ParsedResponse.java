package com.example.vdovin.currencyratesapp.utils.parser;

import android.util.Pair;

import java.util.Map;

public class ParsedResponse {

    private String bankName;
    private Map<String, Pair<String, String>> exchangeRatesMap;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Map<String, Pair<String, String>> getExchangeRatesMap() {
        return exchangeRatesMap;
    }

    public void setExchangeRatesMap(Map<String, Pair<String, String>> exchangeRatesMap) {
        this.exchangeRatesMap = exchangeRatesMap;
    }
}
