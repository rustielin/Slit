//package com.rustie.game.states;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.rustie.game.Slit;
//
///**
// * Created by rustie on 5/15/17.
// */
//
//public class MenuState extends State {
//    private Texture mBackground;
//    private Texture mPlayBtn;
//
//    public MenuState(GameStateManager gsm) {
//        super(gsm);
//        mBackground = new Texture("black.jpg");
//        mPlayBtn = new Texture("play_filled.png");
//    }
//
//
//    @Override
//    public void handleInput() {
//        if (Gdx.input.justTouched()) {
//            mGsm.set(new PlayState(mGsm));
//            dispose(); // get rid of stuff not using anymore
//        }
//    }
//
//    @Override
//    public void update(float dt) {
//        // check this first
//        handleInput();
//    }
//
//    @Override
//    public void render(SpriteBatch spriteBatch) {
//        // open
//        spriteBatch.begin();
//
//        // put
//        spriteBatch.draw(mBackground, 0, 0, Slit.WIDTH, Slit.HEIGHT);
//        spriteBatch.draw(mPlayBtn, (Slit.WIDTH / 2) - (mPlayBtn.getWidth() / 2), Slit.HEIGHT / 2);
//
//        // close
//        spriteBatch.end();
//
//    }
//
//    @Override
//    public void dispose() {
//        mBackground.dispose();
//        mPlayBtn.dispose();
//    }
//}
