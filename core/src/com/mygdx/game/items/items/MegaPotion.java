package com.mygdx.game.items.items;

import com.mygdx.game.creatures.*;
import com.mygdx.game.creatures.Character;
import com.mygdx.game.items.Consumable;

/**
 * Created by Luis on 1/9/2017.
 */
public class MegaPotion extends Consumable {


    int healing;

    public MegaPotion(){
        healing = 40;
        name = "Mega Potion";

        description = "Heals for " + healing + " hp.";
        nonExistent = false;
    }

    @Override
    public void consume(Character character) {
        super.consume(character);


    }
}
