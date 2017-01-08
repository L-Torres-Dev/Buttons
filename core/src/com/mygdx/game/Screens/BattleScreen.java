package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.Hero;
import com.mygdx.game.skills.Skill;
import com.mygdx.game.story.Battle;
import com.mygdx.game.utils.TextDisplay;

import java.text.DecimalFormat;

/**
 * Created by louie on 9/11/2016.
 */
public class BattleScreen implements Screen{

    private static int ENABLE_ATTACK = Battle.ENABLE_ATTACK;
    private static int DISABLE_ATTACK = Battle.DISABLE_ATTACK;

    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Table table;
    Skin skin;
    public Stage stage;
    BitmapFont redFont;
    BitmapFont font;
    Battle battle;
    DecimalFormat formatter;

    TextDisplay battleDisplay;

    Creature creature;
    Hero hero;

    //Declare Tables
    Table mainTable;
    Table charactersTable;
    Table heroTable;
    Table creatureTable;
    Table displayTable;
    Table buttonTable;

    //Declare Labels
    Label heroStatus;
    Label heroName;
    Label mana;
    Label stamina;

    Label creatureName;
    Label creatureMana;
    Label creatureStamina;

    //Declare Buttons
    TextButton nextButton;
    TextButton skill1Button;
    TextButton skill2Button;
    TextButton skill3Button;
    TextButton skill4Button;

    int turn;                // The number of turns there has been in the battle
    int status;              // Will hold a number that represents the status of the battle screen. Will be used to disable/enable buttons


    String currentString;
    String heroHealth;
    String heroMana;
    String heroStamina;
    String creatureHealth;
    String space;
    String creatureManaString;
    String creatureStaminaString;

    Skill skill1;
    Skill skill2;
    Skill skill3;
    Skill skill4;

    Skill chosenSkill;       // The skill that the hero chose

