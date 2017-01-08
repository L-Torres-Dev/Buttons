package com.mygdx.game.creatures;

import com.mygdx.game.skills.Skill;
import com.mygdx.game.skills.SkillTree;

/**
 * Created by louie on 9/10/2016.
 */
public class Creature extends Character {

    int expGive;            //How much exp a creature gives when defeated.
    int goldAmount;          //How much gold a creature gives when defeated.
    int attributePoints;

    Skill chosenSkill;

    public Creature(){
        skillTree = new SkillTree();
        status = new Status();
        setRegens();
    }

    public void attack(Character enemy) {

    }

    public void chooseSkill(){

        /*Skill chosenSkill = skillSet.get(0);
        Skill possibleSkill = skillSet.get(0);

        for(Skill skill: skillSet){
            if(!skill.checkCosts()){
                continue;
            }

            if(current_health <= (.3 * base_health)){
                if(skill.heal > 0) {
                    if (skill.heal > possibleSkill.heal) {
                        possibleSkill = skill;
                    }
                }
            }

            if(skill.hasEffect){

            }
        }

        this.chosenSkill = chosenSkill;*/

    }

    public int getExpGive() {

        return expGive;
    }

    public void setExpGive(int expGive) {
        this.expGive = expGive;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public Skill getChosenSkill() {
        return chosenSkill;
    }

    public void setChosenSkill(Skill chosenSkill) {
        this.chosenSkill = chosenSkill;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }
}
