package com.mygdx.game.skills.specificSkills;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.skills.PhysicalSkill;

/**
 * Created by louie on 9/22/2016.
 */
    public class Strike extends PhysicalSkill {

    public Strike(){
        name = "Strike";
        hasEffect = false;
        power = 2;
        heal = 0;
    }

    public Strike(Character container){
        name = "Strike";
        power = 2;
        heal = 0;
        this.container = container;

        reqLvl = 1;
        staminaConsumption = 2;
    }


    public double use(Character user, Character enemy){
        super.use(user, enemy);

        double dmg = ((power  / 10) * user.getCurrent_strength()) + (enemy.getCurrent_strength() / 2);

        dmg = dmg - ((enemy.getCurrent_defense() / 2) + (enemy.getCurrent_defense()));

        container.setHit(hit(enemy));
        container.setCrit(critical(enemy));
        specialDmgCases(dmg, enemy);

        return dmg;
    }

    public double heal(Character user){
        super.heal(user);
        return 0;
    }

}
