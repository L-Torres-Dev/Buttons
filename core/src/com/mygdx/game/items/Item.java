package com.mygdx.game.items;

/**
 * Created by louie on 12/23/2016.
 */

import com.mygdx.game.creatures.Hero;

/**
 * The Item Class will represent all items that the Hero can
 * use in His Adventure. Will be inherited by the Gear class.
 */
public class Item {

    public String name;
    public String description;

    public boolean nonExistent;

    public Item()
    {
        name = "NONE";
        description = "";
        nonExistent = true;
    }


    public String getName() {

        if(this == null)
        {
            return "";
        }

        return name;
    }

    public boolean isNonExistent() {
        return nonExistent;
    }

    public void setNonExistent(boolean nonExistant) {
        this.nonExistent = nonExistant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
