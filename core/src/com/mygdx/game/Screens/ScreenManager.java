package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.characterScreens.CharacterCreation;
import com.mygdx.game.Screens.characterScreens.InventoryScreen;
import com.mygdx.game.Screens.characterScreens.StatsScreen;

/**
 * Created by louie on 9/9/2016.
 */
public class ScreenManager {

    MyGdxGame game;
    SpriteBatch batch;

    PlayScreen playScreen;
    MainMenuScreen mainMenu;
    MenuScreen menu;
    CharacterCreation characterCreation;
    StatsScreen statsScreen;
    BattleScreen battleScreen;
    InventoryScreen inventoryScreen;

    public ScreenManager(MyGdxGame game, SpriteBatch batch){

        this.game = game;
        this.batch = batch;

        // Create the Screens for the game.
        playScreen = new PlayScreen(game, batch, this);
        mainMenu = new MainMenuScreen(game, batch, this);
        menu = new MenuScreen(game, batch, this);
        characterCreation = new CharacterCreation(game, batch, this);
        inventoryScreen = new InventoryScreen(game, batch, this);




    }

    public void addBattleScreen(BattleScreen battleScreen){
        this.battleScreen = battleScreen;
    }

    public void addStatsScreen(){
        statsScreen = new StatsScreen(game, batch, this);
    }

    public InventoryScreen getInventoryScreen() {
        return inventoryScreen;
    }

    public void setInventoryScreen(InventoryScreen inventoryScreen) {
        this.inventoryScreen = inventoryScreen;
    }

    public MainMenuScreen getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenuScreen mainMenu) {
        this.mainMenu = mainMenu;
    }

    public MenuScreen getMenu() {
        return menu;
    }

    public void setMenu(MenuScreen menu) {
        this.menu = menu;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public com.mygdx.game.Screens.characterScreens.CharacterCreation getCharacterCreation() {
        return characterCreation;
    }

    public void setCharacterCreation(com.mygdx.game.Screens.characterScreens.CharacterCreation characterCreation) {
        this.characterCreation = characterCreation;
    }

    public StatsScreen getStatsScreen() {
        return statsScreen;
    }

    public void setStatsScreen(StatsScreen statsScreen) {
        this.statsScreen = statsScreen;
    }

    public BattleScreen getBattleScreen() {
        return battleScreen;
    }

    public void setBattleScreen(BattleScreen battleScreen) {
        this.battleScreen = battleScreen;
    }
}
