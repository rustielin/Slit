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
import com.rustie.game.screens.PlayScreen;
//import com.rustie.game.states.GameStateManager;
//import com.rustie.game.states.MenuState;

public class Slit extends Game {
    // virtual width and heights
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

    // pixels per meter
    public static final float PPM = 100;

	public static final String TITLE = "Slit";
    public static Application.ApplicationType APP_TYPE;
	public static boolean IS_MOBILE;

//	private GameStateManager mGsm;



	// only need 1
	public static SpriteBatch mBatch;

	Texture img;

	@Override
	public void create() {
		this.mBatch = new SpriteBatch();
		setScreen(new PlayScreen(this));

//		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		APP_TYPE = Gdx.app.getType();
		IS_MOBILE = APP_TYPE == Application.ApplicationType.Android ||
				APP_TYPE == Application.ApplicationType.iOS;


//		this.mGsm = new GameStateManager();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		this.mGsm.push(new MenuState(mGsm));
	}

	@Override
	public void render() {
        super.render();

//
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		this.mGsm.update(Gdx.graphics.getDeltaTime());
//		this.mGsm.render(mBatch);
//
//		mBatch.begin();
//		mBatch.end();
	}

	@Override
	public void dispose() {
		mBatch.dispose();
		img.dispose();
	}
}
