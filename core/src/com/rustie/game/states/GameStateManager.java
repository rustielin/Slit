package com.rustie.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by rustie on 5/15/17.
 */

public class GameStateManager {

    private Stack<State> mStates;

    public GameStateManager() {
        mStates = new Stack<State>();

    }

    public void push(State state) {
        mStates.push(state);
    }

    public void pop() {
        mStates.pop();
    }

    /**
     * Pop and then puush the state
     * @param state
     */
    public void set(State state) {
        mStates.pop();
        mStates.push(state);
    }

    public void update(float dt) {
        mStates.peek().update(dt);

    }

    public void render(SpriteBatch spriteBatch) {
        mStates.peek().render(spriteBatch);
    }
}
