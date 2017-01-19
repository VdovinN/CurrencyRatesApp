package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.database.model.Exchange;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

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

    public void getExchangeList() {
        Observable<List<Exchange>> observable = model.loadAllExchanges();
        subscription = observable
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(
                        banks -> view.display(banks),
                        error -> view.showErrorDialog(error)
                );
    }

    public void unSubscribeSubscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
