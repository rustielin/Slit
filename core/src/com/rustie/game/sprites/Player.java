package com.rustie.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rustie on 5/16/17.
 */

public class Player extends PxSprite {
    private Vector3 mPosition;
    private Vector3 mVelocity;

    /**
     * Makes Player at starting position x,y
     * @param x
     * @param y
     */
    public Player(int x, int y) {
        mPosition = new Vector3(x, y, 0); // only using x,y
        mVelocity = new Vector3(0, 0, 0);
        mPixmap.setColor(0xffffffff);
        mPixmap.fillCircle(50, 50, 5);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public Vector3 getPosition() {
        return this.mPosition;
    }

    @Override
    public Texture getTexture() {
        return new Texture(mPixmap); // calculate this on each call

    }


}
