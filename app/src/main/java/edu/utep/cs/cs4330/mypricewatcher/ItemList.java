package edu.utep.cs.cs4330.mypricewatcher;

import java.util.ArrayList;
import java.util.List;

/*
    Author: Luis Gutierrez
    Class: CS4330
 */
public class ItemList {
    private static List<Item> items = new ArrayList<>();
    private String name;

    public ItemList(String name){
        this.name = name;
    }

    //region Getters

    public String getName(){
        return this.name;
    }

    public List<Item> getItems(){
        return items;
    }

    //endregion

    public void setName(String name){
        this.name = name;
    }

    public void addItem(Item item){
        if(item != null){
            items.add(item);
        }
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public List<Item> sortByItemName(){
        return items;
    }

    public List<Item> sortByDateAdded(){
        return items;
    }

    public List<Item> filterBySourceName(){
        return items;
    }

    public List<Item> filterByPriceRange(){
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }
}
