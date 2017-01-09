package com.example.vdovin.currencyratesapp.screens.main.structure;

import com.example.vdovin.currencyratesapp.utils.parser.StringParser;
import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

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

    public void doSync() {
        Observable<String> observable = model.getCurrencyExchangeResponseObservable();
        subscription = observable
                .flatMap(response -> model.getParsedResponseObservable(response))
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(
                        parsedResponseList -> view.display(parsedResponseList),
                        error -> view.showErrorDialog(error)
                );
    }

    public void unSubscribeSubscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
