package com.example.vdovin.currencyratesapp.utils.parser;

import android.util.Pair;

import java.util.List;
import java.util.Map;

public class ParsedResponse {

    private String bankName;
    private Map<String, Pair<String, String>> exchangeRatesMap;

    private List<String> bankNameList;
    private List<String> currencyNameList;
    //private Map<String, List<TemporaryCurrency>> exchangeCurrencyMap;

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

    public List<String> getCurrencyNameList() {
        return currencyNameList;
    }

    public void setCurrencyNameList(List<String> currencyNameList) {
        this.currencyNameList = currencyNameList;
    }

    public List<String> getBankNameList() {
        return bankNameList;
    }

    public void setBankNameList(List<String> bankNameList) {
        this.bankNameList = bankNameList;
    }
}
