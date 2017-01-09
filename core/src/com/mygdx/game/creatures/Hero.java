package com.mygdx.game.creatures;

import com.mygdx.game.items.BodyType;
import com.mygdx.game.items.Gear;
import com.mygdx.game.items.Inventory;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.items.HealingPotion;
import com.mygdx.game.items.items.MegaPotion;
import com.mygdx.game.items.weapons.Armor;
import com.mygdx.game.items.weapons.Hand;
import com.mygdx.game.items.weapons.Weapon;
import com.mygdx.game.skills.MagicalSkill;
import com.mygdx.game.skills.PhysicalSkill;
import com.mygdx.game.skills.Skill;
import com.mygdx.game.skills.SkillTree;
import com.mygdx.game.skills.SkillType;
import com.mygdx.game.skills.specificSkills.Heal;
import com.mygdx.game.skills.specificSkills.Strike;
import com.mygdx.game.utils.Journal;

import java.util.ArrayList;

/**
 * Created by louie on 9/10/2016.
 */
public class Hero extends Character{

    Journal journal;
    Inventory inventory;

    Torso torso;
    RightArm rightArm;
    LeftArm leftArm;
    Legs legs;
    Feet feet;

    int exp;                    // Holds how much experience points the hero has.
    int nextLevelExp;           // Holds how much experience points the hero need to level up.
    int gold;

    public Hero(int lvl){
        level = lvl;
        inventory = new Inventory();
        skillTree = new SkillTree();
        skillSet = new ArrayList<Skill>(4);
        exp = 0;
        status = new Status();
        name = "Hero";

        torso = new Torso();
        rightArm = new RightArm();
        leftArm = new LeftArm();
        legs = new Legs();
        feet = new Feet();

        addItem(new HealingPotion());
        addItem(new HealingPotion());
        addItem(new HealingPotion());
        addItem(new MegaPotion());
        addItem(new HealingPotion());
        addItem(new HealingPotion());
        initializeInventory();

        gold = 10;
        createSkills();
        setRegens();

    }

    public void initializeInventory()
    {
        for(int i = inventory.getItems().size() - 1; i < Inventory.maxItems - 1; i++)
        {
            addItem(new Item());
        }
    }

    private void createSkills() {

        PhysicalSkill strike = new Strike(this);
        skillTree.add(strike);

        if(strike.getReqLvl() <= this.level){
            skillSet.add(strike);
        }

        MagicalSkill heal = new Heal(this);
        skillTree.add(heal);


        if(strike.getReqLvl() <= this.level){
            skillSet.add(heal);
        }

        if(skillSet.size() < 4){
            for(int i = skillSet.size() - 1; i < 3; i++){
                skillSet.add(i, new Skill());
                skillSet.get(i).setName("NONE" + i);
            }
        }


    }




    public void attack(Skill skill, Character enemy){

        skill.use(this, enemy);

    }

    /**
     * This update() method is called after the Hero object has leveled up
     */
    public void update() {

        super.update();
        int nextLvl = (level * level) +  (level * level / 3) +(6 + level);
        exp = 0;

        setNextLevelExp(nextLvl);


    }
    public void equip(Weapon gear)
{

        switch (gear.getHand())
        {
            case RIGHT:
                rightArm.unequip();
                rightArm.setItem(gear);
                break;

            case LEFT:
                leftArm.unequip();
                leftArm.setItem(gear);
                break;

            default:
                break;
        }
        gear.setEquipped(true);
    }

    public void equip(Armor gear)
    {

    }

    public boolean addItem(Item item)
    {
        boolean inventoryFull = inventory.checkInventory();
        boolean addedItemSuccess = false;
        if(!inventoryFull)
        {
            inventory.add(item);
            addedItemSuccess = true;
        }

        return addedItemSuccess;
    }


    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public Feet getFeet() {
        return feet;
    }

    public void setFeet(Feet feet) {
        this.feet = feet;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LeftArm getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(LeftArm leftArm) {
        this.leftArm = leftArm;
    }

    public Legs getLegs() {
        return legs;
    }

    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    public RightArm getRightArm() {
        return rightArm;
    }

    public void setRightArm(RightArm rightArm) {
        this.rightArm = rightArm;
    }

    public Torso getTorso() {
        return torso;
    }

    public void setTorso(Torso torso) {
        this.torso = torso;
    }

    private class Body{

        Gear item;

        public void unequip()
        {
            inventory.add(item);
            item.setEquipped(false);
        }

        public Gear getItem() {
            return item;
        }

        public void setItem(Gear item) {
            this.item = item;
        }
    }

    private class RightArm extends Body{

        Hand hand = Hand.RIGHT;

    }

    private class LeftArm extends Body{

        Hand hand = Hand.RIGHT;
    }

    private class Legs extends Body{

        Gear pants;
    }

    private class Feet extends Body{

        Gear shoes;
    }

    private class Torso extends Body{

        Gear shirt;
    }

}
