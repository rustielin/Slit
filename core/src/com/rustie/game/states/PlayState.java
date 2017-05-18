package com.rustie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.rustie.game.Slit;
import com.rustie.game.sprites.Player;
import com.rustie.game.sprites.Wave;

/**
 * Created by rustie on 5/15/17.
 */

public class PlayState extends State {
    private ShapeRenderer mShapeRenderer;
    private Player mPlayer;
    private Wave mWave;
    private Texture test;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        this.mShapeRenderer = new ShapeRenderer();
        this.mPlayer = new Player(50, 50);
        this.mWave = new Wave(50, 100, 10, 5);
        test = new Texture("play_filled.png");

    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        // check this first
        handleInput();
        mWave.update(dt);


    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();

        spriteBatch.draw(mPlayer.getTexture(), mPlayer.getPosition().x, mPlayer.getPosition().y);
//        spriteBatch.draw(test, 50, 50);
        spriteBatch.draw(mWave.getTexture(), mWave.getPosition().x, mWave.getPosition().y);


        spriteBatch.end();
    }

    @Override
    public void dispose() {
        mPlayer.dispose();
    }
}
