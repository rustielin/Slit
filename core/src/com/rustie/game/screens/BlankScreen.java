package com.rustie.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by rustie on 7/24/17.
 */

public class BlankScreen extends GameScreen {

    public BlankScreen(GameScreenManager gsm) {
        super(gsm);
    }

    @Override
    void update(float dt) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // clear with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
