package com.mygdx.game.skills.specificSkills;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.skills.MagicalSkill;

/**
 * Created by louie on 9/22/2016.
 */
public class Heal extends MagicalSkill {

    public Heal(Character container){
        this.container = container;
        name = "Heal";
        power = 0;
        heal = 2;
        this.container = container;

        reqLvl = 1;
        manaConsumption = 3;


    }

    public double use(Character user, Character enemy){
        super.use(user, enemy);
        heal(user);
        return 0;
    }

    public double heal(Character user){
        double healing = ((heal / 10) * (container.getCurrent_arcana() / 1));

        specialHealCases(healing);

        return healing;
    }
}
