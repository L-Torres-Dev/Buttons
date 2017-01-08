package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.sun.glass.ui.Menu;

/**
 * Created by louie on 9/9/2016.
 */
public class MenuScreen implements Screen {

    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Table table;
    Skin skin;
    public Stage stage;
    BitmapFont font;

    TextButton mainMenuButton;
    TextButton statsButton;
    TextButton backButton;


    public MenuScreen(MyGdxGame game, SpriteBatch batch, ScreenManager manager){
        this.game = game;
        this.batch = batch;
        this.manager = manager;

        font = new BitmapFont(Gdx.files.internal("TestFont.fnt"));
        table = new Table();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));

        statsButton = new TextButton("Stats", skin, "default");
        mainMenuButton = new TextButton("Main Menu", skin, "default");
        backButton = new TextButton("Back", skin, "default");

        addListeners();

        SetStageProperties();


    }

    private void addListeners(){

        statsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getStatsScreen());
                Gdx.input.setInputProcessor(manager.getStatsScreen().stage);
            }

        });

        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getMainMenu());
                Gdx.input.setInputProcessor(manager.getMainMenu().stage);
            }

        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getPlayScreen());
                Gdx.input.setInputProcessor(manager.getPlayScreen().stage);
            }

        });

    }

    private void SetStageProperties() {

        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        table.add(statsButton).padTop(100);
        table.row();
        table.add(mainMenuButton).padTop(50);
        table.row();
        table.add(backButton).padTop(50);

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
}
