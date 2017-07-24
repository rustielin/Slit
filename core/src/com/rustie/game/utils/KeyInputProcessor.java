package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.rustie.game.sprites.Player;

/**
 * Created by rustie on 7/24/17.
 */

public class KeyInputProcessor implements InputProcessor {

    public static final String TAG = "KeyInputProcessor";
    
    private Player mPlayer;
    private Vector2 mLastTouch;
    private Vector2 mNewTouch;
    private Vector2 mDelta;

    private boolean dragging = false;

    public KeyInputProcessor(Player mPlayer) {
        this.mPlayer = mPlayer;
        mLastTouch = new Vector2();
        mDelta = new Vector2();
        mNewTouch = new Vector2();
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.LEFT:
                mPlayer.setMoveLeft(true);
                break;
            case Input.Keys.RIGHT:
                mPlayer.setMoveRight(true);
                break;
            case Input.Keys.UP:
                mPlayer.setMoveUp(true);
                break;
            case Input.Keys.DOWN:
                mPlayer.setMoveDown(true);
                break;
            case Input.Keys.Z:
                mPlayer.dropBeacon();
                break;
            default:
                break;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                mPlayer.setMoveLeft(false);
                break;
            case Input.Keys.RIGHT:
                mPlayer.setMoveRight(false);
                break;
            case Input.Keys.UP:
                mPlayer.setMoveUp(false);
                break;
            case Input.Keys.DOWN:
                mPlayer.setMoveDown(false);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mLastTouch.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        mNewTouch.set(screenX, screenY);
        mDelta = mNewTouch.cpy().sub(mLastTouch);
        mLastTouch = mNewTouch;
        Gdx.app.log(TAG, "delta: " + mDelta.x + "   " + mDelta.y);


        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
