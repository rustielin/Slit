package com.rustie.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.Slit;

import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by rustie on 5/19/17.
 */

public abstract class InteractiveTileObject {

    protected boolean flagged_for_delete = false;
    protected Fixture mFixture;
    protected World mWorld;
    protected Body mBody;
    protected RayHandler mRayHandler;
    protected PointLight mPointLight;


    private TiledMap mMap;
    private TiledMapTile mTile;
    private Rectangle mBounds;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds, RayHandler rayHandler) {
        this.mWorld = world;
        this.mMap = map;
        this.mBounds = bounds;
        this.mRayHandler = rayHandler;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody; // Static, Dynamic, Kinematic
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Slit.PPM, (bounds.getY() + bounds.getHeight() / 2) / Slit.PPM); // put a rect body

        mBody = world.createBody(bdef);

        // define polygon shape
        shape.setAsBox(bounds.getWidth() / 2 / Slit.PPM, bounds.getHeight() / 2 / Slit.PPM);
        fdef.shape = shape;
        mBody.createFixture(fdef);

        mFixture = mBody.createFixture(fdef);
    }

    public abstract void onCollide();

    public boolean isFlagged_for_delete() {
        return flagged_for_delete;
    }

    public void update(float dt) {
//        if (flagged_for_delete) {
//            mWorld.destroyBody(mBody);
//        }
    }
}
