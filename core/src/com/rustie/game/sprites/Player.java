package com.rustie.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/16/17.
 */

public class Player extends Sprite {

    public World mWorld;
    public Body mB2Body;

    private Vector2 mPosition;
    private Vector2 mVelocity;
    private static final double ACC = 0.25;
    private static final double VMAX = 1;

    public Player(World world) {
        this.mWorld = world;
        definePlayer();
        this.mPosition = mB2Body.getPosition();
        this.mVelocity = mB2Body.getLinearVelocity();


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

//
//    private Vector3 mPosition;
//    private Vector3 mVelocity;
//    private Texture mTexture;
//    private Circle mCircle;
//    private Pixmap mPixmap;
//    private int radius;
//
//    // movement multiplier for slow
//    public static double SPEED_MULTIPLIER = 0.5;
//
//    /**
//     * Makes Player at starting position x,y
//     * @param x
//     * @param y
//     */
//    public Player(int x, int y) {
//        this(x, y, 15);
//    }
//
//    public Player(int x, int y, int r) {
//        radius = r;
//        mPosition = new Vector3(x, y, 0); // only using x,y
//        mVelocity = new Vector3(0, 0, 0);
//
//        // makes a circle of radius 15
//        mPixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
//        mPixmap.setColor(0xffffffff);
//        mPixmap.fillCircle(radius, radius, radius);
//        mTexture = new Texture(mPixmap);
//
//        // for easy calculations
//        mCircle = new Circle(x, y, radius);
//    }
//
//    public void update(float dt) {
//
//    }
//
//    public Vector3 getPosition() {
//        return this.mPosition;
//    }
//
//    public Vector3 getVelocity() {
//        return this.mVelocity;
//    }
//
//    public Texture getTexture() {
//        return mTexture;
//
//    }
//
//    public void dispose() {
//        mTexture.dispose();
//
//    }
//
//    public boolean collision() {
////        mCircle.contains(); // contains some pixel
//        return false;
//    }


}