    public BattleScreen(Hero hero, Creature creature, Game game, SpriteBatch batch, ScreenManager manager){
        this.game = game;
        this.batch = batch;
        this.hero = hero;
        this.creature = creature;
        this.manager = manager;

        formatter = new DecimalFormat("#0");

        redFont = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));
        font = new BitmapFont(Gdx.files.internal("TestFont.fnt"));

        table = new Table();
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));

        battleDisplay = new TextDisplay(font, this, displayTable, 35, 6);

        heroHealth = formatter.format(hero.getCurrent_health()) + " / "  + formatter.format(hero.getBase_health());
        heroMana = formatter.format(hero.getCurrent_mana()) + " / " + formatter.format(hero.getBase_mana());
        heroStamina = formatter.format(hero.getCurrent_stamina()) + " / " + formatter.format(hero.getBase_stamina());
        creatureHealth = formatter.format(creature.getCurrent_health()) + " / " + formatter.format(creature.getBase_health());
        creatureManaString = formatter.format(creature.getCurrent_mana()) + " / " + formatter.format(creature.getBase_mana());
        creatureStaminaString = formatter.format(creature.getCurrent_stamina()) + " / " + formatter.format(creature.getBase_stamina());
        space = "           ";

        initTables();
        initLabels();
        initButtons();

        setStageProperties();
        addListeners();

        turn = 0;


        stage.addActor(mainTable);
        Gdx.input.setInputProcessor(stage);



    }

    public void battleProgress() {

        status = battle.nextProgress();

        if(status == DISABLE_ATTACK){
            disableAttackButtons();
            nextButton.setTouchable(Touchable.enabled);
        }
        else if(status == ENABLE_ATTACK){
            enableAttackButtons();
            nextButton.setTouchable(Touchable.disabled);
        }
    }

    public void startBattle(){
       battle = new Battle(game, manager, battleDisplay, hero, creature);

        // Disable the attack buttons.
        disableAttackButtons();
    }

    private void initTables() {

        mainTable = new Table();
        charactersTable = new Table();
        heroTable = new Table();
        creatureTable = new Table();
        displayTable = new Table();
        buttonTable = new Table();

        displayTable.setDebug(true);
    }

    private void initLabels(){
        heroStatus = new Label("Status:" + hero.getStatus().toString(), new Label.LabelStyle(redFont, redFont.getColor()));
        heroName = new Label("Hero " + heroHealth + space, new Label.LabelStyle(redFont, redFont.getColor()));
        mana = new Label("Mana " + heroMana + space, new Label.LabelStyle(redFont, redFont.getColor()));
        stamina = new Label("Stamina " + heroStamina + space, new Label.LabelStyle(redFont, redFont.getColor()));
        creatureName = new Label(space + creature.getName() + " " + creatureHealth, new Label.LabelStyle(redFont, redFont.getColor()));
        creatureMana = new Label(space + "Mana " + creatureManaString, new Label.LabelStyle(redFont, redFont.getColor()));
        creatureStamina = new Label(space + "Stamina " + creatureStaminaString, new Label.LabelStyle(redFont, redFont.getColor()));

    }

    private void initButtons(){

        skill1Button = new TextButton(hero.getSkillSet().get(0).getName(), skin, "default");
        skill1 = hero.getSkillSet().get(0);

        skill2Button = new TextButton(hero.getSkillSet().get(1).getName(), skin, "default");
        skill2 = hero.getSkillSet().get(1);

        skill3Button = new TextButton(hero.getSkillSet().get(2).getName(), skin, "default");
        skill3 = hero.getSkillSet().get(2);

        skill4Button = new TextButton(hero.getSkillSet().get(3).getName(), skin, "default");
        skill4 = hero.getSkillSet().get(3);

        nextButton = new TextButton("Next", skin, "default");


    }

    private void setStageProperties(){

        mainTable.setWidth(stage.getWidth());
        mainTable.align(Align.center|Align.top);
        mainTable.setPosition(0, Gdx.graphics.getHeight());

        heroTable.align(Align.left|Align.top);
        heroTable.setWidth(stage.getWidth() / 2);
        heroTable.add(heroName);
        heroTable.row();
        heroTable.add(mana);
        heroTable.add(heroStatus);
        heroTable.row();
        heroTable.add(stamina);

        creatureTable.align(Align.right|Align.top);
        creatureTable.setWidth(stage.getWidth() / 2);
        creatureTable.add(creatureName);
        creatureTable.row();
        creatureTable.add(creatureMana);
        creatureTable.row();
        creatureTable.add(creatureStamina);

        charactersTable.setWidth(stage.getWidth());
        charactersTable.align(Align.center|Align.top);
        charactersTable.add(heroTable);
        charactersTable.add(creatureTable);

        displayTable.setWidth(stage.getWidth());
        displayTable.align(Align.center|Align.top);
        battleDisplay.setPosition(displayTable.getX(), displayTable.getY());
        displayTable.add(battleDisplay);


        buttonTable.setWidth(stage.getWidth());
        buttonTable.align(Align.center|Align.top);
        buttonTable.add(nextButton).padBottom(25);
        buttonTable.row();
        buttonTable.add(skill1Button);
        buttonTable.add(skill2Button).padLeft(25);
        buttonTable.add(skill3Button).padLeft(25);
        buttonTable.add(skill4Button).padLeft(25);

        mainTable.add(charactersTable);
        mainTable.row();
        mainTable.add(displayTable).padTop(100);
        mainTable.row();
        mainTable.add(buttonTable).padTop(200);

    }

    private void addListeners(){

        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                battleProgress();
            }
        });

        skill1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                chosenSkill = skill1;
                battle.setChosenSkill(chosenSkill);
                battleProgress();
            }
        });

        skill2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                chosenSkill = skill2;
                battle.setChosenSkill(chosenSkill);
                battleProgress();
            }
        });

        skill3Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                chosenSkill = skill3;
                battle.setChosenSkill(chosenSkill);
                battleProgress();
            }
        });

        skill4Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                chosenSkill = skill4;
                battle.setChosenSkill(chosenSkill);
                battleProgress();
            }
        });

    }

    private void enableAttackButtons() {
        skill1Button.setTouchable(Touchable.enabled);
        skill2Button.setTouchable(Touchable.enabled);
        skill3Button.setTouchable(Touchable.enabled);
        skill4Button.setTouchable(Touchable.enabled);
    }

    private void disableAttackButtons() {
        skill1Button.setTouchable(Touchable.disabled);
        skill2Button.setTouchable(Touchable.disabled);
        skill3Button.setTouchable(Touchable.disabled);
        skill4Button.setTouchable(Touchable.disabled);
    }

    private void update(float delta){
        battleDisplay.update(delta);
        heroHealth = formatter.format(hero.getCurrent_health()) + " / "  +formatter.format(hero.getBase_health());
        heroMana = formatter.format(hero.getCurrent_mana()) + " / " + formatter.format(hero.getBase_mana());
        heroStamina = formatter.format(hero.getCurrent_stamina()) + " / " + formatter.format(hero.getBase_stamina());
        creatureHealth = formatter.format(creature.getCurrent_health()) + " / " + formatter.format(creature.getBase_health());
        creatureManaString = formatter.format(creature.getCurrent_mana()) + " / " + formatter.format(creature.getBase_mana());
        creatureStaminaString = formatter.format(creature.getCurrent_stamina()) + " / " + formatter.format(creature.getBase_stamina());
        space = "           ";

        heroStatus.setText("Status:" + hero.getStatus().toString());
        heroName.setText("Hero " + heroHealth + space);
        mana.setText("Mana " + heroMana + space);
        stamina.setText("Stamina " + heroStamina + space);
        creatureName.setText(space + creature.getName() + " " + creatureHealth);
        creatureStamina.setText(space + "Stamina " + creatureStaminaString);
        creatureMana.setText(space + "Mana " + creatureManaString);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        update(delta);

        batch.begin();
        batch.end();
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

    public Skill getChosenSkill() {
        return chosenSkill;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}
