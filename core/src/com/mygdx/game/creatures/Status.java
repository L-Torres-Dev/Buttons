package com.mygdx.game.creatures;

import com.mygdx.game.skills.effects.Effect;

import java.util.ArrayList;

/**
 * Created by louie on 9/23/2016.
 */
public class Status {

    ArrayList<Effect> effects;

    public Status(){
        effects = new ArrayList<Effect>();
    }

    public void addStatus(Effect effect){

        if(effects.size() < 4) {
            effects.add(effect);
        }
        else{
            effects.add(0, effect);
        }
    }

    public void removeStatus(String effName){
        for(int i = 0; i < effects.size(); i++){
            if(effects.get(i).getName().equals(effName)){
                effects.remove(i);
            }
        }
    }

    public void removeAllStatuses(){
        for(int i = 0; i < effects.size(); i++){
            effects.remove(i);
        }
    }

    public String toString()
    {
        String string = "";

        for(int i = 0; i < effects.size(); i++)
        {
            if(effects.size() == 0 || i == effects.size() - 1)
            {
                string += effects.get(i).getAbreviation();
            }
            else
            {
                string += effects.get(i).getAbreviation() + ",";
            }
        }
        return string;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }
}
