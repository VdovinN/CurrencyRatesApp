package com.example.vdovin.currencyratesapp.utils.rx;

import rx.Scheduler;

public interface RxSchedulers {

    Scheduler mainThread();

    Scheduler io();

    Scheduler computation();

    Scheduler network();

    Scheduler immediate();

    Scheduler background();
}