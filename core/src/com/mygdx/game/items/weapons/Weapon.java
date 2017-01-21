package com.mygdx.game.items.weapons;

import com.mygdx.game.creatures.Hero;
import com.mygdx.game.items.Gear;
import com.mygdx.game.items.BodyType;

import java.util.Random;

/**
 * Created by louie on 12/24/2016.
 */
public class Weapon extends Gear {

    public Hand hand;

    public double attackBuff;
    public double luckBuff;
    public double speedBuff;
    public double arcanaBuff;

    public Weapon()
    {
        bodyType = BodyType.ARMS;

        Random random = new Random();

        int ranNum = random.nextInt(2);

        if(ranNum == 0)
        {
            hand = Hand.RIGHT;
        }
        else if(ranNum == 1)
        {
            hand = Hand.LEFT;
        }
        System.out.println("The number is: " + ranNum);
    }

    public void equip(Hero hero)
    {
        hero.equip(this);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public double getArcanaBuff() {
        return arcanaBuff;
    }

    public void setArcanaBuff(double arcanaBuff) {
        this.arcanaBuff = arcanaBuff;
    }

    public double getAttackBuff() {
        return attackBuff;
    }

    public void setAttackBuff(double attackBuff) {
        this.attackBuff = attackBuff;
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
}
