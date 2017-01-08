package com.mygdx.game.Screens;

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
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Created by louie on 9/9/2016.
 */
public class MainMenuScreen implements Screen{

    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Table table;
    Skin skin;
    Stage stage;
    BitmapFont font;

    MenuScreen menu;

    Label title;
    TextButton start;
    TextButton exit;

    boolean characterCreated;

    public MainMenuScreen(MyGdxGame game, SpriteBatch batch, ScreenManager manager){

        this.game = game;
        this.batch = batch;
        this.manager = manager;

        font = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));

        table = new Table();
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));
        title = new Label("My Game", new Label.LabelStyle(font, font.getColor()));

        start = new TextButton("Start to start", skin, "default");
        exit = new TextButton("Exit", skin, "default");

        characterCreated = false;

        addListeners();

        SetStageProperties();

    }

    private void addListeners(){

        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(!characterCreated) {
                    game.setScreen(manager.getCharacterCreation());
                    Gdx.input.setInputProcessor(manager.getCharacterCreation().stage);
                    characterCreated = true;
                }
                else{
                    game.setScreen(manager.getPlayScreen());
                    Gdx.input.setInputProcessor(manager.getPlayScreen().stage);
                }
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    private void SetStageProperties() {

        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        table.add(title).padTop(100);
        table.row();
        table.add(start).padTop(10);
        table.row();
        table.add(exit).padTop(50);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    public TextButton getExit() {
        return exit;
    }

    public void setExit(TextButton exit) {
        this.exit = exit;
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

    public MenuScreen getMenu() {
        return menu;
    }

    public void setMenu(MenuScreen menu) {
        this.menu = menu;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getStart() {
        return start;
    }

    public void setStart(TextButton start) {
        this.start = start;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }
}
