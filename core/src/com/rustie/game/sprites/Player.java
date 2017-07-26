package com.rustie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.Slit;
import com.rustie.game.screens.PlayScreen;

import org.json.JSONException;

import box2dLight.Light;

/**
 * Created by rustie on 5/16/17.
 */

public class Player extends Sprite {

    public static final String TAG = "Player";

    public World mWorld;
    public Body mB2Body;

    private Vector2 mPosition;
    private Vector2 mVelocity;
    private static final double ACC = 0.25;
    private static final double VMAX = 1;
    private float playerLightDistance;

    public static float MOVEMENT_SPEED = 1f;
    public static float SLOW_SPEED = 0.5f;

    private Light mLight;
    private PlayScreen mPlayScreen;

    public Player(World world, PlayScreen playScreen) {
        this.mWorld = world;
        definePlayer();
        this.mPosition = mB2Body.getPosition();
        this.mVelocity = mB2Body.getLinearVelocity();
        mPlayScreen = playScreen;
        playerLightDistance = 2;
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Slit.PPM, 32 / Slit.PPM); // set this programatically later
        bdef.type = BodyDef.BodyType.DynamicBody;
        mB2Body = mWorld.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Slit.PPM);

        fdef.shape = shape;
        mB2Body.createFixture(fdef);
    }

    public void attachLight(Light light) {
        mLight = light;
    }

    public void move(double ix, double iy) {
        double dx = ix - mVelocity.x;
        double dy = iy - mVelocity.y;
        double dv = dx * dx + dy * dy;
        if (dv * (1 - ACC / 2) <= ACC) {
            mVelocity.set((float) ix, (float) iy);
        } else {
            mVelocity.x += dx * ACC * (0.5 + 1 / dv);
            mVelocity.y += dy * ACC * (0.5 + 1 / dv);
        }
//        mPosition.x += mVelocity.x * VMAX;
//        mPosition.y += mVelocity.y * VMAX;
//        mB2Body.applyLinearImpulse(mVelocity, mPosition, true);
        mB2Body.setLinearVelocity(mVelocity);


//        mB2Body.setTransform(mPosition.x, mPosition.y, 0);
//        mB2Body.applyLinearImpulse(new Vector2((float) ix, (float) iy), mPosition, true);
//        mB2Body.

    }

    public void setMoveRight(boolean b) {
        if (b) {
            mVelocity.x += MOVEMENT_SPEED;
        } else {
            mVelocity.x -= MOVEMENT_SPEED;
        }
        mB2Body.setLinearVelocity(mVelocity);
    }

    public void setMoveLeft(boolean b) {
        setMoveRight(!b);
    }

    public void setMoveUp(boolean b) {
        if (b) {
            mVelocity.y += MOVEMENT_SPEED;
        } else {
            mVelocity.y -= MOVEMENT_SPEED;
        }
        mB2Body.setLinearVelocity(mVelocity);
    }

    public void setMoveDown(boolean b) {
        setMoveUp(!b);
    }

    @Override
    public float getX() {
        return mB2Body.getPosition().x;
    }

    @Override
    public float getY() {
        return mB2Body.getPosition().y;
    }

    public float getXVelocity() {
        return mVelocity.x;
    }

    public float getYVelocity() {
        return mVelocity.y;
    }

    long lastDrop = 0;

    public void dropBeacon() {
        if (lastDrop == 0) {
            mPlayScreen.addBeacon(getX(), getY());
            lastDrop = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - lastDrop > 100) {
            mPlayScreen.addBeacon(getX(), getY());
            lastDrop = System.currentTimeMillis();
        } else {
            Gdx.app.log(TAG, "droppin too fast");

        }
    }

    public boolean isMoving() {
        return mVelocity.x != 0 || mVelocity.y != 0;
    }

    public float getPlayerLightDistance() {
        return playerLightDistance;
    }
}
