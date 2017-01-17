package com.example.vdovin.currencyratesapp.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.vdovin.currencyratesapp.screens.main.CurrencyExchangeActivity;

public class SplashActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 5000;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(
                () -> {
                    Intent intent = new Intent(SplashActivity.this, CurrencyExchangeActivity.class);
                    startActivity(intent);
                    finish();
                }, DELAY_MILLIS
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

}
