package com.rustie.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rustie.game.states.GameStateManager;
import com.rustie.game.states.MenuState;

public class Slit extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Slit";

    private GameStateManager mGsm;

    // only need 1
    private SpriteBatch batch;

    Texture img;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
        this.mGsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        this.mGsm.push(new MenuState(mGsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.mGsm.update(Gdx.graphics.getDeltaTime());
        this.mGsm.render(batch);

        batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
