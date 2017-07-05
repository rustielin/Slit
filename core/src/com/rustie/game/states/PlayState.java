//package com.rustie.game.states;
//
//import com.badlogic.gdx.Application;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.FPSLogger;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.rustie.game.utils.Controller;
//import com.rustie.game.Slit;
//import com.rustie.game.sprites.Player;
//import com.rustie.game.sprites.Wave;
//
///**
// * Created by rustie on 5/15/17.
// */
//
//public class PlayState extends State {
//    private ShapeRenderer mShapeRenderer;
//    private Player mPlayer;
//    private Wave mWave;
//    private Texture test;
//
//    private FPSLogger fpsLogger;
//
//    private Controller controller;
//
//    protected PlayState(GameStateManager gsm) {
//        super(gsm);
//        this.fpsLogger = new FPSLogger();
//        this.mShapeRenderer = new ShapeRenderer();
//        this.mPlayer = new Player(50, 50);
//        this.mWave = new Wave(50, 100, 10, 5);
//        test = new Texture("play_filled.png");
//
//        controller = new Controller();
//
//    }
//
//    /**
//     * Handles input based on mobile / pc
//     */
//    @Override
//    protected void handleInput() {
//
//        double x = mPlayer.getVelocity().x;
//        double y = mPlayer.getVelocity().y;
//
//        // Handle mobile on screen buttons
//        if (controller.isRightPressed()) {
//            if (controller.isSlowPressed()) {
//                // move slower
//            } else {
//                // move normal
//            }
//        } else if (controller.isLeftPressed()) {
//            if (controller.isSlowPressed()) {
//                // move slower
//            } else {
//                // move normal
//            }
//        } else if (controller.isUpPressed()) {
//            if (controller.isSlowPressed()) {
//                // move slower
//            } else {
//                // move normal
//            }
//        } else if (controller.isDownPressed()) {
//            if (controller.isSlowPressed()) {
//                // move slower
//            } else {
//                // move normal
//            }
//        }
//
//        // Handle WASD and arrow keys
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                // move slower
//                System.out.println("A/Left + SHIFT_LEFT");
//            } else {
//                // move normal
//                System.out.println("A/Left");
//
//            }
//        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                // move slower
//            } else {
//                // move normal
//            }
//        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                // move slower
//            } else {
//                // move normal
//            }
//        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                // move slower
//            } else {
//                // move normal
//            }
//        }
//
//
//    }
//
//    private void normalize() {
//
//    }
//
//    @Override
//    public void update(float dt) {
//        // check this first
//        handleInput();
//        mWave.update(dt);
//
//        fpsLogger.log();
//
//    }
//
//    @Override
//    public void render(SpriteBatch spriteBatch) {
//        spriteBatch.begin();
//
//        spriteBatch.draw(mPlayer.getTexture(), mPlayer.getPosition().x, mPlayer.getPosition().y);
////        spriteBatch.draw(test, 50, 50);
//        spriteBatch.draw(mWave.getTexture(), mWave.getPosition().x, mWave.getPosition().y);
//
//
//        // only render the controller if needed
//        if (Slit.APP_TYPE == Application.ApplicationType.Android || Slit.APP_TYPE == Application.ApplicationType.iOS) {
//            controller.draw();
//        }
//
//        spriteBatch.end();
//    }
//
//    @Override
//    public void dispose() {
//        mPlayer.dispose();
//    }
//}
