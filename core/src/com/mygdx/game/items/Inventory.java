package com.mygdx.game.items;

import java.util.ArrayList;

/**
 * Created by louie on 12/23/2016.
 */
public class Inventory {

    ArrayList<Item> items;
   public static int maxItems = 10;

    public Inventory(){
        items = new ArrayList<Item>(maxItems);
    }

    public void add(Item item)
    {
        items.add(item);
    }

    public Item getItemById(int id)
    {
        return items.get(id);
    }

    public boolean checkInventory()
    {
        boolean inventoryFull;

        if(items.size() <= 10)
        {
            inventoryFull = false;
        }

        else
        {
            inventoryFull = true;
        }

        return inventoryFull;
    }

    @Override
    public String toString() {

        String listOfItems = "";

        for(Item item: items){
            if(!item.equals(null))
            {
                listOfItems += item.getName() + "\n";
            }
        }

        return listOfItems;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
