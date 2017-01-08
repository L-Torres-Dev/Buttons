package com.mygdx.game.items;

/**
 * Created by louie on 12/23/2016.
 */

/**
 * The Gear class will represent equipment the Hero will use
 * such as armor and weapons.
 */
public class Gear extends Item {

    public boolean equipped;
    public BodyType bodyType;


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
}
