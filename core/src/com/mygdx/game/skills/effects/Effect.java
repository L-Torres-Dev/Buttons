package com.mygdx.game.skills.effects;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.skills.Skill;

import java.util.ArrayList;

/**
 * Created by louie on 9/10/2016.
 */
public class Effect {

    Character affected;
    String name;
    String abreviation;

    String useString;
    Skill skill;

    int id;

    /**
     * Default constructor for the Effect class. Used only if
     * the skill that will contain this instance of Effect has
     * no effect.
     */
    public Effect(){
    }

    public Effect(String name, Skill skill){
        this.skill = skill;
        this.name = name;
    }

    public void use(Skill skill)
    {
        skill.useEffect();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getUseString() {
        return useString;
    }

    public void setUseString(String useString) {
        this.useString = useString;
    }

    public Character getAffected() {
        return affected;
    }

    public void setAffected(Character affected) {
        this.affected = affected;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
