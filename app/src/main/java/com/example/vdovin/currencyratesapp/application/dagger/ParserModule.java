package com.example.vdovin.currencyratesapp.application.dagger;

import com.example.vdovin.currencyratesapp.utils.parser.StringParser;

import dagger.Module;
import dagger.Provides;

@Module
public class ParserModule {

    @Provides
    @AppScope
    StringParser provideStringParser() {
        return new StringParser();
    }

}
