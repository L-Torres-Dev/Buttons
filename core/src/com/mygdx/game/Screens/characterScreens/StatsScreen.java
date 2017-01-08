package com.mygdx.game.Screens.characterScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.creatures.Hero;

import java.text.DecimalFormat;

/**
 * Created by louie on 9/11/2016.
 */
public class StatsScreen implements Screen{

    Hero hero;
    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Skin skin;
    public Stage stage;
    BitmapFont font;

    DecimalFormat format;

    //Declare necessary Labels
    Label strength;
    Label defense;
    Label arcana;
    Label resistance;
    Label speed;
    Label luck;

    Label lvl;
    Label health;
    Label stamina;
    Label mana;
    Label status;
    Label exp;

    Label gold;

    //Strings for the Label Objects
    String strengthString;
    String defenseString;
    String arcanaString;
    String resistanceString;
    String speedString;
    String luckString;

    String levelString;

    String healthString;
    String staminaString;
    String manaString;

    String expString;

    String goldString;


    //Declare necessary Tables
    Table mainTable;
    Table statsAttributesTable;
    Table miscButtonsTable;
    Table statsTable;
    Table attributeTable;
    Table miscTable;
    Table buttonsTable;

    //Declare necessary Buttons
    TextButton inventoryButton;
    TextButton skillTreeButton;
    TextButton journalButton;
    TextButton backButton;

