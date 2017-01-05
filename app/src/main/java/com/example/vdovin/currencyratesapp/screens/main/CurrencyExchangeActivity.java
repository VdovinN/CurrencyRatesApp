package com.example.vdovin.currencyratesapp.screens.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vdovin.currencyratesapp.R;
import com.example.vdovin.currencyratesapp.network.CurrencyExchangeRequest;

import java.io.IOException;

public class CurrencyExchangeActivity extends AppCompatActivity {

    private CurrencyExchangeRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = new CurrencyExchangeRequest();

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    request.getCurrencyExchangeResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute((Void[]) null);

    }
}
