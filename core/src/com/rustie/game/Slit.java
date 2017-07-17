package com.rustie.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.rustie.game.screens.GameScreenManager;
import com.rustie.game.screens.MenuScreen;

public class Slit extends Game {

    // virtual width and heights
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

    // pixels per meter for scaling purposes
    public static final float PPM = 100;

	// some useful game attributes
	public static final String TITLE = "Slit";
    public static Application.ApplicationType APP_TYPE;
	public static boolean IS_MOBILE;

	// for filters, collisions, lighting, etc
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short COIN_BIT = 4;
	public static final short WALL_BIT = 8;
	public static final short DESTROYED_BIT = 16;

	//
	public static Integer SCORE = 0;

    // stack that keeps track of which screen we're on
	private GameScreenManager mGsm;

	// only need 1 per game instance to save memory
	public static SpriteBatch mBatch;

	@Override
	public void create() {
		this.mBatch = new SpriteBatch();

        // debug
//		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		APP_TYPE = Gdx.app.getType();
		IS_MOBILE = APP_TYPE == Application.ApplicationType.Android ||
				APP_TYPE == Application.ApplicationType.iOS;

		this.mGsm = new GameScreenManager(this);

        // start on a menu screen
		this.mGsm.push(new MenuScreen(mGsm, this));
	}

	@Override
	public void render() {
        super.render();
		float dt = Gdx.graphics.getDeltaTime();

//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.mGsm.update(dt);
		this.mGsm.render(dt);
//
		mBatch.begin();
		mBatch.end();
	}

	@Override
	public void dispose() {
		mBatch.dispose();
	}


}
