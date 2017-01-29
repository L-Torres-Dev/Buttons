package com.mygdx.game.items.armor;

import com.mygdx.game.creatures.Hero;
import com.mygdx.game.items.Gear;

/**
 * Created by louie on 12/24/2016.
 */
public class Armor extends Gear {

    public Armor(){
        nonExistent = false;
    }

    public void equip(Hero hero)
    {
        hero.equip(this);
    }
}
