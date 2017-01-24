package com.example.vdovin.currencyratesapp.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vdovin.currencyratesapp.application.CurrencyApp;
import com.example.vdovin.currencyratesapp.screens.main.MainActivity;
import com.example.vdovin.currencyratesapp.screens.splash.dagger.DaggerSplashComponent;
import com.example.vdovin.currencyratesapp.screens.splash.dagger.SplashModule;
import com.example.vdovin.currencyratesapp.screens.splash.structure.SplashPresenter;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .appComponent(CurrencyApp.getAppComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.doSync();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }

    public void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
