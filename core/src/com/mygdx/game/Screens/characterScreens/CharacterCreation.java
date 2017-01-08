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
import com.mygdx.game.utils.Conversion;

/**
 * Created by louie on 9/10/2016.
 */
public class CharacterCreation implements Screen{

    public Stage stage;
    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Skin skin;
    BitmapFont font;

    Hero hero;

    boolean firstTime;

    // Tables to organize the Character Creation screen.
    Table mainTable;
    Table incrementTable;
    Table attributeTable;
    Table strengthTable;
    Table defenseTable;
    Table arcanaTable;
    Table resistanceTable;
    Table speedTable;
    Table luckTable;

    Label title;
    Label incrementLabel;
    Label attributeLabel;
    Label strength;
    Label defense;
    Label arcana;
    Label resistance;
    Label speed;
    Label luck;

    TextButton done;
    TextButton incrementButton;
    TextButton decrementButton;

    TextButton attributeButton;

    TextButton minStrength;
    TextButton plusStrength;

    TextButton minDefense;
    TextButton plusDefense;

    TextButton minArcana;
    TextButton plusArcana;

    TextButton minResistance;
    TextButton plusResistance;

    TextButton minSpeed;
    TextButton plusSpeed;

    TextButton minLuck;
    TextButton plusLuck;

    String attributeString;

    int startStatPoints;
    int statPoints;
    int str;
    int def;
    int arc;
    int res;
    int spd;
    int lck;

    int firstStr;
    int firstDef;
    int firstArc;
    int firstRes;
    int firstSpd;
    int firstLck;

    int incrementer;        //Incrementor for the stat increases

