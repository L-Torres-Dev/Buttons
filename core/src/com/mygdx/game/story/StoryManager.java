package com.mygdx.game.story;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.creatures.Hero;
import com.mygdx.game.utils.TextDisplay;

/**
 * Created by louie on 9/11/2016.
 */
public class StoryManager {

    Game game;
    SpriteBatch batch;
    TextDisplay display;

    Story currentStory;
    /*public StoryManager(TextDisplay display, Hero hero, Game game, SpriteBatch batch){
        this.display = display;
        this.game = game;
        this.batch = batch;

        Beginning beginning = new Beginning(display, hero, game, batch);
        currentStory = beginning;
    }*/

    public StoryManager(ScreenManager manager, TextDisplay display, Hero hero, Game game, SpriteBatch batch){
        this.display = display;
        this.game = game;
        this.batch = batch;

        Beginning beginning = new Beginning(manager, display, hero, game, batch);
        currentStory = beginning;
    }

    public Story getCurrentStory() {
        return currentStory;
    }

    public void setCurrentStory(Story currentStory) {
        this.currentStory = currentStory;
    }
}
