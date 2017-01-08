package com.mygdx.game.story;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.creatures.Character;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;
import com.mygdx.game.skills.Skill;
import com.mygdx.game.skills.effects.Effect;
import com.mygdx.game.utils.TextDisplay;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by louie on 9/16/2016.
 */
public class Battle extends Story {
    public static int ENABLE_ATTACK = 100;
    public static int DISABLE_ATTACK = 200;

    private static final int MAKE_DECISION = -1;
    private static final int CHOOSE_FIRST_CHARACTER = -2;
    private static final int HERO_FIRST = -3;
    private static final int CREATURE_FIRST = -4;
    private static final int HERO_DIED = -5;
    private static final int CREATURE_DIED = -6;
    private static final int END_BATTLE = -7;
    private static final int END_GAME1 = -8;
    private static final int END_GAME2 = -9;
    private static final int HERO_CRIT = -10;
    private static final int CREATURE_CRIT = -11;
    private static final int NOT_ENOUGH = -12;

    private static final int HERO_MISSED = 9;
    private static final int CREATURE_MISSED = 10;

    private static final int string1 = 0;
    private static final int string2 = 1;
    private static final int string3 = 2;
    private static final int string4 = 3;
    private static final int string5 = 4;
    private static final int string6 = 5;
    private static final int string7 = 6;
    private static final int HERO_DAMAGE_TAKEN = 7;
    private static final int CREATURE_DAMAGE_TAKEN = 8;
    private static final int CRITICAL_HIT = 11;
    private static final int NOT_ENOUGH_STRING = 12;



    Game game;
    TextDisplay display;
    Hero hero;
    Creature creature;

    ArrayList<String> battleStrings;
    DecimalFormat formatter;

    boolean bothAttack;
    boolean heroAttack;
    boolean creatureAttack;
    boolean death;              //Holds a boolean that is only true when someone dies.

    boolean madeDecision;       //Holds a boolean that is only true after the hero made his/her decision.

    double initialHealth;       //Holds the initial health of the character to calculate total dmg
    double finalHealth;         //Holds the final health of the character to calculate total dmg
    double dmg;                 //Holds the final dmg to display the dmg taken by the character in the display

    int turns;               // How many turns have there been in the battle


    Skill chosenSkill;
    public Battle(Game game, ScreenManager manager, TextDisplay display, Hero hero, Creature creature) {
        super(display, manager);

        this.game = game;
        this.display = display;
        this.hero = hero;
        this.creature = creature;

        death = false;

        battleStrings = new ArrayList<String>();
        chosenSkill = hero.getSkillSet().get(0);

        progress = MAKE_DECISION;

        heroAttack = false;
        creatureAttack = false;
        bothAttack = heroAttack && creatureAttack;

        formatter = new DecimalFormat("#0");
        dmg = 0;


        initString();

        display.addText(battleStrings.get(string1));
    }

    /**
     * sets the character's health to 0 if it's health is below 0
     * or sets the character's health to 1 if it's health is between 0
     * and 1 exclusive
     * @param creature the character's whose health will be changed.
     */
    private void setHealthToZero(Character creature)
    {
        if(creature.getCurrent_health() <= 0)
        {
            creature.setCurrent_health(0);
        }

        if(creature.getCurrent_health() < 1 && creature.getCurrent_health() > 0)
        {
            creature.setCurrent_health(1);
        }
    }

