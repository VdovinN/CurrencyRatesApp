package com.example.vdovin.currencyratesapp.screens.splash.dagger;

import com.example.vdovin.currencyratesapp.application.dagger.AppComponent;
import com.example.vdovin.currencyratesapp.screens.splash.SplashActivity;

import dagger.Component;

@SplashScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {

    void inject(SplashActivity splashActivity);

}
