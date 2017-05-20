package com.rustie.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;
import com.rustie.game.scenes.Hud;
import com.rustie.game.sprites.Player;
import com.rustie.game.utils.B2WorldCreator;
import com.rustie.game.utils.Controller;

/**
 * Created by rustie on 5/18/17.
 */

public class PlayScreen implements Screen {

    private static float MOVEMENT_SPEED = 1f;
    private static float SLOW_SPEED = 0.5f;

    private Slit mGame;
    private OrthographicCamera mCam;
    private Viewport mGamePort;
    private Hud mHud;
    private Controller mController;
    private Player mPlayer;

    private TmxMapLoader mMapLoader;
    private TiledMap mMap;
    private OrthogonalTiledMapRenderer mRenderer;

    // box2d stuff
    private World mWorld;
    private Box2DDebugRenderer box2DDebugRenderer;




    public PlayScreen(Slit game) {
        this.mGame = game;
        this.mCam = new OrthographicCamera();
        this.mGamePort = new FitViewport(Slit.WIDTH / Slit.PPM, Slit.HEIGHT / Slit.PPM, mCam);
        this.mHud = new Hud(mGame.mBatch);
        this.mController = new Controller();


        mMapLoader = new TmxMapLoader();
        mMap = mMapLoader.load("level1.tmx");
        mRenderer = new OrthogonalTiledMapRenderer(mMap, 1/ Slit.PPM);
        mCam.position.set(mGamePort.getWorldWidth() / 2, mGamePort.getWorldHeight() / 2, 0);

        mWorld = new World(new Vector2(0, 0), true); // no gravity, and sleep all objects at rest
        box2DDebugRenderer = new Box2DDebugRenderer();

        // create the world
        new B2WorldCreator(mWorld, mMap);

        this.mPlayer = new Player(mWorld);


    }

    /**
     * Checks on screen button input if device is mobile type
     * @param dt
     */
    private void checkButtonInput(float dt) {
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
    }

    /**
     * Check arrow/WASD key input if device is not mobile type
     * @param dt
     */
    private void checkKeyInput(float dt) {

        // Handle WASD and arrow keys
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                mPlayer.mB2Body.setLinearVelocity(-MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
                mPlayer.move(-MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            } else {
//                mPlayer.mB2Body.setLinearVelocity(-MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
                mPlayer.move(-MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                mPlayer.mB2Body.setLinearVelocity(MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
                mPlayer.move(MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);

            } else {
//                mPlayer.mB2Body.setLinearVelocity(MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
                mPlayer.move(MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            }
        } else {
            mPlayer.mB2Body.setLinearVelocity(0, mPlayer.mB2Body.getLinearVelocity().y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED * SLOW_SPEED);
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED * SLOW_SPEED);

            } else {
//                mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED);
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED * SLOW_SPEED);
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED * SLOW_SPEED);
            } else {
//                mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED);
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED);
            }
        } else {
            mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, 0);
        }
    }

    public void handleInput(float dt) {

        // handle mobile
        if (Slit.IS_MOBILE) {
            checkButtonInput(dt);
        } else {
            checkKeyInput(dt);
        }
    }

    public void update(float dt) {
        // handle input first
        handleInput(dt);

        mWorld.step(1 / 60f, 6, 2); // 60 Hz

//        mCam.position.x = mPlayer.mB2Body.getPosition().x; // lol fps mode

        // update after changes
        mCam.update();

        // render only what we can see
        mRenderer.setView(mCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        // clear with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        mRenderer.render();

        // render debug stuff
        box2DDebugRenderer.render(mWorld, mCam.combined);


        // recognize where the camera is and only render that
        mGame.mBatch.setProjectionMatrix(mHud.mStage.getCamera().combined);
        mHud.mStage.draw();

        // only render the controller if needed
        if (Slit.APP_TYPE == Application.ApplicationType.Android || Slit.APP_TYPE == Application.ApplicationType.iOS) {
            mController.draw();
        }




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
        mMap.dispose();
        mRenderer.dispose();
        mWorld.dispose();
        box2DDebugRenderer.dispose();
        mHud.dispose();
    }
}
