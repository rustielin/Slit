package com.rustie.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rustie on 5/15/17.
 */

public abstract class State {
    protected OrthographicCamera mCam;
    protected Vector3 mMouse;
    protected GameStateManager mGsm;

    protected State(GameStateManager gsm) {
        this.mGsm = gsm;
        this.mCam = new OrthographicCamera();
        this.mMouse = new Vector3();

    }

    protected abstract void handleInput();

    /**
     * Processes frame diff with delta time
     * @param dt
     */
    public abstract void update(float dt);

    public abstract void render(SpriteBatch spriteBatch);

    /**
     * Get rid of assets once we're done using them to prevent memory leaks
     */
    public abstract void dispose();
}
