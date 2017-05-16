package com.rustie.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rustie.game.States.GameStateManager;

/**
 * Created by rustie on 5/15/17.
 */

public class PlayState extends State {
    private Texture mBall;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        this.mBall = new Texture("circle.png");
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float v) {
        // check this first
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(mBall, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
