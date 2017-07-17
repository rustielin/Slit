package com.rustie.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;
import com.rustie.game.scenes.Hud;
import com.rustie.game.sprites.Coin;
import com.rustie.game.sprites.Player;
import com.rustie.game.sprites.Wall;
import com.rustie.game.utils.B2WorldCreator;
import com.rustie.game.utils.PulsatingLight;
import com.rustie.game.utils.WorldContactListener;

import box2dLight.RayHandler;

/**
 * Created by rustie on 5/18/17.
 */

public class PlayScreen extends GameScreen {

    public static final String TAG = "PlayScreen";

    private static float MOVEMENT_SPEED = 1f;
    private static float SLOW_SPEED = 0.5f;

    // primary game attributes
    private Slit mGame;
    private Viewport mGamePort;
    private Hud mHud;
    private Player mPlayer;

    // for compatibility with Tiled
    private TmxMapLoader mMapLoader;
    private TiledMap mMap;
    private OrthogonalTiledMapRenderer mRenderer;

    // box2d stuff
    private World mWorld;
    private Box2DDebugRenderer box2DDebugRenderer;

    // lighting
    public RayHandler mRayHandler;
    private box2dLight.PointLight mPlayerLight;
    private float playerLightDistance = 2;
    private int playerLightDirection = 1;
    private PulsatingLight mPlayerPulse;

    // sweeping for updates and dispose
    private Array<Fixture> mFixtureArray;


    public PlayScreen(GameScreenManager gsm, Slit game, String level) {
        super(gsm);

        Gdx.app.log(TAG, "ENTER");

        mGame = game; // persist the game instance

        // new world with no gravity; hud; listens for contact
        mWorld = new World(new Vector2(0, 0), true);
        mHud = new Hud(mGame.mBatch, mWorld);
        mWorld.setContactListener(new WorldContactListener());


        // render attributes
        mGamePort = new FitViewport(Slit.WIDTH / Slit.PPM , Slit.HEIGHT / Slit.PPM, mCam);
        mCam.position.set(mGamePort.getWorldWidth() / 2, mGamePort.getWorldHeight() / 2, 0);
        box2DDebugRenderer = new Box2DDebugRenderer();

        // render Tiled map
        mMapLoader = new TmxMapLoader();
        mMap = mMapLoader.load(level);
        mRenderer = new OrthogonalTiledMapRenderer(mMap, 1/ Slit.PPM);

        // make the player and lights
        mPlayer = new Player(mWorld);
        mRayHandler = new RayHandler(mWorld);
        mPlayerLight = new box2dLight.PointLight(mRayHandler, 100, Color.WHITE, playerLightDistance, 32 / Slit.PPM, 32 / Slit.PPM);
        mPlayerLight.setSoftnessLength(0f);
        mPlayerLight.attachToBody(mPlayer.mB2Body);
        mPlayerPulse = new PulsatingLight(mPlayerLight, 0, 2, 1, 1, true);

        // create the world and inject to physics
        new B2WorldCreator(mWorld, mMap, mRayHandler);
        mFixtureArray = new Array<Fixture>();

    }

    /**
     * Checks on screen button input if device is mobile type
     * @param dt
     */
    private void checkTouchpadInput(float dt) {
        // Handle mobile on screen buttons
        mPlayer.move(MOVEMENT_SPEED * mHud.controller.getTouchpadPercentX(), MOVEMENT_SPEED * mHud.controller.getTouchpadPercentY());
//        mPlayer.move(MOVEMENT_SPEED * Gdx.input.getAccelerometerY(), -1 * MOVEMENT_SPEED * Gdx.input.getAccelerometerX());
    }

    /**
     * Check arrow/WASD key input if device is not mobile type
     * @param dt
     */
    private void checkKeyInput(float dt) {

        // Handle WASD and arrow keys
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                mPlayer.move(-MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            } else {
                mPlayer.move(-MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                mPlayer.move(MOVEMENT_SPEED * SLOW_SPEED, mPlayer.mB2Body.getLinearVelocity().y);

            } else {
                mPlayer.move(MOVEMENT_SPEED, mPlayer.mB2Body.getLinearVelocity().y);
            }
        } else {
            mPlayer.mB2Body.setLinearVelocity(0, mPlayer.mB2Body.getLinearVelocity().y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED * SLOW_SPEED);

            } else {
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, MOVEMENT_SPEED);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED * SLOW_SPEED);
            } else {
                mPlayer.move(mPlayer.mB2Body.getLinearVelocity().x, -MOVEMENT_SPEED);
            }
        } else {
            mPlayer.mB2Body.setLinearVelocity(mPlayer.mB2Body.getLinearVelocity().x, 0);
        }
    }

    public void handleInput(float dt) {

        // handle mobile
        if (Slit.IS_MOBILE) {
            checkTouchpadInput(dt);
        } else {
            checkKeyInput(dt);
//            checkTouchpadInput(dt);

        }
    }

    public void update(float dt) {
        // handle input first
        handleInput(dt);

        mWorld.step(1 / 60f, 6, 2); // 60 Hz

        mCam.position.x = mPlayer.mB2Body.getPosition().x; // lol fps mode
        mCam.position.y = mPlayer.mB2Body.getPosition().y;


        // sweep dead stuff
//        sweepDeadFixtures();

        mRayHandler.update();

        mPlayerPulse.update(dt);

        //sweep all updates
        sweepFixtureUpdates(dt);

        // update after changes
        mCam.update();
        mHud.update();

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

        // correct projection
        mRayHandler.setCombinedMatrix(mCam.combined);
        mRayHandler.render();


        // recognize where the camera is and only render that
        mGame.mBatch.setProjectionMatrix(mHud.mStage.getCamera().combined);
        mHud.mStage.draw();

        // only render the controller if needed
        if (Slit.APP_TYPE == Application.ApplicationType.Android || Slit.APP_TYPE == Application.ApplicationType.iOS) {
            mHud.controller.draw();
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
        mRayHandler.dispose();
        mMap.dispose();
        mRenderer.dispose();
        mWorld.dispose();
        box2DDebugRenderer.dispose();
        mHud.dispose();
    }


    public void sweepFixtureUpdates(float dt) {
        mWorld.getFixtures(mFixtureArray);
        Object userData;
        for (Fixture f : mFixtureArray) {
            userData = f.getUserData();
            if (userData instanceof Coin && ((Coin) userData).mPulsatingLight != null) {
                ((Coin) userData).update(dt);
//                Gdx.app.log(TAG, "COIN UPDATE");
            } else if (userData instanceof Wall && ((Wall) userData).mPulsatingLight != null) {
                ((Wall) userData).update(dt);
//                Gdx.app.log(TAG, "WALL UPDATE");
            }
        }
    }

}
