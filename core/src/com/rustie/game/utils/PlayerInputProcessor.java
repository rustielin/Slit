package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.rustie.game.sprites.Player;

/**
 * Created by rustie on 7/24/17.
 */

public class PlayerInputProcessor implements InputProcessor {
    
    private Player mPlayer;

    public PlayerInputProcessor(Player mPlayer) {
        this.mPlayer = mPlayer;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
