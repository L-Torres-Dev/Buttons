package com.mygdx.game.skills.specificSkills.creaturespecificSkills;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.skills.Globals;
import com.mygdx.game.skills.PhysicalSkill;
import com.mygdx.game.skills.effects.Effect;

import java.util.Random;

/**
 * Created by louie on 9/23/2016.
 */
public class PoisonousStrike extends PhysicalSkill {

    Random rand;

    public PoisonousStrike(Character container){
        name = "Poisonous Strike";
        power = 3;
        heal = 0;
        this.container = container;

        reqLvl = 1;
        staminaConsumption = 4;

        Effect effect = new Effect("Poison", this);
        effect.setAbreviation("PSN");
        effect.setId(Globals.PS_PSN);

        this.effect = effect;

        rand = new Random();
    }

    /**
     * Uses the Poison Strike attack to do damage to the Enemy Character
     * @param user The user using the Skill
     * @param enemy The target Character to be inflicted
     * @return the final Damage dealt to the Enemy Character
     */
    public double use(Character user, Character enemy){
        super.use(user, enemy);

        double dmg = ((power  / 10) * user.getCurrent_strength()) + (enemy.getCurrent_strength() / 2);

        dmg = dmg - ((enemy.getCurrent_defense() / 2) + (enemy.getCurrent_defense()));

        container.setHit(hit(enemy));
        container.setCrit(critical(enemy));
        specialDmgCases(dmg, enemy);

        if(container.isHit()){
            applyStatusChange(enemy);
        }

        return dmg;
    }
    /**
     * applies the effect to the target Character.
     * it does not have a 100% chance of success but a
     * 20% chance of success
     * @param enemy the target Character to be affected.
     */
    private void applyStatusChange(Character enemy) {

        double affected = .2; // Governs the chances of being affected. Usually .2

        //This if Statement checks to see if the enemy Character already has the
        //effect to be inflicted. If it does, then the effect will not stack.
        if(!enemy.effectEquals(effect)) {
            double num = 0;
            num = rand.nextDouble();
            if (num <= affected) {
                enemy.getStatus().addStatus(effect);
                effect.setAffected(enemy);
            }
        }
    }

    /**
     * Uses the effect's debuff or buff to the Character Object that is affected by it.
     */
    public void useEffect(){

        double dmg = ((container.getCurrent_strength() / 2) - (effect.getAffected().getCurrent_defense() / 3));

        if(dmg < 1){
            dmg = 1;
        }

        effect.getAffected().setCurrent_health(effect.getAffected().getCurrent_health() - dmg);
    }

    public double heal(Character user){
        super.heal(user);
        return 0;
    }
}
