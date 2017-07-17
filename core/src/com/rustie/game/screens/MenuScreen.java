package com.rustie.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;

import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by rustie on 7/5/17.
 *
 * Simple menu screen that has a play button.
 *
 * TODO: Full interactive player and environment; Zones for play, credits, other options.
 *
 */

public class MenuScreen extends GameScreen {

    public static final String TAG = "MenuScreen";

    public Slit mGame;

    private Texture mBackground;
    private Texture mPlayBtn;

    // fancy lights for later
    private RayHandler mRayHandler;
    private PointLight light;


    public MenuScreen(GameScreenManager gsm, Slit game) {
        super(gsm);
        Gdx.app.log(TAG, "ENTER");


        this.mGame = game;
        mPlayBtn = new Texture("play_filled.png");
        mBackground = new Texture("black.jpg");

    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput(dt);
    }

    public void handleInput(float dt) {

        // when screen touched, launch PlayScreen with specified level tilemap
        if (Gdx.input.justTouched()) {
            Gdx.app.log(TAG, "EXIT");
            mGsm.set(new PlayScreen(mGsm, mGame, "level1more.tmx"));
            dispose(); // get rid of textures we're not using anymore
        }
    }

    @Override
    public void render(float delta) {

        // open batch to draw textures
        mGame.mBatch.begin();

        // put textures
        mGame.mBatch.draw(mBackground, 0, 0, Slit.WIDTH, Slit.HEIGHT);
        mGame.mBatch.draw(mPlayBtn, (Slit.WIDTH / 2) - (mPlayBtn.getWidth() / 2), Slit.HEIGHT / 2);

        // close
        mGame.mBatch.end();
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
        mPlayBtn.dispose();
        mBackground.dispose();
    }
}
