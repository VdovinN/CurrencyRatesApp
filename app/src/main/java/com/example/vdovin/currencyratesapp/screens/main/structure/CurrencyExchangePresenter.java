package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import rx.Observable;
import rx.Subscription;

public class CurrencyExchangePresenter {

    private CurrencyExchangeModel model;
    private CurrencyExchangeView view;
    private RxSchedulers rxSchedulers;

    private Subscription subscription;

    public CurrencyExchangePresenter(CurrencyExchangeView view, CurrencyExchangeModel model, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.rxSchedulers = rxSchedulers;
    }

    public void doSync() {
        Observable<String> observable = model.getCurrencyExchangeResponse();
        subscription = observable
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(
                        response -> view.display(response),
                        error -> view.showErrorDialog(error)
                );
    }

    public void unsubscribeSubscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
