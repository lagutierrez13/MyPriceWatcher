package edu.utep.cs.cs4330.mypricewatcher;;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    Author: Luis Gutierrez
    Class: CS4330
 */
public class Item {
    private String name;
    private double initPrice;
    private double currentPrice;
    private double change;
    private String url;
    private String dateAdded;
    private String sourceName;
    private PriceFinder priceFinder;

    //region Constructors

    public Item(String name, double initPrice, double currentPrice, String url, String sourceName, String dateAdded){
        priceFinder = new PriceFinder();
        this.name = name;
        this.initPrice = initPrice;
        this.currentPrice = currentPrice;
        this.url = url;
        this.sourceName = sourceName;
        this.change = calculateChange();
        this.dateAdded = dateAdded;
    }

    public Item(String name, String url, String sourceName){
        priceFinder = new PriceFinder();
        this.name = name;
        this.initPrice = priceFinder.findPrice();
        this.currentPrice = this.initPrice;
        this.url = url;
        this.sourceName = sourceName;
        this.change = calculateChange();
        this.dateAdded = setDateAdded();
    }

    //endregion

    //region Getters

    public String getName(){
        return name;
    }

    public double getInitPrice(){
        return initPrice;
    }

    public double getCurrentPrice(){
        return currentPrice;
    }

    public double getChange(){
        return change;
    }

    public String getUrl(){
        return url;
    }

    public String getDateAdded(){
        return dateAdded;
    }

    public String getSourceName(){
        return sourceName;
    }

    //endregion

    //region Setters

    public void setUrl(String url){
        this.url = url;
    }

    private String setDateAdded(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //endregion

    private double calculateChange(){
        if(initPrice == 0 || (Double)currentPrice == null || (Double)initPrice == null){
            return 0;
        }else{
            return ((currentPrice - initPrice) / initPrice) * 100;
        }
    }

    public void refresh(){
        currentPrice = priceFinder.findPrice();
        change = calculateChange();
    }
}
