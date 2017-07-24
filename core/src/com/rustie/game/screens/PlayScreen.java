package com.rustie.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;
import com.rustie.game.scenes.Hud;
import com.rustie.game.sprites.Coin;
import com.rustie.game.sprites.Player;
import com.rustie.game.sprites.Wall;
import com.rustie.game.utils.KeyInputProcessor;
import com.rustie.game.utils.PulsatingLight;
import com.rustie.game.utils.WorldContactListener;

import java.util.HashSet;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by rustie on 7/21/17.
 */

public class PlayScreen extends GameScreen {

    public static final String TAG = "Play Screen";

    private BitmapFont mTitle;


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
    private HashSet<Light> mBeaconSet;


    public PlayScreen(GameScreenManager gsm, Slit game, String level) {
        super(gsm);
        mTitle = new BitmapFont();
        mTitle.setColor(Color.WHITE);

        Gdx.app.log(TAG, "ENTER");

        mGame = game; // persist the game instance

        // new world with no gravity; hud; listens for contact
        mWorld = new World(new Vector2(0, 0), true);
        mHud = new Hud(mGame.mBatch, mWorld, false);
        mWorld.setContactListener(new WorldContactListener());


        // render attributes
//        mGamePort = new FitViewport(Slit.WIDTH / Slit.PPM , Slit.HEIGHT / Slit.PPM, mCam);
        mGamePort = new FillViewport(Slit.WIDTH / Slit.PPM, Slit.HEIGHT / Slit.PPM, mCam);
//        ((ScreenViewport) mGamePort).setUnitsPerPixel(Slit.PPM);

        mCam.position.set(mGamePort.getWorldWidth() / 2, mGamePort.getWorldHeight() / 2, 0);
        box2DDebugRenderer = new Box2DDebugRenderer();

//        // render Tiled map
//        mMapLoader = new TmxMapLoader();
//        mMap = mMapLoader.load(level);
//        mRenderer = new OrthogonalTiledMapRenderer(mMap, 1/ Slit.PPM);

        // make the player and lights
        mPlayer = new Player(mWorld, this);
        mRayHandler = new RayHandler(mWorld);
        mRayHandler.setShadows(false);
        mPlayerLight = new box2dLight.PointLight(mRayHandler, 100, Color.WHITE, playerLightDistance, 32 / Slit.PPM, 32 / Slit.PPM);
        mPlayerLight.setSoftnessLength(0f);
        mPlayerLight.attachToBody(mPlayer.mB2Body);
        mPlayerPulse = new PulsatingLight(mPlayerLight, 0, 2, 0.8f, 1, true);
        mBeaconSet = new HashSet<Light>();

        // create the world and inject to physics
//        new B2WorldCreator(mWorld, mMap, mRayHandler);
        mFixtureArray = new Array<Fixture>();

        // load custom input processor for keys
        if (!Slit.IS_MOBILE) {
            Gdx.input.setInputProcessor(new KeyInputProcessor(mPlayer));
        }

    }

    /**
     * Checks on screen button input if device is mobile type
     * @param dt
     */
    private void checkTouchpadInput(float dt) {
        // Handle mobile on screen buttons
        mPlayer.move(Player.MOVEMENT_SPEED * mHud.controller.getTouchpadPercentX(), Player.MOVEMENT_SPEED * mHud.controller.getTouchpadPercentY());

        if (!mHud.controller.isTouchpadTouched() && Gdx.input.justTouched()) {
            mPlayer.dropBeacon();
            return;
        }

        if (mPlayer.isMoving() && Gdx.input.justTouched()) {
            mPlayer.dropBeacon();
        }


    }



    public void handleTouchInput(float dt) {
        checkTouchpadInput(dt);

    }

    public void update(float dt) {
        // handle touch
        if (Slit.IS_MOBILE) {
            handleTouchInput(dt);
        }

        mWorld.step(1 / 60f, 6, 2); // 60 Hz

        mCam.position.x = mPlayer.mB2Body.getPosition().x; // lol fps mode
        mCam.position.y = mPlayer.mB2Body.getPosition().y;
//        Gdx.app.log(TAG, "posx: " + mCam.position.x);


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
//        mRenderer.setView(mCam);

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
//        mRenderer.render();

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

        // open batch to draw textures
        mGame.mBatch.begin();

        // put textures


        // close
        mGame.mBatch.end();

    }

    @Override
    public void resize(int width, int height) {
        mGamePort.update(width, height, true);

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
//        mMap.dispose();
//        mRenderer.dispose();
        mWorld.dispose();
        box2DDebugRenderer.dispose();
        mHud.dispose();

        // get rid of all lights
        for (Light l : mBeaconSet) {
            l.dispose();
        }
        mPlayerPulse = null;
        mPlayerLight.dispose();

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


    public void addBeacon(float x, float y) {
        Light beacon = new PointLight(mRayHandler, 10, mPlayerPulse.getColor(), playerLightDistance / 10, x, y);
        mBeaconSet.add(beacon);
        Gdx.app.log(TAG, "" + mBeaconSet.size());
    }
}
