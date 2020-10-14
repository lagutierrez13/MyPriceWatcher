package edu.utep.cs.cs4330.mypricewatcher;

/*
    Author: Luis Gutierrez
    Class: CS4330
 */
public class PriceFinder {
    public double findPrice(String url) {
        double max = 30.00;
        double min = 17.00;
        double newPrice = Math.round((Math.random() * (max - min + 1) + min) * 100d) / 100d;
        return newPrice;
    }
}
