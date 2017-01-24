package com.example.vdovin.currencyratesapp.screens.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vdovin.currencyratesapp.R;
import com.example.vdovin.currencyratesapp.application.CurrencyApp;
import com.example.vdovin.currencyratesapp.database.model.Exchange;
import com.example.vdovin.currencyratesapp.screens.main.dagger.DaggerMainComponent;
import com.example.vdovin.currencyratesapp.screens.main.dagger.MainModule;
import com.example.vdovin.currencyratesapp.screens.main.structure.MainPresenter;
import com.example.vdovin.currencyratesapp.screens.main.structure.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.currency_list)
    Spinner spinner;

    @Inject
    MainPresenter presenter;

    private ArrayAdapter<String> currencyAdapter;
    private List<String> currenciesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder()
                .appComponent(CurrencyApp.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.getExchangeList();

        currenciesList = new ArrayList<>();
        currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currenciesList);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(currencyAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribeSubscription();
    }

    @Override
    public void displayCurrencies(List<String> exchangeList) {//Temporary

        for (String exchange : exchangeList) {
            currenciesList.add(exchange);
        }
        currencyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorDialog(Throwable throwable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.network_problem))
                .setMessage(throwable.getMessage())
                .setPositiveButton(getString(R.string.ok), (dialog1, which) -> {
                    dialog1.cancel();
                    finish();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
