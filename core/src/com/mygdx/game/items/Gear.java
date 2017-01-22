package com.mygdx.game.items;

/**
 * Created by louie on 12/23/2016.
 */

import com.mygdx.game.creatures.Hero;

/**
 * The Gear class will represent equipment the Hero will use
 * such as armor and weapons.
 */
public class Gear extends Item {

    public boolean equipped;
    public BodyType bodyType;

    public double strengthBuff;
    public double defenseBuff;
    public double luckBuff;
    public double speedBuff;
    public double arcanaBuff;
    public double resistanceBuff;

    public void setHeroBuffs(Hero hero, boolean equipping){

        if(equipping)
        {
            hero.setCurrent_strength(hero.getCurrent_strength() + strengthBuff);
            hero.setCurrent_defense(hero.getCurrent_defense() + defenseBuff);
            hero.setCurrent_luck(hero.getCurrent_luck() + luckBuff);
            hero.setCurrent_speed(hero.getCurrent_speed() + speedBuff);
            hero.setCurrent_arcana(hero.getCurrent_arcana() + arcanaBuff);
            hero.setCurrent_resistance(hero.getCurrent_resistance() + resistanceBuff);
        }
        else{
            hero.setCurrent_strength(hero.getCurrent_strength() - strengthBuff);
            hero.setCurrent_defense(hero.getCurrent_defense() - defenseBuff);
            hero.setCurrent_luck(hero.getCurrent_luck() - luckBuff);
            hero.setCurrent_speed(hero.getCurrent_speed() - speedBuff);
            hero.setCurrent_arcana(hero.getCurrent_arcana() - arcanaBuff);
            hero.setCurrent_resistance(hero.getCurrent_resistance() - resistanceBuff);
        }
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public double getArcanaBuff() {
        return arcanaBuff;
    }

    public void setArcanaBuff(double arcanaBuff) {
        this.arcanaBuff = arcanaBuff;
    }

    public double getStrengthBuff() {
        return strengthBuff;
    }

    public void setStrengthBuff(double strengthBuff) {
        this.strengthBuff = strengthBuff;
    }

    public double getLuckBuff() {
        return luckBuff;
    }

    public void setLuckBuff(double luckBuff) {
        this.luckBuff = luckBuff;
    }

    public double getSpeedBuff() {
        return speedBuff;
    }

    public void setSpeedBuff(double speedBuff) {
        this.speedBuff = speedBuff;
    }

    public double getDefenseBuff() {
        return defenseBuff;
    }

    public void setDefenseBuff(double defenseBuff) {
        this.defenseBuff = defenseBuff;
    }

    public double getResistanceBuff() {
        return resistanceBuff;
    }

    public void setResistanceBuff(double resistanceBuff) {
        this.resistanceBuff = resistanceBuff;
    }
}