    public CharacterCreation(MyGdxGame game, SpriteBatch batch, ScreenManager manager){
        this.game = game;
        this.batch = batch;
        this.manager = manager;

        firstTime = true;
        attributeString = "Health";

        font = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));

        incrementer = 5;

        startStatPoints = 40;
        statPoints = startStatPoints;
        str = 0;
        def = 0;
        arc = 0;
        res = 0;
        spd = 0;
        lck = 0;

        firstStr = 0;
        firstDef = 0;
        firstArc = 0;
        firstRes = 0;
        firstSpd = 0;
        firstLck = 0;

        createLabels();
        createTables();
        createButtons();
        addListeners();

        setTableProperties();

        stage.addActor(mainTable);


    }

    public void setAttributePoints(Hero hero, int statPoints){

        firstStr = Conversion.convertDoubleToInt(hero.getBase_strength());
        firstDef = Conversion.convertDoubleToInt(hero.getBase_defense());
        firstArc = Conversion.convertDoubleToInt(hero.getBase_arcana());
        firstRes = Conversion.convertDoubleToInt(hero.getBase_resistance());
        firstSpd = Conversion.convertDoubleToInt(hero.getBase_speed());
        firstLck = Conversion.convertDoubleToInt(hero.getBase_luck());

        str = firstStr;
        def = firstDef;
        arc = firstArc;
        res = firstRes;
        spd = firstSpd;
        lck = firstLck;

        this.statPoints = statPoints;


    }

    private void update(float delta) {

        title.setText("Increase Stats: " + statPoints);
        incrementLabel.setText("Increment: " + incrementer);
        attributeLabel.setText(attributeString);
        strength.setText("Strength: " + str);
        defense.setText("Defense: " + def);
        arcana.setText("Arcana: " + arc);
        resistance.setText("Resistance: " + res);
        speed.setText("Speed: " + spd);
        luck.setText("Luck: " + lck);
    }

    /**
     * Adds listeners to all of the TextButtons
     */
    private void addListeners() {

        done.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints == 0){

                    if(firstTime)
                    {
                        createHero();
                        manager.addStatsScreen();
                        firstTime = false;
                    }
                    improveHero();
                    game.setScreen(manager.getPlayScreen());
                    Gdx.input.setInputProcessor(manager.getPlayScreen().stage);
                }


            }
        });

        attributeButton.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               if(attributeString.equals("Health"))
               {
                   attributeString = "Mana";
               }

               else if(attributeString.equals("Mana"))
               {
                   attributeString = "Stamina";
               }

               else
               {
                   attributeString = "Health";
               }
           }
        });

        decrementButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(incrementer > 5 && incrementer <= 10){
                    incrementer = 5;
                }
                else if(incrementer <= 5){
                    incrementer = 1;
                }

                if(incrementer <= 0){
                    incrementer = 1;
                }
            }
        });

        incrementButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(incrementer < 5){
                    incrementer = 5;
                }

                else if(incrementer >= 5){
                    incrementer = 10;
                }

                if(incrementer >= 10){
                    incrementer = 10;
                }
            }
        });

        minStrength.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && str > firstStr) {

                    if(str <= incrementer)
                    {
                        statPoints = (str - firstStr) + statPoints;
                        str = firstStr;
                    }
                    else
                    {
                        str -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusStrength.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    str += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        str += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });

        minDefense.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && def > firstDef) {
                    if(def <= incrementer)
                    {
                        statPoints = (def - firstDef) + statPoints;
                        def = firstStr;
                    }
                    else
                    {
                        def -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusDefense.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    def += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        def += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });

        minArcana.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && arc > firstArc) {
                    if(arc <= incrementer)
                    {
                        statPoints += (arc - firstArc);
                        arc = firstStr;
                    }
                    else
                    {
                        arc -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusArcana.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    arc += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        arc += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });

        minResistance.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && res > firstRes) {
                    if(res <= incrementer)
                    {
                        statPoints += (res - firstRes);
                        res = firstStr;
                    }
                    else
                    {
                        res -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusResistance.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    res += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        res += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });

        minSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && spd > firstSpd) {
                    if(spd <= incrementer)
                    {
                        statPoints += (spd - firstSpd);
                        spd = firstStr;
                    }
                    else
                    {
                        spd -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    spd += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        spd += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });

        minLuck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints < startStatPoints && lck > firstLck) {
                    if(lck <= incrementer)
                    {
                        statPoints += (lck - firstLck);
                        lck = firstStr;
                    }
                    else
                    {
                        lck -= incrementer;
                        statPoints += incrementer;
                    }
                }
            }
        });

        plusLuck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(statPoints > 0) {
                    lck += incrementer;
                    statPoints -= incrementer;

                    if(statPoints < 0)
                    {
                        lck += statPoints;
                        statPoints = 0;
                    }
                }
            }
        });



    }

    /**
     * Creates the Hero based on what was selected during character creation
     */
    private void createHero() {

        Hero hero = new Hero(1);

        hero.setBase_strength(str);
        hero.setBase_defense(def);
        hero.setBase_arcana(arc);
        hero.setBase_resistance(res);
        hero.setBase_speed(spd);
        hero.setBase_luck(lck);

        hero.setBase_health(10);
        hero.setBase_stamina(10);
        hero.setBase_mana(5);

        hero.update();

        this.hero = hero;

        manager.getPlayScreen().setHero(hero);
        manager.getPlayScreen().start();
        manager.getInventoryScreen().initiateHero(hero);

    }

    private void improveHero(){

        hero.setBase_strength(str);
        hero.setBase_defense(def);
        hero.setBase_arcana(arc);
        hero.setBase_resistance(res);
        hero.setBase_speed(spd);
        hero.setBase_luck(lck);

        if(attributeString.equals("Health"))
        {
            hero.setBase_health(hero.getBase_health() + 3);
            hero.setBase_mana(hero.getBase_mana() + 1);
            hero.setBase_stamina(hero.getBase_stamina() + 1);
        }

        else if(attributeString.equals("Mana"))
        {
            hero.setBase_mana(hero.getBase_mana() + 2);
            hero.setBase_stamina(hero.getBase_stamina() + 1);
            hero.setBase_health(hero.getBase_health() + 1);
        }

        else
        {
            hero.setBase_stamina(hero.getBase_stamina() + 2);
            hero.setBase_mana(hero.getBase_mana() + 1);
            hero.setBase_health(hero.getBase_health() + 1);
        }


        hero.update();
    }

    private void setTableProperties() {

        mainTable.setWidth(stage.getWidth());
        mainTable.align(Align.center|Align.top);
        mainTable.setPosition(0, Gdx.graphics.getHeight());

        incrementTable.setWidth(stage.getWidth());
        incrementTable.align(Align.center|Align.top);
        incrementTable.add(decrementButton);
        incrementTable.add(incrementLabel).padLeft(50);
        incrementTable.add(incrementButton).padLeft(50);

        attributeTable.setWidth(stage.getWidth());
        attributeTable.align(Align.center|Align.top);
        attributeTable.add(attributeButton).padRight(50);
        attributeTable.add(attributeLabel);

        strengthTable.setWidth(stage.getWidth());
        strengthTable.align(Align.center|Align.top);
        strengthTable.add(minStrength);
        strengthTable.add(strength).padLeft(50);
        strengthTable.add(plusStrength).padLeft(50);

        defenseTable.setWidth(stage.getWidth());
        defenseTable.align(Align.center|Align.top);
        defenseTable.add(minDefense);
        defenseTable.add(defense).padLeft(50);
        defenseTable.add(plusDefense).padLeft(50);

        arcanaTable.setWidth(stage.getWidth());
        arcanaTable.align(Align.center|Align.top);
        arcanaTable.add(minArcana);
        arcanaTable.add(arcana).padLeft(50);
        arcanaTable.add(plusArcana).padLeft(50);

        resistanceTable.setWidth(stage.getWidth());
        resistanceTable.align(Align.center|Align.top);
        resistanceTable.add(minResistance);
        resistanceTable.add(resistance).padLeft(50);
        resistanceTable.add(plusResistance).padLeft(50);

        speedTable.setWidth(stage.getWidth());
        speedTable.align(Align.center|Align.top);
        speedTable.add(minSpeed);
        speedTable.add(speed).padLeft(50);
        speedTable.add(plusSpeed).padLeft(50);

        luckTable.setWidth(stage.getWidth());
        luckTable.align(Align.center|Align.top);
        luckTable.add(minLuck);
        luckTable.add(luck).padLeft(50);
        luckTable.add(plusLuck).padLeft(50);


        mainTable.add(title).padTop(25);
        mainTable.add(done);
        mainTable.row();

        mainTable.add(incrementTable).padBottom(10);
        mainTable.row();

        mainTable.add(attributeTable);
        mainTable.row();

        mainTable.add(strengthTable).padTop(25);
        mainTable.row();

        mainTable.add(defenseTable).padTop(25);
        mainTable.row();

        mainTable.add(arcanaTable).padTop(25);
        mainTable.row();

        mainTable.add(resistanceTable).padTop(25);
        mainTable.row();

        mainTable.add(speedTable).padTop(25);
        mainTable.row();

        mainTable.add(luckTable).padTop(25);
        mainTable.row();



    }

    private void createLabels(){

        title = new Label("Increase stats: " + statPoints, new Label.LabelStyle(font, font.getColor()));
        incrementLabel = new Label("Increment: " + incrementer, new Label.LabelStyle(font, font.getColor()));
        attributeLabel = new Label(attributeString, new Label.LabelStyle(font, font.getColor()));
        strength = new Label("Strength: " + str, new Label.LabelStyle(font, font.getColor()));
        defense = new Label("Defense: " + def, new Label.LabelStyle(font, font.getColor()));
        arcana = new Label("Arcana: " + arc, new Label.LabelStyle(font, font.getColor()));
        resistance = new Label("Resistance: " + res, new Label.LabelStyle(font, font.getColor()));
        speed = new Label("Speed: " + spd, new Label.LabelStyle(font, font.getColor()));
        luck = new Label("Luck " + lck, new Label.LabelStyle(font, font.getColor()));
    }

    private void createTables(){
        mainTable = new Table();
        incrementTable = new Table();
        attributeTable = new Table();
        strengthTable = new Table();
        defenseTable = new Table();
        resistanceTable = new Table();
        arcanaTable = new Table();
        speedTable = new Table();
        luckTable = new Table();
    }

    private void createButtons() {
        done = new TextButton("done", skin, "default");
        incrementButton = new TextButton("+", skin, "default");
        decrementButton = new TextButton("-", skin, "default");

        attributeButton = new TextButton("attr", skin, "default");

        minStrength = new TextButton("-", skin, "default");
        plusStrength = new TextButton("+", skin, "default");

        minDefense = new TextButton("-", skin, "default");
        plusDefense = new TextButton("+", skin, "default");

        minArcana = new TextButton("-", skin, "default");
        plusArcana = new TextButton("+", skin, "default");

        minResistance = new TextButton("-", skin, "default");
        plusResistance = new TextButton("+", skin, "default");

        minSpeed = new TextButton("-", skin, "default");
        plusSpeed = new TextButton("+", skin, "default");

        minLuck = new TextButton("-", skin, "default");
        plusLuck = new TextButton("+", skin, "default");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        stage.draw();

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

    public Label getArcana() {
        return arcana;
    }

    public void setArcana(Label arcana) {
        this.arcana = arcana;
    }

    public Table getArcanaTable() {
        return arcanaTable;
    }

    public void setArcanaTable(Table arcanaTable) {
        this.arcanaTable = arcanaTable;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Label getDefense() {
        return defense;
    }

    public void setDefense(Label defense) {
        this.defense = defense;
    }

    public Table getDefenseTable() {
        return defenseTable;
    }

    public void setDefenseTable(Table defenseTable) {
        this.defenseTable = defenseTable;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Label getLuck() {
        return luck;
    }

    public void setLuck(Label luck) {
        this.luck = luck;
    }

    public Table getLuckTable() {
        return luckTable;
    }

    public void setLuckTable(Table luckTable) {
        luckTable = luckTable;
    }

    public Table getMainTable() {
        return mainTable;
    }

    public void setMainTable(Table mainTable) {
        this.mainTable = mainTable;
    }

    public ScreenManager getManager() {
        return manager;
    }

    public void setManager(ScreenManager manager) {
        this.manager = manager;
    }

    public TextButton getMinArcana() {
        return minArcana;
    }

    public void setMinArcana(TextButton minArcana) {
        this.minArcana = minArcana;
    }

    public TextButton getMinDefense() {
        return minDefense;
    }

    public void setMinDefense(TextButton minDefense) {
        this.minDefense = minDefense;
    }

    public TextButton getMinLuck() {
        return minLuck;
    }

    public void setMinLuck(TextButton minLuck) {
        this.minLuck = minLuck;
    }

    public TextButton getMinResistance() {
        return minResistance;
    }

    public void setMinResistance(TextButton minResistance) {
        this.minResistance = minResistance;
    }

    public TextButton getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(TextButton minSpeed) {
        this.minSpeed = minSpeed;
    }

    public TextButton getMinStrength() {
        return minStrength;
    }

    public void setMinStrength(TextButton minStrength) {
        this.minStrength = minStrength;
    }

    public TextButton getPlusArcana() {
        return plusArcana;
    }

    public void setPlusArcana(TextButton plusArcana) {
        this.plusArcana = plusArcana;
    }

    public TextButton getPlusDefense() {
        return plusDefense;
    }

    public void setPlusDefense(TextButton plusDefense) {
        this.plusDefense = plusDefense;
    }

    public TextButton getPlusLuck() {
        return plusLuck;
    }

    public void setPlusLuck(TextButton plusLuck) {
        this.plusLuck = plusLuck;
    }

    public TextButton getPlusResistance() {
        return plusResistance;
    }

    public void setPlusResistance(TextButton plusResistance) {
        this.plusResistance = plusResistance;
    }

    public TextButton getPlusSpeed() {
        return plusSpeed;
    }

    public void setPlusSpeed(TextButton plusSpeed) {
        this.plusSpeed = plusSpeed;
    }

    public TextButton getPlusStrength() {
        return plusStrength;
    }

    public void setPlusStrength(TextButton plusStrength) {
        this.plusStrength = plusStrength;
    }

    public Label getResistance() {
        return resistance;
    }

    public void setResistance(Label resistance) {
        this.resistance = resistance;
    }

    public Table getResistanceTable() {
        return resistanceTable;
    }

    public void setResistanceTable(Table resistanceTable) {
        this.resistanceTable = resistanceTable;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Label getSpeed() {
        return speed;
    }

    public void setSpeed(Label speed) {
        this.speed = speed;
    }

    public Table getSpeedTable() {
        return speedTable;
    }

    public void setSpeedTable(Table speedTable) {
        this.speedTable = speedTable;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getStrength() {
        return strength;
    }

    public void setStrength(Label strength) {
        this.strength = strength;
    }

    public Table getStrengthTable() {
        return strengthTable;
    }

    public void setStrengthTable(Table strengthTable) {
        this.strengthTable = strengthTable;
    }


}
