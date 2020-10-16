package edu.utep.cs.cs4330.mypricewatcher;;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    Authors: Luis Gutierrez and Antonio Zavala
    Class: CS4330
 */
public class Item {
    private String name;
    private double initPrice;
    private double currentPrice;
    private String change;
    private String url;
    private String dateAdded;
    private String sourceName;
    private PriceFinder priceFinder;
    private ImageButton launchUrlButton;

    private int id;
    //region Constructors
    public int id() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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
        this.initPrice = priceFinder.findPrice(url);
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
        priceFinder = new PriceFinder();
        currentPrice = priceFinder.findPrice(url);
        return currentPrice;
    }

    public String getChange(){
        return calculateChange();
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
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //endregion

    private String calculateChange(){
        String perDifStr;
        if(initPrice == 0){
            perDifStr = "Price Haven't Changed";
            return perDifStr;
        }else{
            double percDif = ((currentPrice - initPrice) / initPrice) * 100;
            percDif = Math.round(percDif * 100d) /100d;

            if (percDif == 0){
                perDifStr = "Price Haven't Changed";
                return perDifStr;
            }
            else if (percDif < 0){
                percDif = percDif * -1;
                perDifStr = "It is " + percDif + "% more expensive.";
                return perDifStr;
            }
            else{
                perDifStr = "It is " + percDif + "% cheaper.";
                return perDifStr;
            }
        }
    }

    public void refresh(){
        currentPrice = priceFinder.findPrice(url);
        change = calculateChange();
    }

}