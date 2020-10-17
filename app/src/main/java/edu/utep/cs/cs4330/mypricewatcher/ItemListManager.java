package edu.utep.cs.cs4330.mypricewatcher;

import java.util.ArrayList;
import java.util.List;

/*
    Authors: Luis Gutierrez and Antonio Zavala
    Class: CS4330
 */
public class ItemListManager {
    private static List<ItemList> itemLists = new ArrayList<>();
    private static ItemList currentList;

    //region Getters

    public List<ItemList> getItemLists(){
        return itemLists;
    }

    public static ItemList getCurrentList(){
        return currentList;
    }

    //endregion

    public void setCurrentList(ItemList list){
        this.currentList = list;
    }

    public void addList(ItemList list){
        if(list != null){
            itemLists.add(list);
        }
    }

    public void removeList(ItemList list){
        itemLists.remove(list);
    }

    public boolean isEmpty(){
        return itemLists.isEmpty();
    }
}
