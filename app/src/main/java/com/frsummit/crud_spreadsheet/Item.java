package com.frsummit.crud_spreadsheet;

/**
 * Created by F R Summit on 17th July,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class Item {
    private String id;
    private String name;
    private String beand;
    private String price;

    public Item(String id, String name, String beand, String price) {
        this.id = id;
        this.name = name;
        this.beand = beand;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeand() {
        return beand;
    }

    public void setBeand(String beand) {
        this.beand = beand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
