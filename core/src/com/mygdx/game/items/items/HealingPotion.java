package com.mygdx.game.items.items;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.items.Drinkable;
import com.mygdx.game.items.Item;

/**
 * Created by louie on 1/3/2017.
 */
public class HealingPotion extends Item implements Drinkable {

    public int healing;

    public HealingPotion(){

        name = "Healing Potion";
        healing = 15;
    }

    @Override
    public void Drink(Character character) {

        character.setCurrent_health(character.getCurrent_health() + healing);
        if(character.getCurrent_health() > character.getBase_health())
        {
            character.setCurrent_health(character.getBase_health());
        }
    }
}