    public int nextProgress(){
        int num = 0;

        initString();
        switch (progress) {
            case MAKE_DECISION:
                if(madeDecision){
                    hero.recoverAttributes();
                    creature.recoverAttributes();
                    for(Effect effect : hero.getStatus().getEffects()){
                        effect.use(effect.getSkill());
                        System.out.println("Effect in use");
                    }
                    for(Effect effect : creature.getStatus().getEffects()){
                        effect.use(effect.getSkill());
                    }
                }

                setHealthToZero(hero);
                setHealthToZero(creature);

                initString();
                display.addText(battleStrings.get(string2));
                num = ENABLE_ATTACK;
                heroAttack = false;
                creatureAttack = false;
                progress = CHOOSE_FIRST_CHARACTER;

                if(creature.getCurrent_health() <= 0)
                {
                    progress = CREATURE_DIED;
                }
                if(hero.getCurrent_health() <= 0)
                {
                    progress = HERO_DIED;
                    manager.getBattleScreen().battleProgress();
                }
                break;

            case CHOOSE_FIRST_CHARACTER: //Choose which Character Object attacks first.
                //if the Hero goes first
                if(chosenSkill.checkCosts()) {
                    madeDecision = true;
                    if (hero.getCurrent_speed() >= creature.getCurrent_speed()) {
                        display.addText(battleStrings.get(string3));
                        initialHealth = creature.getCurrent_health();
                        hero.attack(chosenSkill, creature);
                        setHealthToZero(creature);
                        finalHealth = creature.getCurrent_health();
                        dmg = finalHealth - initialHealth;
                        initString();
                        num = DISABLE_ATTACK;
                        heroAttack = true;
                        progress = CREATURE_DIED;

                        if (!hero.isHit()) {
                            progress = HERO_MISSED;
                            dmg = 0;
                        }
                        if (hero.isCrit()) {
                            progress = HERO_CRIT;
                            dmg = dmg * 1.5;
                        }
                        hero.resetHitAndCrit();
                        break;
                    }
                    //if the Creature goes first
                    else {
                        initialHealth = hero.getCurrent_health();
                        creature.attack(hero);
                        setHealthToZero(hero);
                        initString();
                        display.addText(battleStrings.get(string4));
                        finalHealth = hero.getCurrent_health();
                        dmg = finalHealth - initialHealth;
                        initString();
                        num = DISABLE_ATTACK;
                        creatureAttack = true;
                        progress = HERO_DIED;

                        if (!creature.isHit()) {
                            progress = CREATURE_MISSED;
                            dmg = 0;
                        }
                        if (creature.isCrit()) {
                            progress = CREATURE_CRIT;
                            dmg = dmg * 1.5;
                        }
                        creature.resetHitAndCrit();
                        break;
                    }
                }
                else {
                    madeDecision = false;
                    num = DISABLE_ATTACK;
                    display.addText(battleStrings.get(NOT_ENOUGH_STRING));
                    progress = MAKE_DECISION;
                    break;
                }

            case HERO_FIRST: //This is done if the hero went first. Now the creature gets to attack
                initialHealth = hero.getCurrent_health();
                creature.attack(hero);
                setHealthToZero(hero);
                initString();
                display.addText(battleStrings.get(string4));
                finalHealth = hero.getCurrent_health();
                dmg = finalHealth - initialHealth;
                initString();
                num = DISABLE_ATTACK;
                creatureAttack = true;
                progress = HERO_DIED;

                if(!creature.isHit()){
                    progress = CREATURE_MISSED;
                    dmg = 0;
                }
                if(creature.isCrit()){
                    progress = CREATURE_CRIT;
                    dmg = dmg * 1.5;
                }
                creature.resetHitAndCrit();
                break;

            case CREATURE_FIRST: //This is done if the creature went first. Now the hero gets to attack
                if(chosenSkill.checkCosts()) {
                    madeDecision = true;
                    initialHealth = creature.getCurrent_health();
                    hero.attack(chosenSkill, creature);
                    setHealthToZero(creature);
                    display.addText(battleStrings.get(string3));
                    finalHealth = creature.getCurrent_health();
                    dmg = finalHealth - initialHealth;
                    initString();
                    num = DISABLE_ATTACK;
                    heroAttack = true;
                    progress = CREATURE_DIED;

                    if (!hero.isHit()) {
                        progress = HERO_MISSED;
                        dmg = 0;
                    }
                    if (hero.isCrit()) {
                        progress = HERO_CRIT;
                        dmg = dmg * 1.5;
                    }
                    hero.resetHitAndCrit();
                }
                else {
                    madeDecision = false;
                    num = DISABLE_ATTACK;
                    display.addText(battleStrings.get(NOT_ENOUGH_STRING));
                    progress = MAKE_DECISION;
                }
                break;

            case HERO_DIED: //Checks to see if the hero's health is at 0
                if(hero.getCurrent_health() <= 0){
                    display.addText(battleStrings.get(string6));
                    num = DISABLE_ATTACK;
                    progress = END_GAME1;
                }
                else{
                    display.addText(battleStrings.get(HERO_DAMAGE_TAKEN));
                    num = DISABLE_ATTACK;
                    updateAttackSequence();
                    if(bothAttack){
                        progress = MAKE_DECISION;
                    }
                    else{
                        progress = CREATURE_FIRST;
                    }
                }

                break;

            case CREATURE_DIED: //Checks to see if the creatures' health is at 0
                if(creature.getCurrent_health() <= 0){
                    display.addText(battleStrings.get(string5));
                    num = DISABLE_ATTACK;
                    progress = END_BATTLE;
                }
                else{
                    display.addText(battleStrings.get(CREATURE_DAMAGE_TAKEN));
                    num = DISABLE_ATTACK;
                    updateAttackSequence();
                    if(bothAttack){
                        progress = MAKE_DECISION;
                    }
                    else{
                        progress = HERO_FIRST;
                    }
                }
                break;

            case HERO_MISSED:
                display.addText(battleStrings.get(HERO_MISSED));
                progress = CREATURE_DIED;
                break;

            case CREATURE_MISSED:
                display.addText(battleStrings.get(CREATURE_MISSED));
                progress = HERO_DIED;
                break;

            case HERO_CRIT:
                display.addText(battleStrings.get(CRITICAL_HIT));
                progress = CREATURE_DIED;
                break;

            case CREATURE_CRIT:
                display.addText(battleStrings.get(CRITICAL_HIT));
                progress = HERO_DIED;
                break;

            case END_BATTLE: //Ends the battle and brings the game back to the playScreen
                boolean levelUp = rewardHero();

                manager.getPlayScreen().getStoryManager().getCurrentStory().progress();
                if(levelUp)
                {
                    manager.getCharacterCreation().setAttributePoints(hero, 5);
                    Gdx.input.setInputProcessor(manager.getCharacterCreation().stage);
                    game.setScreen(manager.getCharacterCreation());
                }
                else
                {
                    Gdx.input.setInputProcessor(manager.getPlayScreen().stage);
                    game.setScreen(manager.getPlayScreen());
                }
                break;

            case END_GAME1: //Prepares to end the game for when the hero dies
                display.addText(battleStrings.get(string7));
                num = DISABLE_ATTACK;
                progress = END_GAME2;
                break;

            case END_GAME2: //Ends the game.
                Gdx.app.exit();
                break;


        }

        death = hero.getCurrent_health() <= 0 || creature.getCurrent_health() <= 0;

        if(death)
        {
            num = DISABLE_ATTACK;
        }
        return num;
    }

