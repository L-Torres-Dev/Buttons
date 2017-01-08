package com.mygdx.game.creatures;

import com.mygdx.game.skills.*;
import com.mygdx.game.skills.effects.Effect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by louie on 9/10/2016.
 */
public abstract class Character {

    public String name;
    public SkillTree skillTree;                 //skillTree to hold all the skills a character can have
    public ArrayList<Skill> skillSet;           //skillSet to hold the skills that a character can use in battle
    public Status status;

    boolean hit;                //Holds true if the hero hits the enemy successfully
    boolean crit;               //Holds true if the hero hits and the hit was a critical hit.

    int baseStatPoints;         // Stat points that a character starts with
    int currentStatPoints;      // Current stat points
    int level;

    // For their original stats.
    double base_strength;
    double base_speed;
    double base_arcana;
    double base_resistance;
    double base_defense;
    double base_luck;

    // For their current stats. Used when in battle and a skill temporarily increases a stat.
    double current_strength;
    double current_speed;
    double current_arcana;
    double current_resistance;
    double current_defense;
    double current_luck;

    // For their original attributes
    double base_health;
    double base_mana;
    double base_stamina;

    // For their current attributes. Used when in battle and a skill temporarily increases an attribute.
    double current_health;
    double current_mana;
    double current_stamina;

    double healthRegen;
    double manaRegen;
    double staminaRegen;


    public String toString(){
        String skillSetString = " Level " + level + "\nSkills:";
        for(int i = 0; i < skillSet.size(); i++){
            skillSetString += skillSet.get(i).getName() + ", ";
        }

        skillSetString += "\nStats:\nStrength: " + base_strength + "\nDefense: " + base_defense + "\nArcana: " + base_arcana + "\nResistance: "
                            + base_resistance + "\nSpeed: " + base_speed + "\nLuck: " + base_luck;

        return skillSetString;
    }



    public void update(){
        current_health = base_health;
        current_stamina = base_stamina;
        current_mana = base_mana;

        current_strength = base_strength;
        current_defense = base_defense;
        current_arcana = base_arcana;
        current_resistance = base_resistance;
        current_speed = base_speed;
        current_luck = base_luck;
    }


    public boolean hit(Character enemy){
        Random rand = new Random();

        double heroRandNum = rand.nextDouble();
        double eRandNum = rand.nextDouble();
        double randNum = rand.nextDouble();

        heroRandNum += (current_speed / 100);

        eRandNum += (enemy.getCurrent_speed() / 100);

        boolean hit = heroRandNum >= (randNum + eRandNum - (heroRandNum / 1.75));
        System.out.println(hit);

        return hit;
    }

    public boolean critical(Character enemy){
        Random rand = new Random();

        double heroRandNum = rand.nextDouble();
        double eRandNum = rand.nextDouble();
        double randNum = rand.nextDouble();

        heroRandNum += (current_luck / 100);

        eRandNum += (enemy.getCurrent_luck() / 100);

        boolean critical = (heroRandNum >= (randNum + eRandNum) && hit);

        return critical;

    }

    public void resetHitAndCrit(){
        hit = true;
        crit = false;
    }

    public void setRegens(){
        healthRegen = (.3 * base_health);
        manaRegen = (.3 * base_mana + 1);
        staminaRegen = (.3 * base_stamina + 2);
    }

    public void recoverAttributes(){

        current_stamina += staminaRegen;
        current_mana += manaRegen;


        if(current_stamina > base_stamina){
            current_stamina = base_stamina;
        }
        if(current_mana > base_mana){
            current_mana = base_mana;
        }
    }

    public boolean effectEquals(Effect effect) {
        boolean equal = false;

        for(Effect indexEffect : status.getEffects()){
            if(indexEffect.getId() == effect.getId()){
                equal = true;
                break;
            }
        }


        return equal;
    }


    public double getBase_arcana() {
        return base_arcana;
    }

    public void setBase_arcana(double base_arcana) {
        this.base_arcana = base_arcana;
    }

    public double getBase_defense() {
        return base_defense;
    }

    public void setBase_defense(double base_defense) {
        this.base_defense = base_defense;
    }

    public double getBase_health() {
        return base_health;
    }

    public void setBase_health(double base_health) {
        this.base_health = base_health;
    }

    public double getBase_luck() {
        return base_luck;
    }

    public void setBase_luck(double base_luck) {
        this.base_luck = base_luck;
    }

    public double getBase_mana() {
        return base_mana;
    }

    public void setBase_mana(double base_mana) {
        this.base_mana = base_mana;
    }

    public double getBase_resistance() {
        return base_resistance;
    }

    public void setBase_resistance(double base_resistance) {
        this.base_resistance = base_resistance;
    }

    public double getBase_speed() {
        return base_speed;
    }

    public void setBase_speed(double base_speed) {
        this.base_speed = base_speed;
    }

    public double getBase_stamina() {
        return base_stamina;
    }

    public void setBase_stamina(double base_stamina) {
        this.base_stamina = base_stamina;
    }

    public double getBase_strength() {
        return base_strength;
    }

    public void setBase_strength(double base_strength) {
        this.base_strength = base_strength;
    }

    public int getBaseStatPoints() {
        return baseStatPoints;
    }

    public void setBaseStatPoints(int baseStatPoints) {
        this.baseStatPoints = baseStatPoints;
    }

    public double getCurrent_arcana() {
        return current_arcana;
    }

    public void setCurrent_arcana(double current_arcana) {
        this.current_arcana = current_arcana;
    }

    public double getCurrent_defense() {
        return current_defense;
    }

    public void setCurrent_defense(double current_defense) {
        this.current_defense = current_defense;
    }

    public double getCurrent_health() {
        return current_health;
    }

    public void setCurrent_health(double current_health) {
        this.current_health = current_health;
    }

    public double getCurrent_luck() {
        return current_luck;
    }

    public void setCurrent_luck(double current_luck) {
        this.current_luck = current_luck;
    }

    public double getCurrent_mana() {
        return current_mana;
    }

    public void setCurrent_mana(double current_mana) {
        this.current_mana = current_mana;
    }

    public double getCurrent_resistance() {
        return current_resistance;
    }

    public void setCurrent_resistance(double current_resistance) {
        this.current_resistance = current_resistance;
    }

    public double getCurrent_speed() {
        return current_speed;
    }

    public void setCurrent_speed(double current_speed) {
        this.current_speed = current_speed;
    }

    public double getCurrent_stamina() {
        return current_stamina;
    }

    public void setCurrent_stamina(double current_stamina) {
        this.current_stamina = current_stamina;
    }

    public double getCurrent_strength() {
        return current_strength;
    }

    public void setCurrent_strength(double current_strength) {
        this.current_strength = current_strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }

    public void setSkillTree(SkillTree skillTree) {
        this.skillTree = skillTree;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentStatPoints() {
        return currentStatPoints;
    }

    public void setCurrentStatPoints(int currentStatPoints) {
        this.currentStatPoints = currentStatPoints;
    }

    public ArrayList<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(ArrayList<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public boolean isCrit() {
        return crit;
    }

    public void setCrit(boolean crit) {
        this.crit = crit;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(double healthRegen) {
        this.healthRegen = healthRegen;
    }

    public double getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(double manaRegen) {
        this.manaRegen = manaRegen;
    }

    public double getStaminaRegen() {
        return staminaRegen;
    }

    public void setStaminaRegen(double staminaRegen) {
        this.staminaRegen = staminaRegen;
    }

}

