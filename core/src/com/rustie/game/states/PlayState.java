package com.rustie.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rustie.game.Slit;
import com.rustie.game.sprites.Player;

/**
 * Created by rustie on 5/15/17.
 */

public class PlayState extends State {
    private ShapeRenderer mShapeRenderer;
    private Player mPlayer;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        this.mShapeRenderer = new ShapeRenderer();
        this.mPlayer = new Player(100, 100);
        mCam.setToOrtho(false, Slit.WIDTH / 2, Slit.HEIGHT / 2);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        // check this first
        handleInput();
        mPlayer.update(dt);

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(mCam.combined);
        spriteBatch.begin();
        spriteBatch.draw(mPlayer.getTexture(), mPlayer.getPosition().x, mPlayer.getPosition().y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
