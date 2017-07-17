package com.rustie.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by rustie on 7/5/17.
 *
 * Wrapper for the libgdx Screen interface. Lets us call update in the GameScreenManager
 */

public class GameScreen implements Screen {
    protected GameScreenManager mGsm;
    protected OrthographicCamera mCam;

    public GameScreen(GameScreenManager gsm) {
        mGsm = gsm;
        mCam = new OrthographicCamera();
    }

    public void update(float dt) {

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
