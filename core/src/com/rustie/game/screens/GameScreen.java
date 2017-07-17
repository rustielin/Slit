package com.rustie.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by rustie on 7/5/17.
 *
 * Wrapper for the libgdx Screen interface that adds update method.
 * All screens extend abstract GameScreen
 *
 * Lets us call update in the GameScreenManager
 */

abstract class GameScreen implements Screen {
    protected GameScreenManager mGsm;
    protected OrthographicCamera mCam;

    public GameScreen(GameScreenManager gsm) {
        mGsm = gsm;
        mCam = new OrthographicCamera();
    }

    abstract void update(float dt);

}
