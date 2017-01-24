package com.example.vdovin.currencyratesapp.screens.main.dagger;

import com.example.vdovin.currencyratesapp.application.dagger.AppComponent;
import com.example.vdovin.currencyratesapp.screens.main.MainActivity;

import dagger.Component;

@MainScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

}
