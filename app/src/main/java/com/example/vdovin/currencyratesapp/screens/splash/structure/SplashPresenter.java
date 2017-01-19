package com.example.vdovin.currencyratesapp.screens.splash.structure;

import com.example.vdovin.currencyratesapp.utils.rx.RxSchedulers;

import rx.Observable;
import rx.Subscription;

public class SplashPresenter {

    private SplashModel model;
    private RxSchedulers rxSchedulers;

    private Subscription subscription;

    public SplashPresenter(SplashModel model, RxSchedulers rxSchedulers) {
        this.model = model;
        this.rxSchedulers = rxSchedulers;
    }

    public void doSync() {
        Observable<String> observable = model.getCurrencyExchangeResponseObservable();
        subscription = observable
                .flatMap(response -> model.getParsedResponseObservable(response))
                .doOnNext(exchangeList -> model.updateDatabase(exchangeList))
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.mainThread())
                .subscribe(exchangeList -> {
                            model.startMainActivity();
                            model.finishSplashActivity();
                        },
                        throwable -> model.finishSplashActivity());
    }

    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
