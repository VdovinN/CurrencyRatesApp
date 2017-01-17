package com.example.vdovin.currencyratesapp.utils.parser;

import android.util.Pair;

import com.example.vdovin.currencyratesapp.database.model.Exchange;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringParser {

    private static final String UTF_ENCODING = "UTF-8";
    private static final String TABLE_BANK_VALUE = "tabelBankValute";
    private static final String ATTRIBUTE_KEY = "class";

    public List<Exchange> parse(String response) {

        List<Exchange> exchangeList = new ArrayList<>();

        //Map<String, Pair<String, String>> exchangeRatesMap = new HashMap<>();

        Document document = Jsoup.parse(response, UTF_ENCODING);
        Element currencyExchangeTable = document.getElementById(TABLE_BANK_VALUE);
        Elements banks = currencyExchangeTable.child(1).children();
        for (Element bank : banks) {
            String bankName = bank.children().get(0).text();
            for (int i = 1; i < bank.children().size(); i += 2) {
                String currency = bank.children().get(i).attr(ATTRIBUTE_KEY);
                String buyingPrice = bank.children().get(i).text();
                String sellingPrice = bank.children().get(i + 1).text();

                String currencyName = currency.substring(4, 7);

                Exchange exchange = new Exchange();
                exchange.setBankName(bankName);
                exchange.setCurrencyName(currencyName);
                exchange.setBuyingPrice(buyingPrice);
                exchange.setSellingPrice(sellingPrice);

                exchangeList.add(exchange);
            }
        }
        return exchangeList;
    }

}
