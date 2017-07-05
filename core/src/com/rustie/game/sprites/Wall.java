package com.rustie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.Slit;
import com.rustie.game.utils.PulsatingLight;

import box2dLight.RayHandler;

/**
 * Created by rustie on 5/19/17.
 */

public class Wall extends InteractiveTileObject {

    public static final String TAG = "Wall";
    public PulsatingLight mPulsatingLight;
    public float minPulseDistance = 0;
    public float maxPulseDistance = 10;
    public float pulseMultiplier = 10;


    public Wall(World world, TiledMap map, Rectangle bounds, RayHandler rayHandler) {
        super(world, map, bounds, rayHandler);
        mFixture.setUserData(this);
    }

    @Override
    public void onCollide() {
        Gdx.app.log(TAG, "Collision");
//        if (mPointLight == null) {
//            mPointLight = new box2dLight.PointLight(mRayHandler, 100, Color.BLUE, maxPulseDistance, 32 / Slit.PPM, 32 / Slit.PPM);
//            mPointLight.setSoftnessLength(0f);
//            mPointLight.attachToBody(mBody);
//            mPulsatingLight = new PulsatingLight(mPointLight, minPulseDistance, maxPulseDistance, pulseMultiplier, 1);
//        }
    }


    @Override
    public void update(float dt) {
//        if (mPulsatingLight != null) {
//            mPulsatingLight.update(dt);
////            Gdx.app.log(TAG, "INSIDE COINT UPDATE");
//        }
        super.update(dt);
    }
}
