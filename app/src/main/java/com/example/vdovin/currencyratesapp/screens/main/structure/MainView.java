package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.database.model.Exchange;

import java.util.List;

public interface MainView {

    void displayCurrencies(List<String> exchangeList);

    void showErrorDialog(Throwable throwable);

}
