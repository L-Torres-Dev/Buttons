package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.ScreenManager;


/**
 * Need to add clickListeners to the Labels so that the user
 * can select an item and do something with it whether they would
 * like to equip a new piece of armor or drink a potion
 *
 * Last Updated: 1/7/2017
 */

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	ScreenManager screenManager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		screenManager = new ScreenManager(this, batch);
		setScreen(screenManager.getMainMenu());
		Gdx.input.setInputProcessor(screenManager.getMainMenu().getStage());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

}


