package com.mygdx.game.items.weapons.weaponList;

import com.mygdx.game.items.weapons.Hand;
import com.mygdx.game.items.weapons.Weapon;

/**
 * Created by louie on 1/3/2017.
 */
public class RustyIronDagger extends Weapon {

    public RustyIronDagger()
    {
        hand = Hand.RIGHT;
        name = "Rusty Iron Dagger";

        attackBuff = 1;
        arcanaBuff = 0;
        speedBuff = 1;
        luckBuff = 0;


    }

}
