package com.example.vdovin.currencyratesapp.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Exchange {

    @Id(autoincrement = true)
    private Long id;

    private String bankName;

    private String currencyName;

    private String sellingPrice;

    private String buyingPrice;

    @Generated(hash = 219038281)
    public Exchange(Long id, String bankName, String currencyName,
                    String sellingPrice, String buyingPrice) {
        this.id = id;
        this.bankName = bankName;
        this.currencyName = currencyName;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    @Generated(hash = 680412139)
    public Exchange() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getBuyingPrice() {
        return this.buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

}
