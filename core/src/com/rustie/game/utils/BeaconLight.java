package com.rustie.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by rustie on 7/25/17.
 */

public class BeaconLight extends PointLight implements Pool.Poolable{

    // BeaconLights are uniquely identified by timestamp
    long timestamp = 0;

    public BeaconLight(RayHandler rayHandler, int rays, Color color, float distance, float x, float y, long timestamp) {
        super(rayHandler, rays, color, distance, x, y);
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeaconLight that = (BeaconLight) o;

        return timestamp == that.timestamp;

    }

    @Override
    public int hashCode() {
        return (int) (timestamp ^ (timestamp >>> 32));
    }

    @Override
    public void reset() {
        timestamp = 0;
        distance = 0;
    }
}
