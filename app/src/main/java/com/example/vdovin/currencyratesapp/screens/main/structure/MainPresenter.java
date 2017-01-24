package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.database.model.Exchange;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import java.util.List;

import rx.Observable;
import rx.Subscription;

public class MainPresenter {

    private MainModel model;
    private MainView view;
    private RxSchedulers rxSchedulers;

    private Subscription subscription;

    public MainPresenter(MainModel model, RxSchedulers rxSchedulers) {
        this.model = model;
        this.rxSchedulers = rxSchedulers;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void getExchangeList() {
        Observable<List<String>> observable = model.loadAllCurrencies();
        subscription = observable
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(
                        banks -> view.displayCurrencies(banks),
                        error -> view.showErrorDialog(error)
                );
    }

    public void unSubscribeSubscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
