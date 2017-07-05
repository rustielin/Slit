package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import box2dLight.PointLight;
import box2dLight.PositionalLight;
import box2dLight.PositionalLight;
import box2dLight.RayHandler;

/**
 * Created by rustie on 7/4/17.
 */

public class PulsatingLight {

    public static final String TAG = "PulsatingLight";
    private PositionalLight mLight;
    private float multiplier;
    private float minDistance;
    private float maxDistance;
    private int direction;
    private boolean alternating;

    private Random rand;

    public PulsatingLight(PositionalLight light, float minDistance, float maxDistance, float multiplier, int direction) {
        this.mLight = light;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.multiplier = multiplier;
        this.direction = direction;
        this.alternating = false;
    }

    public PulsatingLight(PositionalLight light, float minDistance, float maxDistance, float multiplier, int direction,
                          boolean alt) {
        this.mLight = light;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.multiplier = multiplier;
        this.direction = direction;
        this.alternating = alt;

        rand = new Random();
    }

    /**
     * Updates the light's direction and distance for pulse
     * @param dt
     */
    public void update(float dt) {
        float distance = mLight.getDistance();
        float nextDist = distance + dt * multiplier * direction;

        // out of bounds,
        if ( nextDist > maxDistance || nextDist < minDistance) {
            direction *= -1 ;
            if (this.alternating && direction > 0) {
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();

                mLight.setColor(new Color(r,g,b,1));
            }
        }

        mLight.setDistance(distance + dt * multiplier * direction);

//        Gdx.app.log(TAG, "Distance: " + mLight.getDistance() );
//        Gdx.app.log(TAG, "dt: " + dt );

    }

    public PositionalLight getLight() {
        return mLight;
    }

    public Color getColor() {
        return mLight.getColor();
    }

}
