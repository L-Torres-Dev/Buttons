package com.mygdx.game.Screens.characterScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.items.Consumable;
import com.mygdx.game.items.Inventory;
import com.mygdx.game.items.Item;

import java.util.ArrayList;

/**
 * Created by louie on 1/3/2017.
 */
public class InventoryScreen implements Screen {

    Hero hero;
    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Skin skin;
    public Stage stage;
    BitmapFont font;

    Table mainTable;
    Table itemsTable;
    Table interfaceTable;
    Table buttonTable;

    Label items;        // Just a label to indicate where the itemList is
    Label torso;
    Label rightArm;
    Label leftArm;
    Label legs;
    Label feet;

    MyLabel myTorso;
    MyLabel myRightArm;
    MyLabel myLeftArm;
    MyLabel myLegs;
    MyLabel myFeet;

    Item selectedItem;
    MyLabel selectedLabel;
    MyLabel item1;
    MyLabel item2;
    MyLabel item3;
    MyLabel item4;
    MyLabel item5;
    MyLabel item6;
    MyLabel item7;
    MyLabel item8;
    MyLabel item9;
    MyLabel item10;

    TextButton actionButton;
    TextButton backButton;

    ArrayList<String> stringList;
    String itemString1;
    String itemString2;
    String itemString3;
    String itemString4;
    String itemString5;
    String itemString6;
    String itemString7;
    String itemString8;
    String itemString9;
    String itemString10;
    private static final String EMPTY = "EMPTY";

