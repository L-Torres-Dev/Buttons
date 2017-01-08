package com.mygdx.game.story;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.BattleScreen;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;
import com.mygdx.game.creatures.Rat;
import com.mygdx.game.items.weapons.weaponList.RustyIronDagger;
import com.mygdx.game.utils.TextDisplay;

import java.util.ArrayList;

/**
 * Created by louie on 9/11/2016.
 */
public class Beginning extends Story {

    Game game;
    SpriteBatch batch;

    ArrayList<String> introStrings;
    ArrayList<String> strings2;

    Hero hero;

    Beginning(ScreenManager manager, TextDisplay display, Hero hero, Game game, SpriteBatch batch){
        super(display, manager);
        this.game = game;
        this.batch = batch;
        this.hero = hero;

        new RustyIronDagger();


        initStrings();
        progress();
    }

    private void initStrings(){
        introStrings = new ArrayList<String>();
        introStrings.add("You wake up in a dark cave and wonder where you are.");
        introStrings.add("As you gain full consciousness, You realize you are in a dark cave");
        introStrings.add("Suddenly, you are attack by something!!");
        introStrings.add("You have finished!");
        introStrings.add("Great Job!");

        strings2 = new ArrayList<String>();
        strings2.add("You successfully defeated the Rat! Good job!");


    }

    public void continuePrgress(){

    }

    public void progress(){

        switch (progress){
            case 1:
                switch (innerProgress){
                    case 0:
                        display.addText(introStrings.get(innerProgress));
                        System.out.println("CURRENT PROGRESS: " + innerProgress);
                        innerProgress++;
                        break;
                    case 1:
                        display.addText(introStrings.get(innerProgress));
                        System.out.println("CURRENT PROGRESS: " + innerProgress);
                        innerProgress++;
                        break;
                    case 2:
                        display.addText(introStrings.get(innerProgress));
                        System.out.println("CURRENT PROGRESS: " + innerProgress);
                        innerProgress++;
                        break;
                    case 3:
                        BattleScreen battleScreen = new BattleScreen(hero, firstCreature(), game, batch, manager);
                        game.setScreen(battleScreen);
                        battleScreen.startBattle();
                        manager.addBattleScreen(battleScreen);
                        progress++;
                        innerProgress = 0;
                        break;
                    default:
                        display.addText(introStrings.get(innerProgress));
                        progress++;
                }
            case 2:
                switch (innerProgress){
                    case 0:
                        display.addText(strings2.get(innerProgress));
                        break;
                }

        }
    }

    private Creature firstCreature() {

        Rat rat = new Rat(1);
        return rat;
    }
}
