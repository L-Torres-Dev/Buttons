package com.mygdx.game.skills;

import com.badlogic.gdx.Screen;
import com.mygdx.game.creatures.Character;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;

/**
 * Created by louie on 9/10/2016.
 */
public class PhysicalSkill extends Skill {

    public int staminaConsumption;

    public PhysicalSkill(){
        super();
    }

    public PhysicalSkill(Character container){
        this.container = container;
    }

    public double use(Character user, Character enemy){
        user.setCurrent_stamina(user.getCurrent_stamina() - staminaConsumption);

        return 0;
    }

    void applyEffect(Character c) {

    }

    public boolean checkCosts(){

        boolean enough;

        if(staminaConsumption <= container.getCurrent_stamina()){
            enough = true;
        }
        else{
            enough = false;
        }
        return enough;
    }

    public int getStaminaConsumption() {
        return staminaConsumption;
    }

    public void setStaminaConsumption(int staminaConsumption) {
        this.staminaConsumption = staminaConsumption;
    }
}