    public InventoryScreen(MyGdxGame game, SpriteBatch batch, ScreenManager manager)
    {
        this.game = game;
        this.batch = batch;
        this.manager = manager;

        font = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));

        stringList = new ArrayList<String>();

        initTables();
        initButtons();



        stage.addActor(mainTable);
    }

    private void addButtonListeners() {
        backButton.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(manager.getStatsScreen());
                Gdx.input.setInputProcessor(manager.getStatsScreen().stage);
            }
        });

        actionButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(selectedItem instanceof Consumable)
                {
                    ((Consumable) selectedItem).consume(hero);
                    removeItem(selectedLabel);
                }
            }
        });
    }

    private void removeItem(MyLabel label)
    {
        Item itemVariant = selectedItem;


    }

    private void addLabelListeners() {

        item1.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item1);
               updateActionButton();
            }
        });

        item2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item2);
                updateActionButton();
            }
        });

        item3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item3);
                updateActionButton();
            }
        });

        item4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item4);
                updateActionButton();
            }
        });

        item5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item5);
                updateActionButton();
            }
        });

        item6.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item6);
                updateActionButton();
            }
        });

        item7.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectInventoryLabel(item7);
                updateActionButton();
            }
        });

        item8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectInventoryLabel(item8);
                updateActionButton();
            }
        });

        item9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectInventoryLabel(item9);
                updateActionButton();
            }
        });

        item10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectInventoryLabel(item10);
                updateActionButton();
            }
        });
    }

    public void initiateHero(Hero hero){
        System.out.println("initiating Hero...");
        this.hero = hero;
        initString();
        initLabels();
        setStageProperties();
    }

    private void setStageProperties(){

        float padding = 50;

        mainTable.setWidth(stage.getWidth());
        mainTable.setHeight(stage.getHeight());
        mainTable.align(Align.right | Align.top);
        mainTable.setPosition(0, 0);

        itemsTable.setWidth(mainTable.getWidth() / 5);
        itemsTable.align(Align.center | Align.top);
        itemsTable.add(items);
        itemsTable.row();
        itemsTable.add(item1).top();
        itemsTable.row();
        itemsTable.add(item2);
        itemsTable.row();
        itemsTable.add(item3);
        itemsTable.row();
        itemsTable.add(item4);
        itemsTable.row();
        itemsTable.add(item5);
        itemsTable.row();
        itemsTable.add(item6);
        itemsTable.row();
        itemsTable.add(item7);
        itemsTable.row();
        itemsTable.add(item8);
        itemsTable.row();
        itemsTable.add(item9);
        itemsTable.row();
        itemsTable.add(item10);

        interfaceTable.align(Align.center | Align.center);
        interfaceTable.add(torso).padLeft(padding * 4);
        interfaceTable.row();
        interfaceTable.add(myTorso).padLeft(padding * 4);
        interfaceTable.row();
        interfaceTable.add(rightArm).padRight(padding + 20);
        interfaceTable.add(leftArm).padLeft(padding);
        interfaceTable.row();
        interfaceTable.add(myRightArm).padRight(padding + 20);
        interfaceTable.add(myLeftArm).padLeft(padding);
        interfaceTable.row();
        interfaceTable.add(legs).padLeft(padding * 4);
        interfaceTable.row();
        interfaceTable.add(myLegs).padLeft(padding * 4);
        interfaceTable.row();
        interfaceTable.add(feet).padLeft(padding * 4).padTop(20);
        interfaceTable.row();
        interfaceTable.add(myFeet).padLeft(padding * 4);
        interfaceTable.row();

        buttonTable.setWidth(mainTable.getWidth());
        buttonTable.add(backButton).padRight(padding * 2);
        buttonTable.add(actionButton);




        mainTable.add(interfaceTable).width(mainTable.getWidth() * .65f).padRight(0);
        mainTable.add(itemsTable).top().padRight(60);
        mainTable.row();
        mainTable.add(buttonTable);




    }

    private void updateActionButton(){

        if(selectedItem instanceof Consumable)
        {
            System.out.println(true);
            actionButton.setText("Consume");
        }

    }

    private void update(float delta) {

        initString();

        item1.setText(itemString1);
        item1.setLabelText();


        item2.setText(itemString2);
        item2.setLabelText();


        item3.setText(itemString3);
        item3.setLabelText();


        item4.setText(itemString4);
        item4.setLabelText();


        item5.setText(itemString5);
        item5.setLabelText();


        item6.setText(itemString6);
        item6.setLabelText();


        item7.setText(itemString7);
        item7.setLabelText();


        item8.setText(itemString8);
        item8.setLabelText();


        item9.setText(itemString9);
        item9.setLabelText();


        item10.setText(itemString10);
        item10.setLabelText();

    }

    private void initString(){


        // instantiate Strings
        itemString1 = "";
        itemString2 = "";
        itemString3 = "";
        itemString4 = "";
        itemString5 = "";
        itemString6 = "";
        itemString7 = "";
        itemString8 = "";
        itemString9 = "";
        itemString10 = "";



        // add strings to list
        stringList.add(itemString1);
        stringList.add(itemString2);
        stringList.add(itemString3);
        stringList.add(itemString4);
        stringList.add(itemString5);
        stringList.add(itemString6);
        stringList.add(itemString7);
        stringList.add(itemString8);
        stringList.add(itemString9);
        stringList.add(itemString10);



        itemString1 = hero.getInventory().getItemById(0).getName();

        for(int i = 0; i < hero.getInventory().getItems().size() - 1; i++)
        {
            stringList.set(i, hero.getInventory().getItemById(i).getName());
        }

        itemString1 = stringList.get(0);
        itemString2 = stringList.get(1);
        itemString3 = stringList.get(2);
        itemString4 = stringList.get(3);
        itemString5 = stringList.get(4);
        itemString6 = stringList.get(5);
        itemString7 = stringList.get(6);
        itemString8 = stringList.get(7);
        itemString9 = stringList.get(8);
        itemString10 = stringList.get(9);

    }

    private void initTables(){

        mainTable = new Table();

        itemsTable = new Table();

        interfaceTable = new Table();

        buttonTable = new Table();
    }

    private void initButtons(){

        backButton = new TextButton("Back", skin);
        actionButton = new TextButton("", skin);


        addButtonListeners();

    }



    private void initLabels() {

        items = new Label("Items:", new Label.LabelStyle(font, font.getColor()));

        item1 = new MyLabel(itemString1, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(0);
        item2 = new MyLabel(itemString2, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(1);
        item3 = new MyLabel(itemString3, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(2);
        item4 = new MyLabel(itemString4, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(3);
        item5 = new MyLabel(itemString5, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(4);
        item6 = new MyLabel(itemString6, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(5);
        item7 = new MyLabel(itemString7, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(6);
        item8 = new MyLabel(itemString8, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(7);
        item9 = new MyLabel(itemString9, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(8);
        item10 = new MyLabel(itemString10, new Label.LabelStyle(font, font.getColor()));
        item1.setItemId(9);

        torso = new Label("Torso", new Label.LabelStyle(font, font.getColor()));
        rightArm = new Label("Right Arm", new Label.LabelStyle(font, font.getColor()));
        leftArm = new Label("Left Arm", new Label.LabelStyle(font, font.getColor()));
        legs = new Label("Legs", new Label.LabelStyle(font, font.getColor()));
        feet = new Label("Feet", new Label.LabelStyle(font, font.getColor()));

        myTorso = new MyLabel(EMPTY, new Label.LabelStyle(font, font.getColor()));
        myRightArm = new MyLabel(EMPTY, new Label.LabelStyle(font, font.getColor()));
        myLeftArm = new MyLabel(EMPTY, new Label.LabelStyle(font, font.getColor()));
        myLegs = new MyLabel(EMPTY, new Label.LabelStyle(font, font.getColor()));
        myFeet = new MyLabel(EMPTY, new Label.LabelStyle(font, font.getColor()));

        item1.addItem(returnItem(0));
        item2.addItem(returnItem(1));
        item3.addItem(returnItem(2));
        item4.addItem(returnItem(3));
        item5.addItem(returnItem(4));
        item6.addItem(returnItem(5));
        item7.addItem(returnItem(6));
        item8.addItem(returnItem(7));
        item9.addItem(returnItem(8));
        item10.addItem(returnItem(9));

        selectInventoryLabel(item1);

        updateActionButton();


        addLabelListeners();
    }

    private Item returnItem(int i)
    {
        Item item = null;

        if(!hero.getInventory().getItemById(i).equals(null))
        {
            item = hero.getInventory().getItemById(i);
        }

        return item;
    }

    private void selectInventoryLabel(MyLabel label)
    {
        item1.setSelected(false);
        item2.setSelected(false);
        item3.setSelected(false);
        item4.setSelected(false);
        item5.setSelected(false);
        item6.setSelected(false);
        item7.setSelected(false);
        item8.setSelected(false);
        item9.setSelected(false);
        item10.setSelected(false);

        label.setSelected(true);
        selectedItem = label.getItem();
        selectedLabel = label;

    }


    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        update(delta);
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public class MyLabel extends Label{

        boolean selected;
        Item item;
        int itemId;

        public void setLabelText(){

            if(selected)
            {
                this.setText(">" + this.getText());
            }
        }

        public void addItem(Item item)
        {
            if(!this.getText().equals(""))
            {
                setItem(item);
            }
        }

        public MyLabel(CharSequence text, Skin skin) {
            super(text, skin);
        }

        public MyLabel(CharSequence text, Skin skin, String fontName, Color color) {
            super(text, skin, fontName, color);
        }

        public MyLabel(CharSequence text, Skin skin, String fontName, String colorName) {
            super(text, skin, fontName, colorName);
        }

        public MyLabel(CharSequence text, Skin skin, String styleName) {
            super(text, skin, styleName);
        }

        public MyLabel(CharSequence text, LabelStyle style) {
            super(text, style);
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }
    }

}
