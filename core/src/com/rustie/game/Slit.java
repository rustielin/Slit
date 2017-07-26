package com.rustie.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rustie.game.screens.BlankScreen;
import com.rustie.game.screens.GameScreenManager;
import com.rustie.game.screens.PlayScreen;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Slit extends Game {

	public static final String TAG = "SLIT";

    // virtual width and heights
	public static final int WIDTH = 720;
	public static final int HEIGHT = 1280;

    // pixels per meter for scaling purposes
    public static final float PPM = 100;

	// some useful game attributes
	public static final String TITLE = "Slit";
    public static Application.ApplicationType APP_TYPE;
	public static boolean IS_MOBILE;
	public String USERNAME;

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

		try {
			String computername= InetAddress.getLocalHost().getAddress().toString();
			Gdx.app.log(TAG, computername );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		APP_TYPE = Gdx.app.getType();
		IS_MOBILE = APP_TYPE == Application.ApplicationType.Android ||
				APP_TYPE == Application.ApplicationType.iOS;

		mGsm = new GameScreenManager(this);

		mGsm.push(new PlayScreen(mGsm, this, "level1.tmx"));
		mGsm.push(new BlankScreen(mGsm));

		NameInputListener n = new NameInputListener(this);
		Gdx.input.getTextInput(n, "You need a username!!", "", "");

		playMusic();
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

	public void playMusic() {
		Music music = Gdx.audio.newMusic(Gdx.files.internal("satie.mp3"));
		music.setLooping(true);
		music.play();

	}


	/**
	 * Used for getting username at game launch
	 */
	class NameInputListener implements Input.TextInputListener {

		Slit game;
		public NameInputListener(Slit game) {
			this.game = game;
		}

		@Override
		public void input(String text) {
			Gdx.app.log(TAG, text + " logged in");
			game.USERNAME = text;
			mGsm.pop();
		}

		@Override
		public void canceled() {
			NameInputListener n = new NameInputListener(game);
			Gdx.input.getTextInput(n, "You need a username!!", "", "");
		}
	}


}
