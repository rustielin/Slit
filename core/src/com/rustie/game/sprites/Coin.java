package com.rustie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/19/17.
 */

public class Coin extends InteractiveTileObject {

    public static final String TAG = "Coin";

    public Coin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        mFixture.setUserData(this);
    }

    @Override
    public void onCollide() {
        Gdx.app.log(TAG, "Collision");
    }
}
