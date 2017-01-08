package com.mygdx.game.skills;

import com.mygdx.game.creatures.Character;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;
import com.mygdx.game.skills.effects.Effect;

import java.util.Random;

/**
 * Created by louie on 9/10/2016.
 */
public class Skill {

    public Character container;          //The Character that owns this Skill.

    public String name;
    public Effect effect;
    public SkillType skillType;

    public int reqLvl;             // Holds the required level for the skill.
    public double power;             // Holds how much damage the skill deals
    public double heal;            // Holds how much health the skill replenishes

    public boolean hasEffect;

    public Skill(String name, double power, double heal){
        this.name = name;
        this.power = power;
        this.heal = heal;
    }

    public Skill(){

    }

    public Skill(Character container){
        this.container = container;
    }

    public double use(Character user, Character enemy){

        return 0;
    }

    public double heal(Character user){

        return 0;
    }

    public void specialDmgCases(double dmg, Character enemy){
        if(dmg > enemy.getCurrent_health()){
            dmg = enemy.getCurrent_health();
        }

        if(dmg < 1){
            dmg = 1;
        }

        if(!container.isHit()){
            dmg = 0;
        }

        if(container.isCrit()){
            dmg = dmg * 1.5;
        }

        enemy.setCurrent_health(enemy.getCurrent_health() - dmg);

        if(enemy.getCurrent_health() < 1 && enemy.getCurrent_health() > 0){
            enemy.setCurrent_health(1);
        }

        if(enemy.getCurrent_health() < 0){
            enemy.setCurrent_health(0);
        }
    }

    public void specialHealCases(double healing){
        if(healing < 1){
            healing = 1;
        }

        if(healing > container.getCurrent_health()){
            healing = (container.getBase_health() - container.getCurrent_health());
        }

        container.setCurrent_health(container.getCurrent_health() + healing);

        if(container.getCurrent_health() > container.getBase_health()){
            container.setCurrent_health(container.getBase_health()) ;

        }
    }

    public boolean hit(Character enemy){
        Random rand = new Random();

        double userRandNum = rand.nextDouble();
        double eRandNum = rand.nextDouble();
        double randNum = rand.nextDouble();

        userRandNum += (container.getCurrent_speed() / 100);

        eRandNum += (enemy.getCurrent_speed() / 100);

        boolean hit = userRandNum >= (randNum + eRandNum - (userRandNum / 1.15));

        return hit;
    }

    public boolean critical(Character enemy){
        Random rand = new Random();

        double userRandNum = rand.nextDouble();
        double eRandNum = rand.nextDouble();
        double randNum = rand.nextDouble();

        userRandNum += (container.getCurrent_luck() / 100);

        eRandNum += (enemy.getCurrent_luck() / 100);

        boolean critical = (userRandNum >= (randNum + eRandNum) && container.isHit());

        return critical;

    }

    public void useEffect(){

    }

    public boolean checkCosts(){

        return false;
    }


    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public double getHeal() {
        return heal;
    }

    public void setHeal(double heal) {
        this.heal = heal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getReqLvl() {
        return reqLvl;
    }

    public void setReqLvl(int reqLvl) {
        this.reqLvl = reqLvl;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public Character getContainer() {
        return container;
    }

    public void setContainer(Character container) {
        this.container = container;
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }
}
