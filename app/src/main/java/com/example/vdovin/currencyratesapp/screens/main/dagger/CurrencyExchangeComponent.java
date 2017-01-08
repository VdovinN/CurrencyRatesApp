package com.example.vdovin.currencyratesapp.screens.main.dagger;

import com.example.vdovin.currencyratesapp.application.dagger.AppComponent;
import com.example.vdovin.currencyratesapp.screens.main.CurrencyExchangeActivity;

import dagger.Component;

@CurrencyExchangeActivityScope
@Component(modules = CurrencyExchangeModule.class, dependencies = AppComponent.class)
public interface CurrencyExchangeComponent {

    void inject(CurrencyExchangeActivity currencyExchangeActivity);

}
