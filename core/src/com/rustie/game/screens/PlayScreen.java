package com.rustie.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;
import com.rustie.game.scenes.Hud;
import com.rustie.game.utils.Controller;

/**
 * Created by rustie on 5/18/17.
 */

public class PlayScreen implements Screen {

    private Slit mGame;
    private OrthographicCamera mCam;
    private Viewport mGamePort;
    private Hud mHud;
    private Controller mController;

    private TmxMapLoader mMapLoader;
    private TiledMap mMap;
    private OrthogonalTiledMapRenderer mRenderer;


    public PlayScreen(Slit game) {
        this.mGame = game;
        this.mCam = new OrthographicCamera();
        this.mGamePort = new FitViewport(Slit.WIDTH, Slit.HEIGHT, mCam);
        this.mHud = new Hud(mGame.mBatch);
        this.mController = new Controller();

        mMapLoader = new TmxMapLoader();
        mMap = mMapLoader.load("level1.tmx");
        mRenderer = new OrthogonalTiledMapRenderer(mMap);
        mCam.position.set(mGamePort.getWorldWidth() / 2, mGamePort.getWorldHeight() / 2, 0);

    }

    public void handleInput(float dt) {
//        double x = mPlayer.getVelocity().x;
//        double y = mPlayer.getVelocity().y;

        // Handle mobile on screen buttons
        if (mController.isRightPressed()) {
            if (mController.isSlowPressed()) {
                // move slower
            } else {
                // move normal
            }
        } else if (mController.isLeftPressed()) {
            if (mController.isSlowPressed()) {
                // move slower
            } else {
                // move normal
            }
        } else if (mController.isUpPressed()) {
            if (mController.isSlowPressed()) {
                // move slower
            } else {
                // move normal
            }
        } else if (mController.isDownPressed()) {
            if (mController.isSlowPressed()) {
                // move slower
            } else {
                // move normal
            }
        }

        // Handle WASD and arrow keys
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                // move slower
                System.out.println("A/Left + SHIFT_LEFT");
            } else {
                // move normal
                System.out.println("A/Left");
                mCam.position.x += 100 * dt;

            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                // move slower
            } else {
                // move normal
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                // move slower
            } else {
                // move normal
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                // move slower
            } else {
                // move normal
            }
        }

    }

    public void update(float dt) {
        handleInput(dt);
        mCam.update();
        mRenderer.setView(mCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // recognize where the camera is and only render that
        mGame.mBatch.setProjectionMatrix(mHud.mStage.getCamera().combined);
        mHud.mStage.draw();

        // only render the controller if needed
        if (Slit.APP_TYPE == Application.ApplicationType.Android || Slit.APP_TYPE == Application.ApplicationType.iOS) {
            mController.draw();
        }

        mRenderer.render();

    }

    @Override
    public void resize(int width, int height) {
        mGamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