    /**
     * The rewardHero() method rewards the Hero Object
     * for the defeating the creature, the rewards include
     * Experience points and gold.
     */
    private boolean rewardHero(){
        boolean heroLeveledUp = false;
        hero.setExp(hero.getExp() + creature.getExpGive());

        if(hero.getExp() >= hero.getNextLevelExp()){
            hero.setLevel(hero.getLevel() + 1);
            hero.update();
            heroLeveledUp = true;
        }

        hero.setGold(hero.getGold() + creature.getGoldAmount());

        return heroLeveledUp;
    }

    private void updateAttackSequence(){
        bothAttack = heroAttack && creatureAttack;
    }

    private void initString() {

        String dmgString = formatter.format(Math.abs(dmg));

        String s1 = "a " + creature.getName() + " is attacking!";
        String s2 = "What will you do?";
        String s3 = "You used " + chosenSkill.getName();
        String s4 = "The " + creature.getName() + " used " + creature.getChosenSkill().getName();
        String s5 = "You have won!";
        String s6 = "You have lost!";
        String s7 = "GAME OVER";
        String s8 = "You have taken " + dmgString + " damage!";
        String s9 = "The enemy has taken! " + dmgString + " damage!";
        String s10 = "You have missed!";
        String s11 = "The " + creature.getName() + " has missed!";
        String s12 = "IT'S A CRITICAL HIT!";
        String s13 = "You don't have enough stamina or mana!";

        battleStrings.add(string1, s1);
        battleStrings.add(string2, s2);
        battleStrings.add(string3, s3);
        battleStrings.add(string4, s4);
        battleStrings.add(string5, s5);
        battleStrings.add(string6, s6);
        battleStrings.add(string7, s7);
        battleStrings.add(HERO_DAMAGE_TAKEN, s8);
        battleStrings.add(CREATURE_DAMAGE_TAKEN, s9);
        battleStrings.add(HERO_MISSED, s10);
        battleStrings.add(CREATURE_MISSED, s11);
        battleStrings.add(CRITICAL_HIT, s12);
        battleStrings.add(NOT_ENOUGH_STRING, s13);


    }

    private void aftermath(){

        hero.setNextLevelExp(hero.getNextLevelExp() - creature.getExpGive());
    }

    public Skill getChosenSkill() {
        return chosenSkill;
    }

    public void setChosenSkill(Skill chosenSkill) {
        this.chosenSkill = chosenSkill;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}
