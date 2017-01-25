package com.mygdx.game.items.weapons;

import com.mygdx.game.creatures.Hero;
import com.mygdx.game.items.Gear;
import com.mygdx.game.items.BodyType;

import java.util.Random;

/**
 * Created by louie on 12/24/2016.
 */
public class Weapon extends Gear {

    public Hand hand;



    public Weapon()
    {
        bodyType = BodyType.ARMS;

        Random random = new Random();

        int ranNum = random.nextInt(2);

        if(ranNum == 0)
        {
            hand = Hand.RIGHT;
        }
        else if(ranNum == 1)
        {
            hand = Hand.LEFT;
        }
        System.out.println("The number is: " + ranNum);

        nonExistent = false;
    }

    public void equip(Hero hero)
    {
        hero.equip(this);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }


}
