package com.mygdx.game.skills;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;

/**
 * Created by louie on 9/10/2016.
 */
public class MagicalSkill extends Skill {

    public int manaConsumption;

    public MagicalSkill(){
    }

    public MagicalSkill(Character container){
        this.container = container;
    }

    public double use(Character user, Character enemy){
        user.setCurrent_mana(user.getCurrent_mana() - manaConsumption);

        return 0;
    }

    void applyEffect(Character c) {

    }

    public boolean checkCosts(){

        boolean enough;

        if(manaConsumption <= container.getCurrent_mana()){
            enough = true;
        }
        else{
            enough = false;
        }
        return enough;
    }

    public int getManaConsumption() {
        return manaConsumption;
    }

    public void setManaConsumption(int manaConsumption) {
        this.manaConsumption = manaConsumption;
    }
}