    public StatsScreen(MyGdxGame game, SpriteBatch batch, ScreenManager manager){
        this.game = game;
        this.batch = batch;
        this.manager = manager;

        font = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));
        hero = manager.getPlayScreen().getHero();
        format = new DecimalFormat("#0");

        initString();
        initTables();
        initButtons();
        initLabels();

        addListeners();

        setStageProperties();

        stage.addActor(mainTable);
    }

    private void addListeners() {

        inventoryButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getInventoryScreen());
                Gdx.input.setInputProcessor(manager.getInventoryScreen().getStage());
            }

        });

        skillTreeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

            }

        });

        journalButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

            }

        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getMenu());
                Gdx.input.setInputProcessor(manager.getMenu().stage);
            }

        });
    }

    private void setStageProperties() {

        mainTable.setWidth(stage.getWidth());
        mainTable.align(Align.center|Align.top);
        mainTable.setPosition(0, Gdx.graphics.getHeight());

        statsAttributesTable.setWidth(stage.getWidth());
        statsAttributesTable.align(Align.center|Align.top);

        miscButtonsTable.setWidth(stage.getWidth());
        miscButtonsTable.align(Align.center|Align.top);

        //Set statsTable properties and add actors
        statsTable.setWidth(stage.getWidth() / 2);
        statsTable.align(Align.center|Align.top);
        statsTable.add(strength);
        statsTable.row();
        statsTable.add(defense);
        statsTable.row();
        statsTable.add(arcana);
        statsTable.row();
        statsTable.add(resistance);
        statsTable.row();
        statsTable.add(speed);
        statsTable.row();
        statsTable.add(luck);

        //Set attributesTable properties and add actors
        attributeTable.setWidth(stage.getWidth() / 2);
        attributeTable.align(Align.center|Align.top);
        attributeTable.add(lvl);
        attributeTable.row();
        attributeTable.add(health);
        attributeTable.row();
        attributeTable.add(mana);
        attributeTable.row();
        attributeTable.add(stamina);
        attributeTable.row();
        attributeTable.add(status);
        attributeTable.row();
        attributeTable.add(exp);

        //Set miscTable properties and add actors
        miscTable.setWidth(stage.getWidth());
        attributeTable.align(Align.center|Align.top);
        miscTable.add(gold);

        //Set buttonsTable properties and add actors
        buttonsTable.setWidth(stage.getWidth());
        buttonsTable.align(Align.center|Align.top);
        buttonsTable.add(inventoryButton);
        buttonsTable.add(skillTreeButton).padLeft(20);
        buttonsTable.add(journalButton).padLeft(20);
        buttonsTable.add(backButton).padLeft(20);

        //Add actors to the Main table
        statsAttributesTable.add(statsTable).padRight(10);
        statsAttributesTable.add(attributeTable).padLeft(100);

        miscButtonsTable.add(miscTable);
        miscButtonsTable.row();
        miscButtonsTable.add(buttonsTable);

        mainTable.add(statsAttributesTable);
        mainTable.row();
        mainTable.add(miscButtonsTable).padTop(25);

    }

    private void initString() {

        //Strings
        strengthString = "Strength: " + format.format(hero.getCurrent_strength()) + " / " + format.format(hero.getBase_strength());
        defenseString = "Defense: " + format.format(hero.getCurrent_defense()) + " / " + format.format(hero.getBase_defense());
        arcanaString = "Arcana: " + format.format(hero.getCurrent_arcana()) + " / " + format.format(hero.getBase_arcana());
        resistanceString = "Resistance: " + format.format(hero.getCurrent_resistance()) + " / " + format.format(hero.getBase_resistance());
        speedString = "Speed: " + format.format(hero.getCurrent_speed()) + " / " + format.format(hero.getBase_speed());
        luckString = "Luck: " + format.format(hero.getCurrent_luck()) + " / " + format.format(hero.getBase_luck());

        levelString = "Level: " + hero.getLevel();

        healthString = "Health: " + format.format(hero.getCurrent_health()) + " / " + format.format(hero.getBase_health());
        staminaString = "Stamina: " + format.format(hero.getCurrent_stamina()) + " / " + format.format(hero.getBase_stamina());
        manaString = "Mana: " + format.format(hero.getCurrent_mana()) + " / " + format.format(hero.getBase_mana());
        expString = "Exp: " + hero.getExp() + " / " + hero.getNextLevelExp();
        goldString = "Gold: " + hero.getGold();

    }

    private void initLabels() {

        // Stat Labels
        strength = new Label(strengthString, new Label.LabelStyle(font, font.getColor()));
        defense = new Label(defenseString, new Label.LabelStyle(font, font.getColor()));
        arcana = new Label(arcanaString, new Label.LabelStyle(font, font.getColor()));
        resistance = new Label(resistanceString, new Label.LabelStyle(font, font.getColor()));
        speed = new Label(speedString, new Label.LabelStyle(font, font.getColor()));
        luck = new Label(luckString, new Label.LabelStyle(font, font.getColor()));

        // Attribute Labels
        lvl = new Label(levelString, new Label.LabelStyle(font, font.getColor()));
        health = new Label(healthString, new Label.LabelStyle(font, font.getColor()));
        stamina = new Label(staminaString, new Label.LabelStyle(font, font.getColor()));
        mana = new Label(manaString, new Label.LabelStyle(font, font.getColor()));

        // Other labels
        status = new Label("Status:" + hero.getStatus().toString(), new Label.LabelStyle(font, font.getColor()));
        exp = new Label(expString, new Label.LabelStyle(font, font.getColor()));
        gold = new Label(goldString, new Label.LabelStyle(font, font.getColor()));
    }

    private void initButtons() {

        inventoryButton = new TextButton("Inventory", skin, "default");
        skillTreeButton = new TextButton("Skills", skin, "default");;
        journalButton = new TextButton("Journal", skin, "default");
        backButton = new TextButton("Back", skin, "default");;
    }

    private void initTables() {

        mainTable = new Table();
        statsAttributesTable = new Table();
        miscButtonsTable = new Table();
        statsTable = new Table();
        attributeTable = new Table();
        miscTable = new Table();
        buttonsTable = new Table();
    }

    public void update(){
        initString();

        strength.setText(strengthString);
        defense.setText(defenseString);
        arcana.setText(arcanaString);
        resistance.setText(resistanceString);
        speed.setText(speedString);
        luck.setText(luckString);
        lvl.setText(levelString);
        health.setText(healthString);
        stamina.setText(staminaString);
        mana.setText(manaString);
        exp.setText(expString);
        gold.setText(goldString);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        update();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
