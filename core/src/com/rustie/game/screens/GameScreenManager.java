package com.rustie.game.screens;

/**
 * Created by rustie on 7/5/17.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import com.badlogic.gdx.Screen;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/15/17.
 */

public class GameScreenManager {

    private Stack<GameScreen> mScreens;
    private Slit mGame;

    public GameScreenManager(Slit game) {
        mScreens = new Stack<GameScreen>();
        mGame = game;
    }

    public void push(GameScreen screen) {
        mScreens.push(screen);
    }

    public void pop() {
        mScreens.pop();
    }

    /**
     * Pop and then push the GameScreen
     * @param screen
     */
    public void set(GameScreen screen) {
        mScreens.pop();
        mScreens.push(screen);
    }

    public void update(float dt) {
        mScreens.peek().update(dt);
    }

    public void render(float dt) {
        GameScreen pk = mScreens.peek();
        pk.render(dt);
        mGame.setScreen(pk);
    }
}
