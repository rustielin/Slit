package com.rustie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.Slit;
import com.rustie.game.utils.PulsatingLight;

import java.util.Random;

import box2dLight.RayHandler;

/**
 * Created by rustie on 5/19/17.
 */

public class Coin extends InteractiveTileObject {

    public static final String TAG = "Coin";
    public PulsatingLight mPulsatingLight;
    public float minPulseDistance = 0;
    public float maxPulseDistance = 2;
    public float pulseMultiplier = 1;


    public Coin(World world, TiledMap map, Rectangle bounds, RayHandler rayHandler) {
        super(world, map, bounds, rayHandler);
        mFixture.setUserData(this);
    }

    @Override
    public void onCollide() {
        Gdx.app.log(TAG, "Collision");
        flagged_for_delete = true;

        if (mPointLight == null) {
            Random rand = new Random();

            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            mPointLight =  new box2dLight.PointLight(mRayHandler, 100, new Color(r,g,b, 1), maxPulseDistance, 32 / Slit.PPM, 32 / Slit.PPM);
            Gdx.app.log(TAG, "RGB: " + r + " " + g + " " + b);
            mPointLight.setSoftnessLength(0f);
            mPointLight.attachToBody(mBody);
            mPulsatingLight = new PulsatingLight(mPointLight, minPulseDistance, maxPulseDistance, pulseMultiplier, 1);
            Slit.SCORE += 1;
            Gdx.app.log(TAG, "" + Slit.SCORE);
        }

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
